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
/**
 * (created at 2012-5-30)
 */
package org.opencloudb.route.perf;

import java.sql.SQLNonTransientException;

import org.opencloudb.config.model.SchemaConfig;
import org.opencloudb.route.ServerRouterUtil;

/**
 * @author mycat
 */
public class NoShardingSpace {
    private SchemaConfig schema;

    public NoShardingSpace() {
        // CobarConfig conf = CobarServer.getInstance().getConfig();
        // schema = conf.getSchemas().get("dubbo");
    }

    public void testDefaultSpace() throws SQLNonTransientException {
        SchemaConfig schema = this.schema;
        String stmt = "insert into offer (member_id, gmt_create) values ('1','2001-09-13 20:20:33')";
        for (int i = 0; i < 1000000; i++) {
            ServerRouterUtil.route(schema, -1,stmt, null, null);
        }
    }

    public static void main(String[] args) throws SQLNonTransientException {
        NoShardingSpace test = new NoShardingSpace();
        System.currentTimeMillis();

        long start = System.currentTimeMillis();
        test.testDefaultSpace();
        long end = System.currentTimeMillis();
        System.out.println("take " + (end - start) + " ms.");
    }
}