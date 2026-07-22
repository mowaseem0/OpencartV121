package TestCases_using_MySQLWorkbench;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRegister;
import PageObjects.HomePage;
import TestBase.BaseClass;

public class tc01_Registration_test_MySQL extends BaseClass {
	
	@Test(groups = {"Sanity", "Master"})
	public void Register() throws SQLException
	{
		logger.info("***Tc01_Registration_Test_Started***");
		HomePage hp = new HomePage(driver);
		hp.myaccount();
		hp.register();
		AccountRegister ag = new AccountRegister(driver);
		logger.info("***Providing_Details***");
		ag.firstname(randomstring());
		ag.lastname(randomstring());
		String email = randomstring()+"@"+"gmail.com";
		ag.email(email);
		ag.telephone(randomnumeric());
		String pass = randomalphanumeric();
		ag.password(pass);
		ag.cnfpassword(pass);
		ag.agreebtn();
		ag.submitbtn();
		logger.info("***Validation_Starts***");
		String cnfmsg = ag.getconfirmation();
		if(cnfmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("***Your Account has been Created***");
			
			insertvalues(email, pass);
			logger.info("***Wrote data in MySQL_Workbench File***");
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Test Debug");
			Assert.assertTrue(false);
		}
	}
	
	public static void insertvalues(String user, String pass) throws SQLException
	{
		String dburl = "jdbc:mysql://localhost:3306/testdb";
		String username = "root";
		String password = "root";
		
		Connection con = DriverManager.getConnection(dburl,username,password);
		String query = "insert into users (email,password) values(?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1, user);
		pst.setString(2, pass);
		pst.executeUpdate();
		
		pst.close();
		con.close();
	}

}
