package Jasper.Automation.Utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserFactory {

	public static WebDriver StartApp(WebDriver driver, String browsername) {
		
		PropertiesFile.Readproperties();
		
		
		if(browsername.equals("Chrome")) {
		
			System.setProperty("webdriver.chrome.driver", PropertiesFile.chromedriverlocation);
			String downloadFilepath = System.getProperty("user.dir") + File.separator + "Downloadfiles";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("safebrowsing.enabled", "true"); 
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("-–headless");

			
			options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(ChromeOptions.CAPABILITY, options);

			driver= new ChromeDriver(options);
			
			
		}else if (browsername.equals("Firefox")) {
			
		}else if (browsername.equals("IE")) {
			
		}else {
			System.out.println("Browser not supported");
		}
		driver.get(PropertiesFile.jasperUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static void quitbrowser(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	
	

}
