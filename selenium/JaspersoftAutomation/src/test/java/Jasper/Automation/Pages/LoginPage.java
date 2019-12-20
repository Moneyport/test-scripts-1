package Jasper.Automation.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class LoginPage {
	WebDriver driver;
	
	
	public LoginPage(WebDriver ldriver) {
		this.driver=ldriver;
	}
	
	
	@FindBy(name="j_username") WebElement username;
	@FindBy(name="j_password_pseudo") WebElement password;
	//@FindBy(xpath="//*[@id=\"root\"]/div/button") WebElement buttonlogin;
	@FindBy(id="submitButton") WebElement buttonlogin;
	
	public void logintoPortal(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		buttonlogin.click();
	}
	
	public void AssertPage() {
		
		Assert.assertEquals(true, username.isDisplayed());
		Assert.assertEquals(true, password.isDisplayed());
		Assert.assertEquals(true, buttonlogin.isDisplayed());
	}

}