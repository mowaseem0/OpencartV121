package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utilities.Excelutilily_ReadData;
@Listeners(Utilities.ExtentReportManager.class)
public abstract class BaseClass {
	
	protected static WebDriver driver;
	protected Logger logger;
	protected Properties p;
	protected String path = System.getProperty("user.dir")+"/testdata/Login_Data.xlsx";
	protected Excelutilily_ReadData rd = new Excelutilily_ReadData(path);
	protected WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Sanity","Master","Datadriven"})
	@Parameters({"browser","os"})
	protected void setup(@Optional("chrome") String br,@Optional("windows") String os) throws IOException
	{
		p = new Properties();
		FileReader f = new FileReader("./src//test//resources//config.properties");
		p.load(f);
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				logger.info("No Matching Platfrorm");
				return;
			}
			
			switch(br.toLowerCase())
			{
				case "chrome" :cap.setBrowserName("chrome");break;
				case "edge" :cap.setBrowserName("MicrosoftEdge");break;
				case "firefox" :cap.setBrowserName("firefox");break;
				default : logger.info("No Matching Browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome":driver = new ChromeDriver();break;
				case "firefox": driver = new FirefoxDriver();break;
				case "edge": driver = new EdgeDriver();break;
				default: System.out.println("Invalid Browser");return;
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(p.getProperty("url"));
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass(groups = {"Sanity","Master","Datadriven"})
	protected void Teardown()
	{
		driver.quit();
	}

	
	
	@SuppressWarnings("deprecation")
	public String randomstring()
	{
		String generatestring = RandomStringUtils.randomAlphabetic(5);
		return generatestring;
	}
	
	@SuppressWarnings("deprecation")
	public String randomnumeric()
	{
		String generatenumber = RandomStringUtils.randomNumeric(10);
		return generatenumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomalphanumeric()
	{
		String alpha = RandomStringUtils.randomAlphabetic(3);
		String numeric = RandomStringUtils.randomNumeric(3);
		return (alpha+numeric);
	}
	
	public static String captureScreen(String tname) throws IOException
	{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"/screenshots/"+tname+" "+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		
		sourcefile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
}
