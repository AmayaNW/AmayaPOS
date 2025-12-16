package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestBase;
import pages.DashboardPage;

public class LoginTests extends TestBase {

    @Test(description = "Verify successful login after clicking launch dashboard")
    public void loginAfterLaunchDashboard() {
        openDashboardAndLogin();

        DashboardPage dash = new DashboardPage(driverTB);
        Assert.assertTrue(
            dash.isLaunchDashboardLinkDisplayed(),
            "Login failed! Dashboard not displayed"
        );
    }
}
