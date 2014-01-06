package org.opencloudb.performance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleConPool {
	private final String url;
	private final String user;
	private final String password;
	private CopyOnWriteArrayList<Connection> cons = new CopyOnWriteArrayList<Connection>();

	public SimpleConPool(String url, String user, String password, int maxCon)
			throws SQLException {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
		for (int i = 0; i < maxCon; i++) {
			cons.add(getCon());
		}
		System.out.println("success ful created connections ,total :" + maxCon);
	}

	private Connection getCon() throws SQLException {
		Connection theCon = DriverManager.getConnection(url, user, password);
		return theCon;
	}

	public void returnCon(Connection con) {
		try {
			if (con.isClosed()) {
				System.out.println("closed connection ,aband");
			} else {
				this.cons.add(con);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection con = null;
		if (cons.isEmpty()) {
			System.out.println("warn no connection in pool,create new one");
			con = getCon();
			return con;
		} else {
			con = cons.remove(0);
		}
		if (con.isClosed()) {
			System.out.println("warn connection closed ,create new one");
			con = getCon();

		}
		return con;
	}
}
