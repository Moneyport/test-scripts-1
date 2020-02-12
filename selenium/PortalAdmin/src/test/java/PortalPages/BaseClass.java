package PortalPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;									 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;						
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyPair;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.json.*;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.google.gson.stream.JsonReader;										 
import org.testng.Reporter;
import org.testng.reporters.XMLReporter;
import org.testng.ITestResult;
import Portal.Utilities.PropertiesFile;

import Portal.Utilities.BrowserFactory;
import PortalPages.SettlementWindowsPage.WindowDetailButtons;
import java.util.Base64;
import java.security.Key;
import java.security.KeyPair;
//import org.openqa.selenium.Keys;

public class BaseClass {

	//public ThreadLocal<WebDriver> driver;
	public WebDriver driver; 
	public static String quoteid,transferid;
	public static String ilpPacket="AQAAAAAAAADIEHByaXZhdGUucGF5ZWVmc3CCAiB7InRyYW5zYWN0aW9uSWQiOiIyZGY3NzRlMi1mMWRiLTRmZjctYTQ5NS0yZGRkMzdhZjdjMmMiLCJxdW90ZUlkIjoiMDNhNjA1NTAtNmYyZi00NTU2LThlMDQtMDcwM2UzOWI4N2ZmIiwicGF5ZWUiOnsicGFydHlJZEluZm8iOnsicGFydHlJZFR5cGUiOiJNU0lTRE4iLCJwYXJ0eUlkZW50aWZpZXIiOiIyNzcxMzgwMzkxMyIsImZzcElkIjoicGF5ZWVmc3AifSwicGVyc29uYWxJbmZvIjp7ImNvbXBsZXhOYW1lIjp7fX19LCJwYXllciI6eyJwYXJ0eUlkSW5mbyI6eyJwYXJ0eUlkVHlwZSI6Ik1TSVNETiIsInBhcnR5SWRlbnRpZmllciI6IjI3NzEzODAzOTExIiwiZnNwSWQiOiJwYXllcmZzcCJ9LCJwZXJzb25hbEluZm8iOnsiY29tcGxleE5hbWUiOnt9fX0sImFtb3VudCI6eyJjdXJyZW5jeSI6IlVTRCIsImFtb3VudCI6IjIwMCJ9LCJ0cmFuc2FjdGlvblR5cGUiOnsic2NlbmFyaW8iOiJERVBPU0lUIiwic3ViU2NlbmFyaW8iOiJERVBPU0lUIiwiaW5pdGlhdG9yIjoiUEFZRVIiLCJpbml0aWF0b3JUeXBlIjoiQ09OU1VNRVIiLCJyZWZ1bmRJbmZvIjp7fX19";
	public static String condition="HOr22-H3AfTDHrSkPjJtVPRdKouuMkDXTR4ejlQa8Ks";
	
	
    public BrowserFactory browserFactory;
    public static String env = PropertiesFile.env;
	
