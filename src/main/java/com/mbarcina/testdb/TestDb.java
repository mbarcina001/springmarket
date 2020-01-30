package com.mbarcina.testdb;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestDb {

	public static void main(String[] args) {
		// setup connection variables
		String user = "root";
		String pass = "root";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/spring_market?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		// get connection to database
		try {			
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("SUCCESS!!!");
			
			myConn.close();
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	
	
	}

}







