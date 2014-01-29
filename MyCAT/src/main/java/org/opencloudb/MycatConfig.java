/*
 * Copyright (c) 2013, OpenCloudDB/MyCAT and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software;Designed and Developed mainly by many Chinese 
 * opensource volunteers. you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Any questions about this component can be directed to it's project Web address 
 * https://code.google.com/p/opencloudb/.
 *
 */
package org.opencloudb;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.opencloudb.backend.PhysicalDBNode;
import org.opencloudb.backend.PhysicalDBPool;
import org.opencloudb.config.model.QuarantineConfig;
import org.opencloudb.config.model.SchemaConfig;
import org.opencloudb.config.model.SystemConfig;
import org.opencloudb.config.model.UserConfig;
import org.opencloudb.util.TimeUtil;

/**
 * @author mycat
 */
public class MycatConfig {
    private static final int RELOAD = 1;
    private static final int ROLLBACK = 2;

    private volatile SystemConfig system;
    private volatile MycatCluster cluster;
    private volatile MycatCluster _cluster;
    private volatile QuarantineConfig quarantine;
    private volatile QuarantineConfig _quarantine;
    private volatile Map<String, UserConfig> users;
    private volatile Map<String, UserConfig> _users;
    private volatile Map<String, SchemaConfig> schemas;
    private volatile Map<String, SchemaConfig> _schemas;
    private volatile Map<String, PhysicalDBNode> dataNodes;
    private volatile Map<String, PhysicalDBNode> _dataNodes;
    private volatile Map<String, PhysicalDBPool> dataHosts;
    private volatile Map<String, PhysicalDBPool> _dataHosts;
    private long reloadTime;
    private long rollbackTime;
    private int status;
    private final ReentrantLock lock;

    public MycatConfig() {
        ConfigInitializer confInit = new ConfigInitializer();
        this.system = confInit.getSystem();
        this.users = confInit.getUsers();
        this.schemas = confInit.getSchemas();
        this.dataHosts = confInit.getDataHosts();
        this.dataNodes = confInit.getDataNodes();
        this.quarantine = confInit.getQuarantine();
        this.cluster = confInit.getCluster();

        this.reloadTime = TimeUtil.currentTimeMillis();
        this.rollbackTime = -1L;
        this.status = RELOAD;
        this.lock = new ReentrantLock();
    }

    public SystemConfig getSystem() {
        return system;
    }

    public Map<String, UserConfig> getUsers() {
        return users;
    }

    public Map<String, UserConfig> getBackupUsers() {
        return _users;
    }

    public Map<String, SchemaConfig> getSchemas() {
        return schemas;
    }

    public Map<String, SchemaConfig> getBackupSchemas() {
        return _schemas;
    }

    public Map<String, PhysicalDBNode> getDataNodes() {
        return dataNodes;
    }

    public Map<String, PhysicalDBNode> getBackupDataNodes() {
        return _dataNodes;
    }

    public Map<String, PhysicalDBPool> getDataHosts() {
        return dataHosts;
    }

    public Map<String, PhysicalDBPool> getBackupDataHosts() {
        return _dataHosts;
    }

    public MycatCluster getCluster() {
        return cluster;
    }

    public MycatCluster getBackupCluster() {
        return _cluster;
    }

    public QuarantineConfig getQuarantine() {
        return quarantine;
    }

    public QuarantineConfig getBackupQuarantine() {
        return _quarantine;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public long getReloadTime() {
        return reloadTime;
    }

    public long getRollbackTime() {
        return rollbackTime;
    }

    public void reload(Map<String, UserConfig> users, Map<String, SchemaConfig> schemas,
            Map<String, PhysicalDBNode> dataNodes, Map<String, PhysicalDBPool> dataHosts, MycatCluster cluster,
            QuarantineConfig quarantine) {
        apply(users, schemas, dataNodes, dataHosts, cluster, quarantine);
        this.reloadTime = TimeUtil.currentTimeMillis();
        this.status = RELOAD;
    }

    public boolean canRollback() {
        if (_users == null || _schemas == null || _dataNodes == null || _dataHosts == null || _cluster == null
                || _quarantine == null || status == ROLLBACK) {
            return false;
        } else {
            return true;
        }
    }

    public void rollback(Map<String, UserConfig> users, Map<String, SchemaConfig> schemas,
            Map<String, PhysicalDBNode> dataNodes, Map<String, PhysicalDBPool> dataHosts, MycatCluster cluster,
            QuarantineConfig quarantine) {
        apply(users, schemas, dataNodes, dataHosts, cluster, quarantine);
        this.rollbackTime = TimeUtil.currentTimeMillis();
        this.status = ROLLBACK;
    }

    private void apply(Map<String, UserConfig> users, Map<String, SchemaConfig> schemas,
            Map<String, PhysicalDBNode> dataNodes, Map<String, PhysicalDBPool> dataHosts, MycatCluster cluster,
            QuarantineConfig quarantine) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // stop datasource heartbeat
            Map<String, PhysicalDBPool> oldDataHosts = this.dataHosts;
            if (oldDataHosts != null) {
                for (PhysicalDBPool n : oldDataHosts.values()) {
                    if (n != null) {
                        n.stopHeartbeat();
                    }
                }
            }
            // stop MyCat heartbeat
            MycatCluster oldCluster = this.cluster;
            if (oldCluster != null) {
                Map<String, MycatNode> nodes = oldCluster.getNodes();
                for (MycatNode n : nodes.values()) {
                    if (n != null) {
                        n.stopHeartbeat();
                    }
                }
            }
            this._users = this.users;
            this._schemas = this.schemas;
            this._dataNodes = this.dataNodes;
            this._dataHosts = this.dataHosts;
            this._cluster = this.cluster;
            this._quarantine = this.quarantine;

            // start datasoruce heartbeat
            if (dataNodes != null) {
                for (PhysicalDBPool n : dataHosts.values()) {
                    if (n != null) {
                        n.startHeartbeat();
                    }
                }
            }
            // start MyCat heartbeat
            if (cluster != null) {
                Map<String, MycatNode> nodes = cluster.getNodes();
                for (MycatNode n : nodes.values()) {
                    if (n != null) {
                        n.startHeartbeat();
                    }
                }
            }
            this.users = users;
            this.schemas = schemas;
            this.dataNodes = dataNodes;
            this.dataHosts = dataHosts;
            this.cluster = cluster;
            this.quarantine = quarantine;
        } finally {
            lock.unlock();
        }
    }

}