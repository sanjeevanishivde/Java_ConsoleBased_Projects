package com.itvedant.student;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	static Connection con = null;
	
	public static Connection getConnection() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String username = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3306/p204_jdbc";
			
			con = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return con;

	}
	
	public static void closeConnection() {
		
		try {
			
			con.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}

}
