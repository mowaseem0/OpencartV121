package TestCases;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import TestBase.BaseClass;

public class tc00_BrokenLinksonHomePage extends BaseClass
{
	HomePage hp;
	@Test(priority = 1)
	public void Toplinks(Method testMethod)
	{
		hp = new HomePage(driver);
		Brokenlinks(hp.TopLinks());
		logger.info("BrokenLinks in "+testMethod.getName()+" is - "+brokenlinks);
		logger.info("UnbrokenLinks in "+testMethod.getName()+" is - "+unbrokenlinks);
	}
	@Test(priority = 2)
	public void FooterLinks(Method testMethod)
	{
		Brokenlinks(hp.Footerlink());
		logger.info("BrokenLinks in "+testMethod.getName()+" is - "+brokenlinks);
		logger.info("UnbrokenLinks in "+testMethod.getName()+" is - "+unbrokenlinks);
	}
	@BeforeMethod
	public void clear()
	{
		brokenlinks = 0;
		unbrokenlinks = 0;
	}
	
}
