package Portal.TestCases;

import java.io.IOException;
import java.util.NoSuchElementException;

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

public class FinancialMonitoringTests extends BaseClass {
	public static double balanceinitial, balancefinal;
	public static float balanceinitialx, balancefinalx;
	public static String portalvalue, apivalue, ndcpercentage, Requeststatus;
	public static int numberbeforesettlement, numberaftersettlement;

	/********************
	 * FINANCIAL MONITORING TEST CASES
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 ***************************************/

	@Test
	public void Test_Financial_Monitor() {

  
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");
	}

	// TEST CASE ID: MOWDEV-T1
	// LINK:
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T1
	@Test
	public void Test_Financial_Monitor_Check_Current_Window() {

  
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("payeefsp");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");
	}

	// TEST CASE ID: MOWDEV-T76
	// LINK:
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T76
	@Test
	public void Test_Financial_Monitor_Check_Window_History() {

  
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("payeefsp");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Window History");

		fmpage.CheckOptionsDisplayed("Window History");
	}

	@Test
	public void Test_Financial_Monitor_Check_Financial_Controls_Funds_In() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		balanceinitialx = fmpage.GetBalanceFunds("XOF");

		fmpage.DoFundsIn("1500","XOF");

		balancefinalx = fmpage.GetBalanceFunds("XOF");

