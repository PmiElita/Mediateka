package com.mediateka.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import snaq.db.ConnectionPool;

import com.mysql.jdbc.Driver;

public class ConnectionManager {
	private static ConnectionPool pool = null;
	static {
		try {
			Class<?> c = Class.forName("com.mysql.jdbc.Driver");
			Driver driver = (Driver) c.newInstance();
			DriverManager.registerDriver(driver);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		pool = new ConnectionPool(
				"MediatekaPool",
				20,
				30,
				40,
				3600,
				"jdbc:mysql://localhost:3306/mediateka?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10",
				"root", "root");
		// pool = new ConnectionPool("MediatekaPool", 20, 30, 40, 3600,
		// "jdbc:mysql://localhost:3306/mediateka", "root","root");

	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}