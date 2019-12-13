package Jasper.Automation.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.*;

import Jasper.Automation.Pages.BasePage;

import Jasper.Automation.Utilities.BrowserFactory;
import Jasper.Automation.Utilities.PropertiesFile;
import Jasper.Automation.Pages.LoginPage;
import Jasper.Automation.Pages.HomePage;
import Jasper.Automation.Pages.Report311Page;
import Jasper.Automation.Pages.Report312Page;
import Jasper.Automation.Pages.Report315Page;

public class Tests extends BasePage {
	
	
/********************LOGIN TEST CASES***************************************/
	
	
	@Test
	public void Test_Successful_Login() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.username, PropertiesFile.password);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		Wait(2);
		
		homepage.AssertPage();
		
		      
	}
	

	@Test
      public void Test_Bad_Login() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal("theuser", PropertiesFile.password);
		
		Wait(3);
		
		login.AssertPage();
		
		      
	} 
	
	
	
	@Test
	public void Check_Report311() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.username, PropertiesFile.password);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		Wait(2);
		
		homepage.AssertPage();
		
		Wait(3);
		
		homepage.ClickOnReport311();
		
		Report311Page rep311page = PageFactory.initElements(driver, Report311Page.class);
		
		rep311page.AssertPage();
		
		Wait(3);
		
		rep311page.GenerateReport311("2019-04-20T00:00:30", "2019-04-26T23:59:59", "payerfsp");
		
		rep311page.AssertReport311NotEmpty();      
	} 
	
	
	@Test
	public void Check_Report312() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.username, PropertiesFile.password);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		Wait(2);
		
		homepage.AssertPage();
		
		Wait(3);
		
		homepage.ClickOnReport312();
		
		Report312Page rep312page = PageFactory.initElements(driver, Report312Page.class);
		
		rep312page.AssertPage();
		
		Wait(3);
		
		rep312page.GenerateReport312("2019-04-20T00:00:30", "2019-04-26T23:59:59");
		
		rep312page.AssertReport312NotEmpty();      
	}
	
	
	@Test
	public void Check_Report315() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.username, PropertiesFile.password);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		Wait(2);
		
		homepage.AssertPage();
		
		Wait(3);
		
		homepage.ClickOnReport315();
		
		Report315Page rep315page = PageFactory.initElements(driver, Report315Page.class);
		
		rep315page.AssertPage();
		
		Wait(3);
		
		rep315page.GenerateReport315(PropertiesFile.settlementwindowid, PropertiesFile.participantid);
		
		rep315page.AssertReport315NotEmpty();      
	}
	
	

	
}