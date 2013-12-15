/*
 * Copyright 2012-2015 org.opencloudb.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opencloudb.server;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.opencloudb.MycatConfig;
import org.opencloudb.MycatServer;
import org.opencloudb.config.ErrorCode;
import org.opencloudb.mpp.DataMergeService;
import org.opencloudb.mysql.MySQLDataNode;
import org.opencloudb.mysql.nio.MySQLConnection;
import org.opencloudb.mysql.nio.handler.CommitNodeHandler;
import org.opencloudb.mysql.nio.handler.KillConnectionHandler;
import org.opencloudb.mysql.nio.handler.MultiNodeQueryHandler;
import org.opencloudb.mysql.nio.handler.RollbackNodeHandler;
import org.opencloudb.mysql.nio.handler.RollbackReleaseHandler;
import org.opencloudb.mysql.nio.handler.SingleNodeHandler;
import org.opencloudb.mysql.nio.handler.Terminatable;
import org.opencloudb.net.FrontendConnection;
import org.opencloudb.net.mysql.OkPacket;
import org.opencloudb.route.RouteResultset;
import org.opencloudb.route.RouteResultsetNode;
import org.opencloudb.server.parser.ServerParse;

/**
 * @author mycat
 * @author mycat
 */
public class NonBlockingSession implements Session {
    private static final Logger LOGGER = Logger.getLogger(NonBlockingSession.class);

    private final ServerConnection source;
    private final ConcurrentHashMap<RouteResultsetNode, MySQLConnection> target;
    private final AtomicBoolean terminating;

    // life-cycle: each sql execution
    private volatile SingleNodeHandler singleNodeHandler;
    private volatile MultiNodeQueryHandler multiNodeHandler;
    private volatile CommitNodeHandler commitHandler;
    private volatile RollbackNodeHandler rollbackHandler;

    public NonBlockingSession(ServerConnection source) {
        this.source = source;
        this.target = new ConcurrentHashMap<RouteResultsetNode, MySQLConnection>(2, 1);
        this.terminating = new AtomicBoolean(false);
    }

    @Override
    public ServerConnection getSource() {
        return source;
    }

    @Override
    public int getTargetCount() {
        return target.size();
    }

    public Set<RouteResultsetNode> getTargetKeys() {
        return target.keySet();
    }

    public MySQLConnection getTarget(RouteResultsetNode key) {
        return target.get(key);
    }

    public MySQLConnection removeTarget(RouteResultsetNode key) {
        return target.remove(key);
    }

    @Override
    public void execute(RouteResultset rrs, int type) {
        if (LOGGER.isDebugEnabled()) {
            StringBuilder s = new StringBuilder();
            LOGGER.debug(s.append(source).append(rrs).toString()+" rrs ");
        }

        // 检查路由结果是否为空
        RouteResultsetNode[] nodes = rrs.getNodes();
        if (nodes == null || nodes.length == 0||nodes[0].getName().equals("")) {
            source.writeErrMessage(ErrorCode.ER_NO_DB_ERROR, "No dataNode selected");
            return;
        }

        if (nodes.length == 1) {
            singleNodeHandler = new SingleNodeHandler(nodes[0], this);
             try {
				singleNodeHandler.execute();
			} catch (Exception e) {
				LOGGER.warn( new StringBuilder().append(source).append(rrs),e);
				 source.writeErrMessage(ErrorCode.ERR_HANDLE_DATA, e.toString());
			}
        } else {
            boolean autocommit = source.isAutocommit();
            if (autocommit && isModifySQL(type)) {
                autocommit = false;
            }
            DataMergeService dataMergeSvr=null;
            if(ServerParse.SELECT==type && rrs.needMerge())
            {
            	dataMergeSvr=new DataMergeService(rrs);
            }
			multiNodeHandler = new MultiNodeQueryHandler(nodes, autocommit, this,dataMergeSvr);
             try {
				multiNodeHandler.execute();
			} catch (Exception e) {
				LOGGER.warn( new StringBuilder().append(source).append(rrs),e);
				 source.writeErrMessage(ErrorCode.ERR_HANDLE_DATA, e.toString());
			}
        }
    }

    public void commit() {
        final int initCount = target.size();
        if (initCount <= 0) {
            ByteBuffer buffer = source.allocate();
            buffer = source.writeToBuffer(OkPacket.OK, buffer);
            source.write(buffer);
            return;
        }
        commitHandler = new CommitNodeHandler(this);
        commitHandler.commit();
    }

    public void rollback() {
        final int initCount = target.size();
        if (initCount <= 0) {
            ByteBuffer buffer = source.allocate();
            buffer = source.writeToBuffer(OkPacket.OK, buffer);
            source.write(buffer);
            return;
        }
        rollbackHandler = new RollbackNodeHandler(this);
        rollbackHandler.rollback();
    }

