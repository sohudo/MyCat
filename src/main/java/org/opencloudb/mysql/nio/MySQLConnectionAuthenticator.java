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
package org.opencloudb.mysql.nio;

import org.opencloudb.mysql.CharsetUtil;
import org.opencloudb.mysql.SecurityUtil;
import org.opencloudb.mysql.nio.handler.ResponseHandler;
import org.opencloudb.net.NIOHandler;
import org.opencloudb.net.mysql.EOFPacket;
import org.opencloudb.net.mysql.ErrorPacket;
import org.opencloudb.net.mysql.HandshakePacket;
import org.opencloudb.net.mysql.OkPacket;
import org.opencloudb.net.mysql.Reply323Packet;

/**
 * MySQL 验证处理器
 * 
 * @author mycat
 */
public class MySQLConnectionAuthenticator implements NIOHandler {

    private final MySQLConnection source;
    private final ResponseHandler listener;

    public MySQLConnectionAuthenticator(MySQLConnection source, ResponseHandler listener) {
        this.source = source;
        this.listener = listener;
    }

    public void connectionError(Throwable e)
    {
    	listener.connectionError(e, source);
    }
    @Override
    public void handle(byte[] data) {
        try {
            HandshakePacket packet = source.getHandshake();
            if (packet == null) {
                // 设置握手数据包
                packet = new HandshakePacket();
                packet.read(data);
                source.setHandshake(packet);

                source.setThreadId(packet.threadId);

                // 设置字符集编码
                int charsetIndex = (packet.serverCharsetIndex & 0xff);
                String charset = CharsetUtil.getCharset(charsetIndex);
                if (charset != null) {
                    source.setCharsetIndex(charsetIndex);
                    source.setCharset(charset);
                } else {
                    throw new RuntimeException("Unknown charsetIndex:" + charsetIndex);
                }

                // 发送认证数据包
                source.authenticate();
            } else { // 处理认证结果
                switch (data[4]) {
                case OkPacket.FIELD_COUNT:
                    source.setHandler(new MySQLConnectionHandler(source));
                    source.setAuthenticated(true);
                    if (listener != null) {
                        listener.connectionAcquired(source);
                    }
                    break;
                case ErrorPacket.FIELD_COUNT:
                    ErrorPacket err = new ErrorPacket();
                    err.read(data);
                    throw new RuntimeException(new String(err.message));
                case EOFPacket.FIELD_COUNT:
                    auth323(data[3]);
                    break;
                default:
                    throw new RuntimeException("Unknown Packet!");
                }
            }
        } catch (RuntimeException e) {
            if (listener != null) {
                listener.connectionError(e, source);
            }
            throw e;
        }
    }

    private void auth323(byte packetId) {
        // 发送323响应认证数据包
        Reply323Packet r323 = new Reply323Packet();
        r323.packetId = ++packetId;
        String pass = source.getPassword();
        if (pass != null && pass.length() > 0) {
            byte[] seed = source.getHandshake().seed;
            r323.seed = SecurityUtil.scramble323(pass, new String(seed)).getBytes();
        }
        r323.write(source);
    }

}