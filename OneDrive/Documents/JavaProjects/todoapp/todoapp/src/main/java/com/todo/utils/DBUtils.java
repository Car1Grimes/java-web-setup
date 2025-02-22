package com.todo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	private static final String serverName = "localhost";
	private static final String dbName = "todoapp";
	private static final String portNumber = "1433";
	private static final String instance = ""; // LEAVE IT ALONE IF HAVE JUST ONE INSTANCE
	private static final String user = "sa";
	private static final String password = "123456";
	
	
	public static Connection getConnection() {
		String url;
		if(instance.equals(null) || instance.trim().isEmpty()) {
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
		} else {
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName" + dbName + ";encrypt=true;trustServerCertificate=true;";
		}
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch(ClassNotFoundException e) {
			System.out.print("ToDoApp: JDBC Driver is not loaded. Please check your pom.xml file");
		}
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch(SQLException e) {
			System.out.println("ToDoApp: Cannot connect to the SQL Server. Reason: " + e.getMessage());
		}
		return null;
	}
}
