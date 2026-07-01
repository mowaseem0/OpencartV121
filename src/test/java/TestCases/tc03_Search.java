package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.SearchPage;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc03_Search extends BaseClass{
	
	SearchPage sp;
	HomePage hp;
	login_page lp;
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
		sp.SearchBar(data);
		sp.Search();
		sp.Img();
		String dol = sp.dollar();
		try 
		{
			rd.setCellData("Search", r, 1, dol);
			r++;
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sp.clearsearch();		
	}
	
	@Test(priority = 3, dependsOnMethods = {"SearchOptions"})
	public void logofffromweb()
	{
		hp.myaccount();
		hp.logout();
		logger.info("***Logout from the Website***");
	}
}
