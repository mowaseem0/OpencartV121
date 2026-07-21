package TestCases_using_ExcelFile;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.Add_to_cart_page;
import PageObjects.HomePage;
import PageObjects.SearchPage;
import PageObjects.Shop_cart_Page1;
import PageObjects.login_page;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc07_Shopcart_Test extends BaseClass {

	SearchPage sp;
	HomePage hp;
	login_page lp;
	Add_to_cart_page acp;
	Shop_cart_Page1 scp;
	int r=1;
	String A1,A2,A3;
	
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
		scp = new Shop_cart_Page1(driver);
		sp.SearchBar(data);
		sp.Search();
		sp.Img();
		logger.info("***Adding product to the Cart***");
		acp.AddtoCart();
		scp.Shop();
		scp.Quantity(r,"1");
		//scp.Quantity(r, "1");
		
		r++;
		sp.clearsearch();		
	}
	
	@Test(priority = 3, dependsOnMethods = {"AddtoCart"})
	public void CalculateValues() throws InterruptedException
	{
		A1 = scp.Value1();
		A2 = scp.Value2();
		A3 = scp.Total();
		if((Double.parseDouble(A1)+Double.parseDouble(A2))==Double.parseDouble(A3))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		logger.info("***Validation Completed***");
		scp.Shop();
		System.out.println(r);
		for(int i=1; i<r; i++)
		{
			scp.Nullvalues(i);
		}
		logger.info("***Values got Null***");
		scp.Continuebtn();
	}
	
	@Test(priority = 4,dependsOnMethods= {"logontoweb"})
	public void logofffromweb() throws InterruptedException
	{
		hp.myaccount();
		hp.logout();
		logger.info("***Logout from the Website***");
	}
	
}
