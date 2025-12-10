package utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	protected WebDriver driverTB;
	
	@BeforeMethod
	public void setUp() {
		driverTB = DriverFactory.createDriver();
		driverTB.get("https://amaya.lankapage.lk");
	}
	
	public void executeTest() {
		driverTB.clickLaunchDashboard();
	}
	
	/*@AfterMethod
	public void tearDown() {
		if (driverTB != null) {
			driverTB.quit();
		}
	}*/

}
