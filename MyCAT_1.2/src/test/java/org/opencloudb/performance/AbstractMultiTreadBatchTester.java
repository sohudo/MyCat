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
package org.opencloudb.performance;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractMultiTreadBatchTester {
	SimpleConPool conPool;
	protected AtomicInteger finshiedCount = new AtomicInteger();
	protected AtomicInteger failedCount = new AtomicInteger();
	protected int threadCount = 0;// 线程数
	protected String url;
	protected String user;
	protected String password;
	protected ExecutorService executor;
	long start;
	protected String[] rangeItems;
	protected boolean outputMiddleInf = true;

	public boolean parseArgs(String[] args) {
		if (args.length < 5) {
			System.out
					.println("input param,format: [jdbcurl] [user] [password]  [threadpoolsize]  [recordrange] ");
			return false;
		}
		url = args[0];
		user = args[1];
		password = args[2];
		threadCount = Integer.parseInt(args[3]);
		rangeItems = args[4].split(",");
		return true;

	}

	public void run(String[] args) throws Exception {
		if (!this.parseArgs(args)) {
			return;
		}
		startTest();
		runAndReport();
	}

	public void addFinshed(int count) {
		finshiedCount.addAndGet(count);
	}

	public ArrayList<Runnable> createJobs(SimpleConPool conPool, int minId,
			int maxId) throws Exception {
		int recordCount = maxId - minId + 1;
		int batchSize = 1000;
		int totalBatch = recordCount / batchSize;
		ArrayList<Runnable> jobs = new ArrayList<Runnable>(totalBatch);
		for (int i = 0; i < totalBatch; i++) {
			int startId = minId + i * batchSize;
			int endId = (startId + batchSize - 1);
			if (endId >= maxId) {
				endId = maxId;
			} else if (i == totalBatch - 1) {
				endId = maxId;
			}
			int myCount = endId - startId + 1;
			Runnable job = createJob(getConPool(), myCount, 100, startId,
					finshiedCount, failedCount);
			//System.out.println("job record id is " + startId + "-" + endId);
			jobs.add(job);

		}
		return jobs;
	}

	public abstract Runnable createJob(SimpleConPool conPool2, int myCount,
			int batchSize, int startId, AtomicInteger finshiedCount2,
			AtomicInteger failedCount2);

	@SuppressWarnings("unchecked")
	public ArrayList<Runnable>[] createAllJobs() throws Exception {
		ArrayList<Runnable>[] allJobs = new ArrayList[rangeItems.length];
		for (int i = 0; i < rangeItems.length; i++) {
			String[] items = rangeItems[i].split("-");
			int min = (int) parseLong(items[0]);
			int max = (int) parseLong(items[1]);
			allJobs[i] = createJobs(conPool, min, max);

		}
		return allJobs;
	}

	public void addFailed(int count) {
		failedCount.addAndGet(count);
	}

	public SimpleConPool getConPool() throws SQLException,
			ClassNotFoundException {
		if (conPool == null) {
			Class.forName("com.mysql.jdbc.Driver");
			conPool = new SimpleConPool(url, user, password, threadCount);
		}
		return conPool;
	}

	@SuppressWarnings("unchecked")
	public void startTest() throws Exception {
		executor = Executors.newFixedThreadPool(threadCount);
		System.out.println("create jobs ...");
		ArrayList<Runnable>[] allJobs = createAllJobs();
		Iterator<Runnable>[] itors = new Iterator[allJobs.length];
		for (int i = 0; i < allJobs.length; i++) {
			itors[i] = allJobs[i].iterator();
		}
		System.out.println("create jobs finished ,begin run test...");
		int total = 0;
		start = System.currentTimeMillis();
		boolean finished = false;
		while (!finished) {

			finished = true;
			for (int i = 0; i < itors.length; i++) {
				if (itors[i].hasNext()) {
					total++;
					executor.execute(itors[i].next());
					if (finished) {
						finished = !itors[i].hasNext();
					}

				}
			}
		}

		// executor.execute(job);
		System.out.println("success create job count: " + total
				+ " teset threads: " + threadCount);
	}

	public void runAndReport() throws InterruptedException {
		executor.shutdown();

		SimpleDateFormat df = new SimpleDateFormat("dd HH:mm:ss");
		while (!executor.isTerminated()) {
			if (outputMiddleInf) {
				long sucess = finshiedCount.get() - failedCount.get();
				System.out.println(df.format(new Date())
						+ " finished records :" + finshiedCount.get()
						+ " failed:" + failedCount.get() + " speed:" + sucess
						* 1000.0 / (System.currentTimeMillis() - start));
			}
			Thread.sleep(1000);
		}

		long usedTime = (System.currentTimeMillis() - start) / 1000;
		System.out.println("finishend:" + finshiedCount.get() + " failed:"
				+ failedCount.get());
		long sucess = finshiedCount.get() - failedCount.get();
		System.out.println("used time total:" + usedTime + "seconds");
		System.out.println("tps:" + sucess / (usedTime+0.1));
	}

	/**
	 * can parse values like 200M ,200K,200M1(2000001)
	 * 
	 * @param val
	 * @return
	 */
	private static long parseLong(String val) {
		val = val.toUpperCase();
		int indx = val.indexOf("M");

		int plus = 10000;
		if (indx < 0) {
			indx = val.indexOf("K");
			plus = 1000;
		}
		if (indx > 0) {
			String longVal = val.substring(0, indx);

			long theVale = Long.parseLong(longVal) * plus;
			String remain = val.substring(indx + 1);
			if (remain.length() > 0) {
				theVale += Integer.parseInt(remain);
			}
			return theVale;
		} else {
			return Long.parseLong(val);
		}

	}
}