package Portal.TestCases;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
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

public class SettlementWindowsTests extends BaseClass {

	public static float settlementbalanceinitial1, settlementbalancefinal1, settlementbalanceinitial2,
			settlementbalancefinal2;
	public static float settlementbalanceinitial3, settlementbalancefinal3, settlementbalanceinitial4,
			settlementbalancefinal4;

	/********************
	 * SETTLEMENT TEST CASES
	 * 
	 * @throws InterruptedException
	 ***************************************/

	@Test
	public void Test_Settlement_Window_paymentfile_EndtoEndID_Lenght()
			throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		//setwinpage.AssertPage();

		SendTransfer("testfsp3", "testfsp4", "1", PropertiesFile.testfsp3token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		setwinpage.AssertLengthEndtoEndID(setwinpage.ReadPaymentMatrixEndtoEndID(lastFileModified()));

		Assert.assertEquals(true, isFileDownloaded(lastFileModified()));

	}

	
	@Test
	public void Test_Settlement_Window_Check_PaymentFile_Not_Empty()
			throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");
		Wait(1);

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		// SendTransfer("payerfsp","payeefsp", "1",PropertiesFile.bearertoken,
		// "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		IsFileEmpty = setwinpage.IsPaymentFileEmpty(lastFileModified());

		Assert.assertEquals(IsFileEmpty, false);

	}

	@Test
	public void Test_Settlement_Window_Check_PaymentFile_Validate_Country_Code_payerfsp()
			throws SAXException, IOException, ParserConfigurationException {
		String countrycode1, countrycode2;
		String[] countrycodedfsp;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("payerfsp", "payeefsp", "1", PropertiesFile.bearertoken, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		countrycodedfsp = setwinpage.ReadPaymentMatrixGetCountryCode("payerfsp", Is.Payer, lastFileModified());

		setwinpage.AssertCountryCodeforDFSP(PropertiesFile.ccpayer, countrycodedfsp);

		countrycodedfsp = setwinpage.ReadPaymentMatrixGetCountryCode("payeefsp", Is.Payee, lastFileModified());

		setwinpage.AssertCountryCodeforDFSP(PropertiesFile.ccpayee, countrycodedfsp);

	}

	@Test
	public void Test_Settlement_Window_Check_PaymentFile_Validate_Country_Code_testfsp3()
			throws SAXException, IOException, ParserConfigurationException {
		String countrycode1, countrycode2;
		String[] countrycodedfsp;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("testfsp3", "testfsp4", "1", PropertiesFile.testfsp3token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		countrycodedfsp = setwinpage.ReadPaymentMatrixGetCountryCode("testfsp3", Is.Payer, lastFileModified());

		setwinpage.AssertCountryCodeforDFSP(PropertiesFile.cctestfsp3, countrycodedfsp);

		countrycodedfsp = setwinpage.ReadPaymentMatrixGetCountryCode("testfsp4", Is.Payee, lastFileModified());

		setwinpage.AssertCountryCodeforDFSP(PropertiesFile.cctestfsp4, countrycodedfsp);
	}

	@Test
	public void Test_Settlement_Window_Check_PaymentFile_Validate_Country_Code_testfsp2()
			throws SAXException, IOException, ParserConfigurationException {
		String countrycode1, countrycode2;
		String[] countrycodedfsp;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		countrycodedfsp = setwinpage.ReadPaymentMatrixGetCountryCode("testfsp1", Is.Payer, lastFileModified());

		setwinpage.AssertCountryCodeforDFSP(PropertiesFile.cctestfsp1, countrycodedfsp);

		countrycodedfsp = setwinpage.ReadPaymentMatrixGetCountryCode("testfsp2", Is.Payee, lastFileModified());

		setwinpage.AssertCountryCodeforDFSP(PropertiesFile.cctestfsp2, countrycodedfsp);
	}

	@Test
	public void Test_Settlement_Window_Check_PaymentFile_Validate_Ustrd()
			throws SAXException, IOException, ParserConfigurationException {
		String countrycode1, countrycode2;
		String[] arr;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		arr = setwinpage.ReadPaymentMatrixUstrd(lastFileModified());

		setwinpage.AssertUsrdField(arr);
	}

	@Test
	public void Test_Settlement_Window_paymentfile() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.PAYMENTMATRIX);

		Assert.assertEquals(true, isFileDownloaded(lastFileModified()));

	}

	@Test
	public void Test_Settlement_Window_Download_report312() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));
		Wait(2);
		
		setwinpage.ClickOnButton(WindowDetailButtons.REPORT312);

		Assert.assertTrue(lastFileModified().contains("report"));

		Assert.assertEquals(true, isFileDownloaded(lastFileModified()));

	}

	@Test
	public void Test_Settlement_Window_Download_report644() {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));
		
		Wait(2);

		setwinpage.ClickOnButton(WindowDetailButtons.REPORT644);

		Assert.assertTrue(lastFileModified().contains("report"));

		Assert.assertEquals(true, isFileDownloaded(lastFileModified()));

	}

	@Test
	public void Test_Settlement_Window_Settled() throws IOException {

		String windowid_pendingsettlement;
		String windowid_settled;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();
		
		homepage.ClickOnSection("Settlement Windows");
		
		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp3", "testfsp4", "1", PropertiesFile.testfsp3token, "COMMITTED");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("OPEN");
		
		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		windowid_settled = setwinpage.GetFirstWindow("SETTLED");

		Assert.assertEquals(windowid_settled, windowid_pendingsettlement);

	}

	@Test
	public void Test_Settlement_Window_Close() {

		String windowid_open;
		String windowid_pendingset;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		windowid_open = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingset = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(windowid_open, windowid_pendingset);

	}

	@Test
	public void Test_Settlement_Window_Dates() throws Exception {

		String windowid_open;
		String windowid_pendingset;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.FromDate("2019-05-10");

		setwinpage.ToDate("2019-05-22");

	}

	// TEST CASE ID: MOWDEV-T118
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T118

	@Test

	public void Test_Closing_Settlement_window() throws InterruptedException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin,

				PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage Settlement = PageFactory.initElements(driver, SettlementWindowsPage.class);

		//// Enter the window iD to execute

		// Settlement.click_On_SettlementWindowId(501);

		Settlement.ClickOnSettlementWindow(Settlement.GetFirstWindow("OPEN"));

		Settlement.close_Window_Button();

		Settlement.check_WindowClosed();

		Thread.sleep(3000);

		Settlement.ClickOnSettlementWindow(Settlement.GetFirstWindow("OPEN"));

		Settlement.close_Window_X();

		Settlement.check_WindowClosed();

	}

	// TEST CASE ID: MOWDEV-T119
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T119

	@Test

	public void Test_Settlment_Windows_Pagination_Options() throws Exception {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage Settlement = PageFactory.initElements(driver, SettlementWindowsPage.class);

		Settlement.AssertPage();

		Settlement.FromDate(PropertiesFile.frompaginationset);
		Wait(2);

		Settlement.ToDate(PropertiesFile.topaginationset);
		Wait(2);
		
		ScrollDown();

		Settlement.windowid_Count("25");

	}

	// TEST CASE ID: MOWDEV-T72
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T72
	@Test
	public void Test_review_Details_Of_Settlement_Windows() throws InterruptedException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin,

				PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage Settlement = PageFactory.initElements(driver, SettlementWindowsPage.class);

		Settlement.AssertPage();

		Settlement.ClickOnSettlementWindow(Settlement.GetFirstWindow("OPEN"));

		Thread.sleep(3000);

		Settlement.assert_Settlement_window_Details();
	}

	// TEST CASE ID :MOWDEV-T73
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T73
	@Test
	public void Test_review_Settlment_Window_And_Window_Details() throws IOException {
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "25", PropertiesFile.testfsp1token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.assert_SettlementWindow_Details();
	}

	// TEST CASE ID : MOWDEV-T75
	// https://modusbox.atlassian.net/projects/MOWDEV?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.kanoah.test-manager__main-project-page#!/testCase/MOWDEV-T75
	@Test
	public void Test_pagination_settlement_window() throws Exception {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage Settlement = PageFactory.initElements(driver, SettlementWindowsPage.class);

		Settlement.AssertPage();

		Settlement.FromDate(PropertiesFile.frompaginationset);

		Settlement.ToDate(PropertiesFile.topaginationset);

		Thread.sleep(3000);

		ScrollDown();

		Settlement.windowid_Count("100");

		Settlement.scroll();

		// Settlement.validate_pagination_links();
		Settlement.navigation_Links(PropertiesFile.next_win, PropertiesFile.prev_win, PropertiesFile.last_win);
	}

	@Test
	public void Test_Funds_In_Funds_Out_Button_2_DFSPs()
			throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		

		ScrollDown();

		settlementbalanceinitial1 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp2");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		

		ScrollDown();

		settlementbalanceinitial2 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		ScrollUp();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		Wait(3);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));



		setwinpage.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		Wait(3);

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		settlementbalancefinal1 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp2");

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		settlementbalancefinal2 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		Assert.assertEquals(settlementbalancefinal1, settlementbalanceinitial1 - 1);

		Assert.assertEquals(settlementbalancefinal2, settlementbalanceinitial2 + 1);

	}

	@Test
	public void Test_Funds_In_Funds_Out_Button_4_DFSPs()
			throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		Wait(2);

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		FinancialMonitoringPage fmpage = PageFactory.initElements(driver, FinancialMonitoringPage.class);

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		ScrollDown();

		settlementbalanceinitial1 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp2");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		ScrollDown();

		settlementbalanceinitial2 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp3");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		ScrollDown();

		settlementbalanceinitial3 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp4");

		fmpage.CheckAvialableOptionsDfsp();

		fmpage.ClickOptionDfsp("Current Window");

		fmpage.CheckOptionsDisplayed("Current Window");

		ScrollDown();

		settlementbalanceinitial4 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		ScrollUp();

		homepage.ClickOnSection("Settlement Windows");

		// setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "60", PropertiesFile.testfsp1token, "COMMITTED");
		SendTransfer("testfsp1", "testfsp4", "55", PropertiesFile.testfsp1token, "COMMITTED");
		SendTransfer("testfsp3", "testfsp1", "35", PropertiesFile.testfsp3token, "COMMITTED");
		SendTransfer("testfsp2", "testfsp3", "20", PropertiesFile.testfsp2token, "COMMITTED");
		SendTransfer("testfsp4", "testfsp2", "35", PropertiesFile.testfsp4token, "COMMITTED");
		SendTransfer("testfsp4", "testfsp3", "25", PropertiesFile.testfsp4token, "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

	
		Wait(1);

		setwinpage.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		Wait(3);

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp1");

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		settlementbalancefinal1 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp2");

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		settlementbalancefinal2 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp3");

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		settlementbalancefinal3 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		RefreshPage();

		homepage.ClickOnSection("Financial Monitoring");

		fmpage.ClickOnDFSP("testfsp4");

		fmpage.ClickOptionDfsp("Current Window");

		ScrollDown();

		settlementbalancefinal4 = Float.parseFloat(fmpage.accounts_Balance("USD").substring(1));

		Assert.assertEquals(settlementbalancefinal1, settlementbalanceinitial1 - 80);

		Assert.assertEquals(settlementbalancefinal2, settlementbalanceinitial2 + 75);

		Assert.assertEquals(settlementbalancefinal3, settlementbalanceinitial3 + 10);

		Assert.assertEquals(settlementbalancefinal4, settlementbalanceinitial4 - 5);

	}

