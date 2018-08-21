package com.stpl.trainee.assignment6.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyConnect {
    
	 private static final Logger LOGGER = LogManager.getFormatterLogger(MyConnect.class);
	    private static final String HOST_NAME = "10.4.48.18";
	    private static final String PORT = "1433";
	    private static String DATABASE_NAME = "BPIGTN_AGN_APP_DEV";
	    private static final String USER_NAME = "bpigtn_db";
	    private static final String PASSWORD = "^D$b2K5!";
	    

		public static Connection connect() {

	        Connection con = null;

	        String url = "jdbc:sqlserver://" + HOST_NAME + ":" + PORT + ";" + "databaseName=" + DATABASE_NAME + ";user="
	                + USER_NAME + ";" + "password=" + PASSWORD;

	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(url);
	            
	        } catch (ClassNotFoundException | SQLException e) {
	            LOGGER.info("error in connection: " + e);
	        }
	        return con;
	    }
	
}
