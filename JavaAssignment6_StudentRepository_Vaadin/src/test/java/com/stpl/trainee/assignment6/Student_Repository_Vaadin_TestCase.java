package com.stpl.trainee.assignment6;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import org.junit.Test;
import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.service.DeleteService;
import com.stpl.trainee.assignment6.service.LoginService;
import com.stpl.trainee.assignment6.service.SignUpService;
import com.stpl.trainee.assignment6.service.UpdateService;
import com.stpl.trainee.assignment6.service.ViewService;

public class Student_Repository_Vaadin_TestCase {

	@Test
	public void signUpServiceTestCase() {

		StudentBean studentBean = new StudentBean();
		SignUpService signUpService = new SignUpService();

		Random random = new Random();
		int x = random.nextInt(100);

		try {

			studentBean.setFullName("spandan");
			studentBean.setUserName("s" + x);
			studentBean.setPassword("s" + x);

			boolean expected = signUpService.signUpService(studentBean,
					"INSERT INTO STUDENT_REGISTRATION VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			if (expected) {

				boolean expected2 = signUpService.signUp(studentBean);

				if (!expected2) {
					assertEquals(expected2, false);

					studentBean.setFullName("abc");
					studentBean.setUserName("abc" + x);
					studentBean.setPassword("abc" + x);
					studentBean.setDob(new Date().toString());
					signUpService.signUp(studentBean);
				} else {
					assertEquals(expected2, true);
				}

				signUpService.signUpService(studentBean, "wrong sql");

			}
		} catch (SQLException e) {

		} finally {

			studentBean.setFullName("abcd");
			studentBean.setUserName("abcd" + x);
			studentBean.setPassword("abcd" + x);
			boolean expected3;
			try {
				expected3 = signUpService.signUp(studentBean);
				assertEquals(expected3, true);
			} catch (SQLException e) {
			}

		}

	}

	@Test
	public void deleteServiceTestCase() {

		DeleteService deleteService = new DeleteService();
		SignUpService signUpService = new SignUpService();
		StudentBean studentBean = new StudentBean();

		Random random = new Random();
		int x = random.nextInt(100);

		try {

			studentBean.setFullName("xxxx");
			studentBean.setUserName("xuy" + x);
			studentBean.setPassword("s" + x);
			signUpService.signUpService(studentBean, "INSERT INTO STUDENT_REGISTRATION VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			deleteService.delete(studentBean);

			studentBean.setFullName("gggg");
			studentBean.setUserName("kk" + x);
			studentBean.setPassword("s" + x);
			signUpService.signUpService(studentBean, "INSERT INTO STUDENT_REGISTRATION VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			deleteService.deleteService(studentBean, "DELETE FROM STUDENT_REGISTRATION WHERE USERNAME =?");

			deleteService.deleteService(studentBean, "wrng sql");

		} catch (SQLException e) {

		} finally {
			try {
				studentBean.setFullName(null);
				studentBean.setUserName(null);
				studentBean.setPassword(null);
				deleteService.delete(studentBean);
				deleteService.deleteService(studentBean, "DELETE FROM STUDENT_REGISTRATION WHERE USERNAME =?");

			} catch (SQLException e) {
			}
		}

	}

	@Test
	public void updateServiceTestCase() {

		StudentBean studentBean = new StudentBean();
		UpdateService updateService = new UpdateService();
		SignUpService signUpService = new SignUpService();

		Random random = new Random();
		int x = random.nextInt(100);

		try {

			studentBean.setFullName("spandan");
			studentBean.setUserName("s" + x);
			studentBean.setPassword("s" + x);
			signUpService.signUpService(studentBean, "INSERT INTO STUDENT_REGISTRATION VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

			updateService.updateService(studentBean,
					"UPDATE STUDENT_REGISTRATION SET FULLNAME=?, PASWORD=?, ADDRESS=?"
							+ ", DOB=?, GENDER=?, EMAIL=?, CONTACT=? WHERE USERNAME=?");

				

			updateService.updateService(studentBean, "wrong sql");

		} catch (SQLException e) {
		
		} finally {


			try {
				studentBean.setFullName("xyz");
				studentBean.setUserName("xyz" + x);
				signUpService.signUp(studentBean);
				
				studentBean.setFullName("zyx");
				studentBean.setUserName("xyz" + x);
			
				int result = updateService.update(studentBean);
				assertEquals(result, 1);
				 updateService.updateService(studentBean, "UPDATE STUDENT_REGISTRATION SET FULLNAME=?, PASWORD=?, ADDRESS=?"
							+ ", DOB=?, GENDER=?, EMAIL=?, CONTACT=? WHERE USERNAME=?");
				
				studentBean.setFullName("abc");
				studentBean.setUserName("abc" + x);
				studentBean.setPassword("abc" + x);
				studentBean.setDob(new Date().toString());
				updateService.update(studentBean);
				
			} catch (SQLException e) {
			}

		}

	}
	
	@Test
	public void viewServiceTestCase() {
		
		StudentBean studentBean = new StudentBean();
		ViewService viewService = new ViewService();
		SignUpService signUpService = new SignUpService();

		try {
			
			studentBean.setUserName("wrong username");
			viewService.view(studentBean);
			
			
		} catch (SQLException e) {

		}
		finally {
			
			Random random = new Random();
			int x = random.nextInt(100);
			
			studentBean.setFullName("view");
			studentBean.setUserName("v" + x);
			studentBean.setPassword("v" + x);
			
			try {
				
				signUpService.signUp(studentBean);
				viewService.view(studentBean);
				viewService.viewService(studentBean, "SELECT * FROM STUDENT_REGISTRATION WHERE USERNAME = ?");
				
				viewService.viewService(studentBean, "wrong sql");
				
			} catch (SQLException e) {
			}
			
		}
		
	}
	
	@Test
	public void loginServiceTestCase() {
		
		StudentBean studentBean = new StudentBean();
		LoginService loginService = new LoginService();
		SignUpService signUpService = new SignUpService();

		try {
			
			loginService.login("wrong username", "wrong password");
			
			
		} catch (SQLException e) {

		}
		finally {
			
			Random random = new Random();
			int x = random.nextInt(100);
			
			studentBean.setFullName("login");
			studentBean.setUserName("l" + x);
			studentBean.setPassword("l" + x);
			
			try {
				
				signUpService.signUp(studentBean);
				loginService.login("l"+x, "l"+x);
				loginService.loginService("l"+x, "l"+x, "SELECT * FROM STUDENT_REGISTRATION WHERE USERNAME = ? AND PASWORD = ?");
				
				loginService.loginService("l"+x, "l"+x, "wrong sql");
				
			} catch (SQLException e) {
			}
			
		}
		
	}
	

}
