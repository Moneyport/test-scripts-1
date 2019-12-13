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

public class TransfersValidationTests extends BaseClass {
	
	
/********************TRANSFERS VALIDATION TEST CASES***************************************/
	
	// TEST CASE ID: MOWDEV-T66
	//LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T66
  	@Test
	public void Test_Transaction_Validation_Invalid_TransferID() {
		
			LoginPage login= PageFactory.initElements(driver, LoginPage.class);
			
			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
			
			HomePage homepage= PageFactory.initElements(driver, HomePage.class);
			
			homepage.AssertPage();
			
			homepage.ClickOnSection("Transfer Verification");
			
			TransferVerificationPage tranpage =  PageFactory.initElements(driver, TransferVerificationPage.class);
			
			tranpage.AssertPage();
			
			SendText(tranpage.transactionidinput, "$c497f4f-54a3-4cd6-bcc4-cfe3589d4785");
			
			tranpage.ClickButton();
			
			Assert.assertFalse(tranpage.IsTrasnferFound());
			
		}
	
  	
    // TEST CASE ID: MOWDEV-T85
 	//LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T85
	@Test
	public void Test_Transaction_Validation_Valid_TransferID() {
		
			LoginPage login= PageFactory.initElements(driver, LoginPage.class);
			
			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
			
			HomePage homepage= PageFactory.initElements(driver, HomePage.class);
			
			homepage.AssertPage();
			
			homepage.ClickOnSection("Transfer Verification");
			
			TransferVerificationPage tranpage =  PageFactory.initElements(driver, TransferVerificationPage.class);
			
			tranpage.AssertPage();
			SendText(tranpage.transactionidinput, PropertiesFile.transferidx);
			
            tranpage.ClickButton();
			
			Assert.assertTrue(tranpage.IsTrasnferFound());
			
		}
	
	
	// TEST CASE ID: MOWDEV-T86
	//LINK: https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T86
	@Test
	public void Test_Transaction_Validation_Invalid_characters() {
		
			LoginPage login= PageFactory.initElements(driver, LoginPage.class);
			
			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
			
			HomePage homepage= PageFactory.initElements(driver, HomePage.class);
			
			homepage.AssertPage();
			
			homepage.ClickOnSection("Transfer Verification");
			
			TransferVerificationPage tranpage =  PageFactory.initElements(driver, TransferVerificationPage.class);
			
			tranpage.AssertPage();
			SendText(tranpage.transactionidinput, "#$%$%$%");
			
            tranpage.ClickButton();
			
			Assert.assertFalse(tranpage.IsTrasnferFound());
			
		}
}
