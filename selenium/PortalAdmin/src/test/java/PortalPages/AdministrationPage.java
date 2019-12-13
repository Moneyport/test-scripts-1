package PortalPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AdministrationPage {

WebDriver driver;
	
	
	public AdministrationPage(WebDriver ldriver) {
		this.driver=ldriver;
	}
	//[1]/div/span
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div/div/ul/div/div/span") List<WebElement> rows;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[1]/div[3]/div[2]/div/div/div/input") WebElement NET_DEBIT_CAP_ADJUSTMENT_EMAIL;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/input") WebElement NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[3]/div[3]/div[2]/div/div/div/input") WebElement SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL;
	
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[1]/div[2]/div[2]/div") WebElement CURRENT_DEBIT_CAP_ADJUSTMENT_EMAIL;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div") WebElement CURRENT_NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div") WebElement CURRENT_SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[1]/div[3]/div[2]/div/button/span[1]") WebElement BUTTON_DEBIT_CAP_ADJUSTMENT_EMAIL;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[2]/div[3]/div[2]/div/button/span[1]") WebElement BUTTON_NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/div/div[3]/div[3]/div[2]/div/button/span[1]") WebElement BUTTON_SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL;
	
	
	
	public void ClickOnDFSP(String dfsp) {
		int i=1;
		String dfspxpath="";
		
		for(WebElement e : rows) {
			//System.out.println(e.getText());
	        if((e.getText()).contains(dfsp)) {
	        	dfspxpath= "//*[@id=\"root\"]/div/div/div/div/div/ul/div["+i+"]";
	        	break;
	        }
	        i++;
	    }
		
		if (dfspxpath != ""){
			WebElement dfsp_ = driver.findElement(By.xpath(dfspxpath));
			dfsp_.click();
		}else {
			System.out.println("This DFSP is not listed in the Administration Page");
		}
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Notification Type')]")));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void AssertPage() {
		
		Assert.assertEquals(true, (rows.size() > 0));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public enum NotificationEmail {
		NET_DEBIT_CAP_ADJUSTMENT_EMAIL,
		NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL,
		SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL;
			
	}
	
	public void EnterNewEmail(NotificationEmail email, String emailvalue) {
		
		  switch (email) {
          case NET_DEBIT_CAP_ADJUSTMENT_EMAIL:
        	  NET_DEBIT_CAP_ADJUSTMENT_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_ADJUSTMENT_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_ADJUSTMENT_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_ADJUSTMENT_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_ADJUSTMENT_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_ADJUSTMENT_EMAIL.sendKeys(emailvalue);
        	  BUTTON_DEBIT_CAP_ADJUSTMENT_EMAIL.click();
              break;
          case NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL:
        	  NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.sendKeys(emailvalue);
        	  BUTTON_NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.click();
              break;
          case SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL:
        	  SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.sendKeys(Keys.BACK_SPACE);
        	  SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.sendKeys(emailvalue);
        	  BUTTON_SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.click();
              break;
          default:
              throw new AssertionError("Unknown option " + email);

      }
		  try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
	}
	
	
	public String GetCurrentEmail(NotificationEmail email) {
		
		  switch (email) {
          case NET_DEBIT_CAP_ADJUSTMENT_EMAIL:
        	  return CURRENT_DEBIT_CAP_ADJUSTMENT_EMAIL.getText();
              
          case NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL:
        	  return CURRENT_NET_DEBIT_CAP_THRESHOLD_BREACH_EMAIL.getText();
             
          case SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL:
        	  return CURRENT_SETTLEMENT_TRANSFER_POSITION_CHANGE_EMAIL.getText();
            
          default:
              throw new AssertionError("Unknown email " + email);

      }
		
	}
	
}
