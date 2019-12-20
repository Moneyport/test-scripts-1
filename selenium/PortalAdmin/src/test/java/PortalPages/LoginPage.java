package PortalPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class LoginPage {
	WebDriver driver;
	
	
	public LoginPage(WebDriver ldriver) {
		this.driver=ldriver;
	}
	
	
	@FindBy(id="1val-login-username") WebElement username;
	@FindBy(id="1val-login-password") WebElement password;
	//@FindBy(xpath="//*[@id=\"root\"]/div/button") WebElement buttonlogin;
	@FindBy(id="1val-login-btn") WebElement buttonlogin;
	
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