    @Override
    public void cancel(FrontendConnection sponsor) {
        // TODO Auto-generated method stub

    }

    /**
     * {@link ServerConnection#isClosed()} must be true before invoking this
     */
    public void terminate() {
        if (!terminating.compareAndSet(false, true)) {
            return;
        }
        kill(new Runnable() {
            @Override
            public void run() {
                new Terminator().nextInvocation(singleNodeHandler).nextInvocation(multiNodeHandler)
                        .nextInvocation(commitHandler).nextInvocation(rollbackHandler)
                        .nextInvocation(new Terminatable() {
                            @Override
                            public void terminate(Runnable runnable) {
                                clearConnections(false);
                            }
                        }).nextInvocation(new Terminatable() {
                            @Override
                            public void terminate(Runnable runnable) {
                                terminating.set(false);
                            }
                        }).invoke();
            }
        });
    }

    public boolean closeConnection(RouteResultsetNode key) {
        MySQLConnection conn = target.remove(key);
        if (conn != null) {
            conn.close();
            return true;
        }
        return false;
    }

    public void setConnectionRunning(RouteResultsetNode[] route) {
        for (RouteResultsetNode rrn : route) {
            MySQLConnection c = target.get(rrn);
            if (c != null) {
                c.setRunning(true);
            }
        }
    }

    public void clearConnections() {
        clearConnections(true);
    }

    public void releaseConnections() {
        for (RouteResultsetNode rrn : target.keySet()) {
            MySQLConnection c = target.remove(rrn);
            if (c != null) {
                if (c.isRunning()) {
                    c.close();
                    try {
                        throw new IllegalStateException("running connection is found: " + c);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                } else if (!c.isClosedOrQuit()) {
                    if (source.isClosed()) {
                        c.quit();
                    } else {
                        c.release();
                    }
                }
            }
        }
    }

    /**
     * @return previous bound connection
     */
    public MySQLConnection bindConnection(RouteResultsetNode key, MySQLConnection conn) {
    	//System.out.println("bind connection "+conn+ " to key "+key.getName()+" on sesion "+this);
        return target.put(key, conn);
    }

    private static class Terminator {
        private LinkedList<Terminatable> list = new LinkedList<Terminatable>();
        private Iterator<Terminatable> iter;

        public Terminator nextInvocation(Terminatable term) {
            list.add(term);
            return this;
        }

        public void invoke() {
            iter = list.iterator();
            terminate();
        }

        private void terminate() {
            if (iter.hasNext()) {
                Terminatable term = iter.next();
                if (term != null) {
                    term.terminate(new Runnable() {
                        @Override
                        public void run() {
                            terminate();
                        }
                    });
                } else {
                    terminate();
                }
            }
        }
    }

    private void kill(Runnable run) {
        boolean hooked = false;
        AtomicInteger count = null;
        Map<RouteResultsetNode, MySQLConnection> killees = null;
        for (RouteResultsetNode node : target.keySet()) {
            MySQLConnection c = target.get(node);
            if (c != null && c.isRunning()) {
                if (!hooked) {
                    hooked = true;
                    killees = new HashMap<RouteResultsetNode, MySQLConnection>();
                    count = new AtomicInteger(0);
                }
                killees.put(node, c);
                count.incrementAndGet();
            }
        }
        if (hooked) {
            for (Entry<RouteResultsetNode, MySQLConnection> en : killees.entrySet()) {
                KillConnectionHandler kill = new KillConnectionHandler(en.getValue(), this, run, count);
                MycatConfig conf = MycatServer.getInstance().getConfig();
                MySQLDataNode dn = conf.getDataNodes().get(en.getKey().getName());
                try {
                    dn.getConnection(kill, en.getKey());
                } catch (Exception e) {
                    LOGGER.error("get killer connection failed for " + en.getKey(), e);
                    kill.connectionError(e, null);
                }
            }
        } else {
            run.run();
        }
    }

    private void clearConnections(boolean pessimisticRelease) {
        for (RouteResultsetNode node : target.keySet()) {
            MySQLConnection c = target.remove(node);

            if (c == null || c.isClosedOrQuit()) {
                continue;
            }

            // 如果通道正在运行中，则关闭当前通道。
            if (c.isRunning() || (pessimisticRelease && source.isClosed())) {
                c.close();
                continue;
            }

            // 非事务中的通道，直接释放资源。
            if (c.isAutocommit()) {
                c.release();
                continue;
            }

            c.setResponseHandler(new RollbackReleaseHandler());
            c.rollback();
        }
    }

    public boolean closed() {
        return source.isClosed();
    }

    private static boolean isModifySQL(int type) {
        switch (type) {
        case ServerParse.INSERT:
        case ServerParse.DELETE:
        case ServerParse.UPDATE:
        case ServerParse.REPLACE:
            return true;
        default:
            return false;
        }
    }

}