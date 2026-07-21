package TestCases_using_ExcelFile;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRegister;
import PageObjects.HomePage;
import TestBase.BaseClass;
import Utilities.ExcelUtilities_writeData;

public class tc01_Registration extends BaseClass {
	
	@Test(groups = {"Sanity", "Master"})
	public void Register()
	{
		try
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
				
				ExcelUtilities_writeData.writeData(email, pass);
				logger.info("***Wrote data in Excel File***");
			}
			else
			{
				logger.error("Test Failed");
				logger.debug("Test Debug");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Tc01_Registration_Test_Finished***");
	}

}
