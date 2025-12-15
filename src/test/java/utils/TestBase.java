package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	protected WebDriver driverTB;
	
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
	@AfterMethod
	public void tearDown() {
		if (driverTB != null) {
			driverTB.quit();
		}
	}
}