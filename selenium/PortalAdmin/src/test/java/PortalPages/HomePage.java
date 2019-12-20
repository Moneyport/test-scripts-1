package PortalPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage {
	
	WebDriver driver;
	
	
	public HomePage(WebDriver ldriver) {
		this.driver=ldriver;
	}
	
	@FindBy(xpath="//*[@id=\"root\"]/div/header/div/div/div/div") WebElement MainMenu;
	@FindBy(xpath="//*[contains(text(), 'Financial Monitoring')]") WebElement FinancialM;
	@FindBy(xpath="//*[contains(text(), 'Settlement Windows')]") WebElement SettlementW;
	@FindBy(xpath="//*[contains(text(), 'Administration')]") WebElement Admin;
	@FindBy(xpath="//*[contains(text(), 'Transfer Verification')]") WebElement TransferV;
	
	
	public void AssertPage() {
		
		Assert.assertEquals(true, MainMenu.isDisplayed());
		Assert.assertEquals(true, FinancialM.isDisplayed());
		Assert.assertEquals(true, SettlementW.isDisplayed());
		Assert.assertEquals(true, Admin.isDisplayed());
		Assert.assertEquals(true, TransferV.isDisplayed());
		
	}
	
	public void ClickOnSection(String section) {
		
		if(section.equals("Financial Monitoring")) {
			
			FinancialM.click();
			
			
		}else if (section.equals("Settlement Windows")) {
			SettlementW.click();
			
		}else if (section.equals("Administration")) {
			Admin.click();
			
		}else if (section.equals("Transfer Verification")) {
			TransferV.click();
			
		}else {
			System.out.println("Section not found");
		}
		
	}

}
