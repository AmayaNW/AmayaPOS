package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.DashboardPage;
import pages.LoginPage;

public class TestBase {
	protected WebDriver driverTB;
	protected WebDriverWait waitTB = new WebDriverWait(driverTB, Duration.ofSeconds(8));
	
	/**
	 * Sets up the WebDriver before each test method.
	 */
	@BeforeMethod
	public void setUp() {
		// Use the factory to create a driver (e.g., ChromeDriver)
		driverTB = DriverFactory.createDriver();
		// Navigate to the application URL
		driverTB.get("https://amaya.lankapage.lk");
	}
	
	// Removed the redundant executeTest() method. 
	// Test logic belongs in the test classes.
	
	/**
	 * Tears down the WebDriver after each test method.
	 */
	
	//Added dashboard load and login into a reusable helper
	protected void openDashboardAndLogin() {
	    DashboardPage dash = new DashboardPage(driverTB);
	    dash.clickLaunchDashboard();

	    LoginPage login = new LoginPage(driverTB);
	    login.login("admin1", "admin123");
	}
	
	
	@AfterMethod
	public void tearDown() {
		if (driverTB != null) {
			driverTB.quit();
		}
	}
}