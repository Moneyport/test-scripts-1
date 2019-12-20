package PortalPages;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Portal.Utilities.BrowserFactory;
import Portal.Utilities.PropertiesFile;

import org.testng.Assert;
import org.testng.collections.Lists;

import com.google.gson.stream.JsonReader;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FinancialMonitoringPage {

	WebDriver driver;
	@FindBy(xpath = "//input[@type=\"date\"]")
	List<WebElement> datepickers;
	int settlementcount;
	int settlementafterTransfer;

	public FinancialMonitoringPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/table/tbody/tr/th")
	List<WebElement> rows;

	public void ClickOnDFSP(String dfsp) {
		int i = 1;
		String dfspxpath = "";

		for (WebElement e : rows) {
			if (dfsp.equals(e.getText())) {
				dfspxpath = "//*[@id=\"root\"]/div/div/div/table/tbody/tr[" + i + "]";
				break;
			}
			i++;
		}

		if (dfspxpath != "") {
			WebElement dfsp_ = driver.findElement(By.xpath(dfspxpath));
			dfsp_.click();
		} else {
			System.out.println("This DFSP is not listed in the Financial Monitoring page");
		}

		if (dfsp != "Hub") {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Current Settlement Window')]")));
		}
	}

	public void AssertPage() {

		Assert.assertEquals(true, (rows.size() > 0));
	}

	public void CheckAvialableOptionsDfsp() {

		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Current Window')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Window History')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Financial Controls')]")).isDisplayed());
	}

	public void ClickOptionDfsp(String opt) {
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		if (opt.equals("Current Window")) {

			driver.findElement(By.xpath("//*[contains(text(), 'Current Window')]")).click();

		} else if (opt.equals("Window History")) {
			driver.findElement(By.xpath("//*[contains(text(), 'Window History')]")).click();

		} else if (opt.equals("Financial Controls")) {
			driver.findElement(By.xpath("//*[contains(text(), 'Financial Controls')]")).click();

		} else {
			System.out.println("Option not available");
		}

	}

	public void CheckOptionsDisplayed(String option) {

	  //Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 15);

		if (option.equals("Current Window")) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Current Settlement Window')]")));

			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Current Settlement Window')]")).isDisplayed());
			Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Accounts')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Settlements')]")).isDisplayed());

		} else if (option.equals("Window History")) {

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Previous Settlement Window')]")));

			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Previous Settlement Window')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Transaction Average')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Outgoing Transactions')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Incoming Transactions')]")).isDisplayed());
			Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Limits')]")).isDisplayed());
			Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Positions')]")).isDisplayed());

		} else if (option.equals("Financial Controls")) {

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Disable transactions for DFSP')]")));
			Assert.assertEquals(true, driver
					.findElement(By.xpath("//*[contains(text(), 'Disable transactions for DFSP')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Net Debit Cap Management')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//*[contains(text(), 'Funds Management')]")).isDisplayed());
			Assert.assertEquals(true,
					driver.findElement(By.xpath("//input[contains(@id, 'val-ndcManagement-NDC')]")).isDisplayed());

		} else {
			System.out.println("Option not available");
		}
	}

	public void UpdateNDC(String curr, String val) throws InterruptedException {
		String ele, but;
		List<WebElement> ndcelementcells;
		String xpathstrx="//div[.//h2[text()='Net Debit Cap Management']]/table/tbody/tr";
	    
	    
        List<WebElement> ndclist = driver.findElements(By.xpath(xpathstrx));
        
        int i = 1;
        int j=1;
        String valz,balancex ="";
        for (WebElement e : ndclist) {
        	
            valz = e.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[2]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
            //val = e.
            if (valz.equals(curr)) {
            	WebElement ndcbox = driver.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[5]"+"//input[contains(@id, 'val-ndcManagement-NDC')]"));
        		ndcbox.click();
        		
        		ndcbox.sendKeys(Keys.LEFT);
        		ndcbox.sendKeys(Keys.LEFT);
        		ndcbox.sendKeys(Keys.LEFT);
        		ndcbox.sendKeys(Keys.LEFT);
        		ndcbox.sendKeys(Keys.DELETE);
        		ndcbox.sendKeys(val);
        		
        		Thread.sleep(2000);
        		
        		
        		WebElement buttonx = driver.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[5]"+"//button[contains(@id, 'val-ndcManagement-NDC')]"));
        		buttonx.click();
        		Thread.sleep(2000);
                
                break;
            }
            i++;
        }
        
        
		
		
	}

	public void ClickOnSwitchStopTransfers() {

		WebElement ndcbox = driver.findElement(By.xpath("//input[contains(@id, 'val-stop-transacions-testfsp1')]"));
		ndcbox.click();

	}

	public void AssertSwitchChanged() {

		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Update Successful!')]")).isDisplayed());
		try {
			Thread.sleep(7000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AssertNDCis(String val) {
		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ele;
		// ele=
		// "//*[@id=\"root\"]/div/div/div/table/tbody/tr[6]/td/div/div[2]/div/table/tbody/tr/td[4]";
		String valx = driver
				.findElement(By.xpath("//div[.//h2[text()='Net Debit Cap Management']]/table/tbody/tr/td[4]"))
				.getText();
		// WebElement element = driver.findElement(By.xpath(ele));
		Assert.assertEquals(val, valx);

	}

	public void DoFundsIn(String val, String curr) {
		
		String xpathstrx="//tr/td/div/div/div[.//h2[text()='Funds Management']]/table/tbody/tr";
		String value;	    
		float value2=0;
			    
		        List<WebElement> fundslist = driver.findElements(By.xpath(xpathstrx));
		        
		        int i = 1;
		        int j=1;
		        String valz,balancex ="";
		        for (WebElement e : fundslist) {
		        	
		            valz = e.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[2]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
		            //val = e.
		            if (valz.equals(curr)) {
		            	List<WebElement> fundstxt = driver
		        				.findElements(By.xpath(xpathstrx+"["+i+"]"+"//input[contains(@id, 'val-fundsManagement-amount')]"));
		        		List<WebElement> buttons = driver
		        				.findElements(By.xpath(xpathstrx+"["+i+"]"+"//button[contains(@id, 'val-fundsManagement-process')]"));

		        		fundstxt.get(0).click();
		        		// ndcbox.clear();
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.DELETE);
		        		fundstxt.get(0).sendKeys(val);

		        		buttons.get(0).click();

		        		driver.findElement(By.xpath("//*[contains(text(), 'OK')]")).click();

		        	
		                
		                break;
		            }
		            i++;
		        }
		        
		        
		    	try {
        			Thread.sleep(3500);

        		} catch (InterruptedException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
		

	}

	

	public float GetBalanceNDC(String curr) {
		
String xpathstrx="//div[.//h2[text()='Net Debit Cap Management']]/table/tbody/tr";
String value;	    
float value2=0;
	    
        List<WebElement> ndclist = driver.findElements(By.xpath(xpathstrx));
        
        int i = 1;
        int j=1;
        String valz,balancex ="";
        for (WebElement e : ndclist) {
        	
            valz = e.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[2]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
            //val = e.
            if (valz.equals(curr)) {
            	 value = driver
        				.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[4]"))
        				.getText();
        		 value2 = Math.abs(Float.parseFloat(value));

                
                break;
            }
            i++;
        }
		
		
		
		
		return value2;
	}

	public void CheckPositionAccounts(int val) {
		List<WebElement> positionstablerows = driver
				.findElements(By.xpath("//div/div[.//h2[text()='Accounts']]/div[2]/div"));
		Assert.assertTrue(positionstablerows.size() == val);
	}

	public void alertHandler(String okOrcancel) {

		String ok = "OK";
		String cancel = "Cancel";

		if (okOrcancel.equals(ok)) {
			driver.findElement(By.xpath("//*[contains(text(),'OK')]")).click();
		} else if (okOrcancel.equals(cancel)) {
			driver.findElement(By.xpath("//*[contains(text(), 'Cancel')]")).click();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public float GetBalanceFunds(String curr) {
		
		

		String xpathstrx="//tr/td/div/div/div[.//h2[text()='Funds Management']]/table/tbody/tr";
		String value;	    
		float value2=0;
			    
		        List<WebElement> fundslist = driver.findElements(By.xpath(xpathstrx));
		        
		        int i = 1;
		        int j=1;
		        String valz,balancex ="";
		        for (WebElement e : fundslist) {
		        	
		            valz = e.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[2]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
		            //val = e.
		            if (valz.equals(curr)) {
		            	value = driver.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[3]"))
		        				.getText();
		        		value2 = Math.abs(Float.parseFloat(value));

		                
		                break;
		            }
		            i++;
		        }
		        
		        
		        
		

		return value2;
	}


	public void DoFundsOut(String val, String curr) {

		String xpathstrx="//tr/td/div/div/div[.//h2[text()='Funds Management']]/table/tbody/tr";
		String value;	    
		float value2=0;
			    
		        List<WebElement> fundslist = driver.findElements(By.xpath(xpathstrx));
		        
		        int i = 1;
		        int j=1;
		        String valz,balancex ="";
		        for (WebElement e : fundslist) {
		        	
		            valz = e.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[2]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
		            //val = e.
		            if (valz.equals(curr)) {
		            	List<WebElement> fundstxt = driver
		        				.findElements(By.xpath(xpathstrx+"["+i+"]"+"//input[contains(@id, 'val-fundsManagement-amount')]"));
		        	
		        		List<WebElement> buttons = driver
		        				.findElements(By.xpath(xpathstrx+"["+i+"]"+"//button[contains(@id, 'val-fundsManagement-process')]"));

		        		fundstxt.get(1).click();
		        		// ndcbox.clear();
		        		fundstxt.get(1).sendKeys(Keys.LEFT);
		        		fundstxt.get(1).sendKeys(Keys.LEFT);
		        		fundstxt.get(1).sendKeys(Keys.LEFT);
		        		fundstxt.get(1).sendKeys(Keys.LEFT);
		        		fundstxt.get(1).sendKeys(Keys.DELETE);
		        		fundstxt.get(1).sendKeys(val);

		        		buttons.get(1).click();

		                
		                break;
		            }
		            i++;
		        }
		        
		        
	

		/*
		 * driver.findElement(By.xpath("//*[contains(text(), 'Cancel')]")).click();
		 * 
		 * 
		 * 
		 * try { Thread.sleep(3000);
		 * 
		 * } catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}
	
	public float GetBalanceSettlement() {
		//String value = driver.findElement(By.xpath("//div[.//h2[text()='Funds Management']]/table/tbody/tr[2]/td[3]")).getText();
		String value = driver.findElement(By.xpath("//div[.//h2[text()='Funds Management']]/table/tbody/tr/td[3]")).getText();
		float value2 = Math.abs(Float.parseFloat(value));
		
		return value2;
	}

	public void verifyPopUpNDC() {

		driver.switchTo().alert().accept();

	}

	public void selectApply() {
		List<WebElement> buttons = driver
				.findElements(By.xpath("//button[contains(@id, 'val-fundsManagement-process')]"));
		buttons.get(1).click();
	}

	public void click_On_Disable_Transactions() throws InterruptedException {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement Disable_Transactions = driver.findElement(By.xpath("//input[@type='checkbox']"));
		Disable_Transactions.isDisplayed();
		Disable_Transactions.click();
		String disabletranascation = driver.findElement(By.xpath("//*[contains(text(),'Update Successful')]"))
				.getText();
		Assert.assertEquals(disabletranascation, "Update Successful!");
		Disable_Transactions.click();

	}

	public void click_On_Enable_Transactions() throws InterruptedException {

		Thread.sleep(3000);
		WebElement EnableTransactions = driver.findElement(By.xpath("//input[@type='checkbox']"));
		EnableTransactions.click();
		EnableTransactions.click();
		String disabletranascation = driver.findElement(By.xpath("//*[contains(text(),'Update Successful')]"))
				.getText();
		Assert.assertEquals(disabletranascation, "Update Successful!");

	}

	public void DoFundsIn_SpecialCharacters(String val, String curr) {

		String xpathstrx="//tr/td/div/div/div[.//h2[text()='Funds Management']]/table/tbody/tr";
		String value;	    
		float value2=0;
			    
		        List<WebElement> fundslist = driver.findElements(By.xpath(xpathstrx));
		        
		        int i = 1;
		        int j=1;
		        String valz,balancex ="";
		        for (WebElement e : fundslist) {
		        	
		            valz = e.findElement(By.xpath(xpathstrx+"["+i+"]"+"/td[2]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
		            //val = e.
		            if (valz.equals(curr)) {
		            	List<WebElement> fundstxt = driver
		        				.findElements(By.xpath(xpathstrx+"["+i+"]"+"//input[contains(@id, 'val-fundsManagement-amount')]"));
		        		List<WebElement> buttons = driver
		        				.findElements(By.xpath(xpathstrx+"["+i+"]"+"//button[contains(@id, 'val-fundsManagement-process')]"));

		        		fundstxt.get(0).click();
		        		// ndcbox.clear();
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.LEFT);
		        		fundstxt.get(0).sendKeys(Keys.DELETE);
		        		fundstxt.get(0).sendKeys(val);

		        		String fundsin = fundstxt.get(0).getText();

		        		Assert.assertEquals(fundsin, "");

		                
		                break;
		            }
		            i++;
		        }
		
		
		
		
	

	}

	public void DoFundsOut_Special_Characters(String val) {

		List<WebElement> fundstxt = driver
				.findElements(By.xpath("//input[contains(@id, 'val-fundsManagement-amount')]"));
		List<WebElement> buttons = driver
				.findElements(By.xpath("//button[contains(@id, 'val-fundsManagement-process')]"));

		fundstxt.get(1).click();
		// ndcbox.clear();
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.DELETE);
		fundstxt.get(1).sendKeys(val);

		String fundsout = fundstxt.get(1).getText();

		Assert.assertEquals(fundsout, "");
	}

	public void NDC_table_Labels() {

		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Account ID')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Currency')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Position')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'NDC')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'New NDC')]")).isDisplayed());

	}

	public void verify_CurrentWindow_Sub_Fields() {

		List<WebElement> currencysettlementwindow = driver.findElements(By.xpath("//div[contains(text(),'Currency')]"));
		List<WebElement> noOftransactionssettlementwindow = driver
				.findElements(By.xpath(" //*[contains(text(),'Number of transactions')]"));
		List<WebElement> amountsettlementwindow = driver.findElements(By.xpath("//*[contains(text(),'Amount')] "));

		currencysettlementwindow.get(0).isDisplayed();
		currencysettlementwindow.get(1).isDisplayed();

		noOftransactionssettlementwindow.get(0).isDisplayed();
		noOftransactionssettlementwindow.get(1).isDisplayed();

		amountsettlementwindow.get(0).isDisplayed();
		amountsettlementwindow.get(1).isDisplayed();

	}

	public void verify_Accounts_Sub_Fields() {

		List<WebElement> currencyaccounts = driver.findElements(By.xpath("//div[contains(text(),'Currency')]"));

		currencyaccounts.get(2).isDisplayed();

		Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Balance')]")).isDisplayed());

	}

	public void verify_Position_Sub_Fields() {

		List<WebElement> currencyposition = driver.findElements(By.xpath("//div[contains(text(),'Currency')]"));
		currencyposition.get(3).isDisplayed();
		Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Position')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'NDC')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//div[contains(text(),'Percentage of NDC Used')]")).isDisplayed());

	}

	public void doFundsIn_Verify_Zero_Input(String val) {

		List<WebElement> fundstxt = driver
				.findElements(By.xpath("//input[contains(@id, 'val-fundsManagement-amount')]"));
		List<WebElement> buttons = driver
				.findElements(By.xpath("//button[contains(@id, 'val-fundsManagement-process')]"));

		fundstxt.get(0).click();
		// ndcbox.clear();
		fundstxt.get(0).sendKeys(Keys.LEFT);
		fundstxt.get(0).sendKeys(Keys.LEFT);
		fundstxt.get(0).sendKeys(Keys.LEFT);
		fundstxt.get(0).sendKeys(Keys.LEFT);
		fundstxt.get(0).sendKeys(Keys.DELETE);
		fundstxt.get(0).sendKeys(val);

		buttons.get(0).click();

	}

	public void doFundsOut_Verify_Zero_Input(String val) {

		List<WebElement> fundstxt = driver
				.findElements(By.xpath("//input[contains(@id, 'val-fundsManagement-amount')]"));
		List<WebElement> buttons = driver
				.findElements(By.xpath("//button[contains(@id, 'val-fundsManagement-process')]"));

		fundstxt.get(1).click();
		// ndcbox.clear();
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.LEFT);
		fundstxt.get(1).sendKeys(Keys.DELETE);
		fundstxt.get(1).sendKeys(val);

		buttons.get(1).click();

	}

//	public String accounts_Balance() {
//
//	
//		WebElement XOF = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/table/tbody/tr[6]/td/div/div[2]/div/div/div[1]/div[6]/div[2]/div"));
//		
//		String xof = XOF.getText();
//		
//		String value =xof.substring(0, xof.indexOf("."));
//	
//		System.out.println(value);
//		
//		return value;
//		
//		
//
//	}

	public String accounts_Balance(String curr) {
	      String xpathstr="//tr/td/div/div/div/div[.//h4[text()='Settlement']]/div[1]/div";
	    
	    
	        List<WebElement> Settlementsaccountscurrs = driver.findElements(By.xpath(xpathstr));
	        
	        int i = 1;
	        String val,balancex ="";
	        for (WebElement e : Settlementsaccountscurrs) {
	            val = e.findElement(By.xpath(xpathstr+"["+i+"]"+"/div[1]")).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
	            //val = e.
	            if (val.equals(curr)) {
	                balancex = e.findElement(By.xpath(xpathstr+"["+i+"]"+"/div[2]")).getText();
	                
	                break;
	            }
	            i++;
	        }
	       // float value2 = Math.abs(Float.parseFloat(balancex));
	       // balancex = Float.toString(value2);
	        return balancex;
	    
	        
	        
	    }
	
	
	
	
	
//	public String accounts_Position() {
//
//		
//		WebElement XOF = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/table/tbody/tr[6]/td/div/div[2]/div/div/div[2]/div[8]/div[2]/div"));
//		
//		
//		String xof = XOF.getText();
//		
//		String value =xof.substring(0, xof.indexOf("."));
//
//		System.out.println(value);
//
//		return value;
//		
//	
//	}

	 public String accounts_Position(String curr, String tab) {
	     String xpathstr,xpathcurr,xpathid; 
	     if(tab.equals("Current Window")) {
         xpathstr="//tr/td/div/div/div/div[.//h4[text()='Position']]/div[2]/div";
         xpathcurr="/div[1]";
         xpathid="/div[2]";
	     }else { 
	     xpathstr="//tr/td/div/div[2]/div[.//h2[text()='Net Debit Cap Management']]/table/tbody/tr";
	     xpathcurr="/td[2]";
         xpathid="/td[1]";
	     }
            List<WebElement> posaccountscurrs = driver.findElements(By.xpath(xpathstr));
            
            int i = 1;
            String val,balancex ="";
            for (WebElement e : posaccountscurrs) {
                val = e.findElement(By.xpath(xpathstr+"["+i+"]"+xpathcurr)).getText();//driver.findElement(By.xpath(xpathstr+"/div[1]")).getText();
                //val = e.
                if (val.equals(curr)) {
                    balancex = e.findElement(By.xpath(xpathstr+"["+i+"]"+xpathid)).getText();
                    
                    break;
                }
                i++;
            }
            
            return balancex;
        
    
    }	
	
	
	
	
/*	public void FromDateCurrentWindow(String datex) {
		String idx;
		String day = datex.substring(8, 10);
		datepickers.get(0).click();
		idx = datepickers.get(0).getAttribute("id");
		// datepickers.get(0).sendKeys(datex);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + idx + "').value='" + datex + "'");
		datepickers.get(0).sendKeys(day);
	}

	public void ToDateCurrentWindow(String datex) {
		String idx;
		String day = datex.substring(8, 10);
		datepickers.get(1).click();
		idx = datepickers.get(1).getAttribute("id");
		// datepickers.get(0).sendKeys(datex);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + idx + "').value='" + datex + "'");
		datepickers.get(1).sendKeys(day);
	}*/

		public void FromDateCurrentWindow(String datex) throws Exception {
			String idx,day2,month2;
			String day = datex.substring(8, 10);
			day2=day.substring(0, 1);
			if(day2.equals("0")) {
				day = day.substring(1, 2);
			}
			
			String month = datex.substring(5, 7);
			month2=month.substring(0, 1);
			if(month2.equals("0")) {
				month = month.substring(1, 2);
			}
			
			String year = datex.substring(0, 4);
			 Robot robot = new Robot();  // Robot class throws AWT Exception	
	        	
	       
	         
	         
			datepickers.get(0).click();
			Thread.sleep(1000);
			
			
			datepickers.get(0).sendKeys(month);
			Thread.sleep(1000);
			  
			datepickers.get(0).sendKeys(day);
			Thread.sleep(1000);
			 
			datepickers.get(0).sendKeys(year);
			
		}

		public void ToDateCurrentWindow(String datex) throws Exception {
			String idx,day2x,month2x;
			String dayx = datex.substring(8, 10);
			day2x=dayx.substring(0, 1);
			if(day2x.equals("0")) {
				dayx = dayx.substring(1, 2);
			}
			
			String monthx = datex.substring(5, 7);
			month2x=monthx.substring(0, 1);
			if(month2x.equals("0")) {
				monthx = monthx.substring(1, 2);
			}
			
			String yearx = datex.substring(0, 4);
			 Robot robot = new Robot();  // Robot class throws AWT Exception	
	     	
		       
			
			datepickers.get(1).click();
			Thread.sleep(1000);
			
			datepickers.get(1).sendKeys(monthx);
			Thread.sleep(1000);
			 
			datepickers.get(1).sendKeys(dayx);
			Thread.sleep(1000);
			
			datepickers.get(1).sendKeys(yearx);
			
			
		}
	
	
	public int verify_Settlement_History_For_Fsp() {

		List<WebElement> settlementhistory = driver.findElements(By.xpath("//ul/li"));
		settlementcount = settlementhistory.size();
		System.out.println(settlementcount);
		
		return settlementcount ;

	}

/*	public void verify_Settelement_History_After_Transfer() {

		List<WebElement> settlementhistoryaftertransfer = driver.findElements(By.xpath("//ul/li"));

		System.out.println(settlementhistoryaftertransfer);

		settlementafterTransfer = settlementhistoryaftertransfer.size();

		System.out.println(settlementafterTransfer);

		Assert.assertEquals(settlementafterTransfer, settlementcount + 1);

	}*/

	public static String OutGOingCurrency;
	public static String OutGoingNoOfTransactions;
	public static String OutGongAmount;

	public void verify_Outgoing_Transactions_CurrentWindow() {

		WebElement outgoingcurrency = driver
				.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[1]/div"));

		OutGOingCurrency = outgoingcurrency.getText();
		
		System.out.println(OutGOingCurrency);
		
		WebElement outgoingnooftransactions = driver
				.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[2]/div"));

		OutGoingNoOfTransactions = outgoingnooftransactions.getText();
		
		System.out.println(OutGoingNoOfTransactions);

		WebElement outgoingamount = driver
				.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[3]/div"));

		OutGongAmount = outgoingamount.getText();
		
	
		System.out.println(OutGongAmount);
	}

	public void verify_Incoming_Transactions_CurrentWindow() {

		WebElement outgoingcurrency = driver
				.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[1]/div"));

		String IncomingCurrency = outgoingcurrency.getText();

		Assert.assertEquals(IncomingCurrency, OutGOingCurrency);

		WebElement outgoingnooftransactions = driver
				.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[2]/div"));

		String IncomingNoOfTransactions = outgoingnooftransactions.getText();

		Assert.assertEquals(IncomingNoOfTransactions, OutGoingNoOfTransactions);

		WebElement outgoingamount = driver
				.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[3]/div"));

		String IncomingAmount = outgoingamount.getText();

		Assert.assertEquals(IncomingAmount, OutGongAmount);

	}

	

	public String GetOutgoingTransactionsNumberTransfers(String curr) {
		String xpathr ="//div/div/div/div[.//h4[text()='Outgoing Transactions']]/div[1]/div"; 
	    List<WebElement> outgoingtable = driver.findElements(By.xpath("//div/div/div/div[.//h4[text()='Outgoing Transactions']]/div[1]/div"));
	  //  String val;
	  /*  if(outgoingtable.size() > 4) {
	        val = driver.findElement(By.xpath("(//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[5]/div[2]/div)[1]")).getText();
	    }
	    else {
	     val = driver.findElement(By.xpath("(//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[4]/div[2]/div)[1]")).getText();
	   
	     System.out.println(val);
	    
	    }
	    
	    return val;*/
	    
	    int i = 4;
	    int j=0;
        String val,numtxns ="";
        for (WebElement e : outgoingtable) {
           if(j==3) {
        	val = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[1]/div")).getText();
            //val = e.
            if (val.equals(curr)) {
            	numtxns = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[2]/div")).getText();
                
                break;
            }
            i++;
           }else {j++;}
        }
        
        return numtxns;
	}
	public String GetOutgoingTransactionsTotalAmount(String curr) {
		String xpathr ="//div/div/div/div[.//h4[text()='Outgoing Transactions']]/div[1]/div"; 
	    List<WebElement> outgoingtable = driver.findElements(By.xpath("//div/div/div/div[.//h4[text()='Outgoing Transactions']]/div[1]/div"));
	  /*  List<WebElement> outgoingtable = driver.findElements(By.xpath("//div/div/div/div[.//h4[text()='Outgoing Transactions']]/div[1]/div"));
	    String val;
	    if(outgoingtable.size() > 4) {
	        val = driver.findElement(By.xpath("(//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[5]/div[3]/div)[1]")).getText();
	    }
	    else {
	     val = driver.findElement(By.xpath("(//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[4]/div[3]/div)[1]")).getText();
	    }
	    
	    return val;*/
	    int i = 4;
	    int j=0;
        String val,totaltxns ="";
        for (WebElement e : outgoingtable) {
           if(j==3) {
        	val = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[1]/div")).getText();
            //val = e.
            if (val.equals(curr)) {
            	totaltxns = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[3]/div")).getText();
                
                break;
            }
            i++;
           }else {j++;}
        }
        
        return totaltxns;
	}
	
	
	
	public String GetIncommingTransactionsNumberTransfers(String curr) {
		String xpathr ="//div/div/div/div[.//h4[text()='Incoming Transactions']]/div[2]/div"; 
	    List<WebElement> outgoingtable = driver.findElements(By.xpath("//div/div/div/div[.//h4[text()='Incoming Transactions']]/div[2]/div"));
	    int i = 4;
	    int j=0;
        String val,numtxns ="";
        for (WebElement e : outgoingtable) {
           if(j==3) {
        	val = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[1]/div")).getText();
            //val = e.
            if (val.equals(curr)) {
            	numtxns = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[2]/div")).getText();
                
                break;
            }
            i++;
           }else {j++;}
        }
        
        return numtxns;
	}
	
	public String GetIncommingTransactionsTotalAmount(String curr) {
		String xpathr ="//div/div/div/div[.//h4[text()='Incoming Transactions']]/div[2]/div"; 
	    List<WebElement> outgoingtable = driver.findElements(By.xpath("//div/div/div/div[.//h4[text()='Incoming Transactions']]/div[2]/div"));
	
	    int i = 4;
	    int j=0;
        String val,totaltxns ="";
        for (WebElement e : outgoingtable) {
           if(j==3) {
        	val = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[1]/div")).getText();
            //val = e.
            if (val.equals(curr)) {
            	totaltxns = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[3]/div")).getText();
                
                break;
            }
            i++;
           }else {j++;}
        }
        
        return totaltxns;
	}

public String verify_NDC_Average_Percentage(String curr) {
	
/*	WebElement ndcaveragepercentage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/table/tbody/tr[6]/td/div/div[2]/div/table/tbody/tr/td[3]"));

	
	String ndcpercentage = ndcaveragepercentage.getText();
	
	System.out.println(ndcpercentage);
	
	return ndcpercentage;*/
	String xpathr ="//table/tbody/tr/td/div/div/div[.//h2[text()='Net Debit Cap Management']]/table/tbody/tr"; 
    List<WebElement> tablerows = driver.findElements(By.xpath(xpathr));
	  int i = 1;
	    int j=0;
      String val,posamount ="";
      for (WebElement e : tablerows) {
   /*      if(j==3) {
      	val = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[1]/div")).getText();
          //val = e.
          if (val.equals(curr)) {
          	totaltxns = e.findElement(By.xpath(xpathr+"["+i+"]"+"/div[3]/div")).getText();
              
              break;
          }
          i++;
         }else {j++;}*/
    		val = e.findElement(By.xpath(xpathr+"["+i+"]"+"/td[2]")).getText();
         
            if (val.equals(curr)) {
            	posamount = e.findElement(By.xpath(xpathr+"["+i+"]"+"/td[3]")).getText();
                
                break;
            }
            i++;
      }
      
      return posamount;

}

public void FromDateWindowHistory(String datex) {
	String idx;
	String day = datex.substring(8, 10);
	datepickers.get(0).click();
	idx = datepickers.get(0).getAttribute("id");
	// datepickers.get(0).sendKeys(datex);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("document.getElementById('" + idx + "').value='" + datex + "'");
	datepickers.get(0).sendKeys(day);
}

public void ToDateWindowHistory(String datex) {
	String idx;
	String day = datex.substring(8, 10);
	datepickers.get(1).click();
	idx = datepickers.get(1).getAttribute("id");
	// datepickers.get(0).sendKeys(datex);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("document.getElementById('" + idx + "').value='" + datex + "'");
	datepickers.get(1).sendKeys(day);
}

public void assert_PreviousWindow_TransactionAverage() {
	
	
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'From')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'To')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'Avg. Payments')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'Avg. Payments Amt')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'Avg. Receipts')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'Avg. Receipts Amt')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'Avg. NDC')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'Avg. Position')]")).isDisplayed());	
	
}
public void assert_Hub_Stop_Functionality_Displayed() {
	
	WebElement Disable_Transactions = driver.findElement(By.xpath("//input[@type='checkbox']"));
	Disable_Transactions.isDisplayed();
}
	
	public void validate_Hub_FinancialControl_NoDataDisplayed() { 
	
	WebElement applayButton=driver.findElement(By.xpath("//span[contains(text(),'Apply')]"));
	
	Assert.assertFalse(applayButton.isDisplayed());
}

