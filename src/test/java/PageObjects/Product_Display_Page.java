package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product_Display_Page extends BasePage {
	
	public Product_Display_Page(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//ul[@class='thumbnails']//li[1]//a[1]") WebElement bigimg;
	@FindBy(xpath = "//button[@title='Next (Right arrow key)']") WebElement rightarrow;
	@FindBy(xpath = "//button[@title='Previous (Left arrow key)']") WebElement leftarrow;
	@FindBy(xpath = "//button[normalize-space()='×']") WebElement crossbarkey;
	
	public void crossbtn()
	{
		crossbarkey.click();
	}
	
	public void Bigimgclick()
	{
		bigimg.click();
	}
	
	public void leftarrowkey()
	{
		leftarrow.click();
	}
	
	public void rightarrowkey()
	{
		rightarrow.click();
	}
	

}
