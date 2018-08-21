package com.stpl.trainee.assignment6.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.connection.MyConnect;
import com.stpl.trainee.assignment6.constants.Constants;

public class DeleteService {
	
	private PreparedStatement ps;
	
	public DeleteService()
	{
		// To fix sonar issue
	}

    public int delete(StudentBean studentBean) throws SQLException {
        
        String sql = "DELETE FROM STUDENT_REGISTRATION WHERE USERNAME =?";
        return deleteService(studentBean, sql);
    }
    
    public int deleteService(StudentBean studentBean, String sql) throws SQLException
    {
    	final Connection con = MyConnect.connect(); 
		
    	try {
    		
    		ps = con.prepareStatement(sql);
            ps.setString(Constants.ONE, studentBean.getUserName());
            return ps.executeUpdate();

        }
    	finally {
    		ps.close();
			con.close();
		}

    }

}
