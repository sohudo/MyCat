package org.opencloudb.mysql.nio.handler;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.opencloudb.backend.PhysicalConnection;
import org.opencloudb.net.mysql.ErrorPacket;

/**
 * heartbeat check for mysql connections
 * 
 * @author wuzhih
 * 
 */
public class ConnectionHeartBeatHandler implements ResponseHandler {
	private static final Logger LOGGER = Logger
			.getLogger(ConnectionHeartBeatHandler.class);
	protected final ReentrantLock lock = new ReentrantLock();
	private final Collection<HeartBeatCon> allCons = new LinkedList<HeartBeatCon>();

	public void doHeartBeat(PhysicalConnection conn, String sql) {
		long timeOutTimestamp = System.currentTimeMillis() + 5 * 1000L;
		HeartBeatCon hbCon = new HeartBeatCon(timeOutTimestamp, conn);
		conn.setRunning(true);
		conn.setResponseHandler(this);
		try {
			conn.query(sql);
			addHeartBeatCon(hbCon);
		} catch (Exception e) {
			executeException(conn, e);
		}
	}

	private void addHeartBeatCon(HeartBeatCon hbCon) {
		lock.lock();
		try {
			allCons.add(hbCon);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * remove timeout connections
	 */
	public void abandTimeOuttedConns() {
		if (allCons.isEmpty()) {
			return;
		}
		Collection<PhysicalConnection> abandCons = new LinkedList<PhysicalConnection>();
		long curTime = System.currentTimeMillis();
		lock.lock();
		try {
			Iterator<HeartBeatCon> itors = allCons.iterator();
			while (itors.hasNext()) {
				HeartBeatCon hbCon = (HeartBeatCon) itors.next();
				if (hbCon.timeOutTimestamp < curTime) {
					abandCons.add(hbCon.conn);
					itors.remove();
				}
			}

		} finally {
			lock.unlock();
		}

		if (!abandCons.isEmpty()) {
			for (PhysicalConnection con : abandCons) {
				try {
					con.close("heartbeat timeout ");
				} catch (Exception e) {
					LOGGER.warn("close err:" + e);
				}
			}
		}

	}

	@Override
	public void connectionAcquired(PhysicalConnection conn) {
		// not called
	}

	@Override
	public void connectionError(Throwable e, PhysicalConnection conn) {
		// not called

	}

	@Override
	public void errorResponse(byte[] data, PhysicalConnection conn) {
		removeFinished(conn);
		conn.setRunning(false);
		ErrorPacket err = new ErrorPacket();
		err.read(data);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("errorResponse " + err.errno + " "
					+ new String(err.message));
		}

		conn.setRunning(false);
		conn.release();

	}

	@Override
	public void okResponse(byte[] ok, PhysicalConnection conn) {
		boolean executeResponse = conn.syncAndExcute();
		if (executeResponse) {
			removeFinished(conn);
			conn.setRunning(false);
			conn.release();
		}

	}

	@Override
	public void rowResponse(byte[] row, PhysicalConnection conn) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("received row from " + conn);
		}
	}

	@Override
	public void rowEofResponse(byte[] eof, PhysicalConnection conn) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("received row data end from " + conn);
		}
		removeFinished(conn);
		conn.setRunning(false);
		conn.release();
	}

	private void executeException(PhysicalConnection c, Throwable e) {
		removeFinished(c);
		LOGGER.warn("executeException   " + e);
		c.setRunning(false);
		c.close("heatbeat exception:" + e);

	}

	private void removeFinished(PhysicalConnection con) {
		lock.lock();
		try {
			Iterator<HeartBeatCon> itors = allCons.iterator();
			while (itors.hasNext()) {
				if (((HeartBeatCon) itors.next()).conn == con) {
					itors.remove();
					break;
				}
			}

		} finally {
			lock.unlock();
		}
	}

	@Override
	public void writeQueueAvailable() {

	}

	@Override
	public void connectionClose(PhysicalConnection conn, String reason) {
		removeFinished(conn);
		LOGGER.warn("connection closed " + conn + " reason:" + reason);
	}

	@Override
	public void fieldEofResponse(byte[] header, List<byte[]> fields,
			byte[] eof, PhysicalConnection conn) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("received field eof  from " + conn);
		}
	}

}

class HeartBeatCon {
	public final long timeOutTimestamp;
	public final PhysicalConnection conn;

	public HeartBeatCon(long timeOutTimestamp, PhysicalConnection conn) {
		super();
		this.timeOutTimestamp = timeOutTimestamp;
		this.conn = conn;
	}

}