package Jasper.Automation.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.*;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.testng.Reporter;
import org.testng.reporters.XMLReporter;
import org.testng.ITestResult;
//import org.exp.annotations.Xray;

//import org.testng.internal.PropertiesFile;
import Jasper.Automation.Utilities.PropertiesFile;

import Jasper.Automation.Utilities.BrowserFactory;


public class BasePage {

	public WebDriver driver;
	public static String quoteid,transferid,ilpPacket,condition;
	
	
	@BeforeMethod
	public void setup() {
		driver=BrowserFactory.StartApp(driver, "Chrome");
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		BrowserFactory.quitbrowser(driver);
	}
	
	public void RefreshPage() {
		driver.navigate().refresh();
	}
	
	
	public void ClickOnButton(WebElement ele) {
		ele.click();
	}
	
	public void SendText(WebElement ele, String txt) {
		ele.sendKeys(txt);
	}
	
	public void ClearTxtBox(WebElement ele) {
		ele.clear();
	}
	
	
	public boolean isFileDownloaded(String fileName) {
		  File dir = new File(System.getProperty("user.dir") + File.separator + "Downloadfiles");
		  File[] dirContents = dir.listFiles();
		  String fileNamex;
		  int lastPeriodPos;

		  for (int i = 0; i < dirContents.length; i++) {
			  //System.out.println(dirContents[i].getName());
			  fileNamex = dirContents[i].getName();
              lastPeriodPos = fileNamex.lastIndexOf('.');
              if (lastPeriodPos > 0)
              fileNamex = fileNamex.substring(0, lastPeriodPos);
		      if (fileNamex.equals(fileName)) {
		          // File has been found, it can now be deleted:
		    	  //System.out.println(fileNamex);
		          dirContents[i].delete();
		          return true;
		      }
		          }
		      return false;
		  }
	
	
	public String lastFileModified() {
		String dir =System.getProperty("user.dir") + File.separator + "Downloadfiles";
		//String dir =".\\Downloads";
		String fileNamex;
		int lastPeriodPos;
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    
	    fileNamex = choice.getName();
        lastPeriodPos = fileNamex.lastIndexOf('.');
        if (lastPeriodPos > 0)
        fileNamex = fileNamex.substring(0, lastPeriodPos);
        
	    return fileNamex;
	}
	
