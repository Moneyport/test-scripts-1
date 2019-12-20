package PortalPages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TransferVerificationPage {

	
	  WebDriver driver;
			
		public TransferVerificationPage(WebDriver ldriver) {
			this.driver=ldriver;
		}
		
		
		@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div/div/div/div/input")
		public WebElement transactionidinput;
		@FindBy(xpath="//*[contains(text(), 'Validate')]") public WebElement  Validatebutton;
		
		
	public void AssertPage() {
			
			Assert.assertEquals(true, transactionidinput.isDisplayed());
			Assert.assertEquals(true, Validatebutton.isDisplayed());

		}
		
	
	public void ClickButton() {
		
		Validatebutton.click();
		
		
		

	}
	
	public boolean IsTrasnferFound() {
		/*WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Is Transfer Valid')]")))*/
		//Transfer not found
		boolean x=false;
				if(isDisplaying(By.xpath("//*[contains(text(), 'Is Transfer Valid')]"))){
					 x = true;
				}else if(isDisplaying(By.xpath("//*[contains(text(), 'Transfer not found')]"))) {
					x = false;
				}
				return x;
	}
	
	public boolean isDisplaying(By locator)
	{
	    try
	    {
	        new WebDriverWait(driver, 7).until(ExpectedConditions.visibilityOfElementLocated(locator));
	        return true;
	    }
	    catch (TimeoutException e)
	    {
	        return false;
	    }
	}
	
	
}
