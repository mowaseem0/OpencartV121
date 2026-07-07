package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Register']") WebElement register;
	@FindBy(xpath = "//span[normalize-space()='My Account']") WebElement myaccount;
	@FindBy(xpath = "//a[normalize-space()='Login']") WebElement login;
	@FindBy(xpath = "//a[normalize-space()='Logout']") WebElement logout;
	
	@FindBy(xpath = "//footer//div[@class='row']//div//ul//li//a") List<WebElement> footerlinks;
	@FindBy(xpath = "//div[@id='top-links']//ul//li//a") List<WebElement> toplinks;
	
	public List<WebElement> TopLinks()
	{
		return toplinks;
	}
	
	public List<WebElement> Footerlink()
	{		
		return footerlinks;
	}

	public void logout()
	{
		logout.click();
	}
	
	public void login()
	{
		login.click();
	}
	
	public void register()
	{
		register.click();
	}
	
	public void myaccount()
	{
		myaccount.click();
	}
}
