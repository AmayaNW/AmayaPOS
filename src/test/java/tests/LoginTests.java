package tests;

import org.testng.Assert; // <--- ADD THIS IMPORT
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.TestBase;

public class LoginTests extends TestBase {
	
	@Test(description = "Verify successful login with valid credentials")
	public void loginWithValidCredentials() {
		// 1. Arrange/Act on LoginPage
		LoginPage login = new LoginPage(driverTB);
		login.login("admin1", "admin123");
		
		// 2. Act/Assert on DashboardPage
		DashboardPage dash = new DashboardPage(driverTB);
		
		// Verify that a key element on the dashboard is displayed
		Assert.assertTrue(dash.isLaunchDashboardLinkDisplayed(), "Login failed! Dashboard link is not displayed.");
	}
}