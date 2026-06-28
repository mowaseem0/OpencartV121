package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;
import Utilities.Excelutilily_ReadData;

public class tc02_Login_Test extends BaseClass {
	
	String path = System.getProperty("user.dir")+"/testdata/Login_Data.xlsx";
	Excelutilily_ReadData rd = new Excelutilily_ReadData(path);
	int r=1;

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"Datadriven","Master"})
	public void Login(String user, String pass)
	{
		try
		{
			logger.info("***Starting TC02_Login_Test***");
			logger.info("***Retrieving Data from Excel File through DataProvider***");
			HomePage hp = new HomePage(driver);
			hp.myaccount();
			hp.login();
			login_page lp = new login_page(driver);
			lp.username(user);
			lp.password(pass);
			lp.loginbtn();
			logger.info("***Checking validation***");
			boolean target = lp.anct();
			if(target)
			{
				rd.setCellData("Register_Data", r, 2, "Test Passed");
				rd.fillGreenColor("Register_Data", r, 2);
				r++;
				logger.info("***Test Passed***");
			}
			else
			{
				rd.setCellData("Register_Data", r, 2, "Test failed");
				rd.fillRedColor("Register_Data", r, 2);
				r++;
				logger.info("***Test Failed***");
				logger.debug("Debug Test");
			}
			
			lp.logout();
			lp.login();
		}
		catch(Exception e)
		{
			logger.info("***Test Failed due to Exception Occurred***");
			Assert.fail();
		}
	}
}
