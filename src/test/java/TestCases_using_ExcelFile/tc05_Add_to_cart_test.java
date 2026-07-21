package TestCases_using_ExcelFile;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.Add_to_cart_page;
import PageObjects.HomePage;
import PageObjects.SearchPage;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc05_Add_to_cart_test extends BaseClass {

	SearchPage sp;
	HomePage hp;
	login_page lp;
	Add_to_cart_page acp;
	int r=1;
	
	@Test(priority = 1)
	public void logontoweb()
	{
		logger.info("***Starting Search Method for Searching the Items***");
		hp= new HomePage(driver);
		lp = new login_page(driver);
		hp.myaccount();
		logger.info("***Login to the Website***");
		hp.login();
		lp.username(p.getProperty("email"));
		lp.password(p.getProperty("password"));
		lp.loginbtn();
		logger.info("***Validating the Credentials***");
		boolean target = lp.anct();
		if(target)
		{
			logger.info("***Seccesfully Login to Website***");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("***Login Failed***");
			Assert.fail();
		}
	}
	
	@Test(priority = 2, dependsOnMethods = {"logontoweb"}, dataProvider = "SearchData", dataProviderClass = DataProviders.class)
	public void SearchOptions(String data)
	{
		logger.info("***Searching the Items using dataprovider Method***");
		sp = new SearchPage(driver);
		acp = new Add_to_cart_page(driver);
		sp.SearchBar(data);
		sp.Search();
		sp.Img();
		logger.info("***Adding product to the Cart***");
		acp.AddtoCart();
		acp.checkout();
		sp.clearsearch();		
	}
	
	@Test(priority = 3, dependsOnMethods = {"SearchOptions"})
	public void logofffromweb() throws InterruptedException
	{
		hp.myaccount();
		hp.logout();
		logger.info("***Logout from the Website***");
	}
	
}
