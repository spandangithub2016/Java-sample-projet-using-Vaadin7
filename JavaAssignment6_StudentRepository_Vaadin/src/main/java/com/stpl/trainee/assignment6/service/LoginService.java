package com.stpl.trainee.assignment6.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stpl.trainee.assignment6.connection.MyConnect;
import com.stpl.trainee.assignment6.constants.Constants;

public class LoginService {
	
private PreparedStatement ps;
private ResultSet resultSet;
	
	public LoginService()
	{
		// To fix sonar issue
	}

	public boolean login(String username, String pasword) throws SQLException {

		String sql = "SELECT * FROM STUDENT_REGISTRATION WHERE USERNAME = ? AND PASWORD = ?";
		return loginService(username, pasword, sql);

	}

	public boolean loginService(String username, String pasword, String sql) throws SQLException {

		boolean flag = false;
		Connection con = MyConnect.connect();
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(Constants.ONE, username);
			ps.setString(Constants.TWO, pasword);
			
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				flag = true;
			}
		} 
		finally {
			
			resultSet.close();
			ps.close();	
			con.close();
		}
		return flag;
	}
}