public void verify_Outgoing_Transactions_CurrentWindow_TestFSP1() {

	WebElement outgoingcurrency = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[1]/div"));

	OutGOingCurrency = outgoingcurrency.getText();
	Assert.assertEquals(OutGOingCurrency, "XOF");

	WebElement outgoingnooftransactions = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[2]/div"));

	OutGoingNoOfTransactions = outgoingnooftransactions.getText();
	
	Assert.assertEquals(OutGoingNoOfTransactions, "2");

	WebElement outgoingamount = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[3]/div"));
	
	OutGongAmount = outgoingamount.getText();
	Assert.assertEquals(OutGongAmount, "115.000");
	
}

public void verify_No_SettlementFound() {
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'No settlements found')]")).isDisplayed());
	
}

public void verify_Incoming_Transactions_CurrentWindow_TestFsp2() {
	

	WebElement incomoingcurrencytestfsp2 = driver
			.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[1]/div"));

	String IncomingCurrency = incomoingcurrencytestfsp2.getText();

	System.out.println(IncomingCurrency);
	
	Assert.assertEquals(IncomingCurrency, "XOF");

	WebElement incomingnooftranscationstestfsp2 = driver
			.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[2]/div"));

	String IncomingNoOfTransactions = incomingnooftranscationstestfsp2.getText();
	
	System.out.println(IncomingNoOfTransactions);

	Assert.assertEquals(IncomingNoOfTransactions, "1");

	
	WebElement incomingamounttestfsp2 = driver
			.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[3]/div"));

	String IncomingAmount = incomingamounttestfsp2.getText();
	
	System.out.println(IncomingAmount);

	Assert.assertEquals(IncomingAmount, "60.0000");

}

