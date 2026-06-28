package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegister extends BasePage {

	public AccountRegister(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']") WebElement txtfirstname;
	@FindBy(xpath = "//input[@id='input-lastname']") WebElement txtlastname;
	@FindBy(xpath = "//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath = "//input[@id='input-telephone']") WebElement txttelephone;
	@FindBy(xpath = "//input[@id='input-password']") WebElement txtpass;
	@FindBy(xpath = "//input[@id='input-confirm']") WebElement txtcnfpass;
	@FindBy(xpath = "//input[@name='agree']") WebElement agree;
	@FindBy(xpath = "//input[@value='Continue']") WebElement submit;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement successregister;
	//@FindBy(xpath = "//a[normalize-space()='Continue']") WebElement returnpage;
	
	public void firstname(String firstname)
	{
		txtfirstname.sendKeys(firstname);
	}
	
	public void lastname(String lastname)
	{
		txtlastname.sendKeys(lastname);
	}
	
	public void email(String email) 
	{
		txtemail.sendKeys(email);
	}
	
	public void telephone(String telephone)
	{
		txttelephone.sendKeys(telephone);
	}
	
	public void password(String pass)
	{
		txtpass.sendKeys(pass);
	}
	
	public void cnfpassword(String pass)
	{
		txtcnfpass.sendKeys(pass);
	}
	
	public void agreebtn()
	{
		agree.click();
	}
	
	public void submitbtn()
	{
		submit.click();
	}
	
	public String getconfirmation()
	{
		try
		{
			return successregister.getText();
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
}
