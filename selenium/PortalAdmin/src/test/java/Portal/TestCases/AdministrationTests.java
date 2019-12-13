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

public class AdministrationTests extends BaseClass {
	
	
/********************ADMINISTRATION TEST CASES
 * @throws InterruptedException ***************************************/
	
	
	@Test
	public void Test_Administration_NDC_Adjustment_email() {
		
			LoginPage login= PageFactory.initElements(driver, LoginPage.class);
			
			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
			
			HomePage homepage= PageFactory.initElements(driver, HomePage.class);
			
			homepage.AssertPage();
			
			homepage.ClickOnSection("Administration");
			
			AdministrationPage adminpage =  PageFactory.initElements(driver, AdministrationPage.class);
			
			adminpage.AssertPage();
			
			adminpage.ClickOnDFSP("testfsp1");
			
			adminpage.ClickOnDFSP("testfsp2");
			
			adminpage.ClickOnDFSP("testfsp1");
			
			adminpage.EnterNewEmail(NotificationEmail.NET_DEBIT_CAP_ADJUSTMENT_EMAIL, "emerson.pereira@modusbox.com");
			
			Assert.assertEquals(adminpage.GetCurrentEmail(NotificationEmail.NET_DEBIT_CAP_ADJUSTMENT_EMAIL), "emerson.pereira@modusbox.com");
			
		}
	
	@Test
	public void Test_Administration_NDC_Threshold_breach_email() {
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
		
		homepage.ClickOnSection("Administration");
		
		AdministrationPage adminpage =  PageFactory.initElements(driver, AdministrationPage.class);
		
		adminpage.AssertPage();
		
		adminpage.ClickOnDFSP("payerfsp");
		
		adminpage.EnterNewEmail(NotificationEmail.NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL, "emerson.pereira@modusbox.com");
		
		Assert.assertEquals(adminpage.GetCurrentEmail(NotificationEmail.NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL), "emerson.pereira@modusbox.com");
		
	}
	
	  @Test	
      public void Test_Administration_Settlement_position_change() {
		
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
		
		HomePage homepage= PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();
		
		homepage.ClickOnSection("Administration");
		
		AdministrationPage adminpage =  PageFactory.initElements(driver, AdministrationPage.class);
		
		adminpage.AssertPage();
		
		adminpage.ClickOnDFSP("payerfsp");
		
		adminpage.EnterNewEmail(NotificationEmail.SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL, "emerson.pereira@modusbox.com");
		
		Assert.assertEquals(adminpage.GetCurrentEmail(NotificationEmail.SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL), "emerson.pereira@modusbox.com");
		
	}

	
	// TEST CASE ID: MOWDEV-T81
	
	  	@Test
		public void Test_validate_the_Email_Format_In_Notifications() throws InterruptedException {
			
				LoginPage login= PageFactory.initElements(driver, LoginPage.class);
				
				login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
				
				HomePage homepage= PageFactory.initElements(driver, HomePage.class);
				
				homepage.AssertPage();
				
				homepage.ClickOnSection("Administration");
				
				AdministrationPage adminpage =  PageFactory.initElements(driver, AdministrationPage.class);
				
				adminpage.AssertPage();
				
				adminpage.ClickOnDFSP("payerfsp");
				
				adminpage.EnterNewEmail(NotificationEmail.NET_DEBIT_CAP_ADJUSTMENT_EMAIL, "email");
				
				PopUpHandler("OK");
				
				Thread.sleep(2000);
				
				adminpage.EnterNewEmail(NotificationEmail.NET_DEBIT_CAP_ADJUSTMENT_EMAIL, "");
				
				PopUpHandler("OK");
				
				adminpage.EnterNewEmail(NotificationEmail.NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL, "email");
				
				PopUpHandler("OK");
				
				adminpage.EnterNewEmail(NotificationEmail.NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL, "");
			
				PopUpHandler("OK");
	  	
				adminpage.EnterNewEmail(NotificationEmail.SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL, "email");
	  	
				PopUpHandler("OK");
	  	
				adminpage.EnterNewEmail(NotificationEmail.SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL, "");
				
				PopUpHandler("OK");
	  	
	  	}
			
	  
	
}
