package org.opencloudb.backend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.opencloudb.mysql.nio.handler.ResponseHandler;
import org.opencloudb.net.ClosableConnection;
import org.opencloudb.route.RouteResultsetNode;
import org.opencloudb.server.ServerConnection;

public interface PhysicalConnection extends ClosableConnection{
	
	public boolean isModifiedSQLExecuted();
	
	public boolean isFromSlaveDB();

	public String getSchema();

	public void setSchema(String newSchema);

	public long getLastTime();

	public void closeNoActive(String reason);

	public boolean isClosedOrQuit();

	public void setAttachment(Object attachment);

	public void quit();

	public void setLastTime(long currentTimeMillis);

	public void release();


	public void setRunning(boolean running);

	public boolean setResponseHandler(ResponseHandler commandHandler);
	

	public void commit();

	public void query(String sql) throws UnsupportedEncodingException;

	public Object getAttachment();

	public long getThreadId();

	public String getCharset();

	public void execute(RouteResultsetNode node, ServerConnection source,
			boolean autocommit) throws IOException;

	public void recordSql(String host, String schema, String statement);

	public boolean syncAndExcute();

	public void rollback();

	public boolean isSuppressReadTemporay();

	public void setSuppressReadTemporay(boolean b);

	public boolean isRunning();
	
	public boolean isBorrowed();
	
	public void setBorrowed(boolean borrowed);

	public boolean isAutocommit();
}
