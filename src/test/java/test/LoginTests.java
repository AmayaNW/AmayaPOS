package test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.TestBase;

public class LoginTests extends TestBase {
	@Test
	public void loginWithValidCredentials() {
		LoginPage login = new LoginPage(driverTB);
		
		login.enterUsername("admin1");
		login.enterPassword("admin123");
		login.clickLoginBtn();
		
		DashboardPage dash = new DashboardPage(driverTB);
		
		
	}
}
