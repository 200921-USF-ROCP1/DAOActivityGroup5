package com.revature.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static final String connectionString = "jdbc:postgresql://lallah.db.elephantsql.com:5432/wualvcfo",
			username = "wualvcfo",
			password = "mvHFDJEOaCBCbG1Q180ItPOmyxLL-r3J";
	
	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(connectionString, username, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	return null;
	}
}
