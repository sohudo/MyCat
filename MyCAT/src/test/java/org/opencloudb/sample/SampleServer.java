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
package org.opencloudb.sample;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.opencloudb.net.NIOAcceptor;
import org.opencloudb.net.NIOProcessor;
import org.opencloudb.util.TimeUtil;

/**
 * 服务器组装示例
 * 
 * @author mycat
 */
public class SampleServer {
    private static final int SERVER_PORT = 8066;
    private static final long TIME_UPDATE_PERIOD = 100L;
    private static final SampleServer INSTANCE = new SampleServer();
    private static final Logger LOGGER = Logger.getLogger(SampleServer.class);

    public static final SampleServer getInstance() {
        return INSTANCE;
    }

    private SampleConfig config;
    private Timer timer;
    private NIOProcessor[] processors;
    private NIOAcceptor server;

    private SampleServer() {
        this.config = new SampleConfig();
    }

    public SampleConfig getConfig() {
        return config;
    }

    public void startup() throws IOException {
        String name = config.getServerName();
        LOGGER.info("===============================================");
        LOGGER.info(name + " is ready to startup ...");

        // schedule timer task
        timer = new Timer(name + "Timer", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimeUtil.update();
            }
        }, 0L, TIME_UPDATE_PERIOD);
        LOGGER.info("Task Timer is started ...");

        // startup processors
        processors = new NIOProcessor[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new NIOProcessor(name + "Processor" + i,1024*1024,4096,1);
            processors[i].startup();
        }

        // startup server
        SampleConnectionFactory factory = new SampleConnectionFactory();
        server = new NIOAcceptor(name + "Server", SERVER_PORT, factory);
        server.setProcessors(processors);
        server.start();
        LOGGER.info(server.getName() + " is started and listening on " + server.getPort());

        // end
        LOGGER.info("===============================================");
    }

}