package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.DashboardPage;
import pages.LoginPage;

public class TestBase {
	protected static WebDriver driverTB;
	//protected WebDriverWait waitTB = new WebDriverWait(driverTB, Duration.ofSeconds(8)); Wrong: Gives null pointer exception
	protected WebDriverWait waitTB;
	
	
	/**
	 * Sets up the WebDriver before each test method.
	 */
	@BeforeMethod
	public void setUp() {
		// Use the factory to create a driver (e.g., ChromeDriver)
		driverTB = DriverFactory.createDriver();
		waitTB = new WebDriverWait(driverTB, Duration.ofSeconds(5));
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
	
	// Get current date and time in EEEE, MMMM dd, yyyy, hh.mm a format.
	// helper
	public String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formattedDT = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy, hh.mm a", Locale.ENGLISH);
		return now.format(formattedDT);
	}
	
	
	@AfterMethod
	public void tearDown() {
		if (driverTB != null) {
			driverTB.quit();
		}
	}

	public static WebDriver getDriver() {
		return driverTB;
	}
}