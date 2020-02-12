package Portal.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import Portal.Utilities.PropertiesFile;
import PortalPages.BaseClass;
import PortalPages.FinancialMonitoringPage;
import PortalPages.HomePage;
import PortalPages.LoginPage;
import PortalPages.SettlementWindowsPage;
import PortalPages.SettlementWindowsPage.WindowDetailButtons;
import Portal.Utilities.BrowserFactory;

public class Funds_In_and_Out_Test_Different_Windows {

	public static float settlementbalanceinitial1, settlementbalancefinal1, settlementbalanceinitial2,
	settlementbalancefinal2, settlementbalanceinitial3, settlementbalancefinal3,settlementbalanceinitial4, settlementbalancefinal4;


	
	
	@Test
	public void FundsInOut_ClickOn_Settled_Window_9()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory9 = BrowserFactory.getInstance();
		browserFactory9.setDriver("Chrome");
		browserFactory9.getDriver();
		browserFactory9.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory9.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory9.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_1 |"+Thread.currentThread().getId());

		LoginPage login9 = PageFactory.initElements(browserFactory9.getDriver(), LoginPage.class);

		login9.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage9 = PageFactory.initElements(browserFactory9.getDriver(), HomePage.class);
		
		FinancialMonitoringPage fmpage9 = PageFactory.initElements(browserFactory9.getDriver(), FinancialMonitoringPage.class);
		
		settlementbalanceinitial3 = Math.abs(Float.parseFloat(fmpage9.GetParticipantAccounts("testfsp3", "SETTLEMENT",  "USD", "balance")));
	

		settlementbalanceinitial4 = Math.abs(Float.parseFloat(fmpage9.GetParticipantAccounts("testfsp4", "SETTLEMENT",  "USD", "balance")));
		

		homepage9.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage9 = PageFactory.initElements(browserFactory9.getDriver(), SettlementWindowsPage.class);


		setwinpage9.ClickOnSettlementWindow(setwinpage9.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage9.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);
		
		Thread.sleep(2500);
		
		settlementbalancefinal3 = Math.abs(Float.parseFloat(fmpage9.GetParticipantAccounts("testfsp3", "SETTLEMENT",  "USD", "balance")));
		

		settlementbalancefinal4 = Math.abs(Float.parseFloat(fmpage9.GetParticipantAccounts("testfsp4", "SETTLEMENT",  "USD", "balance")));
		
		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal3) + " & " + (Math.abs(settlementbalanceinitial3) - 1) );
		Assert.assertEquals(settlementbalancefinal3, (settlementbalanceinitial3 - 1));
		
		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal4) + " & " + (Math.abs(settlementbalanceinitial4) + 1) );
		Assert.assertEquals(settlementbalancefinal4, (settlementbalanceinitial4 + 1));
		
		Thread.sleep(2000);
		browserFactory9.getDriver().quit();

	}
	
	
	
   @Test
	public void FundsInOut_ClickOn_Settled_Window_10()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory10 = BrowserFactory.getInstance();
		browserFactory10.setDriver("Chrome");
		browserFactory10.getDriver();
		browserFactory10.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory10.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory10.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_2 |"+Thread.currentThread().getId());

		LoginPage login10 = PageFactory.initElements(browserFactory10.getDriver(), LoginPage.class);

		login10.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage10 = PageFactory.initElements(browserFactory10.getDriver(), HomePage.class);

		homepage10.ClickOnSection("Financial Monitoring");
		
		FinancialMonitoringPage fmpage10 = PageFactory.initElements(browserFactory10.getDriver(), FinancialMonitoringPage.class);
		
		settlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage10.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		

		settlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage10.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		
		homepage10.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage10 = PageFactory.initElements(browserFactory10.getDriver(), SettlementWindowsPage.class);


		setwinpage10.ClickOnSettlementWindow(setwinpage10.GetNextWindow("PENDING_SETTLEMENT"));

		setwinpage10.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);
		
		Thread.sleep(2500);

         settlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage10.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		

		settlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage10.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal1) + " & " + (Math.abs(settlementbalanceinitial1) - 1) );
		Assert.assertEquals(settlementbalancefinal1, (settlementbalanceinitial1 - 1));

		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal2) + " & " + (Math.abs(settlementbalanceinitial2) + 1) );
		Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 + 1));
		
		
	
		Thread.sleep(2000);

		browserFactory10.getDriver().quit();

	}
	
}
