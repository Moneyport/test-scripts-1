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

public class Funds_In_and_Out_Test_Different_Windows_Overlaped {

	public static float settlbalanceinitial1, settlbalancefinal1, settlbalanceinitial2,
	settlbalancefinal2, settlbalanceinitial3, settlbalancefinal3,bsettlbalanceinitial2, bsettlbalancefinal2;


	
	
	@Test(groups={"Overlapping"})
	public void FundsInOut_ClickOn_Settled_Window_7()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory7 = BrowserFactory.getInstance();
		browserFactory7.setDriver("Chrome");
		browserFactory7.getDriver();
		browserFactory7.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory7.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory7.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_1 |"+Thread.currentThread().getId());

		LoginPage login7 = PageFactory.initElements(browserFactory7.getDriver(), LoginPage.class);

		login7.logintoPortal(PropertiesFile.usernameAdmin, PropertiesFile.passwordAdmin);

		HomePage homepage7 = PageFactory.initElements(browserFactory7.getDriver(), HomePage.class);
		
		FinancialMonitoringPage fmpage7 = PageFactory.initElements(browserFactory7.getDriver(), FinancialMonitoringPage.class);
		
		settlbalanceinitial2 = Math.abs(Float.parseFloat(fmpage7.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "XOF", "balance")));
	
		Thread.sleep(2500);
		settlbalanceinitial3 = Math.abs(Float.parseFloat(fmpage7.GetParticipantAccounts("testfsp3", "SETTLEMENT",  "XOF", "balance")));
		

		homepage7.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage7 = PageFactory.initElements(browserFactory7.getDriver(), SettlementWindowsPage.class);


		setwinpage7.ClickOnSettlementWindow(setwinpage7.GetFirstWindow("PENDING_SETTLEMENT"));

		setwinpage7.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);
		
		Thread.sleep(2500);
		
		settlbalancefinal2 = Math.abs(Float.parseFloat(fmpage7.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "XOF", "balance")));
		
		Thread.sleep(2500);
		settlbalancefinal3 = Math.abs(Float.parseFloat(fmpage7.GetParticipantAccounts("testfsp3", "SETTLEMENT",  "XOF", "balance")));
		
	//	System.out.println("Checking 2 y 3 | "+Thread.currentThread().getId()+" "+ settlementbalancefinal2 + " & " + (settlementbalanceinitial2 - 1) );
		//Assert.assertEquals(settlementbalancefinal2, (settlementbalanceinitial2 - 1));
		
		System.out.println("Checking FundsInOut_ClickOn_Settled_Window_7 |"+ settlbalancefinal3 + " & " + (settlbalanceinitial3 + 1) );
		Assert.assertEquals(settlbalancefinal3, (settlbalanceinitial3 + 1));
		
		Thread.sleep(2000);
		browserFactory7.getDriver().quit();

	}
	
	
	
   @Test(groups={"Overlapping"})
	public void FundsInOut_ClickOn_Settled_Window_8()
			throws Exception {
		String QuoteStatus, TransferStatus;
		boolean IsFileEmpty;
		String windowid_pendingsettlement; String openwindow_id;
		
		BrowserFactory browserFactory8 = BrowserFactory.getInstance();
		browserFactory8.setDriver("Chrome");
		browserFactory8.getDriver();
		browserFactory8.getDriver().get(PropertiesFile.PortalUrl);
		//browserFactory.getDriver().manage().window().maximize();
		browserFactory8.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory8.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.out.println("Im in FundsInOut_ClickOn_Settled_Window_2 |"+Thread.currentThread().getId());

		LoginPage login8 = PageFactory.initElements(browserFactory8.getDriver(), LoginPage.class);

		login8.logintoPortal(PropertiesFile.usernameUser, PropertiesFile.passwordUser);

		HomePage homepage8 = PageFactory.initElements(browserFactory8.getDriver(), HomePage.class);

		homepage8.ClickOnSection("Financial Monitoring");
		
		FinancialMonitoringPage fmpage8 = PageFactory.initElements(browserFactory8.getDriver(), FinancialMonitoringPage.class);
		
		settlbalanceinitial1 = Math.abs(Float.parseFloat(fmpage8.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "XOF", "balance")));
		
		Thread.sleep(2500);
		bsettlbalanceinitial2 = Math.abs(Float.parseFloat(fmpage8.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "XOF", "balance")));

		
		homepage8.ClickOnSection("Settlement Windows");

		SettlementWindowsPage setwinpage8 = PageFactory.initElements(browserFactory8.getDriver(), SettlementWindowsPage.class);


		setwinpage8.ClickOnSettlementWindow(setwinpage8.GetNextWindow("PENDING_SETTLEMENT"));

		setwinpage8.ClickOnButton(WindowDetailButtons.SETTLEWINDOW);
		
		Thread.sleep(2500);

         settlbalancefinal1 = Math.abs(Float.parseFloat(fmpage8.GetParticipantAccounts("testfsp1", "SETTLEMENT",  "XOF", "balance")));
		
         Thread.sleep(2500);
		bsettlbalancefinal2 = Math.abs(Float.parseFloat(fmpage8.GetParticipantAccounts("testfsp2", "SETTLEMENT",  "XOF", "balance")));
		
		System.out.println("Checking FundsInOut_ClickOn_Settled_Window_8 |"+ settlbalancefinal1 + " & " + (settlbalanceinitial1 - 1) );
		Assert.assertEquals(settlbalancefinal1, (settlbalanceinitial1 - 1));

		//System.out.println("Checking 1 y 2| "+Thread.currentThread().getId()+" "+ bsettlementbalancefinal2 + " & " + (bsettlementbalanceinitial2 + 1) );
		//Assert.assertEquals(bsettlementbalancefinal2, (bsettlementbalanceinitial2 + 1));
		
		
	
		Thread.sleep(2000);

		browserFactory8.getDriver().quit();

	}
	
}
