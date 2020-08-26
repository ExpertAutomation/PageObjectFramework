package com.murali.pom.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DataBaseHelper {
	
	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	
	private static String url = "jdbc:mysql://localhost/person";
	private static String driverName="com.mysql.jdbc.Driver";
	private static String userName="root";
	private static String password="password";
	private static Connection conn;
	private static DataBaseHelper instance = null;
	
	public DataBaseHelper() {
		conn = getSingleInstanceConnection();
	}
	
	public static DataBaseHelper getInstance() {
		if(instance == null) {
			instance = new DataBaseHelper();
		}
		return instance;
	}
	
	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driverName);
			try {
				conn = DriverManager.getConnection(url, userName, password);
				if(conn!=null) {
					log.info("Connected to data base..");
				}
			}
			catch(SQLException e) {
				log.error("Failed to create Data base connection" +e);
			}
		}
		catch(ClassNotFoundException e) {
			log.info("Driver not found.."+e);
		}
		return conn;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public ResultSet getResultSet(String dbQuery) {
		instance = DataBaseHelper.getInstance();
		conn = instance.getConnection();
		log.info("Executing query: " +dbQuery);
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
