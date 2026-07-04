package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Shop_cart_Page extends BasePage
{
	public Shop_cart_Page(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-8']//table//tbody//tr[2]//td[2]") WebElement total;
	@FindBy(xpath = "//span[normalize-space()='Shopping Cart']") WebElement shopcart;
	@FindBy(xpath = "//div[@class='table-responsive']//table//tbody//tr[1]//td[6]") WebElement value1;
	@FindBy(xpath = "//div[@class='table-responsive']//table//tbody//tr[2]//td[6]") WebElement value2;
	
	public void Quantity(int r, String n)
	{
		driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr["+r+"]//td[4]//div//input")).clear();
		driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr["+r+"]//td[4]//div//input")).sendKeys(n);
		driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr["+r+"]//td[4]//div//span//button[@type='submit']")).click();
	}
	
	public String Value1()
	{
		return value1.getText().replaceAll("[$,]", "");
	}
	
	public String Value2()
	{
		return value2.getText().replaceAll("[$,]", "");
	}
	
	public String Total()
	{
		return total.getText().replaceAll("[$,]", "");
	}
	
	public void Shop()
	{
		shopcart.click();
	}

}
