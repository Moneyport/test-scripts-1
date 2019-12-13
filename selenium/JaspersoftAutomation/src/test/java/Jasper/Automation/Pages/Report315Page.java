package Jasper.Automation.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Jasper.Automation.Pages.BasePage;
import Jasper.Automation.Utilities.PropertiesFile;

public class Report315Page extends BasePage {
	
	WebDriver driver;
	
	
	public Report315Page(WebDriver ldriver) {
		this.driver=ldriver;
	}
	
	@FindBy(xpath="//*[@id=\"settlementWindowId\"]/label/input") WebElement windowid;
	@FindBy(xpath="//*[@id=\"participantId\"]/label/input") WebElement participantid;

	
	
	
	@FindBy(xpath="//*[@id=\"apply\"]") WebElement ApplyButton;
	@FindBy(xpath="//*[@id=\"ok\"]") WebElement OKButton;
	
	
	
	public void AssertPage() {
		
		Assert.assertEquals(true, windowid.isDisplayed());
		Assert.assertEquals(true, participantid.isDisplayed());
		
		
	}
	
	
	
	public void GenerateReport315(String windowidx, String participantidx) {
		ClearTxtBox(windowid);
		ClearTxtBox(participantid);
		
		
		windowid.sendKeys(windowidx);
		participantid.sendKeys(participantidx);
	
		
		ClickOnButton(ApplyButton);
		Wait(5);
		ClickOnButton(OKButton);
	}
	
	
	public void AssertReport315NotEmpty() {
		List<WebElement> tablerows = driver.findElements(By.xpath("//*[@id=\"reportContainer\"]/table/tbody/tr"));
		Assert.assertTrue(tablerows.size() > Integer.parseInt(PropertiesFile.rows));
	}
	
	


}
