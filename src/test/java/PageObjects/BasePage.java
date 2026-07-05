package PageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	protected WebDriver driver;
	WebDriverWait wait;
	Wait<WebDriver> fluentWait;
	JavascriptExecutor js;
	BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		//Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Fluent Wait
		fluentWait = new FluentWait<>(driver)
		        .withTimeout(Duration.ofSeconds(10))      
		        .pollingEvery(Duration.ofSeconds(2))      
		        .ignoring(NoSuchElementException.class)   
		        .ignoring(StaleElementReferenceException.class);
		//JavascriptExecutor
		js = (JavascriptExecutor)driver;
	}

}
