package test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import pages.DashboardPage;
import utils.TestBase;

public class DashboardTests extends TestBase {
	@Test
	public void testLaunchDashboard() {
		DashboardPage dash = new DashboardPage(driverTB);
		dash.clickLaunchDashboard();
	}

}
