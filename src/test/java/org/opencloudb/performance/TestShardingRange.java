package org.opencloudb.performance;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 测试按数据范围分片功能
 * 
 * @author shenzhw
 *
 */
public class TestShardingRange {
	public static void main(String[] args) {
		int threadCount = 0;//线程数
		int recordCount = 0;//要插入的记录数
		try{
			threadCount = Integer.parseInt(args[0]);
			recordCount = Integer.parseInt(args[1]);
		}catch(NumberFormatException e){
			System.out.println("请输入正确的参数,格式为:[线程池大小] [记录数]");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				String params = br.readLine();
				threadCount = Integer.parseInt(params.split(" ")[1]);
				recordCount = Integer.parseInt(params.split(" ")[0]);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(threadCount);
		System.out.println(recordCount);
	}
}
