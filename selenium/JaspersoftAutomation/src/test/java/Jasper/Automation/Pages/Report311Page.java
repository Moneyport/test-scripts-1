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

public class Report311Page extends BasePage {
	
	WebDriver driver;
	
	
	public Report311Page(WebDriver ldriver) {
		this.driver=ldriver;
	}
	
	@FindBy(xpath="//*[@id=\"START_DATE_TIME\"]/label/input") WebElement Startdatefield;
	@FindBy(xpath="//*[@id=\"END_DATE_TIME\"]/label/input") WebElement Enddatefield;
	@FindBy(xpath="//*[@id=\"FSP_ID\"]/label/input") WebElement Fspid;
	
	
	
	@FindBy(xpath="//*[@id=\"apply\"]") WebElement ApplyButton;
	@FindBy(xpath="//*[@id=\"ok\"]") WebElement OKButton;
	
	
	
	public void AssertPage() {
		
		Assert.assertEquals(true, Startdatefield.isDisplayed());
		Assert.assertEquals(true, Enddatefield.isDisplayed());
		Assert.assertEquals(true, Fspid.isDisplayed());
		
	}
	
	
	
	public void GenerateReport311(String startdate, String enddate, String fspid) {
		ClearTxtBox(Startdatefield);
		ClearTxtBox(Enddatefield);
		ClearTxtBox(Fspid);
		
		Startdatefield.sendKeys(startdate);
		Enddatefield.sendKeys(enddate);
		Fspid.sendKeys(fspid);
		
		ClickOnButton(ApplyButton);
		Wait(5);
		ClickOnButton(OKButton);
	}
	
	
	public void AssertReport311NotEmpty() {
		List<WebElement> tablerows = driver.findElements(By.xpath("//*[@id=\"reportContainer\"]/table/tbody/tr"));
		Assert.assertTrue(tablerows.size() > 5);
	}
	
	


}