/*	@Test
	public void Test_Settlement_Window_Settled_Amount_zero() throws IOException {

		String windowid_pendingsettlement;
		String windowid_settled;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		windowid_pendingsettlement = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		windowid_settled = setwinpage.GetFirstWindow("SETTLED");

		Assert.assertEquals(windowid_settled, windowid_pendingsettlement);

	}*/

	@Test
	public void Check_Currency_and_NET_balance() throws Exception {

		String currandnetamount;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		// setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		SendTransfer("testfsp1", "testfsp2", "60", PropertiesFile.testfsp1token, "COMMITTED");

		SendTransfer("testfsp1", "testfsp4", "55", PropertiesFile.testfsp1token, "COMMITTED");

		SendTransfer("testfsp3", "testfsp1", "35", PropertiesFile.testfsp3token, "COMMITTED");

		SendTransfer("testfsp2", "testfsp3", "20", PropertiesFile.testfsp2token, "COMMITTED");

		SendTransfer("testfsp4", "testfsp2", "35", PropertiesFile.testfsp4token, "COMMITTED");

		SendTransfer("testfsp4", "testfsp3", "25", PropertiesFile.testfsp4token, "COMMITTED");

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		currandnetamount = setwinpage.GetWindowCurrencyAndAmount(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		Assert.assertTrue(currandnetamount.contains("USD"));

		Assert.assertTrue(currandnetamount.contains("85.00"));
	}

	@Test
	public void review_Settlment_Window_And_Window_Details() throws IOException {
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "25", PropertiesFile.testfsp1token, "COMMITTED");

		// SendTransfer("payerfsp","payeefsp", "1",PropertiesFile.bearertoken,
		// "COMMITTED");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

	}

	// TEST CASE ID: MOWDEV-T77

	@Test
	public void Test_settlement_Accounts_Balance() throws IOException {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		// setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		SendTransfer("testfsp1", "testfsp2", "60", PropertiesFile.testfsp1token, "COMMITTED");

		SendTransfer("testfsp1", "testfsp4", "55", PropertiesFile.testfsp1token, "COMMITTED");

		SendTransfer("testfsp3", "testfsp1", "35", PropertiesFile.testfsp3token, "COMMITTED");

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.verify_Settlement_Account_Balance();

	}

	// TEST CASE ID: MOWDEV-T121

	@Test
	public void Test_settlement_Window_Navigation() throws Exception {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Wait(2);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");
		
		Wait(2);

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.assert_FromDate_ToDate();

		setwinpage.FromDate(PropertiesFile.frompaginationset);

		setwinpage.ToDate(PropertiesFile.topaginationset);

		setwinpage.Verify_Windowids_IsDisplayed(PropertiesFile.win1, PropertiesFile.win2);

		ScrollDown();

		setwinpage.windowid_Count("100");

		setwinpage.scroll();

		setwinpage.navigation_Links(PropertiesFile.next_win, PropertiesFile.prev_win, PropertiesFile.last_win);

	}

	// TEST CASE ID: MOWDEV-T117

	@Test
	public void Test_ValidateSettlement_Window_Using_One_Currency_Type() throws IOException {
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp4", "testfsp3", "25", PropertiesFile.testfsp4token, "COMMITTED");

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		setwinpage.verify_Window_Status();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage.verify_Currency_And_Amount_Using_One_CurrencyType();

	}

	// TEST CASE ID: MOWDEV- T56

	@Test
	public void Test_Settlement_Window_Date_Range() throws Exception {

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage Settlement = PageFactory.initElements(driver, SettlementWindowsPage.class);

		Settlement.AssertPage();

		Settlement.FromDate("2019-05-13");

		Settlement.ToDate("2019-05-14");

		Thread.sleep(3000);

		Settlement.validateDateRange();

		Settlement.FromDate("2019-05-13");

		Settlement.ToDate("2019-05-13");

		Settlement.invalid_Date_Range_Zero_SettlmentWindows();

		Settlement.FromDate("2019-05-15");

		Settlement.ToDate("2019-05-13");

		Settlement.invalid_Date_Range_Zero_SettlmentWindows();

		Settlement.FromDate("2025-05-15");

		Settlement.ToDate("2030-05-13");

		Settlement.invalid_Date_Range_Zero_SettlmentWindows();

	}

	@Test
	public void FundsInOut_Create_To_Settled_Window() throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement;
		String openwindow_id;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();

		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		Wait(2);

		RefreshPage();

		Wait(3);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);

	}
	
	
	@Test 
	public void FundsInOut_Create_2_Pending_Windows_OverlapingDFSP() throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement;
		String openwindow_id;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();


		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		
		Wait(2);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);
		
		RefreshPage();
		
		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();
		
		homepage.ClickOnSection("Settlement Windows");


		SendTransfer("testfsp2", "testfsp3", "1", PropertiesFile.testfsp2token, "COMMITTED");
		
		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		
		Wait(2);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);

	}
	
	
	
	@Test
	public void FundsInOut_Create_2_Pending_Windows_Diff_DFSP() throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement;
		String openwindow_id;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();


		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		
		Wait(2);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);
		
		RefreshPage();
		
		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();
		
		homepage.ClickOnSection("Settlement Windows");


		SendTransfer("testfsp3", "testfsp4", "1", PropertiesFile.testfsp3token, "COMMITTED");
		
		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		
		Wait(2);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);

	}

	
	@Test
	public void FundsInOut_Create_2_Pending_Windows_Same_DFSPs() throws SAXException, IOException, ParserConfigurationException {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement;
		String openwindow_id;

		LoginPage login = PageFactory.initElements(driver, LoginPage.class);

		login.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		homepage.AssertPage();

		homepage.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage = PageFactory.initElements(driver, SettlementWindowsPage.class);

		setwinpage.AssertPage();

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(2);

		RefreshPage();


		homepage.ClickOnSection("Settlement Windows");

		setwinpage.AssertPage();

		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");

		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		
		Wait(2);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);
		
		RefreshPage();
		
		homepage.ClickOnSection("Settlement Windows");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);

		Wait(3);

		RefreshPage();
		
		homepage.ClickOnSection("Settlement Windows");


		SendTransfer("testfsp1", "testfsp2", "1", PropertiesFile.testfsp1token, "COMMITTED");
		
		openwindow_id = setwinpage.GetFirstWindow("OPEN");

		setwinpage.ClickOnSettlementWindow(setwinpage.GetFirstWindow("OPEN"));

		setwinpage.ClickOnButton(WindowDetailButtons.CLOSEWINDOW);
		
		Wait(3);

		RefreshPage();

		Wait(2);

		homepage.ClickOnSection("Settlement Windows");

		windowid_pendingsettlement = setwinpage.GetFirstWindow("PENDING_SETTLEMENT");

		Assert.assertEquals(openwindow_id, windowid_pendingsettlement);

	}

	

}
