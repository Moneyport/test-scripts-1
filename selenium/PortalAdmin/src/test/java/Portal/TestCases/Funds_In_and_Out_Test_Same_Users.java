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

public class Funds_In_and_Out_Test_Same_Users {

	public static float settlementbalanceinitial1, settlementbalancefinal1, settlementbalanceinitial2,
	settlementbalancefinal2;


	
	
	@Test
	public void FundsInOut_ClickOn_Settled_Window_11()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory11 = BrowserFactory.getInstance();
		browserFactory11.setDriver("Chrome");
		browserFactory11.getDriver();
		browserFactory11.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory11.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory11.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_1 |"+Thread.currentThread().getId());

		LoginPage login11 = PageFactory.initElements(browserFactory11.getDriver(), LoginPage.class);

		login11.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage11 = PageFactory.initElements(browserFactory11.getDriver(), HomePage.class);
		
        FinancialMonitoringPage fmpage11 = PageFactory.initElements(browserFactory11.getDriver(), FinancialMonitoringPage.class);
		
		settlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage11.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		
		settlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage11.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
		
    	homepage11.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage11 = PageFactory.initElements(browserFactory11.getDriver(), SettlementWindowsPage.class);

		setwinpage11.ClickOnSettlementWindow(setwinpage11.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage11.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

		Thread.sleep(2500);
		
		settlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage11.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		
		settlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage11.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));
			
		Assert.assertEquals(settlementbalancefinal1, (settlementbalanceinitial1 - 1));

		Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 + 1));
			
		browserFactory11.getDriver().quit();

	}
	
	
	
   @Test
	public void FundsInOut_ClickOn_Settled_Window_12()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory12 = BrowserFactory.getInstance();
		browserFactory12.setDriver("Chrome");
		browserFactory12.getDriver();
		browserFactory12.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory12.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory12.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_2 |"+Thread.currentThread().getId());

		LoginPage login12 = PageFactory.initElements(browserFactory12.getDriver(), LoginPage.class);

		login12.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage12 = PageFactory.initElements(browserFactory12.getDriver(), HomePage.class);

	    FinancialMonitoringPage fmpage12 = PageFactory.initElements(browserFactory12.getDriver(), FinancialMonitoringPage.class);
			
		settlementbalanceinitial1 = Math.abs(Float.parseFloat(fmpage12.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
			
		settlementbalanceinitial2 = Math.abs(Float.parseFloat(fmpage12.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		homepage12.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage12 = PageFactory.initElements(browserFactory12.getDriver(), SettlementWindowsPage.class);


		setwinpage12.ClickOnSettlementWindow(setwinpage12.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage12.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);

	
		Thread.sleep(2500);
		
		settlementbalancefinal1 = Math.abs(Float.parseFloat(fmpage12.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "USD", "balance")));
		
		settlementbalancefinal2 = Math.abs(Float.parseFloat(fmpage12.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "USD", "balance")));

		Assert.assertEquals(settlementbalancefinal1, (settlementbalanceinitial1 - 1));

		Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 + 1));
		
		browserFactory12.getDriver().quit();

	}
	
}
