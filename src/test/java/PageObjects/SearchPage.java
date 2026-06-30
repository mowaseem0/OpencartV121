package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
	
	public SearchPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@placeholder='Search']") WebElement searchbar;
	@FindBy(xpath = "//i[@class='fa fa-search']") WebElement search;
	@FindBy(xpath = "//div[@class='image']") WebElement img;
	@FindBy(xpath = "//h2[normalize-space()][starts-with(text(),'$')]") WebElement dollar;
	
	public String dollar()
	{
		return dollar.getText();
	}
	
	public void clearsearch()
	{
		searchbar.clear();
	}
	
	public void SearchBar(String s1)
	{
		searchbar.sendKeys(s1);
	}
	
	public void Search()
	{
		search.click();
	}
	
	public void Img()
	{
		img.click();
	}

}
