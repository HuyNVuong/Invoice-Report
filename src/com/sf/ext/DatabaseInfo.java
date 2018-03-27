package com.sf.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInfo {

	public static final String url = "jdbc:mysql://cse.unl.edu/hvuong";
	public static final String username = "hvuong";
	public static final String password = "Vnh15111999";
    public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InvoiceData.class);

	static public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			log.error(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			log.error(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			log.error(e);
		}
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			log.error(e);
		}
		return conn;
	}
	public static void closeConnection(Connection conn) {
        
		//Close connection
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException sqle) {
			log.error(sqle);
		}

	}

}
