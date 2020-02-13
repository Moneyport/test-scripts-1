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

public class Funds_In_and_Out_Test_Different_Users_Sequence {

	public static float settlementbalanceinitial1, settlementbalancefinal1, settlementbalanceinitial2,
	settlementbalancefinal2, csettlementbalanceinitial1, csettlementbalancefinal1, csettlementbalanceinitial2,
	csettlementbalancefinal2;


	
	
	@Test
	public void FundsInOut_ClickOn_Settled_Window_3()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory3 = BrowserFactory.getInstance();
		browserFactory3.setDriver("Chrome");
		browserFactory3.getDriver();
		browserFactory3.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory3.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory3.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_1 |"+Thread.currentThread().getId());

		LoginPage login3 = PageFactory.initElements(browserFactory3.getDriver(), LoginPage.class);

		login3.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage3 = PageFactory.initElements(browserFactory3.getDriver(), HomePage.class);
		
		FinancialMonitoringPage fmpage3 = PageFactory.initElements(browserFactory3.getDriver(), FinancialMonitoringPage.class);
		
	    settlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage3.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
	    settlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage3.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		homepage3.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage3 = PageFactory.initElements(browserFactory3.getDriver(), SettlementWindowsPage.class);

		setwinpage3.ClickOnSettlementWindow(setwinpage3.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage3.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		Thread.sleep(2500);
		
		
	    settlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage3.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
	    settlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage3.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
	    Assert.assertEquals(settlementbalancefinal1, (settlementbalanceinitial1 - 1));

		 Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 + 1));
		
		
		browserFactory3.getDriver().quit();

	}
	
	
	
   @Test
	public void FundsInOut_ClickOn_Settled_Window_4()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory4 = BrowserFactory.getInstance();
		browserFactory4.setDriver("Chrome");
		browserFactory4.getDriver();
		browserFactory4.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory4.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory4.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_2 |"+Thread.currentThread().getId());

		LoginPage login4 = PageFactory.initElements(browserFactory4.getDriver(), LoginPage.class);

		login4.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage4 = PageFactory.initElements(browserFactory4.getDriver(), HomePage.class);

        FinancialMonitoringPage fmpage4 = PageFactory.initElements(browserFactory4.getDriver(), FinancialMonitoringPage.class);
		
	    csettlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage4.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
	    csettlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage4.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		homepage4.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage4 = PageFactory.initElements(browserFactory4.getDriver(), SettlementWindowsPage.class);


		setwinpage4.ClickOnSettlementWindow(setwinpage4.GetFirstWindow("PENDING_SETTLEMENT"));

		Thread.sleep(1000);
		
		setwinpage4.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

	
		Thread.sleep(2500);
		  
		csettlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage4.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
		csettlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage4.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
			
		Assert.assertEquals(csettlementbalancefinal1, (csettlementbalanceinitial1 - 1));

	    Assert.assertEquals(csettlementbalancefinal2, (csettlementbalanceinitial2 + 1));
	    
		browserFactory4.getDriver().quit();

	}
	
}
