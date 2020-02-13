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

public class Funds_In_and_Out_Test__Same_DFSPs_Diff_Windows {

	public static float settlementbalanceinitial1, settlementbalancefinal1, settlementbalanceinitial2,
	settlementbalancefinal2, bsettlementbalanceinitial1, bsettlementbalancefinal1, bsettlementbalanceinitial2,
	bsettlementbalancefinal2;


	
	
	@Test
	public void FundsInOut_ClickOn_Settled_Window_1()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory = BrowserFactory.getInstance();
		browserFactory.setDriver("Chrome");
		browserFactory.getDriver();
		browserFactory.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_1 |"+Thread.currentThread().getId());

		LoginPage login1 = PageFactory.initElements(browserFactory.getDriver(), LoginPage.class);

		login1.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage1 = PageFactory.initElements(browserFactory.getDriver(), HomePage.class);
		
		FinancialMonitoringPage fmpage1 = PageFactory.initElements(browserFactory.getDriver(), FinancialMonitoringPage.class);
		
		settlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage1.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
	

		settlementbalanceinitial2= Math.abs(Float.parseFloat(fmpage1.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		

		homepage1.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage1 = PageFactory.initElements(browserFactory.getDriver(), SettlementWindowsPage.class);


		setwinpage1.ClickOnSettlementWindow(setwinpage1.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage1.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);
		
		Thread.sleep(2500);
		
		settlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage1.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		

		settlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage1.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal3) + " & " + (Math.abs(settlementbalanceinitial3) - 1) );
		Assert.assertEquals(settlementbalancefinal1, (settlementbalanceinitial1 - 2));
		
		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal4) + " & " + (Math.abs(settlementbalanceinitial4) + 1) );
		Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 + 2));
		
		Thread.sleep(2000);
		browserFactory.getDriver().quit();

	}
	
	
	
   @Test
	public void FundsInOut_ClickOn_Settled_Window_2()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory = BrowserFactory.getInstance();
		browserFactory.setDriver("Chrome");
		browserFactory.getDriver();
		browserFactory.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_2 |"+Thread.currentThread().getId());

		LoginPage login2 = PageFactory.initElements(browserFactory.getDriver(), LoginPage.class);

		login2.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage2 = PageFactory.initElements(browserFactory.getDriver(), HomePage.class);

		homepage2.ClickOnSection("Financial Monitoring");
		
		FinancialMonitoringPage fmpage2 = PageFactory.initElements(browserFactory.getDriver(), FinancialMonitoringPage.class);
		
		bsettlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage2.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		

		bsettlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage2.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		
		homepage2.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage2 = PageFactory.initElements(browserFactory.getDriver(), SettlementWindowsPage.class);


		setwinpage2.ClickOnSettlementWindow(setwinpage2.GetNextWindow("PENDING_SETTLEMENT"));

		setwinpage2.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);
		
		Thread.sleep(2500);

         bsettlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage2.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		

		bsettlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage2.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal1) + " & " + (Math.abs(settlementbalanceinitial1) - 1) );
		Assert.assertEquals(bsettlementbalancefinal1, (bsettlementbalanceinitial1 - 2));

		//System.out.println("Checking |"+ Math.abs(settlementbalancefinal2) + " & " + (Math.abs(settlementbalanceinitial2) + 1) );
		Assert.assertEquals(bsettlementbalancefinal2, (bsettlementbalanceinitial2 + 2));
		
		
	
		Thread.sleep(2000);

		browserFactory.getDriver().quit();

	}
	
}
