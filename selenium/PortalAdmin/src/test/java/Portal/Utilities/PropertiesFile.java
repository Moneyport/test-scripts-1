package Portal.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
	
	public static String PortalUrl,chromedriverlocation,fileExt;
	public static String usernameAdmin;
	public static String passwordAdmin;
	public static String usernameUser;
	public static String passwordUser;
	public static String quotesuri;
	public static String transfersuri;
	public static String bearertoken;
	public static String simulator;
	public static String testfsp1token;
	public static String testfsp2token;
	public static String testfsp3token;
	public static String testfsp4token;
	public static String transferidx,getaccounturi;
	public static String env,os;
	
	public static String AutousernameAdmin;
	public static String AutopasswordAdmin;
	public static String AutousernameUser;
	public static String AutopasswordUser;
	
	public static String cctestfsp3;
	public static String cctestfsp4;
	public static String cctestfsp1;
	public static String cctestfsp2;
	
	public static String ccpayer;
	public static String ccpayee;
	public static String win1,win2, next_win,prev_win,last_win,testfsp4accountid;
	public static String frompaginationset,topaginationset;
	
	/*public static void main(String[] args) {
		Readproperties();
	}*/

	public static void Readproperties(){
		Properties prop = new Properties();
		env="dev";
		os="mac";
		
		try {
		 if(os.equals("windows")) {
			 InputStream input = new FileInputStream(".\\src\\test\\java\\Portal\\Utilities\\config.properties");
			    prop.load(input);
			 chromedriverlocation=prop.getProperty("chromedriverlocation");
			 fileExt=prop.getProperty("fileext_win");
		 } else if(os.equals("mac")) {
			 InputStream input = new FileInputStream("./src/test/java/Portal/Utilities/config.properties");
			    prop.load(input);
			 chromedriverlocation=prop.getProperty("chromedriverlocationmac");
			 fileExt=prop.getProperty("fileext_mac");
		 }else {
			 InputStream input = new FileInputStream("./src/test/java/Portal/Utilities/config.properties");
			    prop.load(input);
			 chromedriverlocation=prop.getProperty("chromedriverlocationlinux");
			 fileExt=prop.getProperty("fileext_linux");
		 }
		}
        catch (Exception e) {
			
			e.printStackTrace();
		}
		 
		 
		try {
			
		    
		    
		    
		    if(env.equals("dev")) {
		    //DEV
		    PortalUrl= prop.getProperty("PortalUrldev");
		    usernameAdmin=prop.getProperty("usernameadmindev");
		    passwordAdmin=prop.getProperty("passwordadmindev");
		    usernameUser=prop.getProperty("usernameuserdev");
		    passwordUser=prop.getProperty("passworduserdev");
		    AutousernameAdmin=prop.getProperty("autousernameadmindev");
		    AutopasswordAdmin=prop.getProperty("autopasswordadmindev");
		    AutousernameUser=prop.getProperty("autousernameuserdev");
		    AutopasswordUser=prop.getProperty("autopassworduserdev");
		    
		    quotesuri=prop.getProperty("dev_quotes");
		    transfersuri=prop.getProperty("dev_transfers");
		    bearertoken=prop.getProperty("dev_token");
		    simulator=prop.getProperty("sim_dev");
		    testfsp1token=prop.getProperty("dev_token_testfsp1");
		    testfsp2token=prop.getProperty("dev_token_testfsp2");
		    testfsp3token=prop.getProperty("dev_token_testfsp3");
		    testfsp4token=prop.getProperty("dev_token_testfsp4");
		    transferidx=prop.getProperty("transferiddev");
			 getaccounturi=prop.getProperty("getaccounturidev");
			 cctestfsp1=prop.getProperty("cctestfsp1_DEV");
			 cctestfsp2=prop.getProperty("cctestfsp2_DEV");
			 cctestfsp3=prop.getProperty("cctestfsp3_DEV");
			 cctestfsp4=prop.getProperty("cctestfsp4_DEV");
			 ccpayer=prop.getProperty("ccpayer_DEV");
			 ccpayee=prop.getProperty("ccpayee_DEV");
			 win1=prop.getProperty("win1_DEV");
			 win2=prop.getProperty("win2_DEV");
			 next_win=prop.getProperty("next_win_DEV");
			 prev_win=prop.getProperty("prev_win_DEV");
			 last_win=prop.getProperty("last_win_DEV");
			 testfsp4accountid=prop.getProperty("testfsp4_account_dev");
			 frompaginationset=prop.getProperty("frompaginationset_DEV");
			 topaginationset=prop.getProperty("topaginationset_DEV");
		    }
		    else if(env.equals("uat")) {
		    //UAT
		    PortalUrl= prop.getProperty("PortalUrluat");
		    usernameAdmin=prop.getProperty("usernameadminduat");
		    passwordAdmin=prop.getProperty("passwordadminuat");
		    usernameUser=prop.getProperty("usernameuseruat");
		    passwordUser=prop.getProperty("passworduseruat");
		    
		    AutousernameAdmin=prop.getProperty("autousernameadminuat");
		    AutopasswordAdmin=prop.getProperty("autopasswordadminuat");
		    AutousernameUser=prop.getProperty("autousernameuseruat");
		    AutopasswordUser=prop.getProperty("autopassworduseruat");
		    
		    
		    quotesuri=prop.getProperty("uat_quotes");
		    transfersuri=prop.getProperty("uat_transfers");
		    bearertoken=prop.getProperty("uat_token");
		    simulator=prop.getProperty("sim_uat");
		    testfsp1token=prop.getProperty("uat_token_testfsp1");
		    testfsp2token=prop.getProperty("uat_token_testfsp2");
		    testfsp3token=prop.getProperty("uat_token_testfsp3");
		    testfsp4token=prop.getProperty("uat_token_testfsp4");
		    transferidx=prop.getProperty("transferiduat");
			  getaccounturi=prop.getProperty("getaccounturiuat");
			  cctestfsp1=prop.getProperty("cctestfsp1_UAT");
				 cctestfsp2=prop.getProperty("cctestfsp2_UAT");
				 cctestfsp3=prop.getProperty("cctestfsp3_UAT");
				 cctestfsp4=prop.getProperty("cctestfsp4_UAT");
				 ccpayer=prop.getProperty("ccpayer_UAT");
				 ccpayee=prop.getProperty("ccpayee_UAT");
				 win1=prop.getProperty("win1_UAT");
				 win2=prop.getProperty("win2_UAT");
				 next_win=prop.getProperty("next_win_UAT");
				 prev_win=prop.getProperty("prev_win_UAT");
				 last_win=prop.getProperty("last_win_UAT");
				 testfsp4accountid=prop.getProperty("testfsp4_account_uat");
				 frompaginationset=prop.getProperty("frompaginationset_UAT");
				 topaginationset=prop.getProperty("topaginationset_UAT");
		    }
		    else if(env.equals("stg")) {
		    	
			 PortalUrl= prop.getProperty("PortalUrlstg");
				    usernameAdmin=prop.getProperty("usernameadmindstg");
				    passwordAdmin=prop.getProperty("passwordadminstg");
				    usernameUser=prop.getProperty("usernameuserstg");
				    passwordUser=prop.getProperty("passworduserstg");
				    quotesuri=prop.getProperty("stg_quotes");
				    transfersuri=prop.getProperty("stg_transfers");
				    bearertoken=prop.getProperty("stg_token");
				    simulator=prop.getProperty("sim_stg");
				    testfsp1token=prop.getProperty("stg_token_testfsp1");
				    testfsp2token=prop.getProperty("stg_token_testfsp2");
				    testfsp3token=prop.getProperty("stg_token_testfsp3");
				    testfsp4token=prop.getProperty("stg_token_testfsp4");
				    transferidx=prop.getProperty("transferidstg");
				    getaccounturi=prop.getProperty("getaccounturistg");
				    testfsp4accountid=prop.getProperty("testfsp4_account_stg");
				    
				    cctestfsp1=prop.getProperty("cctestfsp1_STG");
					 cctestfsp2=prop.getProperty("cctestfsp2_STG");
					 cctestfsp3=prop.getProperty("cctestfsp3_STG");
					 cctestfsp4=prop.getProperty("cctestfsp4_STG");
					 ccpayer=prop.getProperty("ccpayer_STG");
					 ccpayee=prop.getProperty("ccpayee_STG");
					 win1=prop.getProperty("win1_STG");
					 win2=prop.getProperty("win2_STG");
					 next_win=prop.getProperty("next_win_STG");
					 prev_win=prop.getProperty("prev_win_STG");
					 last_win=prop.getProperty("last_win_STG");
					 frompaginationset=prop.getProperty("frompaginationset_STG");
					 topaginationset=prop.getProperty("topaginationset_STG");
					 
		    	
		    }  else if(env.equals("sandbox")) {
			    //UAT
			    PortalUrl= prop.getProperty("PortalUrlsb");
			    usernameAdmin=prop.getProperty("usernameadmindsb");
			    passwordAdmin=prop.getProperty("passwordadminsb");
			    usernameUser=prop.getProperty("usernameusersb");
			    passwordUser=prop.getProperty("passwordusersb");
			    
			    AutousernameAdmin=prop.getProperty("autousernameadminsb");
			    AutopasswordAdmin=prop.getProperty("autopasswordadminsb");
			    AutousernameUser=prop.getProperty("autousernameusersb");
			    AutopasswordUser=prop.getProperty("autopasswordusersb");
			    
			    
			    quotesuri=prop.getProperty("sb_quotes");
			    transfersuri=prop.getProperty("sb_transfers");
			    bearertoken=prop.getProperty("sb_token");
			    simulator=prop.getProperty("sim_sb");
			    testfsp1token=prop.getProperty("sb_token_testfsp1");
			    testfsp2token=prop.getProperty("sb_token_testfsp2");
			    testfsp3token=prop.getProperty("sb_token_testfsp3");
			    testfsp4token=prop.getProperty("sb_token_testfsp4");
			    transferidx=prop.getProperty("transferidsb");
				  getaccounturi=prop.getProperty("getaccounturisb");
				  cctestfsp1=prop.getProperty("cctestfsp1_SB");
					 cctestfsp2=prop.getProperty("cctestfsp2_SB");
					 cctestfsp3=prop.getProperty("cctestfsp3_SB");
					 cctestfsp4=prop.getProperty("cctestfsp4_SB");
					 ccpayer=prop.getProperty("ccpayer_SB");
					 ccpayee=prop.getProperty("ccpayee_SB");
					 win1=prop.getProperty("win1_SB");
					 win2=prop.getProperty("win2_SB");
					 next_win=prop.getProperty("next_win_SB");
					 prev_win=prop.getProperty("prev_win_SB");
					 last_win=prop.getProperty("last_win_SB");
					 testfsp4accountid=prop.getProperty("testfsp4_account_sb");
					 frompaginationset=prop.getProperty("frompaginationset_SB");
					 topaginationset=prop.getProperty("topaginationset_SB");
			    }
		    else if(env.equals("prod")) {
		    	
		        PortalUrl= prop.getProperty("PortalUrlprod");
			    usernameAdmin=prop.getProperty("usernameadmindprod");
			    passwordAdmin=prop.getProperty("passwordadminprod");
			    usernameUser=prop.getProperty("usernameuserprod");
			    passwordUser=prop.getProperty("passworduserprod");
			    bearertoken=prop.getProperty("prod_token");
			    simulator=prop.getProperty("sim_prod");
			    transferidx=prop.getProperty("transferidprod");
			    getaccounturi=prop.getProperty("getaccounturiprod");
			    	
			    }
		    else {
		    	System.out.println("Not valid Environment");
		    }
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
}
