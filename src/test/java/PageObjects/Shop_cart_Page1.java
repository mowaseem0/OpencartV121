package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Shop_cart_Page1 extends BasePage {

	public Shop_cart_Page1(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-8']//table//tbody//tr[2]//td[2]") WebElement total;
	@FindBy(xpath = "//span[normalize-space()='Shopping Cart']") WebElement shopcart;
	@FindBy(xpath = "//div[@class='table-responsive']//table//tbody//tr[1]//td[6]") WebElement value1;
	@FindBy(xpath = "//div[@class='table-responsive']//table//tbody//tr[2]//td[6]") WebElement value2;
	@FindBy(xpath = "//a[@class='btn btn-primary']") WebElement contbtn;
	
	public void Continuebtn()
	{
		js.executeScript("arguments[0].click();", contbtn);
	}
	
	public void Quantity(int r, String n)
	{
		WebElement text = driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr["+r+"]//td[4]//div//input"));
		js.executeScript("arguments[0].value = '';", text);
		js.executeScript("arguments[0].value = arguments[1]", text,n);
		//text.clear();
		//text.sendKeys(n);
		WebElement btn = driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr["+r+"]//td[4]//div//span//button[@data-original-title='Update']"));
		js.executeScript("arguments[0].click();", btn);
	}
	
	public void Nullvalues(int r1)
	{
		WebElement btn1 = driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr["+r1+"]//td[4]//div//span//button[@data-original-title='Remove']"));
		js.executeScript("arguments[0].click();", btn1);
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
