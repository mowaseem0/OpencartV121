package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.Add_to_cart_page;
import PageObjects.HomePage;
import PageObjects.SearchPage;
import PageObjects.Shop_cart_Page;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc07_Shopcart_Test extends BaseClass {

	SearchPage sp;
	HomePage hp;
	login_page lp;
	Add_to_cart_page acp;
	Shop_cart_Page scp;
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
	public void AddtoCart(String data)
	{
		logger.info("***Searching the Items using dataprovider Method***");
		sp = new SearchPage(driver);
		acp = new Add_to_cart_page(driver);
		sp.SearchBar(data);
		sp.Search();
		sp.Img();
		logger.info("***Adding product to the Cart***");
		acp.AddtoCart();
		sp.clearsearch();		
	}
	
	@Test(priority = 3, dependsOnMethods = {"AddtoCart"})
	public void CalculateValues() throws InterruptedException
	{
		scp = new Shop_cart_Page(driver);
		scp.Shop();
		scp.Quantity(1,"1");
		scp.Quantity(2, "1");
		Thread.sleep(6000);
		String A1 = scp.Value1();
		String A2 = scp.Value2();
		String A3 = scp.Total();
		if((Double.parseDouble(A1)+Double.parseDouble(A2))==Double.parseDouble(A3))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 4, dependsOnMethods = {"CalculateValues"})
	public void logofffromweb() throws InterruptedException
	{
		hp.myaccount();
		hp.logout();
		logger.info("***Logout from the Website***");
	}
	
}
