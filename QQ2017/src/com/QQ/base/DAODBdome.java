package com.QQ.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAODBdome {

	private static String driver ="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/qq";
	private static String username="root";
	private static String password="root";
	private static Connection conn = null;
	static{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection DBdome() {
		
		try {
			if(conn.isClosed()){
			conn = DriverManager.getConnection(url,username,password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
