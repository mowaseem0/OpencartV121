package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Wish_List_Page extends BasePage{
	
	public Wish_List_Page(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//button[@type='button']//i[@class='fa fa-heart']") WebElement addwish;
	@FindBy(xpath = "//a[@id='wishlist-total']//i[@class='fa fa-heart']") WebElement wishlistbtn;
	
	public void Addwish()
	{
		addwish.click();
	}
	
	public void wishlist()
	{
		wishlistbtn.click();
	}
}
