package org.opencloudb.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.opencloudb.performance.job.InsertJob;

/**
 * 测试按哈希分片功能
 * 1）连接MySQL集群中间件的8066端口
 * 2）获取采集数据库的数据将其插入到MySQL集群
 * 3） 取数据进行计算
 * 4）获得计算后的数据条数
 * @author shenzhw
 *
 */
public class TestShardingHash {
	private static int count;
	
	public static void add(int count){
		synchronized(TestShardingHash.class){
			TestShardingHash.count += count;
		}
	}
	
	public static void main(String[] args) throws Exception{
		ExecutorService pool = Executors.newFixedThreadPool(100);
		
		long start = System.currentTimeMillis();
		List<Map> list = new ArrayList<Map>();
		int c = 0;
		int sumcount = 100*10000;
		for(int i=0;i<sumcount;i++){
			Map m = new HashMap();
			m.put("id", i);
			m.put("name", "customer" + i);
			if(i%3==0){
				m.put("company_id", "1");
				m.put("sharding_id", "10000");
			}else if(i%3==1){
				m.put("company_id", "2");
				m.put("sharding_id", "10010");
			}else if(i%3==2){
				m.put("company_id", "3");
				m.put("sharding_id", "10086");
			}
			list.add(m);
			c += 1;
			if(c==5000){
				InsertJob job = new InsertJob();
				job.setList(list);
				pool.execute(job);
				c=0;
				list = new ArrayList<Map>();
			}
		}
		System.out.println("已分配完任务，耗时:" + (System.currentTimeMillis() - start)/1000 + "秒");
		while(count < sumcount){
			System.out.println("已插入记录条数:" + count);
			Thread.sleep(1000);
		}
		System.out.println("最终记录路:" + count);
		pool.shutdown();
		System.out.println("共用时" + (System.currentTimeMillis() - start)/1000 + "秒");
	}
}