		Assert.assertEquals(balancefinalx, 1500 + balanceinitialx);

	}

  
	// TEST CASE ID: MOWDEV-T112
	@Test
	public void Test_Financial_Monitor_Financial_Controls_Update_NDC_User() throws InterruptedException {

   
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		balanceinitial = fmpage.GetBalanceNDC("XOF");

		fmpage.UpdateNDC("XOF","9000");

		PopUpHandler("OK");

		balancefinal = fmpage.GetBalanceNDC("XOF");

		Assert.assertEquals(balanceinitial, balancefinal);
	}

	@Test
	public void Test_Financial_Monitor_Financial_Controls_DFSP_stop_transfers() throws IOException {
		String QuoteStatus, TransferStatus;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");
		fmpage.ClickOnSwitchStopTransfers();
		fmpage.AssertSwitchChanged();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "FAILED");
		fmpage.ClickOnSwitchStopTransfers();
		fmpage.AssertSwitchChanged();
		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");
	}

	// TEST CASE ID: MOWDEV-T111
	// LINK:
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T111
	@Test
	public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC1() throws InterruptedException {
        String valz;
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		fmpage.UpdateNDC("XOF","25000");

		valz = String.valueOf(fmpage.GetBalanceNDC("XOF"));
		
		Assert.assertEquals("25000.0", valz);
   }
	
							  

	@Test
	   
	public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC2() {
  
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
  
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
			  
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		
		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("payerfsp");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

	}

	@Test
	public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC3() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("payeefsp");
	   
	   

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");
																		 
																		   

		fmpage.CheckOptionsDisplayed("Financial Controls");

	}

	@Test
	public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC4() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp2");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

	}

	@Test
	public void Test_Financial_Monitor_Check_Only_Active_Positions_Accounts() throws Exception {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();
								  
																																													   

		homepage.ClickOnSection("Financial Monitoring");
												 

		
		Requeststatus = InactiveDFSPAccount("testfsp4", PropertiesFile.testfsp4accountid ,false);
		
		Assert.assertEquals(Requeststatus, "OK");
		

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp4");

		fmpage.CheckAvialableOptionsDfsp();

		ScrollDown();
		

		fmpage.CheckPositionAccounts(4);
		
        Requeststatus = InactiveDFSPAccount("testfsp4", PropertiesFile.testfsp4accountid ,true);
		
		Assert.assertEquals(Requeststatus, "OK");
		
		RefreshPage();
		
		fmpage.ClickOnDFSP("testfsp4");

		fmpage.CheckAvialableOptionsDfsp();

		ScrollDown();
		

		fmpage.CheckPositionAccounts(5);
		

	}
	// TEST CASE ID: MOWDEV-T2
	// LINK:https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T2

	@Test
	public void Test_Financial_Monitor_Check_Financial_Controls_Funds_Out()

	{

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
								 
																																													  

		fmpage.ClickOnDFSP("testfsp1");
			
												  

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		balanceinitialx = fmpage.GetBalanceFunds("XOF");

		fmpage.DoFundsOut("500", "XOF");

		fmpage.alertHandler("Cancel");

		balancefinalx = fmpage.GetBalanceFunds("XOF");

		Assert.assertEquals(balancefinalx, balanceinitialx);

		fmpage.selectApply();

		fmpage.alertHandler("OK");

		balancefinalx = fmpage.GetBalanceFunds("XOF");

		Assert.assertEquals(balancefinalx, balanceinitialx - 500);

	}

	//// TEST CASE ID: MOWDEV-T122
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T122

				@Test
				public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC_as_User() throws InterruptedException {
						  
						 
						  LoginPage login= PageFactory.initElements(driver, LoginPage.class);
						  
						  login.logintoPortal(PropertiesFile.usernameUser,
						  PropertiesFile.passwordUser);
						  
						  HomePage homepage= PageFactory.initElements(driver, HomePage.class);
						 
						
						  homepage.AssertPage();
						  
						  homepage.ClickOnSection("Financial Monitoring");
						  
						  FinancialMonitoringPage fmpage= PageFactory.initElements(driver,FinancialMonitoringPage.class);
						  
						  fmpage.ClickOnDFSP("testfsp1");
						  
						  fmpage.CheckAvialableOptionsDfsp();
						  
						  fmpage.ClickOptionDfsp("Financial Controls");
						  
						  fmpage.CheckOptionsDisplayed("Financial Controls");
						  
						  fmpage.UpdateNDC("XOF","00");
						  
						  Thread.sleep(2000);
						  
						  fmpage.verifyPopUpNDC();
						  
						  Thread.sleep(5000);
						  
				  }

	// TEST CASE ID: MOWDEV-T90
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T90

	@Test

	public void Test_Disable_Transactions_Hub() throws InterruptedException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver,

				FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("Hub");

		fmpage.CheckAvialableOptionsDfsp();

		Thread.sleep(3000);

		fmpage.ClickOptionDfsp("Financial Controls");

	fmpage.click_On_Disable_Transactions();

	}

	// TEST CASE ID: MOWDEV-MOWDEV-T9
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T9

	@Test
	public void Test_funds_In_Decimal_Value() throws InterruptedException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		balanceinitial = fmpage.GetBalanceFunds("XOF");

		Thread.sleep(3000);

		fmpage.DoFundsIn("500.50","XOF");

		Thread.sleep(3000);

		balancefinal = fmpage.GetBalanceFunds("XOF");

		System.out.println(balancefinal);

		Assert.assertEquals(balancefinal, balanceinitial + 500.50);

	}

	@Test
	public void Test_funds_Out_Decimal_Value() throws InterruptedException {
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		balanceinitial = fmpage.GetBalanceFunds("XOF");

		Thread.sleep(3000);

		fmpage.DoFundsOut("500.50", "XOF");

		fmpage.alertHandler("OK");

		Thread.sleep(3000);

		balancefinal = fmpage.GetBalanceFunds("XOF");

		System.out.println(balancefinal);

		Assert.assertEquals(balancefinal, balanceinitial - 500.50);

	}
	// TEST CASE ID: MOWDEV-T10
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T10

	@Test
	public void Test_fundsIn_Special_Characters() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		fmpage.DoFundsIn_SpecialCharacters("£$£%£%","XOF");

	}

	// TEST CASE ID: MOWDEV-T5
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T5

	@Test
	public void Test_fundsOut_Special_Characters() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		fmpage.DoFundsOut_Special_Characters("£$£%£%");

	}

	@Test
	public void Test_verify_financial_Controls__NDC_Columns() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		fmpage.NDC_table_Labels();
	}

	@Test
	public void Test_verify_Outgoing_And_Incoming_Sub_Fields() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		fmpage.verify_CurrentWindow_Sub_Fields();

	}

	@Test
	public void Test_verify_Accounts_Sub_Fields() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		fmpage.verify_Accounts_Sub_Fields();

	}

	@Test
	public void Test_verify_Position_Sub_Fields() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		fmpage.verify_Position_Sub_Fields();

	}

	// TEST CASE ID: MOWDEV-T11
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T11

	@Test
	public void Test_fundsIn_verify_Zero_value() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		fmpage.doFundsIn_Verify_Zero_Input("0");

		fmpage.alertHandler("Cancel");

	}

	// TEST CASE ID: MOWDEV-T6 //
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T6

	@Test
	public void Test_fundsOut_verify_Zero_value() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Financial Controls");

		fmpage.CheckOptionsDisplayed("Financial Controls");

		ScrollDown();

		fmpage.doFundsOut_Verify_Zero_Input("0");

		fmpage.alertHandler("Cancel");

	}

	// TEST CASE ID: MOWDEV-T60
	
	@Test
	public void Test_Accounts_Settlement() throws IOException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		portalvalue =  fmpage.accounts_Balance("XOF");
		
		System.out.println(portalvalue);
		
		apivalue = GetParticipantAccounts("testfsp1", "SETTLEMENT",  "XOF", "balance");
		
		System.out.println(apivalue);
		
		Assert.assertEquals(portalvalue, apivalue);

	}

	// TEST CASE ID: MOWDEV-T61
	
	@Test
	public void Test_Accounts_Position() throws IOException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		portalvalue = fmpage.accounts_Position("XOF","Current Window");
		
		System.out.println(portalvalue);
		
		apivalue = GetParticipantAccounts("testfsp1", "POSITION",  "XOF", "balance");
		
		System.out.println(apivalue);
		 
		Assert.assertEquals(portalvalue, apivalue);
	
	}

	@Test
    public void Financial_Monitoring_Previous_Window() throws IOException {
        
            String numberoftxns,totalamount;
            
            LoginPage login= PageFactory.initElements(driver, LoginPage.class);
            
            login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
            
            HomePage homepage= PageFactory.initElements(driver, HomePage.class);
            
            homepage.AssertPage();
            
            homepage.ClickOnSection("Settlement Windows");
            
            SettlementWindowsPage setwinpage =  PageFactory.initElements(driver, SettlementWindowsPage.class);
            
            //setwinpage.AssertPage();
            
            setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));
            
            
           setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
            
            RefreshPage();
            
            SendTransfer("testfsp1","testfsp2", "10",PropertiesFile.testfsp1token, "COMMITTED");
            
            SendTransfer("testfsp1","testfsp4", "5",PropertiesFile.testfsp1token, "COMMITTED");
            
            homepage.ClickOnSection("Settlement Windows");
            
            setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));
            
            
            setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
                
            RefreshPage();
             
            homepage.ClickOnSection("Financial Monitoring");
            FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
            fmpage.ClickOnDFSP("testfsp1");
            fmpage.CheckAvialableOptionsDfsp();
            fmpage.ClickOptionDfsp("Window History");
            
            numberoftxns = fmpage.GetOutgoingTransactionsNumberTransfers("XOF");
            
            totalamount = fmpage.GetOutgoingTransactionsTotalAmount("XOF");
            
            Assert.assertEquals(numberoftxns, "2");
            
            Assert.assertEquals(totalamount, "15");
            
    }
									 
	 
	@Test
	public void settlement_History_For_Fsp() throws Exception {

		String windowid_pendingsettlement;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		fmpage.FromDateCurrentWindow(Getdate(0));
		
	//	PopUpHandler("OK");
		
		Thread.sleep(2000);

		fmpage.ToDateCurrentWindow(Getdate(2));
		
	//	PopUpHandler("OK");

		Thread.sleep(2000);

		numberbeforesettlement = fmpage.verify_Settlement_History_For_Fsp();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		//setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		windowid_pendingsettlement = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Thread.sleep(3000);
		
		

		RefreshPage();

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		fmpage.FromDateCurrentWindow(Getdate(0));
		
		Thread.sleep(2000);
		
		//PopUpHandler("OK");

		fmpage.ToDateCurrentWindow(Getdate(2));

		Thread.sleep(2000);
		
		//PopUpHandler("OK");

		numberaftersettlement = fmpage.verify_Settlement_History_For_Fsp();
		
		Assert.assertEquals(numberaftersettlement, numberbeforesettlement + 1);

	}

	
	  @Test public void currentSettlement_Using_One_CurrencyType_Outgoing_And_Incoming() throws
	  IOException, InterruptedException {
	  
	  LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	  
	  login.logintoPortal(PropertiesFile.usernameAdmin,
	  PropertiesFile.passwordAdmin);
	  
	  HomePage homepage = PageFactory.initElements(driver, HomePage.class);
	  
	  homepage.AssertPage();
  
	  homepage.ClickOnSection("Settlement Windows");
	  
	  SettlementWindowsPage setwinpage = PageFactory.initElements(driver,
	  SettlementWindowsPage.class);
	  
	  setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));
	  
	  setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
	  
	  SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");
	  
	  Thread.sleep(3000);
	  
	  homepage.ClickOnSection("Financial Monitoring");
	  
	  FinancialMonitoringPage fmpage = PageFactory.initElements(driver,
	  FinancialMonitoringPage.class);
	  
	  fmpage.ClickOnDFSP("testfsp1");
	  
	  fmpage.CheckAvialableOptionsDfsp();
	  
	  fmpage.ClickOptionDfsp("Current Window");
	  
	  fmpage.verify_Outgoing_Transactions_CurrentWindow();
	  
	  homepage.ClickOnSection("Settlement Windows");
	 
	  homepage.ClickOnSection("Financial Monitoring");
	  
	  fmpage.ClickOnDFSP("testfsp2");
	  
	  fmpage.CheckAvialableOptionsDfsp();
	  
	  fmpage.ClickOptionDfsp("Current Window");
	  
	  Thread.sleep(2000);
	  
	  fmpage.verify_Incoming_Transactions_CurrentWindow();
	  
	  }

	// TEST CASE ID: MOWDEV- T79
	
	@Test
	public void Test_NDC_Average_Percentage() throws InterruptedException {
		
			
			String ndcvalue;
			
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		
			
			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
			
			
			HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		
			homepage.AssertPage();
		
			homepage.ClickOnSection("Financial Monitoring");
		
			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
		
			fmpage.ClickOnDFSP("payerfsp");
		
			fmpage.CheckAvialableOptionsDfsp();
		
			fmpage.ClickOptionDfsp("Financial Controls");
			
			fmpage.CheckOptionsDisplayed("Financial Controls");
			
			ndcvalue = fmpage.verify_NDC_Average_Percentage("XOF");
			
			fmpage.UpdateNDC("XOF",ndcvalue);
			
			Thread.sleep(3000);
			
			homepage.ClickOnSection("Settlement Windows");
			
			homepage.ClickOnSection("Financial Monitoring");
			
			fmpage.ClickOnDFSP("payerfsp");
			
			fmpage.CheckAvialableOptionsDfsp();
			
			fmpage.ClickOptionDfsp("Current Window");
			
			ScrollDown();
			
			ndcpercentage =fmpage.Ndc_Average_Percentage("XOF");
			
			Assert.assertEquals(ndcpercentage, "100.00%");
			
			homepage.ClickOnSection("Settlement Windows");
			
			homepage.ClickOnSection("Financial Monitoring");
			
			fmpage.ClickOnDFSP("payerfsp");
			
			fmpage.CheckAvialableOptionsDfsp();
			
			fmpage.ClickOptionDfsp("Financial Controls");
			
			fmpage.CheckOptionsDisplayed("Financial Controls");
			
			fmpage.UpdateNDC("XOF","50000");
	
	}

	
	
	
	// TEST CASE ID: MOWDEV-T64
	@Test
	public void Test_previous_Window_TranscationAverage() throws Exception {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();
		homepage.ClickOnSection("Financial Monitoring");
		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
		fmpage.ClickOnDFSP("testfsp1");
		fmpage.CheckAvialableOptionsDfsp();
		fmpage.ClickOptionDfsp("Window History");
		ScrollDown();
		Wait(3);
		fmpage.FromDateCurrentWindow("2018-10-10");
		fmpage.ToDateCurrentWindow("2019-10-11");
		Thread.sleep(3000);
		fmpage.assert_PreviousWindow_TransactionAverage();
	
	
	}

	
	// TEST CASE ID: MOWDEV-T92
	
	@Test
	public void Test_hub_CurrentWindow_Page() {
	
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
	
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
	
		homepage.AssertPage();
	
		homepage.ClickOnSection("Financial Monitoring");
	
		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
	
		fmpage.ClickOnDFSP("Hub");
	
		fmpage.ClickOptionDfsp("Current Window");
		
		
}
	
	// TEST CASE ID: MOWDEV-T93
	@Test
	public void Test_hub_WindowHistory_Page() {
	
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
	
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
	
		homepage.AssertPage();
	
		homepage.ClickOnSection("Financial Monitoring");
	
		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
	
		fmpage.ClickOnDFSP("Hub");
	
		fmpage.ClickOptionDfsp("Window History");
		
		
}
	
	// TEST CASE ID: MOWDEV-T102
	@Test(expectedExceptions = {org.openqa.selenium.NoSuchElementException.class})
	public void Test_hub_FinancialControl_Page() {
	
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	
		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
	
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
	
		homepage.AssertPage();
	
		homepage.ClickOnSection("Financial Monitoring");
	
		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
	
		fmpage.ClickOnDFSP("Hub");
	
		fmpage.ClickOptionDfsp("Financial Controls");
		
		fmpage.assert_Hub_Stop_Functionality_Displayed();
		
		fmpage.validate_Hub_FinancialControl_NoDataDisplayed();
		
		
		
		
		
}

		// TEST CASE ID: MOWDEV-T103
		@Test
		public void Test_tabs_Available_On_Hub() {
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		
			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
		
			HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		
			homepage.AssertPage();
		
			homepage.ClickOnSection("Financial Monitoring");
		
			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
		
			fmpage.ClickOnDFSP("Hub");
		
			fmpage.ClickOptionDfsp("Financial Controls");	
	
			fmpage.CheckAvialableOptionsDfsp();
			
			fmpage.assert_Hub_Stop_Functionality_Displayed();
	

		}	
		
		
	// TEST CASE ID: MOWDEV-T104
		
		@Test 
	
		public void Test_current_SettlementWindow_Using_Multiple_DFSPs_in_One_window() throws
		  IOException, InterruptedException {
		  
		  LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		  
		  login.logintoPortal(PropertiesFile.usernameAdmin,
		  PropertiesFile.passwordAdmin);
		  
		  HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		  
		  homepage.AssertPage();
	  
		  homepage.ClickOnSection("Settlement Windows");
		  
		  SettlementWindowsPage setwinpage = PageFactory.initElements(driver,
		  SettlementWindowsPage.class);
		  
		  setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));
		  
		  setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		  
		  RefreshPage();
		  
		  SendTransfer("testfsp1", "testfsp2", "60", PropertiesFile.testfsp1token, "COMMITTED");
		  
		  SendTransfer("testfsp1", "testfsp4", "55", PropertiesFile.testfsp1token, "COMMITTED");

		  SendTransfer("testfsp3", "testfsp1", "35", PropertiesFile.testfsp3token, "COMMITTED");
		  
		  Thread.sleep(3000);
		  
		  homepage.ClickOnSection("Financial Monitoring");
		  
		  FinancialMonitoringPage fmpage = PageFactory.initElements(driver,
		  FinancialMonitoringPage.class);
		  
		  fmpage.ClickOnDFSP("testfsp1");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.ClickOptionDfsp("Current Window");
		  
		  fmpage.verify_Outgoing_Transactions_CurrentWindow();
		  
		  homepage.ClickOnSection("Settlement Windows");
		 
		  homepage.ClickOnSection("Financial Monitoring");
		  
		  fmpage.ClickOnDFSP("testfsp2");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.ClickOptionDfsp("Current Window");
		  
		  Thread.sleep(2000);
		  
		  fmpage.verify_Incoming_Transactions_CurrentWindow_TestFsp2();;
		  
		  homepage.ClickOnSection("Settlement Windows");
		  
		  homepage.ClickOnSection("Financial Monitoring");
		  
		  fmpage.ClickOnDFSP("testfsp4");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.verify_Incoming_Transactions_CurrentWindow_TestFsp4();
		  
		  homepage.ClickOnSection("Settlement Windows");
		  
		  homepage.ClickOnSection("Financial Monitoring");
		  
		  fmpage.ClickOnDFSP("testfsp3");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.verify_Outgoing_Transactions_CurrentWindow_TestFsp3();
		  
		  homepage.ClickOnSection("Settlement Windows");
		  
		  homepage.ClickOnSection("Financial Monitoring");
		  
		  fmpage.ClickOnDFSP("testfsp1");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.verify_Incoming_Transactions_CurrentWindow_TestFsp1();
		  
		  
		  }	
	
		// TEST CASE ID: MOWDEV-T105
		
		@Test 
		
		public void Test_Window_History_Using_Multiple_DFSPs_in_One_window() throws
		  IOException, InterruptedException {
		  
			String numberoftxns, totalamount;
		  LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		  
		  login.logintoPortal(PropertiesFile.usernameAdmin,
		  PropertiesFile.passwordAdmin);
		  
		  HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		  
		  homepage.AssertPage();
	  
		  homepage.ClickOnSection("Settlement Windows");
		  
		  SettlementWindowsPage setwinpage = PageFactory.initElements(driver,
		  SettlementWindowsPage.class);
		  
		  setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));
		  
		  setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		  
		  RefreshPage();
		  
		  SendTransfer("testfsp1", "testfsp2", "60", PropertiesFile.testfsp1token, "COMMITTED");
		  
		  SendTransfer("testfsp1", "testfsp4", "55", PropertiesFile.testfsp1token, "COMMITTED");

		  SendTransfer("testfsp3", "testfsp1", "35", PropertiesFile.testfsp3token, "COMMITTED");
		  
		  RefreshPage();
		  
		  Thread.sleep(3000);
		  
          homepage.ClickOnSection("Settlement Windows");
		  
		  setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));
		  
		  setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		  
		  RefreshPage();
		  
		  homepage.ClickOnSection("Financial Monitoring");
		  
		  FinancialMonitoringPage fmpage = PageFactory.initElements(driver,
		  FinancialMonitoringPage.class);
		  
		  fmpage.ClickOnDFSP("testfsp1");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.ClickOptionDfsp("Window History");
		  
		  //fmpage.verify_Outgoing_Transactions_WindowHistory_TestFsp1();
		  
		  numberoftxns = fmpage.GetOutgoingTransactionsNumberTransfers("XOF");
          
          totalamount = fmpage.GetOutgoingTransactionsTotalAmount("XOF");
          
          Assert.assertEquals(numberoftxns, "2");
          
          Assert.assertEquals(totalamount, "115");
		  
		  RefreshPage();
		  
		  fmpage.ClickOnDFSP("testfsp2");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  fmpage.ClickOptionDfsp("Window History");
		  
		  Thread.sleep(2000);
	  
		 // fmpage.verify_Incoming_Transactions_WindowHistory_TestFsp2();
		  
          numberoftxns = fmpage.GetIncommingTransactionsNumberTransfers("XOF");
          
          totalamount = fmpage.GetIncommingTransactionsTotalAmount("XOF");
          
          Assert.assertEquals(numberoftxns, "1");
          
          Assert.assertEquals(totalamount, "60");
          
		  
		  RefreshPage();
		  
		  fmpage.ClickOnDFSP("testfsp4");
		  
		  fmpage.ClickOptionDfsp("Window History");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		 // fmpage.verify_Incoming_Transactions_WindowHistory_TestFsp4();
		  
          numberoftxns = fmpage.GetIncommingTransactionsNumberTransfers("XOF");
          
          totalamount = fmpage.GetIncommingTransactionsTotalAmount("XOF");
          
          Assert.assertEquals(numberoftxns, "1");
          
          Assert.assertEquals(totalamount, "55");
		  
		  RefreshPage();
		  
		  fmpage.ClickOnDFSP("testfsp3");
		  
		  fmpage.ClickOptionDfsp("Window History");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  //fmpage.verify_Outgoing_Transactions_WindowHistory_TestFsp3();
          numberoftxns = fmpage.GetOutgoingTransactionsNumberTransfers("XOF");
          
          totalamount = fmpage.GetOutgoingTransactionsTotalAmount("XOF");
          
          Assert.assertEquals(numberoftxns, "1");
          
          Assert.assertEquals(totalamount, "35");
		  
		  RefreshPage();
		  
		  fmpage.ClickOnDFSP("testfsp1");
		  
		  fmpage.ClickOptionDfsp("Window History");
		  
		  fmpage.CheckAvialableOptionsDfsp();
		  
		  //fmpage.verify_Incoming_Transactions_WindowHistory_TestFsp1();
		  
         numberoftxns = fmpage.GetIncommingTransactionsNumberTransfers("XOF");
          
          totalamount = fmpage.GetIncommingTransactionsTotalAmount("XOF");
          
          Assert.assertEquals(numberoftxns, "1");
          
          Assert.assertEquals(totalamount, "35");
		    
		  }	
	
	
	
	
	
	// TEST CASE ID: MOWDEV-T108
	
	@Test
	public void Test_settlement_History_Choose_Invalid_Dates_In_Date_Pickers() throws Exception {
		

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		fmpage.FromDateCurrentWindow("2019-10-12");
		
		Thread.sleep(3000);

		fmpage.ToDateCurrentWindow("2019-10-10");
		
		fmpage.verify_No_SettlementFound();
	}
	
	
		
		// TEST CASE ID: MOWDEV-T18
		
		@Test
		public void Test_financialControl_NDC_Currency() {
		
			
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		        float balan;
				login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);
		
				HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		
				homepage.AssertPage();
		
				homepage.ClickOnSection("Financial Monitoring");
		
				FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
		
				fmpage.ClickOnDFSP("payerfsp");
		
				fmpage.CheckAvialableOptionsDfsp();
		
				fmpage.ClickOptionDfsp("Financial Controls");	
				
				fmpage.CheckAvialableOptionsDfsp();
				
				//fmpage.verify_FinancialControl_Ndc_Currency();
				balan = fmpage.GetBalanceNDC("XOF");
				
				Assert.assertNotNull(balan);
	}
	
	
		
		
		//TEST CASE ID: MOWDEV-T124
		@Test
		public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC_special_chars() throws InterruptedException {
				  
				 
				  LoginPage login= PageFactory.initElements(driver, LoginPage.class);
				  
				  login.logintoPortal(PropertiesFile.usernameAdmin,
				  PropertiesFile.passwordAdmin);
				  
				  HomePage homepage= PageFactory.initElements(driver, HomePage.class);
				 
				
				  homepage.AssertPage();
				  
				  homepage.ClickOnSection("Financial Monitoring");
				  
				  FinancialMonitoringPage fmpage= PageFactory.initElements(driver,FinancialMonitoringPage.class);
				  
				  fmpage.ClickOnDFSP("testfsp1");
				  
				  fmpage.CheckAvialableOptionsDfsp();
				  
				  fmpage.ClickOptionDfsp("Financial Controls");
				  
				  fmpage.CheckOptionsDisplayed("Financial Controls");
				  
				  balanceinitial = fmpage.GetBalanceNDC("XOF");

				  fmpage.UpdateNDC("XOF","ghjklo");
                  
				  Thread.sleep(2000);
				  
				  fmpage.verifyPopUpNDC();
				  
				  Thread.sleep(2000);

				  balancefinal = fmpage.GetBalanceNDC("XOF");

				 Assert.assertEquals(balanceinitial, balancefinal);
				 
				  
		  }
		
		
		//TEST CASE ID: MOWDEV-T114
		@Test
		public void Test_Financial_Monitor_Check_Financial_Controls_Funds_In_User() {

			LoginPage login = PageFactory.initElements(driver, LoginPage.class);

			login.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);

			homepage.AssertPage();

			homepage.ClickOnSection("Financial Monitoring");

			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

			fmpage.ClickOnDFSP("testfsp1");

			fmpage.CheckAvialableOptionsDfsp();

			fmpage.ClickOptionDfsp("Financial Controls");

			fmpage.CheckOptionsDisplayed("Financial Controls");

			ScrollDown();

			balanceinitialx = fmpage.GetBalanceFunds("XOF");

			fmpage.DoFundsIn("100","XOF");

			balancefinalx = fmpage.GetBalanceFunds("XOF");

			Assert.assertEquals(balancefinalx, 100 + balanceinitialx);

		}

		@Test
		public void Test_Financial_Monitor_Check_Financial_Controls_Funds_Out_User()

		{

			LoginPage login = PageFactory.initElements(driver, LoginPage.class);

			login.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);

			homepage.AssertPage();

			homepage.ClickOnSection("Financial Monitoring");

			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);
									 
																																														  

			fmpage.ClickOnDFSP("testfsp1");
				
													  

			fmpage.CheckAvialableOptionsDfsp();

			fmpage.ClickOptionDfsp("Financial Controls");

			fmpage.CheckOptionsDisplayed("Financial Controls");

			ScrollDown();

			balanceinitialx = fmpage.GetBalanceFunds("XOF");

			fmpage.DoFundsOut("100","XOF");

			fmpage.alertHandler("Cancel");

			balancefinalx = fmpage.GetBalanceFunds("XOF");

			Assert.assertEquals(balancefinalx, balanceinitialx);

			fmpage.selectApply();

			fmpage.alertHandler("OK");

			balancefinalx = fmpage.GetBalanceFunds("XOF");

			Assert.assertEquals(balancefinalx, balanceinitialx - 100);

		}
		
		
		//TEST CASE ID: MOWDEV-T123
		@Test
		public void Test_Financial_Monitor_Check_Financial_Controls_Update_NDC_decimals() throws InterruptedException {

			LoginPage login = PageFactory.initElements(driver, LoginPage.class);

			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);

			homepage.AssertPage();

			homepage.ClickOnSection("Financial Monitoring");

			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

			fmpage.ClickOnDFSP("testfsp1");

			fmpage.CheckAvialableOptionsDfsp();

			fmpage.ClickOptionDfsp("Financial Controls");

			fmpage.CheckOptionsDisplayed("Financial Controls");
			
    		fmpage.UpdateNDC("XOF","75000.01");

			balancefinalx = fmpage.GetBalanceNDC("XOF");

			Assert.assertEquals(String.valueOf(balancefinalx), "75000.01");
			
			fmpage.UpdateNDC("XOF","75000");
			
			balancefinalx = fmpage.GetBalanceNDC("XOF");
			
			Assert.assertEquals(String.valueOf(balancefinalx), "75000.0");
			
	   }
		
		
		
		
	//TEST CASE ID: MOWDEV-T169
		@Test
		public void Test_Financial_Monitor_Check_Update_NDC_Auto_Admin() throws InterruptedException {

			String bal; 
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);

			login.logintoPortal(PropertiesFile.AutousernameAdmin, PropertiesFile.AutopasswordAdmin);

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);

			homepage.AssertPage();

			homepage.ClickOnSection("Financial Monitoring");

			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

			fmpage.ClickOnDFSP("payeefsp");
		   
		   
            fmpage.CheckAvialableOptionsDfsp();

			fmpage.ClickOptionDfsp("Financial Controls");
																			 
			fmpage.CheckOptionsDisplayed("Financial Controls");
			
			fmpage.UpdateNDC("XOF","75001");
            
			bal = String.valueOf(fmpage.GetBalanceNDC("XOF"));
			
			Assert.assertEquals(bal, "75001.0");

		}
		
		
		//TEST CASE ID: MOWDEV-T170
		@Test
		public void Test_Financial_Monitor_Check_Update_NDC_Auto_User() throws InterruptedException {

			LoginPage login = PageFactory.initElements(driver, LoginPage.class);

			login.logintoPortal(PropertiesFile.AutousernameUser, PropertiesFile.AutopasswordUser);

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);

			homepage.AssertPage();

			homepage.ClickOnSection("Financial Monitoring");

			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

			fmpage.ClickOnDFSP("payeefsp");
		   
		   
            fmpage.CheckAvialableOptionsDfsp();

			fmpage.ClickOptionDfsp("Financial Controls");
																			 
			fmpage.CheckOptionsDisplayed("Financial Controls");
			
			balanceinitial = fmpage.GetBalanceNDC("XOF");

			fmpage.UpdateNDC("XOF","75002");

			PopUpHandler("OK");

			balancefinal = fmpage.GetBalanceNDC("XOF");

			Assert.assertEquals(balanceinitial, balancefinal);

		}
		
		
		//TEST CASE ID: MOWDEV-T166
		@Test
		public void Review_NDC_Management_participantsID() throws IOException {

			LoginPage login = PageFactory.initElements(driver, LoginPage.class);

			login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);

			homepage.AssertPage();

			homepage.ClickOnSection("Financial Monitoring");

			FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

			fmpage.ClickOnDFSP("testfsp1");

			fmpage.CheckAvialableOptionsDfsp();

			fmpage.ClickOptionDfsp("Financial Controls");

			portalvalue = fmpage.accounts_Position("XOF", "Financial Controls");
			
			System.out.println(portalvalue);
			
			apivalue = GetParticipantAccounts("testfsp1", "POSITION",  "XOF", "id");
			
			System.out.println(apivalue);
			 
			Assert.assertEquals(portalvalue, apivalue);
		
		}
}