	public void PopUpHandler(String txt) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);      
		Alert alert = wait.until(ExpectedConditions.alertIsPresent()); 
		if (txt.equals("ok") || txt.equals("OK") ) {
		   alert.accept();
		} else if(txt.equals("Cancel") || txt.equals("CANCEL")) {
			alert.dismiss();
		}
	}
	
	
	public void Wait(int x) {
		x = x * 1000;
		try {
			Thread.sleep(x);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Logout() {
		//btnLogout
		WebElement ele = driver.findElement(By.id("btnLogout"));
		ClickOnButton(ele);
	}
	
	
	
	
	public void ScrollDown() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static String GenerateUUID() throws IOException {
		 UUID uuid = UUID.randomUUID();
	     String randomUUIDString = uuid.toString();
	     return randomUUIDString;
	}
	
/*	public static String SendQuote(String payerdfsp, String payeedfsp, String token) throws IOException {
		quoteid= GenerateUUID();
		transferid=GenerateUUID();
		String responsecode;
	    final String POST_PARAMS = "{\n" + "\"quoteId\": "+"\""+quoteid+"\""+",\r\n" +
	        "    \"transactionId\": "+"\""+transferid+"\""+",\r\n" +
	        "    \"payee\": { \r\n" +
	        "          \"partyIdInfo\": { \r\n" +
	        "    \"partyIdType\": \"MSISDN\",  \r\n" + 
	        "    \"partyIdentifier\": \"27713803915\", \r\n" + 
	        "    \"fspId\": " +"\""+payeedfsp+"\""+ " \r\n" + 
	        "    } \r\n" + 
	        "    }, \r\n" +
	        "    \"payer\": { \r\n" +
	        "        \"partyIdInfo\": { \r\n" +
	        "    \"partyIdType\": \"MSISDN\",  \r\n" + 
	        "    \"partyIdentifier\": \"27713803905\", \r\n" + 
	        "    \"fspId\": "+"\""+payerdfsp+"\""+" \r\n" + 
	        "    }, \r\n" + 
	        " \"personalInfo\": { \r\n" +
	        " \"complexName\": { \r\n" +
	        "    \"firstName\": \"Mats\",  \r\n" + 
	        "    \"lastName\": \"Hagman\" \r\n" + 
	        "    }, \r\n" +
	        "    \"dateOfBirth\": \"1983-10-25\" \r\n" + 
	        "    } \r\n" +
	        "    }, \r\n" +
	        "    \"amountType\": \"SEND\",  \r\n" + 
	        " \"amount\": { \r\n" +
	        "    \"amount\": \"1\",  \r\n" + 
	        "    \"currency\": \"XOF\" \r\n" + 
	        "    }, \r\n" +
	        " \"transactionType\": { \r\n" +
	        "    \"scenario\": \"TRANSFER\",  \r\n" + 
	        "    \"initiator\": \"PAYER\", \r\n" + 
	        "    \"initiatorType\": \"CONSUMER\" \r\n" + 
	        "    }, \r\n" +
	        "    \"note\": \"hej\"  \r\n" + 
	        " } \n";
	    //System.out.println(POST_PARAMS);
	    DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
		Date date = new Date();
		String strDate = dateFormat.format(date);
		//strDate = strDate;
				
	    URL obj = new URL(PropertiesFile.quotesuri);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Authorization","Bearer "+ token); 
	    postConnection.setRequestProperty("Accept", "application/vnd.interoperability.quotes+json;version=1");
	    postConnection.setRequestProperty("Content-Type", "application/vnd.interoperability.quotes+json;version=1.0");
	    postConnection.setRequestProperty("Accept", "application/vnd.interoperability.quotes+json;version=1");
	    postConnection.setRequestProperty("Date", strDate);
	    postConnection.setRequestProperty("FSPIOP-Source", payerdfsp);
	    postConnection.setRequestProperty("FSPIOP-Destination", payeedfsp);
	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
	    responsecode= String.valueOf(responseCode);
	    return responsecode;
	 
	}
	
	
	
	public static String GetQuoteInfo(String payerdfsp, String quote) throws IOException {
	    URL urlForGetRequest = new URL(PropertiesFile.simulator +"/"+payerdfsp+"/correlationid/"+quote);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	   String status,res = null;
	   
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_ACCEPTED) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        // print result
	       // System.out.println("JSON String Result " + response.toString());
	        res=response.toString();
	        if(res.length() != 0) {
	        JSONObject jsonObject = new JSONObject(response.toString());
	        //JSONObject myResponse = jsonObject.getJSONObject("MyResponse");
	        String ilppack = (String) jsonObject.get("ilpPacket");
	        String con = (String) jsonObject.get("condition");

	        ilpPacket = ilppack;
	        condition = con;
	        System.out.println("IlpPacket " + ilpPacket);
	        System.out.println("Condition " + condition);
	        status ="OK";
	        }else {
	        	status ="FAILED";
	        }
	        
	       
	    } else {
	        System.out.println("QUOTE INFO NOT RETURNED");
	        status ="FAILED";
	    }
	    
	    return status;
	}
	
	
	
	public static  String SendTransfer(String payerdfsp, String payeedfsp, String token, String transferidx,String ilppacket,String condition) throws IOException {
		
		    DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
		    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
			Date date = new Date();
			String strDate = dateFormat.format(date);
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			String expDate = dateFormat2.format(date);
			String status,res = null;
			
			
	    final String POST_PARAMS = "{\r\n" + 
	    		"  \"transferId\": "+"\""+transferidx+"\""+",\r\n" + 
	    		"  \"payerFsp\": "+"\""+payerdfsp+"\""+",\r\n" + 
	    		"  \"payeeFsp\": "+"\""+payeedfsp+"\""+",\r\n" + 
	    		"  \"amount\": {\r\n" + 
	    		"    \"amount\": \"1\",\r\n" + 
	    		"    \"currency\": \"XOF\" \r\n" + 
	    		"  },\r\n" + 
	    		"  \"expiration\": "+"\""+expDate+"\""+",\r\n" + 
	    		"  \"ilpPacket\": "+"\""+ilppacket+"\""+",\r\n" + 
	    		"  \"condition\": "+"\""+condition+"\""+"\r\n" + 
	    		"}";
	  //  System.out.println(POST_PARAMS);
	  
		
		//strDate = strDate;
				
	    URL obj = new URL(PropertiesFile.transfersuri);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Authorization","Bearer "+ token); 
	    postConnection.setRequestProperty("Accept", "application/vnd.interoperability.transfers+json;version=1");
	    postConnection.setRequestProperty("Content-Type", "application/vnd.interoperability.transfers+json;version=1.0");
	    postConnection.setRequestProperty("Date", strDate);
	    postConnection.setRequestProperty("FSPIOP-Source", payerdfsp);
	    postConnection.setRequestProperty("FSPIOP-Destination", payeedfsp);
	    postConnection.setRequestProperty("FSPIOP-HTTP-Method", "POST");
	    postConnection.setRequestProperty("FSPIOP-Signature", "{\"signature\":\"iU4GBXSfY8twZMj1zXX1CTe3LDO8Zvgui53icrriBxCUF_wltQmnjgWLWI4ZUEueVeOeTbDPBZazpBWYvBYpl5WJSUoXi14nVlangcsmu2vYkQUPmHtjOW-yb2ng6_aPfwd7oHLWrWzcsjTF-S4dW7GZRPHEbY_qCOhEwmmMOnE1FWF1OLvP0dM0r4y7FlnrZNhmuVIFhk_pMbEC44rtQmMFv4pm4EVGqmIm3eyXz0GkX8q_O1kGBoyIeV_P6RRcZ0nL6YUVMhPFSLJo6CIhL2zPm54Qdl2nVzDFWn_shVyV0Cl5vpcMJxJ--O_Zcbmpv6lxqDdygTC782Ob3CNMvg\\\",\\\"protectedHeader\\\":\\\"eyJhbGciOiJSUzI1NiIsIkZTUElPUC1VUkkiOiIvdHJhbnNmZXJzIiwiRlNQSU9QLUhUVFAtTWV0aG9kIjoiUE9TVCIsIkZTUElPUC1Tb3VyY2UiOiJPTUwiLCJGU1BJT1AtRGVzdGluYXRpb24iOiJNVE5Nb2JpbGVNb25leSIsIkRhdGUiOiIifQ\"}");
	    postConnection.setRequestProperty("FSPIOP-URI", "/transfers");
	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    int responseCode = postConnection.getResponseCode();
	    System.out.println("Transfer POST Response Code :  " + responseCode);
	    System.out.println("Transfer POST Response Message : " + postConnection.getResponseMessage());
	    res=String.valueOf(responseCode);
      return res;
	
	}
	
	
	
	public static String GetTransfersInfo(String payerdfsp, String tran) throws IOException {
	    //URL urlForGetRequest = new URL(PropertiesFile.simulator +"/"+payerdfsp+"/callbacks/"+tran);
		  URL urlForGetRequest = new URL(PropertiesFile.simulator +"/"+payerdfsp+"/correlationid/"+tran);
		String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    String transferstatus="";
	  
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_ACCEPTED) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        // print result
	        System.out.println("JSON String Result " + response.toString());
	        JSONObject jsonObject = new JSONObject(response.toString());
	      //  JSONObject data = jsonObject.getJSONObject("data");
	      //  transferstatus = (String) data.get("transferState");
	        transferstatus = (String) jsonObject.get("transferState");
	        
	        System.out.println("Transfer Status " + transferstatus);
	        
	        
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
	    return transferstatus;
	}
	
	
	
	public void SendTransfer(String payer, String payee, String token) throws IOException {
		String QuoteStatus,TransferStatus;
		QuoteStatus = SendQuote(payer, payee, token);
		Assert.assertEquals(QuoteStatus, "202");
		QuoteStatus = GetQuoteInfo(payer,quoteid);
		Assert.assertEquals(QuoteStatus, "OK");
		TransferStatus = SendTransfer(payer, payee, token,transferid,ilpPacket,condition);
		Assert.assertEquals(TransferStatus, "202");
		TransferStatus = GetTransfersInfo(payer,transferid);
		Assert.assertEquals(TransferStatus, "COMMITTED");
	}
	
	
	String[] removeNulls(String[] nullsArray) {
	    int countNulls = 0;

	    for (int i = 0; i < nullsArray.length; i++) { // count nulls in array
	        if (nullsArray[i] == null) {
	            countNulls++;
	        }
	    }
	    // creating new array with new length (length of first array - counted nulls)
	    String[] nullsRemoved = new String[nullsArray.length - countNulls];

	    for (int i = 0, j = 0; i < nullsArray.length; i++) {

	        if (nullsArray[i] != null) {
	            nullsRemoved[j] = nullsArray[i];
	            j++;
	        }
	    }
	    return nullsRemoved;
	}
	
	public void AddTestCaseID(String testcase) {
       
		ITestResult result = Reporter.getCurrentTestResult();
        
        result.setAttribute("test", testcase);    
	}*/
	
}