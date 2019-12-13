package PortalPages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.PropertiesFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SettlementWindowsPage extends BaseClass {

	  WebDriver driver;
	  String currentwindow;
		
		
		public SettlementWindowsPage(WebDriver ldriver) {
			this.driver=ldriver;
		}
		
		
		
		@FindBy(xpath="//*[contains(text(), 'From')]") WebElement FromDatePicker;
		@FindBy(xpath="//*[contains(text(), 'To')]") WebElement ToDatePicker;
		@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr/th/button/span[1]") List<WebElement> TableSettlementswindows;
		@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr") List<WebElement> TableSettlementsrows;
		//@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr/td[1]") List<WebElement> TableSettlementsStatusColumns;
		@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr/td[2]") List<WebElement> TableSettlementsStatusColumns;
																																		
		@FindBy(xpath="//input[@type=\"date\"]") List<WebElement> datepickers;
		@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr/td[3]") List<WebElement> Createddates;
		
		public void ClickOnSettlementWindow(String windowid) {
			int i=1;
			currentwindow=windowid;
			String windowx="";
			
			for(WebElement e : TableSettlementswindows) {
		        if(windowid.equals(e.getText())) {
		        	windowx= "//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr["+i+"]/th/button";
		        	break;
		        }
		        i++;
		    }
			
			if (windowx != ""){
				WebElement dfsp_ = driver.findElement(By.xpath(windowx));
				dfsp_.click();
			}else {
				System.out.println("This window is not listed");
			}
			WebDriverWait wait = new WebDriverWait(driver, 15);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Settlement Window Details')]")));
		}
		
  public String GetWindowCurrencyAndAmount(String windowid) throws Exception {
			int i=1;
			String val = null;
			String currencycol="";
			
			Thread.sleep(3000);
			
			for(WebElement e : TableSettlementswindows) {
		        if(windowid.equals(e.getText())) {
		        	currencycol= "//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr["+i+"]/td[1]";
		        	break;
		        }
		        i++;
		    }
			
			if (currencycol != ""){
				WebElement dfsp_ = driver.findElement(By.xpath(currencycol));
				val = dfsp_.getText();
			}else {
				System.out.println("This window is not listed");
			}
			return val;
		}
   
		public enum WindowDetailButtons {
		    PAYMENTMATRIX,
		    REPORT312,
		    REPORT644,
		    SETTLEWINDOW,
		    CLOSEWINDOW;
		}

		

	public void ClickOnButton(WindowDetailButtons option) {
		boolean ctrl;
		String val = "";// paymentmatrix.toString();
		String checker;

		switch (option) {
		case PAYMENTMATRIX:
			val = "//*[contains(text(), 'Payment Matrix')]";
			break;
		case REPORT312:
			val = "//*[contains(text(), 'HUB 312 Report')]";
			break;
		case REPORT644:
			val = "//*[contains(text(), 'HUB 644 Report')]";
			break;
		case SETTLEWINDOW:
			val = "//*[contains(text(), 'Settle Window')]";
			break;
		case CLOSEWINDOW:
			val = "//*[contains(text(), 'Close Window')]";
			break;
		default:
			throw new AssertionError("Unknown option " + option);
		}

		if (option == WindowDetailButtons.SETTLEWINDOW) {
			WebElement dfsp_ = driver.findElement(By.xpath(val));
			dfsp_.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Commit Settlement Window')]")));

			WebElement Yesbutton = driver.findElement(By.xpath("//button/span[contains(text(), 'Yes')]"));
			Yesbutton.click();

		} else if (option == WindowDetailButtons.CLOSEWINDOW) {
			WebElement dfsp_ = driver.findElement(By.xpath(val));
			dfsp_.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Close Settlement Window')]")));

			WebElement Yesbutton = driver.findElement(By.xpath("//button/span[contains(text(), 'Yes')]"));
			Yesbutton.click();

		} else if (option == WindowDetailButtons.PAYMENTMATRIX) {
			//ctrl = isFileDownloaded(currentwindow);
			WebElement dfsp_ = driver.findElement(By.xpath(val));
			dfsp_.click();

			try {
				Thread.sleep(4000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			WebElement dfsp_ = driver.findElement(By.xpath(val));
			dfsp_.click();

			try {
				Thread.sleep(4000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
		
public void AssertPage() {
			
			Assert.assertEquals(true, FromDatePicker.isDisplayed());
			Assert.assertEquals(true, ToDatePicker.isDisplayed());
			Assert.assertEquals(true, (TableSettlementsrows.size() > 0));
            Assert.assertEquals(true,driver.findElement(By.xpath("//*[contains(text(), 'Settlement Window Id')]")).isDisplayed());
			Assert.assertEquals(true,driver.findElement(By.xpath("//*[contains(text(), 'Amount-Currency')]")).isDisplayed());
			Assert.assertEquals(true,driver.findElement(By.xpath("//*[contains(text(), 'State')]")).isDisplayed());
			Assert.assertEquals(true,driver.findElement(By.xpath("//*[contains(text(), 'Start Date')]")).isDisplayed());
			Assert.assertEquals(true,driver.findElement(By.xpath("//*[contains(text(), 'End Date')]")).isDisplayed());
			
			
			
			String txtdate="";
			
			for(WebElement e : Createddates) {
				txtdate=e.getText();
		        if(txtdate == "") {
		        	Assert.assertTrue(0>1);
		        }
		        
		    }
			
			for(WebElement e : Createddates) {
				txtdate=e.getText();
		        if(txtdate == "") {
		        	Assert.assertTrue(0>1);
		        }
		        
		    }
			
		}


	

	public String GetFirstWindow(String windowstatus) {
		String windowid = "";
		int i = 1;
		String windowx = "";

		for (WebElement e : TableSettlementsStatusColumns) {
			if (windowstatus.equals(e.getText())) {
				windowx = "//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr[" + i + "]/th/button/span[1]";
				break;
			}
			i++;
		}

		if (windowx != "") {
			windowid = driver.findElement(By.xpath(windowx)).getText();

		} else {
			System.out.println("This window is not listed");
		}

		return windowid;
	}
	
	public String GetNextWindow(String windowstatus) {
		String windowid = "";
		int i = 1;
		int j = 1;
		String windowx = "";

		for (WebElement e : TableSettlementsStatusColumns) {
			if (windowstatus.equals(e.getText())) {
				if(j == 3) {
				windowx = "//*[@id=\"root\"]/div/div/div/div[2]/div/div/table/tbody/tr[" + i + "]/th/button/span[1]";
				break;}
				else{j++;}
			}
			i++;
		}

		if (windowx != "") {
			windowid = driver.findElement(By.xpath(windowx)).getText();

		} else {
			System.out.println("This window is not listed");
		}

		return windowid;
	}

	public void FromDate(String datex) throws Exception {
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

	public void ToDate(String datex) throws Exception {
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

	public String[] ReadPaymentMatrixEndtoEndID(String filename)
			throws SAXException, IOException, ParserConfigurationException {
		// String[] Elements;
		// try {
		int h = 0;
		// File fXmlFile = new File("C:\\Users\\Emerson\\Downloads\\64.xml");

		File fXmlFile = new File(
				System.getProperty("user.dir") + File.separator + "Downloadfiles" + File.separator + filename); //+".xml"
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();

		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("PmtId");

		String[] Elements = new String[nList.getLength()];

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			// System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				// h = 0;
				Element eElement = (Element) nNode;
				Elements[h] = eElement.getElementsByTagName("EndToEndId").item(0).getTextContent();
				// System.out.println("Staff id : " + eElement.getAttribute("id"));
				// System.out.println("EndToEnd Name : " +
				// eElement.getElementsByTagName("EndToEndId").item(0).getTextContent());

				h++;
				/*
				 * for (int j = 0; j < Elements.length; j++) {
				 * System.out.println("Array EndToEnd Name : " + Elements[j] ); }
				 */
			}
		}
		return Elements;
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	public void AssertLengthEndtoEndID(String[] array) {

		for (int i = 0; i < array.length; i++) {
			Assert.assertEquals(array[i].length(), 10);
		}

	}

	public boolean IsPaymentFileEmpty(String name) {
		boolean val;
		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(
				System.getProperty("user.dir") + File.separator + "Downloadfiles" + File.separator + name);//+".xml"
		if (file.length() == 0) {
			System.out.println("File is empty!!!");
			val = true;
		} else {
			System.out.println("File is not empty!!!");
			val = false;
		}
		return val;
	}

	public String[] ReadPaymentMatrixGetCountryCode(String testdfsp, Is enumx, String filename)
			throws SAXException, IOException, ParserConfigurationException {
		int h = 0;

		File fXmlFile = new File(
				System.getProperty("user.dir") + File.separator + "Downloadfiles" + File.separator + filename);//+".xml"
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		NodeList nList = null;
		;

		doc.getDocumentElement().normalize();

		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());
		if (enumx.equals(Is.Payee)) {
			nList = doc.getElementsByTagName("Cdtr");
		} else if (enumx.equals(Is.Payer)) {
			nList = doc.getElementsByTagName("Dbtr");
		}
		String[] Elements = new String[nList.getLength()];

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			// System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				// h = 0;
				Element eElement = (Element) nNode;
				if (testdfsp.equals(eElement.getElementsByTagName("Nm").item(0).getTextContent())) {
					Elements[h] = eElement.getElementsByTagName("Ctry").item(0).getTextContent();
				}

				// System.out.println("Staff id : " + eElement.getAttribute("id"));
				// System.out.println("EndToEnd Name : " +
				// eElement.getElementsByTagName("EndToEndId").item(0).getTextContent());

				h++;
				/*
				 * for (int j = 0; j < Elements.length; j++) {
				 * System.out.println("Array EndToEnd Name : " + Elements[j] ); }
				 */
			}
		}

		return removeNulls(Elements);
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	public void AssertCountryCodeforDFSP(String cc, String[] array) {
		if (array.length != 0) {
			for (int i = 0; i < array.length; i++) {
				Assert.assertEquals(array[i], cc);
			}
		} else {
			Assert.assertEquals(false, true);
			System.out.println("Country codes array is empty");
		}
	}

	public void AssertUsrdField(String[] array) {
		String regex = "Settlement Window [0-9]+";
		for (int i = 0; i < array.length; i++) {
			Assert.assertTrue(array[i].matches(regex));
		}

	}

	public String[] ReadPaymentMatrixUstrd(String filename)
			throws SAXException, IOException, ParserConfigurationException {
		// String[] Elements;
		// try {
		int h = 0;
		// File fXmlFile = new File("C:\\Users\\Emerson\\Downloads\\64.xml");

		File fXmlFile = new File(
				System.getProperty("user.dir") + File.separator + "Downloadfiles" + File.separator + filename); //+".xml"
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();

		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("RmtInf");

		String[] Elements = new String[nList.getLength()];

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			// System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				// h = 0;
				Element eElement = (Element) nNode;
				Elements[h] = eElement.getElementsByTagName("Ustrd").item(0).getTextContent();
				// System.out.println("Staff id : " + eElement.getAttribute("id"));
				// System.out.println("EndToEnd Name : " +
				// eElement.getElementsByTagName("EndToEndId").item(0).getTextContent());

				h++;
				/*
				 * for (int j = 0; j < Elements.length; j++) {
				 * System.out.println("Array EndToEnd Name : " + Elements[j] ); }
				 */
			}
		}
		return Elements;
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	public void close_Window_Button() {

		WebElement closewindow = driver
				.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[3]/button[1]/span[1]"));

		closewindow.click();

	}

	public void close_Window_X() {

		WebElement closewindowX = driver
				.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/button[1]/span[1]/*"));

		closewindowX.click();

	}

	public void check_WindowClosed() {

		Assert.assertEquals(true,
				driver.findElement(By.xpath("//h3[contains(text(),'Settlement Window Id')]")).isDisplayed());
	}

	public void windowid_Count(String text) throws InterruptedException {

		Thread.sleep(2000);

		WebElement pageination = driver.findElement(By.xpath("//select")); //// *[contains(text(),'Settlement Window
																			//// Id')]

		pageination.click();

		Select numberofRows = new Select(pageination);

		// ScrollDown();

		numberofRows.selectByVisibleText(text);

		// ScrollDown();

		// pageination.click();

		int pagecount = Integer.parseInt(text);

		Thread.sleep(3000);

		List<WebElement> rowList = driver.findElements(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr"));

		int windowcount = rowList.size();

		System.out.println("windows count   " + windowcount);

		Assert.assertEquals(windowcount, pagecount);

	}

	public void assert_Settlement_window_Details() {

		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Payment Matrix')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'HUB 312 Report')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'HUB 644 Report')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Settle Window')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Close Window')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Settlement Window Id')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Status')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Total Amount')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Currency')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Start DateTime')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'End DateTime')]")).isDisplayed());

	}

	public void review_Settlement_windows() {

		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Settlement Window Id')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Status')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Total Amount')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Currency')]")).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//*[contains(text(), 'Start DateTime')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'End DateTime')]")).isDisplayed());

	}

// temporary use of method 

	public void openWindow() {

		driver.findElement(By.xpath("//span[contains(text(),'524')]")).click();

	}

	public void assert_SettlementWindow_Details() {

		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'FSP ID')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'In Amount')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'In Amount')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(), 'Net Amount')]")).isDisplayed());
		List<WebElement> fsprows = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div[2]/table/tbody/tr"));
		System.out.println("Number of rows=  " + fsprows.size());
		Assert.assertEquals(fsprows.size(), 2);

	}

	public void validate_pagination_links() throws InterruptedException {

		driver.findElement(By.xpath("//button[@aria-label='Next Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'459')]")).isDisplayed());

		driver.findElement(By.xpath("//button[@aria-label='Previous Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'469')]")).isDisplayed());

		driver.findElement(By.xpath("//button[@aria-label='Last Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'452')]")).isDisplayed());

		driver.findElement(By.xpath("//button[@aria-label='First Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'465')]")).isDisplayed());

	}

	public void assert_FromDate_ToDate() {
		
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'From')]")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[contains(text(),'To')]")).isDisplayed());
		
		
	}
	public void Verify_Windowids_IsDisplayed(String win1, String win2) {
		//dev 1277 and 1287
		//uat 516 and 515
		
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'"+win1+"')]")).isDisplayed());

		//Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'"+win2+"')]")).isDisplayed());
	
	}
	
	public void navigation_Links(String next, String prev, String last) throws InterruptedException {
		
		driver.findElement(By.xpath("//button[@aria-label='Next Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'"+next+"')]")).isDisplayed());

		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@aria-label='Previous Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'"+prev+"')]")).isDisplayed());
		
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@aria-label='Last Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'"+last+"')]")).isDisplayed());
		
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@aria-label='First Page']")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'"+prev+"')]")).isDisplayed());
	}
	
	public void scroll() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,2000)");
		
		
	}
	
	public void verify_Window_Status() {////tbody//tr[1]//td[contains(text(),'PENDING_SETTLEMENT')]
		
		Assert.assertEquals(true, driver.findElement(By.xpath("//tbody//tr[2]//td[contains(text(),'PENDING_SETTLEMENT')]")).isDisplayed());
	
	}
	
	
	
public void verify_Settlement_Account_Balance() {
	
	Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'115.00')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'XOF')]")).isDisplayed());
	
	List<WebElement> fsprows = driver.findElements(By.xpath("//html/body/div[2]/div[2]/div/div[2]/div[2]/table/tbody/tr"));
	System.out.println("Number of rows=  " + fsprows.size());
	Assert.assertEquals(fsprows.size(), 4);
	
}
	
	
public void verify_Currency_And_Amount_Using_One_CurrencyType() {
	
	Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'25.00')]")).isDisplayed());
	Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'XOF')]")).isDisplayed());
}

public void validateDateRange() {
	WebElement fromdate=driver.findElement(By.xpath("//tr[1]//td[3]"));
	String FromDate=fromdate.getText();
	String FDate=FromDate.substring(0, 10);
	Assert.assertEquals(FDate, "2019-05-13");
	
	
	WebElement todate=driver.findElement(By.xpath("//tr[1]//td[4]"));
	String ToDate=todate.getText();
	String TDate=ToDate.substring(0, 10);
	Assert.assertEquals(TDate, "2019-05-14");	
	
}

public void invalid_Date_Range_Zero_SettlmentWindows() {
	
	Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'0-0 of 0')]")).isDisplayed());
}

}
