package PageObjects;

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
