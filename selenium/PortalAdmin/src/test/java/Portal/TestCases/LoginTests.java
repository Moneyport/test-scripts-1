package Portal.TestCases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.*;

import PortalPages.BaseClass;
import PortalPages.FinancialMonitoringPage;
import Portal.Utilities.BrowserFactory;
import Portal.Utilities.PropertiesFile;
import PortalPages.LoginPage;
import PortalPages.HomePage;
import PortalPages.SettlementWindowsPage;
import PortalPages.SettlementWindowsPage.WindowDetailButtons;
import PortalPages.TransferVerificationPage;
import PortalPages.AdministrationPage;
import PortalPages.AdministrationPage.NotificationEmail;
import org.testng.Reporter;
import org.testng.reporters.XMLReporter;
import org.testng.ITestResult;
//import org.exp.annotations.Xray;

public class LoginTests extends BaseClass {
	
	
/********************LOGIN TEST CASES
 * @throws InterruptedException ***************************************/
	
	
	//TEST CASE ID: MOWDEV-T25
	//LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T25
	
	@Test(description="MOWDEV-T25")
	public void Test_Successful_Login_as_Admin() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
		
		Logout();
		
		login.AssertPage();
		
		      
	}
   
  
	//TEST CASE ID: MOWDEV-T25
	//LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T25
	
   @Test
	public void Test_Successful_Login_as_User() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
        
		Logout();
		
		login.AssertPage();
	}
   
   
    //TEST CASE ID: MOWDEV-T88 
 	//LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T88
 	
   @Test
	public void Test_Bad_Login_Invalid_User() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal("notvalid", PropertiesFile.passwordAdmin);
				
		PopUpHandler("OK");
		
		login.AssertPage();
	}
   
   
   //TEST CASE ID: MOWDEV-T89 
   //LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T89
	
   @Test
	public void Test_Bad_Login_Invalid_Password() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.usernameAdmin, "*1223456");

		PopUpHandler("OK");
		
		login.AssertPage();
	}
   
   
   
   
   //TEST CASE ID: MOWDEV-T127 
   //LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T127
	
   @Test
  	public void Test_Bad_Login_Invalid_Password_Extra_blankspaces() {
  	
  		
  		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
  		
  		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin + "            ");

  		PopUpHandler("OK");
  		
  		login.AssertPage();
  	}
   
   
   
   //TEST CASE ID: MOWDEV-T96
   //LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T96
	 
   @Test
 	public void Test_Bad_Login_Blank_username() {
 	
 		
 		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
 		
 		login.logintoPortal("", PropertiesFile.passwordAdmin);

 		PopUpHandler("OK");
 		
 		login.AssertPage();
 	}
   
   
   
   //TEST CASE ID: MOWDEV-T96
   //LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T96
   @Test
	public void Test_Bad_Login_Blank_password() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.usernameAdmin, "");

		PopUpHandler("OK");
		
		login.AssertPage();
	}
   
   
   //TEST CASE ID: MOWDEV-T96
   //LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T96
	
   @Test
	public void Test_Bad_Login_Blank_username_and_password() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal("", "");

		PopUpHandler("OK");
		
		login.AssertPage();
	}

   
	
	// TEST CASE ID: MOWDEV-T106
	
	
	@Test(expectedExceptions = {org.openqa.selenium.NoSuchElementException.class})
	public void Test_Using_The_Back_Button_In_Web_Browser_After_Using_Log_out_button() throws InterruptedException {
	
	
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
       
		Logout();
		
		Thread.sleep(3000);
		
		login.AssertPage();
		
		driver.navigate().back();
		
		login.AssertPage();
		
		
		
	}
	
	
	
	@Test(description="MOWDEV-T168")
	public void Test_Successful_Login_as_Automated_Admin() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		
		login.logintoPortal(PropertiesFile.AutousernameAdmin, PropertiesFile.AutopasswordAdmin);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
		
		Logout();
		
		login.AssertPage();
		
		      
	}
   
	
	
	@Test(description="MOWDEV-T168")
	public void Test_Successful_Login_as_Automated_Non_Admin() {
	
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		
		login.logintoPortal(PropertiesFile.AutousernameUser, PropertiesFile.AutopasswordUser);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
		
		Logout();
		
		login.AssertPage();
		
		      
	}
   
	
	
  
 	
}
