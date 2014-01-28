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

import org.opencloudb.SimpleCachePool;
import org.opencloudb.cache.LayerCachePool;
import org.opencloudb.config.loader.SchemaLoader;
import org.opencloudb.config.loader.xml.XMLSchemaLoader;
import org.opencloudb.config.model.SchemaConfig;
import org.opencloudb.route.ServerRouterUtil;

/**
 * @author mycat
 */
public class ShardingDefaultSpace {
    private SchemaConfig schema;
    private static int total=1000000;
    protected LayerCachePool cachePool = new SimpleCachePool();
    public ShardingDefaultSpace() throws InterruptedException {
         String schemaFile = "/route/schema.xml";
 		String ruleFile = "/route/rule.xml";
 		SchemaLoader schemaLoader = new XMLSchemaLoader(schemaFile, ruleFile);
 		schema = schemaLoader.getSchemas().get("cndb");
    }

    /**
     * 路由到defaultSpace的性能测试
     */
    public void testDefaultSpace() throws SQLNonTransientException {
        SchemaConfig schema = this.getSchema();
        String sql = "insert into offer (member_id, gmt_create) values ('1','2001-09-13 20:20:33')";
        for (int i = 0; i < total; i++) {
            ServerRouterUtil.route(schema,-1, sql, null, null,cachePool);
        }
    }

    protected SchemaConfig getSchema() {
        return schema;
    }

    public static void main(String[] args) throws Exception {
        ShardingDefaultSpace test = new ShardingDefaultSpace();
        System.currentTimeMillis();

        long start = System.currentTimeMillis();
        test.testDefaultSpace();
        long end = System.currentTimeMillis();
        System.out.println("take " + (end - start) + " ms. avg "+(end-start+0.0)/total);
    }
}