package TestCases_using_MySQLWorkbench;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc02_Login_Test_MySQL extends BaseClass {
	
	
	int r=1;
	boolean b;
	login_page lp;
	HomePage hp;

	@Test(dataProvider = "TestDB", dataProviderClass = DataProviders.class, groups = {"Datadriven","Master"})
	public void Login(String user, String pass)
	{
		try
		{
			logger.info("***Starting TC02_Login_Test***");
			logger.info("***Retrieving Data from Excel File through DataProvider***");
			hp = new HomePage(driver);
			hp.myaccount();
			hp.login();
			lp = new login_page(driver);
			lp.username(user);
			lp.password(pass);
			lp.loginbtn();
			logger.info("***Checking validation***");
			boolean target = lp.anct();
			if(target)
			{
				Testvalid(user,"Test Passed");
				logger.info("***Test Passed***");
				b=true;
				
			}
			else
			{
				Testvalid(user,"Test Failed");
				logger.info("***Test Failed***");
				logger.debug("Debug Test");
				b=false;
			}
		}
		catch(Exception e)
		{
			logger.info("***Test Failed due to Exception Occurred***");
			Assert.fail();
		}
	}
	public static void Testvalid(String email, String test) throws SQLException
	{
		String dburl = "jdbc:mysql://localhost:3306/testdb";
		String username = "root";
		String password = "root";
		
		Connection con = DriverManager.getConnection(dburl, username, password);
		String query = "Update users set TestPassfail = ? where email = ?;"; 
		
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1, test);
		pst.setString(2, email);
		
		pst.executeUpdate();
		pst.close();
		con.close();
	}
	
	@AfterMethod
	public void logout()
	{
		if(b==true)
		{
			hp.myaccount();
			hp.logout();
			lp.login();
		}
		else
		{
			lp.login();
		}
	}
}
