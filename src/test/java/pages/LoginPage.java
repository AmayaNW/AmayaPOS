package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driverLP;
	private WebDriverWait waitLP;
	// WebDriverWait waitLP = new WebDriverWait(driverLP, Duration.ofSeconds(10)); Wrong: gives null pointer exception
	
	@FindBy(id = "username")
	WebElement txtUsername;
	
	@FindBy(id = "password")
	WebElement txtPassword;
	
	@FindBy(id = "loginButton")
	WebElement btnLogin;


	public LoginPage(WebDriver driverTB) {
		this.driverLP = driverTB;
		this.waitLP = new WebDriverWait(driverLP, Duration.ofSeconds(5));
		PageFactory.initElements(driverLP, this);
	}
	
	public void enterUsername(String username) {
		waitLP.until(ExpectedConditions.visibilityOf(txtUsername));
		txtUsername.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		waitLP.until(ExpectedConditions.visibilityOf(txtPassword));
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		waitLP.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
	}
	
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginBtn();
	}
}