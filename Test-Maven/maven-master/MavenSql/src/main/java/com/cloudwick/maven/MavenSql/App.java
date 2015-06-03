package com.cloudwick.maven.MavenSql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * This file base code has been taken from 
 * http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
 */
public class App {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, SQLException {

		//STEP 1 : Extract the Connection data from db.properties file
		Properties props = new Properties();
		InputStream in = new FileInputStream("target/classes/db.properties");
		props.load(in);
		in.close();

		// STEP 2 : Establish connection with the Database
		String driver = props.getProperty("db.driver");
		if (driver != null) {
			System.out.println(driver);
			Class.forName(driver);
		}

		String url = props.getProperty("db.url");
		String username = props.getProperty("db.username");
		String password = props.getProperty("db.password");

		Connection conn = null;
		Statement stmt = null;

		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(url, username, password);

		// STEP 3: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();

		String sql = "SELECT id, ename FROM emp";
		ResultSet rs = stmt.executeQuery(sql);

		// STEP 4: Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String ename = rs.getString("ename");

			// STEP 5 : Display values
			System.out.print("Id: " + id);
			System.out.println(", Name: " + ename);
		}
		
		// STEP 6: Clean-up environment
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
		if (rs != null) {
			rs.close();
		}
	}
}