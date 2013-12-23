package org.opencloudb.backend;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.opencloudb.config.Alarms;
import org.opencloudb.mysql.nio.handler.GetConnectionHandler;

public class PhysicalDBPool {
	protected static final Logger LOGGER = Logger
			.getLogger(PhysicalDBPool.class);
	private final String hostName;
	protected PhysicalDatasource[] sources;
	protected Map<Integer, PhysicalDatasource[]> readSources;
	protected volatile int activedIndex;
	protected volatile boolean initSuccess;
	protected final ReentrantLock switchLock = new ReentrantLock();
	private final Collection<PhysicalDatasource> allDs;

	public PhysicalDBPool(String name, PhysicalDatasource[] writeSources,
			Map<Integer, PhysicalDatasource[]> readSources) {
		this.hostName = name;
		this.sources = writeSources;
		Iterator<Map.Entry<Integer, PhysicalDatasource[]>> entryItor = readSources
				.entrySet().iterator();
		while (entryItor.hasNext()) {
			PhysicalDatasource[] values = entryItor.next().getValue();
			if (values.length == 0) {
				entryItor.remove();
			}
		}
		this.readSources = readSources;
		this.allDs = this.genAllDataSources();
		LOGGER.info("total resouces of dataHost " + this.hostName + " is :"
				+ allDs.size());
		setDataSourceProps();
	}

	private void setDataSourceProps() {
		for (PhysicalDatasource ds : this.allDs) {
			ds.setDbPool(this);
		}
	}

	public String getHostName() {
		return hostName;
	}

	public PhysicalDatasource[] getSources() {
		return sources;
	}

	public PhysicalDatasource getSource() {
		return sources[activedIndex];
	}

	public int getActivedIndex() {
		return activedIndex;
	}

	public boolean isInitSuccess() {
		return initSuccess;
	}

	public int next(int i) {
		if (checkIndex(i)) {
			return (++i == sources.length) ? 0 : i;
		} else {
			return 0;
		}
	}

	/**
	 * 切换数据源
	 */
	public boolean switchSource(int newIndex, boolean isAlarm, String reason) {
		if (!checkIndex(newIndex)) {
			return false;
		}
		final ReentrantLock lock = this.switchLock;
		lock.lock();
		try {
			int current = activedIndex;
			if (current != newIndex) {
				// 清理即将使用的数据源并开启心跳
				sources[newIndex].clearCons();
				sources[newIndex].startHeartbeat();

				// 执行切换赋值
				activedIndex = newIndex;

				// 清理切换前的数据源
				sources[current].clearCons();
				sources[current].stopHeartbeat();

				// 记录切换日志
				LOGGER.warn(switchMessage(current, newIndex, false, reason));

				return true;
			}
		} finally {
			lock.unlock();
		}
		return false;
	}

	private String switchMessage(int current, int newIndex, boolean alarm,
			String reason) {
		StringBuilder s = new StringBuilder();
		if (alarm) {
			s.append(Alarms.DATANODE_SWITCH);
		}
		s.append("[Host=").append(hostName).append(",result=[").append(current)
				.append("->");
		s.append(newIndex).append("],reason=").append(reason).append(']');
		return s.toString();
	}

	private int loop(int i) {
		return i < sources.length ? i : (i - sources.length);
	}

	public void init(int index) {
		if (!checkIndex(index)) {
			index = 0;
		}
		int active = -1;
		for (int i = 0; i < sources.length; i++) {
			int j = loop(i + index);
			if (initSource(j, sources[j])) {
				active = j;
				break;
			}
		}
		if (checkIndex(active)) {
			activedIndex = active;
			initSuccess = true;
			LOGGER.info(getMessage(active, " init success"));
		} else {
			initSuccess = false;
			StringBuilder s = new StringBuilder();
			s.append(Alarms.DEFAULT).append(hostName).append(" init failure");
			LOGGER.error(s.toString());
		}
	}

	private boolean checkIndex(int i) {
		return i >= 0 && i < sources.length;
	}

	private String getMessage(int index, String info) {
		return new StringBuilder().append(hostName).append(':').append(index)
				.append(info).toString();
	}

	private boolean initSource(int index, PhysicalDatasource ds) {
		int initSize = ds.getConfig().getMinCon();
		LOGGER.info("init backend myqsl source ,create connections total "
				+ initSize + " for " + ds.getName());
		CopyOnWriteArrayList<PhysicalConnection> list = new CopyOnWriteArrayList<PhysicalConnection>();
		GetConnectionHandler getConHandler = new GetConnectionHandler(list,
				initSize);
		// long start=System.currentTimeMillis();
		// long timeOut=start+5000*1000L;
		for (int i = 0; i < initSize; i++) {
			try {
				ds.getConnection(getConHandler, null, null);
			} catch (Exception e) {
				LOGGER.warn(getMessage(index, " init connection error."), e);
			}
		}
		// waiting for finish
		while (!getConHandler.finished()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (PhysicalConnection c : list) {
			c.release();
		}
		return !list.isEmpty();
	}

	public void doHeartbeat() {

		// 检查内部是否有连接池配置信息
		if (sources == null || sources.length == 0) {
			return;
		}

		for (PhysicalDatasource source : this.getAllDataSources()) {
			// 准备执行心跳检测
			if (source != null) {
				source.doHeartbeat();
			} else {
				StringBuilder s = new StringBuilder();
				s.append(Alarms.DEFAULT).append(hostName)
						.append(" current dataSource is null!");
				LOGGER.error(s.toString());
			}
		}
		// 读库的心跳检测
		// todo
	}

	/**
	 * 空闲检查
	 */
	public void idleCheck() {
		for (PhysicalDatasource ds : sources) {
			if (ds != null) {
				ds.idleCheck(ds.getConfig().getIdleTimeout());
			}
		}
	}

	public void startHeartbeat() {
		for (PhysicalDatasource source : this.allDs) {
			source.startHeartbeat();
		}
	}

	public void stopHeartbeat() {
		for (PhysicalDatasource source : this.allDs) {
			source.stopHeartbeat();
		}
	}

	public void clearDataSources() {
		LOGGER.info("clear datasours of pool " + this.hostName);
		for (PhysicalDatasource source : this.allDs) {
			LOGGER.info("clear datasoure of pool  " + this.hostName + " ds:"
					+ source.getConfig());
			source.clearCons();
		}

	}

	public Collection<PhysicalDatasource> genAllDataSources() {
		LinkedList<PhysicalDatasource> allSources = new LinkedList<PhysicalDatasource>();
		for (PhysicalDatasource ds : sources) {
			if (ds != null) {
				allSources.add(ds);
			}
		}
		for (PhysicalDatasource[] dataSources : this.readSources.values()) {
			for (PhysicalDatasource ds : dataSources) {
				if (ds != null) {
					allSources.add(ds);
				}
			}
		}
		return allSources;
	}

	public Collection<PhysicalDatasource> getAllDataSources() {
		return this.allDs;
	}
}
