package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class login_page extends BasePage{

	public login_page(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']") WebElement username;
	@FindBy(xpath = "//input[@id='input-password']") WebElement Password;
	@FindBy(xpath = "//input[@value='Login']") WebElement loginbtn;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logout;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Login']") WebElement login;
	@FindBy(xpath = "//h2[normalize-space()='My Account']") WebElement anct;
	
	public boolean anct()
	{
		try
		{
			return(anct.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void username(String user)
	{
		username.sendKeys(user);
	}

	public void password(String pass)
	{
		Password.sendKeys(pass);
	}
	
	public void loginbtn()
	{
		loginbtn.click();
	}
	
	public void logout()
	{
		logout.click();
	}
	
	public void login()
	{
		login.click();
	}
}