	@BeforeMethod
	public void setup() throws Exception  {
		//PropertiesFile.Readproperties();
		
		//driver=BrowserFactory.StartApp(driver, "Chrome");
		BrowserFactory browserFactory = BrowserFactory.getInstance();
		browserFactory.setDriver("Chrome");
		driver = browserFactory.getDriver();
		browserFactory.getDriver().get(PropertiesFile.PortalUrl);
		browserFactory.getDriver().manage().window().maximize();
		browserFactory.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		browserFactory.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//System.out.println("Im in setup |"+Thread.currentThread().getId());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException {
		// Add a screen shot script 
		
	/*	String fileName=result.getName();
		
		String sourcePath="/Users/satishkumar/Documents/workspace/test-scripts/selenium/PortalAdmin/test-output/screenshots/"+fileName+".png";
		
		if(ITestResult.FAILURE==result.getStatus()) {
			try {
				
				TakesScreenshot ts=(TakesScreenshot)driver;
				 
				// Call method to capture screenshot
				File source=ts.getScreenshotAs(OutputType.FILE);
				
				FileUtils.copyFile(source, new File(sourcePath));
				
				
				
			}catch(Exception e){
			
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
			
		}*/
		
		
		 BrowserFactory.quitbrowser(driver);
		 //System.out.println("Im in teardown |"+Thread.currentThread().getId());
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
	
	
	
	
	public static String GenerateUUID() throws IOException {
		 UUID uuid = UUID.randomUUID();
	     String randomUUIDString = uuid.toString();
	     return randomUUIDString;
	}
	
	
	public void ScrollDown() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		action.sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).build().perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
public void ScrollUp() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		action.sendKeys(org.openqa.selenium.Keys.PAGE_UP).build().perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	
	
	
	public static String SendQuote(String payerdfsp, String payeedfsp, String amount, String token) throws IOException {
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
	        "    \"amount\":"+"\""+amount+"\""+",  \r\n" + 
	        "    \"currency\": \"USD\" \r\n" + 
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
		
		if((PropertiesFile.env).equals("stg") || (PropertiesFile.env).equals("uat")|| (PropertiesFile.env).equals("sandbox")) {
		  System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-11.0.2\\lib\\security\\cacerts");
		    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		    System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		    System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Emerson\\Downloads\\latest certificate\\finalcombined.pfx");
		    System.setProperty("javax.net.ssl.keyStorePassword", "wso2carbon");
		    System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		}
				
	    URL obj = new URL(PropertiesFile.quotesuri);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    //postConnection.setRequestProperty("Authorization","Bearer "+ token); 
	    postConnection.setRequestProperty("Accept", "application/vnd.interoperability.quotes+json;version=1");
	    postConnection.setRequestProperty("Content-Type", "application/vnd.interoperability.quotes+json;version=1.0");
	    //postConnection.setRequestProperty("Accept", "application/vnd.interoperability.quotes+json;version=1");
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

	
/*	public static String SendQuote(String payerdfsp, String payeedfsp, String amount, String token,String curr) throws IOException {
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
	        "    \"amount\":"+"\""+amount+"\""+",  \r\n" + 
	        "    \"currency\":"+"\""+curr+"\""+" \r\n" + 
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
		
	//	if((PropertiesFile.env).equals("stg") || (PropertiesFile.env).equals("uat")|| (PropertiesFile.env).equals("sandbox")) {
		  System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-11.0.2\\lib\\security\\cacerts");
		    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		    System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		    System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Emerson\\Downloads\\latest certificate\\finalcombined.pfx");
		    System.setProperty("javax.net.ssl.keyStorePassword", "wso2carbon");
		    System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
	//	}
		String header = "{\"alg\":\"RS256\",\"FSPIOP-Source\":\"payerfsp\",\"FSPIOP-Destination\":\"payeefsp\", \"FSPIOP-URI\":\"/quotes\", \"FSPIOP-HTTP-Method\":\"POST\", \"Date\":"+strDate+"}";
		String claims = POST_PARAMS;
	
	    KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

	    String tokenxxx = Jwts.builder().setSubject(claims).signWith(keyPair.getPrivate()).compact();
	    
	    
				
	    //URL obj = new URL("https://extgw.public.tips-sandbox.live:8243/fsp/1.0/quotes");
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
	    postConnection.setRequestProperty("FSPIOP-Signature", tokenxxx);
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
	*/
	
	public static String GetQuoteInfo(String payerdfsp, String quote) throws IOException {
	   // URL urlForGetRequest = new URL(PropertiesFile.simulator +"/"+payerdfsp+"/callbacks/"+quote);
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
	
	
	
	public static  String SendTransfer(String payerdfsp, String payeedfsp, String amount, String token, String transferidx,String ilppacket,String condition) throws IOException {
		
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
	    		"    \"amount\": "+"\""+amount+"\""+",\r\n" + 
	    		"    \"currency\": \"USD\" \r\n" + 
	    		"  },\r\n" + 
	    		"  \"expiration\": "+"\""+expDate+"\""+",\r\n" + 
	    		"  \"ilpPacket\": "+"\""+ilppacket+"\""+",\r\n" + 
	    		"  \"condition\": "+"\""+condition+"\""+"\r\n" + 
	    		"}";
	 
				
	    URL obj = new URL(PropertiesFile.transfersuri);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	 //   postConnection.setRequestProperty("Authorization","Bearer "+ token); 
	    postConnection.setRequestProperty("Accept", "application/vnd.interoperability.transfers+json;version=1");
	    postConnection.setRequestProperty("Content-Type", "application/vnd.interoperability.transfers+json;version=1.0");
	    postConnection.setRequestProperty("Date", strDate);
	    postConnection.setRequestProperty("FSPIOP-Source", payerdfsp);
	    postConnection.setRequestProperty("FSPIOP-Destination", payeedfsp);
	    postConnection.setRequestProperty("FSPIOP-HTTP-Method", "POST");
	  //  postConnection.setRequestProperty("FSPIOP-Signature", "{\"signature\":\"iU4GBXSfY8twZMj1zXX1CTe3LDO8Zvgui53icrriBxCUF_wltQmnjgWLWI4ZUEueVeOeTbDPBZazpBWYvBYpl5WJSUoXi14nVlangcsmu2vYkQUPmHtjOW-yb2ng6_aPfwd7oHLWrWzcsjTF-S4dW7GZRPHEbY_qCOhEwmmMOnE1FWF1OLvP0dM0r4y7FlnrZNhmuVIFhk_pMbEC44rtQmMFv4pm4EVGqmIm3eyXz0GkX8q_O1kGBoyIeV_P6RRcZ0nL6YUVMhPFSLJo6CIhL2zPm54Qdl2nVzDFWn_shVyV0Cl5vpcMJxJ--O_Zcbmpv6lxqDdygTC782Ob3CNMvg\\\",\\\"protectedHeader\\\":\\\"eyJhbGciOiJSUzI1NiIsIkZTUElPUC1VUkkiOiIvdHJhbnNmZXJzIiwiRlNQSU9QLUhUVFAtTWV0aG9kIjoiUE9TVCIsIkZTUElPUC1Tb3VyY2UiOiJPTUwiLCJGU1BJT1AtRGVzdGluYXRpb24iOiJNVE5Nb2JpbGVNb25leSIsIkRhdGUiOiIifQ\"}");
	 //   postConnection.setRequestProperty("FSPIOP-URI", "/transfers");
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
	
	/*
public static  String SendTransfer(String payerdfsp, String payeedfsp, String amount,String token, String transferidx,String ilppacket,String condition,String curr) throws IOException {
		
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
    		"    \"amount\": "+"\""+amount+"\""+",\r\n" + 
    		"    \"currency\": "+"\""+curr+"\""+"\r\n" +  
    		"  },\r\n" + 
    		"  \"expiration\": "+"\""+expDate+"\""+",\r\n" + 
    		"  \"ilpPacket\": "+"\""+ilppacket+"\""+",\r\n" + 
    		"  \"condition\": "+"\""+condition+"\""+"\r\n" + 
    		"}";
  
    String header = "{\"alg\":\"RS256\",\"FSPIOP-Source\":\"payerfsp\",\"FSPIOP-Destination\":\"payeefsp\", \"FSPIOP-URI\":\"/transfers\", \"FSPIOP-HTTP-Method\":\"POST\", \"Date\":"+strDate+"}";
	String claims = POST_PARAMS;
	
    KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

	String tokenxxx = Jwts.builder().setSubject(claims).signWith(keyPair.getPrivate()).compact();
	    
	    
   // URL obj = new URL("https://extgw.public.tips-sandbox.live:8243/fsp/1.0/transfers");
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
    postConnection.setRequestProperty("FSPIOP-Signature", tokenxxx);
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
*/

	
	
	public static String GetTransfersInfo(String payerdfsp, String tran) throws IOException {
	    
		URL urlForGetRequest = new URL(PropertiesFile.simulator +"/"+payerdfsp+"/correlationid/"+tran);  
		//URL urlForGetRequest = new URL(PropertiesFile.simulator +"/callbacks/"+tran);
		  
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
	     try {
	        transferstatus = (String) jsonObject.get("transferState");
	     }
	     catch(Exception e) {
	    	 transferstatus = "FAILED";
	     }
	        System.out.println("Transfer Status " + transferstatus);
	        
	        
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
	    return transferstatus;
	}
	
	
	
	//public void SendTransfer(String payer, String payee, String amount, String token, String ExpectedResult,String curr) throws IOException{
	public void SendTransfer(String payer, String payee, String amount, String token, String ExpectedResult) throws IOException{	
	String QuoteStatus,TransferStatus;
		if(ExpectedResult != "FAILED") {
		try {
			QuoteStatus = SendQuote(payer, payee, amount , token);
			//QuoteStatus = SendQuote(payer, payee, amount , token, curr);
		
		Thread.sleep(2500);
		Assert.assertEquals(QuoteStatus, "202");
	//	QuoteStatus = GetQuoteInfo(payer,quoteid);
		Thread.sleep(2500);
	//	Assert.assertEquals(QuoteStatus, "OK");
		TransferStatus = SendTransfer(payer, payee, amount,token,transferid,ilpPacket,condition);
		//TransferStatus = SendTransfer(payer, payee, amount,token,transferid,ilpPacket,condition, curr);
		Thread.sleep(3500);
		Assert.assertEquals(TransferStatus, "202");
		TransferStatus = GetTransfersInfo(payer,transferid);
		Thread.sleep(2500);
		Assert.assertEquals(TransferStatus, ExpectedResult);
		}catch(InterruptedException e) {e.printStackTrace();}
		
		}else {
			
			try {
			QuoteStatus = SendQuote(payer, payee, amount, token);
			//QuoteStatus = SendQuote(payer, payee, amount, token, curr);
			Thread.sleep(2500);
		/*	Assert.assertEquals(QuoteStatus, "500");*/
		//	QuoteStatus = GetQuoteInfo(payer,quoteid);
		//	Assert.assertEquals(QuoteStatus, "FAILED");
			TransferStatus = SendTransfer(payer, payee, amount, token,transferid,ilpPacket,condition);
		//	TransferStatus = SendTransfer(payer, payee, amount, token,transferid,ilpPacket,condition, curr);
			Thread.sleep(3500);
			TransferStatus = GetTransfersInfo(payer,transferid);
			Thread.sleep(2500);
			Assert.assertEquals(TransferStatus, ExpectedResult);
			//Assert.assertEquals(TransferStatus, "400");
			}catch(InterruptedException e) {e.printStackTrace();}
		}
	}
	
	
	public static String InactiveDFSPAccount(String dfspname, String accountid, boolean status) throws IOException {
	   
		//if((PropertiesFile.env).equals("stg") || (PropertiesFile.env).equals("uat")|| (PropertiesFile.env).equals("sandbox")) {
		/*	  System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-11.0.2\\lib\\security\\cacerts");
			    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
			    System.setProperty("javax.net.ssl.trustStoreType", "JKS");
			    System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Emerson\\Downloads\\latest certificate\\finalcombined.pfx");
			    System.setProperty("javax.net.ssl.keyStorePassword", "wso2carbon");
			    System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");*/
		//	}
				
		
		URL urlForGetRequest = new URL(PropertiesFile.getaccounturi+dfspname+"/accounts/"+accountid);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("PUT");
	   String res = null;
	   
	   conection.setRequestProperty("Authorization","Bearer "+ PropertiesFile.bearertoken); 

	   conection.setRequestProperty("Content-Type", "application/json");
	   
	   conection.setRequestProperty("FSPIOP-Source", "payerfsp");
	   
	   conection.setDoOutput(true);
	   
	   final String POST_PARAMS = "{\r\n" + 
	    		"  \"isActive\": "+status+"\r\n" + 
	    		"}";
	    		
	    		
	    OutputStream os = conection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    
	    
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	    	res ="OK";
	        }else {
	        	res ="FAILED";
	        }
	        
	       
	  
	    
	    return res;
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
	}
	
	public enum Is {
		  Payer,
		  Payee
		}
	
	
	
	public static String GetParticipantAccounts(String payerdfsp, String Accounttype,  String curr, String valuexxx) throws IOException {
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
	        					System.out.println(val.indexOf('.')+2);
	        					 System.out.println(val.length()-1);
	        					val=val+"0";
	        					break;
	        				}else {
	        					System.out.println(val.indexOf('.')+2);
	        					 System.out.println(val.length()-1);
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
	
	
	public String Getdate(int days) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, days);
		dt = c.getTime();
		String strDate = dateFormat.format(dt);
		return strDate;
	}
}
