package Jasper.Automation.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
	
	static String jasperUrl,chromedriverlocation;
	public static String username;
	public static String password;
	public static String startdate;
	public static String enddate;
	public static String participantid;
	public static String settlementwindowid,rows;
	
	public static String env,os;
	
	/*public static void main(String[] args) {
		Readproperties();
	}*/

	public static void Readproperties(){
		Properties prop = new Properties();
		env="dev";
		os="linux";
		
		try {
		 if(os.equals("windows")) {
			 InputStream input = new FileInputStream(".\\src\\test\\java\\Jasper\\Automation\\Utilities\\config.properties");
			    prop.load(input);
			 chromedriverlocation=prop.getProperty("chromedriverlocation");
		 } else if(os.equals("mac")) {
			 InputStream input = new FileInputStream("./src/test/java/Jasper/Automation/Utilities/config.properties");
			    prop.load(input);
			 chromedriverlocation=prop.getProperty("chromedriverlocationmac");
		 }else {
			 InputStream input = new FileInputStream("./src/test/java/Jasper/Automation/Utilities/config.properties");
			    prop.load(input);
			 chromedriverlocation=prop.getProperty("chromedriverlocationlinux");
		 }
		}
        catch (Exception e) {
			
			e.printStackTrace();
		}
		 
		 
		try {
			
		    
		    
		    
		    if(env.equals("dev")) {
		    //DEV
		    	jasperUrl= prop.getProperty("JasperUrldev");
		    	username=prop.getProperty("usernameadmindev");
		    	password=prop.getProperty("passwordadmindev");
		    	startdate=prop.getProperty("usernameuserdev");
		    	enddate=prop.getProperty("passworduserdev");
		    	participantid=prop.getProperty("participantiddev");
		    	settlementwindowid=prop.getProperty("windowiddev");
		    	rows=prop.getProperty("rowsdev");
		   
		    }
		    else if(env.equals("uat")) {
		    //UAT
		    	jasperUrl= prop.getProperty("JasperUrluat");
		    	username=prop.getProperty("usernameadminduat");
		    	password=prop.getProperty("passwordadminuat");
		    	startdate=prop.getProperty("startdate");
		    	enddate=prop.getProperty("enddate");
		    	participantid=prop.getProperty("participantiduat");
		    	settlementwindowid=prop.getProperty("windowiduat");
		    	rows=prop.getProperty("rowsuat");
		    }
		    else if(env.equals("stg")) {
		    	
		    	jasperUrl= prop.getProperty("JasperUrlstg");
		    	username=prop.getProperty("usernameadminstg");
		    	password=prop.getProperty("passwordadminstg");
		    	startdate=prop.getProperty("startdatestg");
		    	enddate=prop.getProperty("enddatestg");
		    	participantid=prop.getProperty("participantidstg");
		    	settlementwindowid=prop.getProperty("windowidstg");
		    	rows=prop.getProperty("rowsstg");
		    	
		    	
		    	
		    }
		    else {
		    	System.out.println("Not valid Environment");
		    }
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
}

