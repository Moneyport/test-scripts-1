package Jasper.Automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Jasper.Automation.Pages.BasePage;

public class HomePage extends BasePage {
	
	WebDriver driver;
	
	
	public HomePage(WebDriver ldriver) {
		this.driver=ldriver;
	}
	
	@FindBy(id="main_logOut") WebElement Logoutbutton;
	@FindBy(id="main_library") WebElement MainLibrary;
	@FindBy(xpath="//*[contains(text(), '311-dfspDailyTxnReport')]") WebElement Report311Link;
	
	@FindBy(xpath="//*[contains(text(), '312-hubDailyTxnReport')]") WebElement Report312Link;
	
	@FindBy(xpath="//*[contains(text(), '315-reconciliationAmountReport')]") WebElement Report315Link;
	
	
	
	@FindBy(xpath="//*[contains(text(), 'Administration')]") WebElement Admin;
	@FindBy(xpath="//*[contains(text(), 'Transfer Verification')]") WebElement TransferV;
	
	
	public void AssertPage() {
		
		Assert.assertEquals(true, Logoutbutton.isDisplayed());
		Assert.assertEquals(true, MainLibrary.isDisplayed());
	
		
	}
	
	public void ClickOnSection(String section) {
		
	/*	if(section.equals("Financial Monitoring")) {
			
			FinancialM.click();
			
			
		}else if (section.equals("Settlement Windows")) {
			SettlementW.click();
			
		}else if (section.equals("Administration")) {
			Admin.click();
			
		}else if (section.equals("Transfer Verification")) {
			TransferV.click();
			
		}else {
			System.out.println("Section not found");
		}*/
		
	}
	
	public void ClickOnReport311() {
		
		
		
		ClickOnButton(MainLibrary);
		
		Wait(2);
		
		ClickOnButton(Report311Link);
		
		Wait(3);
	}
	
	
	public void ClickOnReport312() {
		
		
		
		ClickOnButton(MainLibrary);
		
		Wait(2);
		
		ClickOnButton(Report312Link);
		
		Wait(3);
	}
	
	public void ClickOnReport315() {
		
		
		
		ClickOnButton(MainLibrary);
		
		Wait(2);
		
		ClickOnButton(Report315Link);
		
		Wait(3);
	}
	
	
	public void Logout() {
		ClickOnButton(Logoutbutton);
	}

}
