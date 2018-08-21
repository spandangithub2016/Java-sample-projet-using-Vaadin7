package com.stpl.trainee.assignment6.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.connection.MyConnect;
import com.stpl.trainee.assignment6.constants.Constants;

public class ViewService {
	
	private PreparedStatement ps;
	private ResultSet resultSet;

	public ViewService() {
		// To fix sonar issue
	}


	public StudentBean view(StudentBean beanObject) throws SQLException {

		String sql = "SELECT * FROM STUDENT_REGISTRATION WHERE USERNAME = ?";
		return viewService(beanObject, sql);
	}
	
	public StudentBean viewService(StudentBean beanObject, String sql) throws SQLException {

		StudentBean studentBean = new StudentBean();
		final Connection conn = MyConnect.connect(); 
		
		
		try {
			
			ps = conn.prepareStatement(sql);

			ps.setString(Constants.ONE, beanObject.getUserName());
			resultSet = ps.executeQuery();

				if (resultSet.next()) {
					studentBean.setFullName(resultSet.getString(Constants.ONE));
					studentBean.setUserName(resultSet.getString(Constants.TWO));
					studentBean.setPassword(resultSet.getString(Constants.THREE));
					studentBean.setAddress(resultSet.getString(Constants.FOUR));
					studentBean.setDob(resultSet.getString(Constants.FIVE));
					studentBean.setGender(resultSet.getString(Constants.SIX));
					studentBean.setEmail(resultSet.getString(Constants.SEVEN));
					studentBean.setContact(resultSet.getString(Constants.EIGHT));

				}
			
			return studentBean;
		}
		finally
		{
			resultSet.close();
			ps.close();
			conn.close();
		}
	}
}
