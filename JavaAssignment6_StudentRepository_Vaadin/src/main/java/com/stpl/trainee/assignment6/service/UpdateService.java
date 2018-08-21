package com.stpl.trainee.assignment6.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.connection.MyConnect;
import com.stpl.trainee.assignment6.constants.Constants;

public class UpdateService {
	
	private PreparedStatement ps;

	public UpdateService() {
		// To fix sonar issue
	}


    public int update(StudentBean beanObject) throws SQLException {

    	String sql = "UPDATE STUDENT_REGISTRATION SET FULLNAME=?, PASWORD=?, ADDRESS=?"
				+ ", DOB=?, GENDER=?, EMAIL=?, CONTACT=? WHERE USERNAME=?";	
      return updateService(beanObject, sql);

    }
    
    public int updateService(StudentBean beanObject, String sql) throws SQLException {

        int result = Constants.ZERO;
        final Connection connection = MyConnect.connect(); 

        try {
        		
        	ps = connection.prepareStatement(sql);

            ps.setString(Constants.ONE, beanObject.getFullName());
            ps.setString(Constants.TWO, beanObject.getPassword());
            ps.setString(Constants.THREE, beanObject.getAddress());
            ps.setString(Constants.FOUR, beanObject.getDob());
            ps.setString(Constants.FIVE, beanObject.getGender());
            ps.setString(Constants.SIX, beanObject.getEmail());
            ps.setString(Constants.SEVEN, beanObject.getContact());

            ps.setString(Constants.EIGHT, beanObject.getUserName());
            result = ps.executeUpdate();
            
        }
        finally {
        	ps.close();
			connection.close();
		}
        return result;

    }
}