public void verify_Incoming_Transactions_CurrentWindow_TestFsp4() {
	

	WebElement incomoingcurrencytestfsp4 = driver
			.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[1]/div"));

	String IncomingCurrency = incomoingcurrencytestfsp4.getText();

	System.out.println(IncomingCurrency);
	
	Assert.assertEquals(IncomingCurrency, "XOF");

	WebElement incomingnooftranscationstestfsp4 = driver
			.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[2]/div"));

	String IncomingNoOfTransactions = incomingnooftranscationstestfsp4.getText();
	
	System.out.println(IncomingNoOfTransactions);

	Assert.assertEquals(IncomingNoOfTransactions, "1");

	
	WebElement incomingamountterstfsp4 = driver
			.findElement(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[3]/div"));

	String IncomingAmount = incomingamountterstfsp4.getText();
	
	System.out.println(IncomingAmount);

	Assert.assertEquals(IncomingAmount, "55.0000");

}
public void verify_Outgoing_Transactions_CurrentWindow_TestFsp3() {

	WebElement outgoingcurrencytestfsp3 = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[1]/div"));

	OutGOingCurrency = outgoingcurrencytestfsp3.getText();
	
	System.out.println(OutGOingCurrency);
	
	Assert.assertEquals(OutGOingCurrency, "XOF");

	WebElement outgoingnooftransactionstestfsp3 = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[2]/div"));

	OutGoingNoOfTransactions = outgoingnooftransactionstestfsp3.getText();
	
	System.out.println(OutGoingNoOfTransactions);
	
	Assert.assertEquals(OutGoingNoOfTransactions, "1");

	WebElement outgoingamounttestfsp3 = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div/div[4]/div[3]/div"));

	OutGongAmount = outgoingamounttestfsp3.getText();
	
	System.out.println(OutGongAmount);
	
	
	Assert.assertEquals(OutGongAmount, "35.0000");
	
}


public void verify_Incoming_Transactions_CurrentWindow_TestFsp1() {
	

	List <WebElement> incomoingcurrencytestfsp1 = driver
			.findElements(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[1]/div"));
	
	incomoingcurrencytestfsp1.get(1);

	String IncomingCurrency = incomoingcurrencytestfsp1.get(1).getText();

	System.out.println(IncomingCurrency);
	
	Assert.assertEquals(IncomingCurrency, "XOF");

	List <WebElement> incomingnooftranscationstestfsp1 = driver
			.findElements(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[2]/div"));

	String IncomingNoOfTransactions1 = incomingnooftranscationstestfsp1.get(1).getText();
	
	System.out.println(IncomingNoOfTransactions1);

	Assert.assertEquals(IncomingNoOfTransactions1, "1");

	
	List <WebElement> incomingamountterstfsp1 = driver
			.findElements(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[4]/div[3]/div"));

	String IncomingAmount1 = incomingamountterstfsp1.get(1).getText();
	
	System.out.println(IncomingAmount1);

	Assert.assertEquals(IncomingAmount1, "35.0000");

}

public void verify_Outgoing_Transactions_WindowHistory_TestFsp1() {

	WebElement outgoingcurrencywindowhistory = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[7]/div[1]/div[1]"));

	OutGOingCurrency = outgoingcurrencywindowhistory.getText();
	
	Assert.assertEquals(OutGOingCurrency, "XOF");
	
	System.out.println(OutGOingCurrency);
	
	WebElement outgoingnooftransactionswindowhistory = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[7]/div[2]/div[1]"));

	OutGoingNoOfTransactions = outgoingnooftransactionswindowhistory.getText();
	
	Assert.assertEquals(OutGoingNoOfTransactions, "2");
	
	System.out.println(OutGoingNoOfTransactions);

	WebElement outgoingamountwindowhistory = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[7]/div[3]/div[1]"));

	OutGongAmount = outgoingamountwindowhistory.getText();
	
	Assert.assertEquals(OutGongAmount, "115");
	

	System.out.println(OutGongAmount);
}

public void verify_Incoming_Transactions_WindowHistory_TestFsp2() {
	

	WebElement incomoingcurrencytestfsp2 = driver
			.findElement(By.xpath("//div[2]//div[6]//div[1]//div[1]"));

	String IncomingCurrency = incomoingcurrencytestfsp2.getText();

	System.out.println(IncomingCurrency);
	
	Assert.assertEquals(IncomingCurrency, "XOF");

	WebElement incomingnooftranscationstestfsp2 = driver
			.findElement(By.xpath("//div[2]//div[6]//div[2]//div[1]"));

	String IncomingNoOfTransactions = incomingnooftranscationstestfsp2.getText();
	
	System.out.println(IncomingNoOfTransactions);

	Assert.assertEquals(IncomingNoOfTransactions, "1");

	
	WebElement incomingamounttestfsp2 = driver
			.findElement(By.xpath("//div[2]//div[6]//div[3]//div[1]"));

	String IncomingAmount = incomingamounttestfsp2.getText();
	
	System.out.println(IncomingAmount);

	Assert.assertEquals(IncomingAmount, "60");

}

public void verify_Incoming_Transactions_WindowHistory_TestFsp4() {
	

	WebElement incomoingcurrencytestfsp4 = driver
			.findElement(By.xpath("//div[2]//div[5]//div[1]//div[1]"));

	String IncomingCurrency = incomoingcurrencytestfsp4.getText();

	System.out.println(IncomingCurrency);
	
	Assert.assertEquals(IncomingCurrency, "XOF");

	WebElement incomingnooftranscationstestfsp4 = driver
			.findElement(By.xpath("//div[2]//div[5]//div[2]//div[1]"));

	String IncomingNoOfTransactions = incomingnooftranscationstestfsp4.getText();
	
	System.out.println(IncomingNoOfTransactions);

	Assert.assertEquals(IncomingNoOfTransactions, "1");

	
	WebElement incomingamountterstfsp4 = driver
			.findElement(By.xpath("//div[2]//div[5]//div[3]//div[1]"));

	String IncomingAmount = incomingamountterstfsp4.getText();
	
	System.out.println(IncomingAmount);

	Assert.assertEquals(IncomingAmount, "55");

}

public void verify_Outgoing_Transactions_WindowHistory_TestFsp3() {

	WebElement outgoingcurrencytestfsp3 = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[6]/div[1]/div[1]"));

	OutGOingCurrency = outgoingcurrencytestfsp3.getText();
	
	System.out.println(OutGOingCurrency);
	
	Assert.assertEquals(OutGOingCurrency, "XOF");

	WebElement outgoingnooftransactionstestfsp3 = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[6]/div[2]/div[1]"));

	OutGoingNoOfTransactions = outgoingnooftransactionstestfsp3.getText();
	
	System.out.println(OutGoingNoOfTransactions);
	
	Assert.assertEquals(OutGoingNoOfTransactions, "1");

	WebElement outgoingamounttestfsp3 = driver
			.findElement(By.xpath("//div[.//h4[text()='Outgoing Transactions']]/div[1]/div[6]/div[3]/div[1]"));

	OutGongAmount = outgoingamounttestfsp3.getText();
	
	System.out.println(OutGongAmount);
	
	
	Assert.assertEquals(OutGongAmount, "35");
	
}

public void verify_Incoming_Transactions_WindowHistory_TestFsp1() {
	

	List <WebElement> incomoingcurrencytestfsp1 = driver
			.findElements(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[7]/div[1]/div"));
	
	incomoingcurrencytestfsp1.get(1);

	String IncomingCurrency = incomoingcurrencytestfsp1.get(1).getText();

	System.out.println(IncomingCurrency);
	
	Assert.assertEquals(IncomingCurrency, "XOF");

	List <WebElement> incomingnooftranscationstestfsp1 = driver
			.findElements(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[7]/div[2]/div"));

	String IncomingNoOfTransactions1 = incomingnooftranscationstestfsp1.get(1).getText();
	
	System.out.println(IncomingNoOfTransactions1);

	Assert.assertEquals(IncomingNoOfTransactions1, "1");

	
	List <WebElement> incomingamountterstfsp1 = driver
			.findElements(By.xpath("//div[.//h4[text()='Incoming Transactions']]/div/div[7]/div[3]/div"));

	String IncomingAmount1 = incomingamountterstfsp1.get(1).getText();
	
	System.out.println(IncomingAmount1);

	Assert.assertEquals(IncomingAmount1, "35");

}

public void verify_FinancialControl_Ndc_Currency() {
	
	
	List <WebElement> ndccurrencylabel = driver
			.findElements(By.xpath("//div[.//h2[text()='Net Debit Cap Management']]/table/tbody/tr[1]/td[2]"));
	
	String ndccurrency = ndccurrencylabel.get(0).getText();
	
	Assert.assertEquals(ndccurrency, "XOF");

}


public void UpdateNDC_Percentage(String val) throws InterruptedException {
	String ele, but;
	// 10val-ndcManagement-NDC
	// ele=

	// but
	// ="//*[@id=\"root\"]/div/div/div/table/tbody/tr[6]/td/div/div[2]/div/table/tbody/tr/td[5]/button";

	List<WebElement> ndcbox = driver.findElements(By.xpath("//input[contains(@id, 'val-ndcManagement-NDC')]"));
	ndcbox.get(3).click();
	// ndcbox.clear();
	ndcbox.get(3).sendKeys(Keys.LEFT);
	ndcbox.get(3).sendKeys(Keys.LEFT);
	ndcbox.get(3).sendKeys(Keys.LEFT);
	ndcbox.get(3).sendKeys(Keys.LEFT);
	ndcbox.get(3).sendKeys(Keys.DELETE);
	ndcbox.get(3).sendKeys(val);
	
	Thread.sleep(3000);
	
	
	List<WebElement> buttonx = driver.findElements(By.xpath("//button[contains(@id, 'val-ndcManagement-NDC')]"));
	buttonx.get(3).click();
}

public String Ndc_Average_Percentage(String curr) {
	
/*	WebElement ndcaveragepercentage = driver.findElement(By.xpath("//div[8]//div[4]//div[1]"));
	
	String ndcpercentage = ndcaveragepercentage.getText();
	
	System.out.println(ndcpercentage);*/
	String xpathstr="//tr/td/div/div/div/div[.//h4[text()='Position']]/div[2]/div";
    
    
    List<WebElement> postable = driver.findElements(By.xpath(xpathstr));

    int i = 5;
    int j=0;
    String val,porcen ="";
    for (WebElement e : postable) {
       if(j==4) {
    	val = e.findElement(By.xpath(xpathstr+"["+i+"]"+"/div[1]/div")).getText();
        //val = e.
        if (val.equals(curr)) {
        	porcen = e.findElement(By.xpath(xpathstr+"["+i+"]"+"/div[4]/div")).getText();
            
            break;
        }
        i++;
       }else {j++;}
    }
    
    return porcen;
	
	

	
}


public String GetParticipantAccounts(String payerdfsp, String Accounttype,  String curr, String valuexxx) throws IOException {
	URL urlForGetRequest = new URL(PropertiesFile.getaccounturi+payerdfsp+"/accounts");
    String readLine = null;
    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
    conection.setRequestMethod("GET");
    conection.setRequestProperty("FSPIOP-Source", "payerfsp");
    conection.setRequestProperty("Authorization","Bearer "+ PropertiesFile.bearertoken); 
    String status,res,id,currency,ledgerAccountType,isActive,value,val = null;
    int i=0;
    int j=0;
    String[] elements;
    elements = new String[100];
   // conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
    int responseCode = conection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(conection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in .readLine()) != null) {
            response.append(readLine);
        } in .close();
        // print result
        //System.out.println("JSON String Result " + response.toString());
        //GetAndPost.POSTRequest(response.toString());
        
        res=response.toString();
        if(res.length() != 0) {
        	try {
        		JsonReader reader = new JsonReader(new StringReader(res));

                reader.beginArray();
                while (reader.hasNext()) {

                    reader.beginObject();
                    while (reader.hasNext()) {
                    	
                        String name = reader.nextName();

                        if (name.equals("id")) {

                           id = Integer.toString(reader.nextInt());
                           elements[i]=id;
                           i++;

                        } else if (name.equals("ledgerAccountType")) {

                        	ledgerAccountType= reader.nextString();
                        	 elements[i]=ledgerAccountType;
	                           i++;

                        } else if (name.equals("currency")) {

                        	currency=reader.nextString();
                        	 elements[i]=currency;
	                           i++;

                        }else if (name.equals("isActive")) {

                        	isActive=Integer.toString(reader.nextInt());
                        	 elements[i]=isActive;
	                           i++;

                        } else if (name.equals("value")) {
                        	value =String.valueOf(reader.nextDouble()); 
                        	 elements[i]=value;
	                           i++;
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                }
                reader.endArray();

                reader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        	
        	for(j=0; j<i; j++) {
        		if(elements[j].equals(Accounttype)) {
        			
        			if(elements[j+1].equals(curr)) {
        				if("id".equals(valuexxx)) {
        					val =elements[j-1];
        					break;
        					}
        				else {
        				val =elements[j+3];
        				if((val.indexOf('.')+2) != (val.length()-1)) {
        				//	System.out.println(val.indexOf('.')+2);
        				//	 System.out.println(val.length()-1);
        					val=val+"0";
        					break;
        				}else {
        				//	System.out.println(val.indexOf('.')+2);
        				//	 System.out.println(val.length()-1);
        					 break;
        				}
        				}
        			}
        			
        		}
        	}
        	
        	if(i == j && val == null) {
        		System.out.println("Account Not found");
        	}else {
        		System.out.println("JSON String Result " + val);
        	}
        	
        }
        else {
        	System.out.println("Error during API call");
        }
        
        
    } else {
        System.out.println("GET NOT WORKED");
    }
    return val;
}

}
