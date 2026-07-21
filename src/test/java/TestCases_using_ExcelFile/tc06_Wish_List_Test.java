package TestCases_using_ExcelFile;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.SearchPage;
import PageObjects.Wish_List_Page;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc06_Wish_List_Test extends BaseClass{

	SearchPage sp;
	HomePage hp;
	login_page lp;
	Wish_List_Page wlp;
	
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
		wlp = new Wish_List_Page(driver);
		sp.SearchBar(data);
		sp.Search();
		wlp.Addwish();
		wlp.wishlist();
		logger.info("***Adding product to WishList***");
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
