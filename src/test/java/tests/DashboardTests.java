package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import utils.TestBase;

public class DashboardTests extends TestBase {

    @Test(description = "Verify that the dashboard loads when clicked.")
    public void dashboardLoadTest() {
        // Used helper instead of repeating click + login
        openDashboardAndLogin();

        // Then verified dashboard element
        /*
        DashboardPage dash = new DashboardPage(driverTB);
        Assert.assertTrue(
            dash.isLaunchDashboardLinkDisplayed(),
            "Dashboard did not load after login"
        );
        */
        WebElement welcomeText = waitTB.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='welcome-text']")));
        
        if(welcomeText.isDisplayed()) {
        	System.out.println("Dashboard load successfull.");
        } else {
        	System.out.println("Dashboard load failed.");
        }
        
    }
}
