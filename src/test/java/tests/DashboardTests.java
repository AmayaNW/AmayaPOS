package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestBase;
import pages.DashboardPage;

public class DashboardTests extends TestBase {

    @Test(description = "Verify that the dashboard loads when clicked.")
    public void dashboardLoadTest() {
        // Used helper instead of repeating click + login
        openDashboardAndLogin();

        // Then verified dashboard element
        DashboardPage dash = new DashboardPage(driverTB);
        Assert.assertTrue(
            dash.isLaunchDashboardLinkDisplayed(),
            "Dashboard did not load after login"
        );
    }
}
