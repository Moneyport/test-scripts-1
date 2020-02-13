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

public class Funds_In_and_Out_Test_Different_Users {

	public static float settlementbalanceinitial1, settlementbalancefinal1, settlementbalanceinitial2,
	settlementbalancefinal2,bsettlementbalanceinitial1, bsettlementbalancefinal1, bsettlementbalanceinitial2,
	bsettlementbalancefinal2;


	
	
	@Test
	public void FundsInOut_ClickOn_Settled_Window_5()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory5 = BrowserFactory.getInstance();
		browserFactory5.setDriver("Chrome");
		browserFactory5.getDriver();
		browserFactory5.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory5.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory5.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_1 |"+Thread.currentThread().getId());

		LoginPage login5 = PageFactory.initElements(browserFactory5.getDriver(), LoginPage.class);

		login5.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage5 = PageFactory.initElements(browserFactory5.getDriver(), HomePage.class);
		
		FinancialMonitoringPage fmpage5 = PageFactory.initElements(browserFactory5.getDriver(), FinancialMonitoringPage.class);
			
	    settlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage5.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
	    settlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage5.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		homepage5.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage5 = PageFactory.initElements(browserFactory5.getDriver(), SettlementWindowsPage.class);


		setwinpage5.ClickOnSettlementWindow(setwinpage5.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage5.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		Thread.sleep(2500);
		
		 settlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage5.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
		 settlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage5.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
		 Assert.assertEquals(settlementbalancefinal1, (settlementbalanceinitial1 - 1));

		 Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 + 1));
		 
		 browserFactory5.getDriver().quit();

	}
	
	
	
   @Test
	public void FundsInOut_ClickOn_Settled_Window_6()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory6 = BrowserFactory.getInstance();
		browserFactory6.setDriver("Chrome");
		browserFactory6.getDriver();
		browserFactory6.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory6.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory6.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_2 |"+Thread.currentThread().getId());

		LoginPage login6 = PageFactory.initElements(browserFactory6.getDriver(), LoginPage.class);

		login6.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage6 = PageFactory.initElements(browserFactory6.getDriver(), HomePage.class);
		
		FinancialMonitoringPage fmpage6 = PageFactory.initElements(browserFactory6.getDriver(), FinancialMonitoringPage.class);
		
		bsettlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage6.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
		bsettlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage6.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		homepage6.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage6 = PageFactory.initElements(browserFactory6.getDriver(), SettlementWindowsPage.class);


		setwinpage6.ClickOnSettlementWindow(setwinpage6.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage6.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

	
		Thread.sleep(2500);
		
		 bsettlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage6.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
		 bsettlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage6.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
		 Assert.assertEquals(bsettlementbalancefinal1, (bsettlementbalanceinitial1 - 1));

		 Assert.assertEquals(bsettlementbalancefinal2, (bsettlementbalanceinitial2 + 1));
		 

		browserFactory6.getDriver().quit();

	}
	
}
