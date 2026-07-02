package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Add_to_cart_page extends BasePage{
	
	public Add_to_cart_page(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//button[@id='button-cart']") WebElement addtocartbtn;
	@FindBy(xpath = "//span[normalize-space()='Checkout']") WebElement checkoutbtn;
	
	public void AddtoCart()
	{
		addtocartbtn.click();
	}
	
	public void checkout()
	{
		checkoutbtn.click();
	}
}
