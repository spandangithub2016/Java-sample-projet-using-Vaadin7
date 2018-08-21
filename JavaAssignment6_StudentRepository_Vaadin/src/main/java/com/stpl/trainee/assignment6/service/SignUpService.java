package com.stpl.trainee.assignment6.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.connection.MyConnect;
import com.stpl.trainee.assignment6.constants.Constants;

public class SignUpService {

	private PreparedStatement ps;

	public SignUpService() {
		// To fix sonar issue
	}

	public boolean signUp(StudentBean studentBean) throws SQLException {

		String sql = "INSERT INTO STUDENT_REGISTRATION VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		return signUpService(studentBean, sql);

	}

	public boolean signUpService(StudentBean studentBean, String sql) throws SQLException {

		boolean flag = false;
		final Connection con = MyConnect.connect();

		try {

			ps = con.prepareStatement(sql);

			ps.setString(Constants.ONE, studentBean.getFullName());
			ps.setString(Constants.TWO, studentBean.getUserName());
			ps.setString(Constants.THREE, studentBean.getPassword());
			ps.setString(Constants.FOUR, studentBean.getAddress());
			ps.setString(Constants.FIVE, studentBean.getDob());
			ps.setString(Constants.SIX, studentBean.getGender());
			ps.setString(Constants.SEVEN, studentBean.getEmail());
			ps.setString(Constants.EIGHT, studentBean.getContact());

			ps.executeUpdate();
			flag = true;

		} 
		finally {
			ps.close();
			con.close();
		}
		return flag;

	}

}
