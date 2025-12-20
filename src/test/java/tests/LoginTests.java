package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.TestBase;

public class LoginTests extends TestBase {

    @Test(description = "Verify successful login after clicking launch dashboard")
    public void loginAfterLaunchDashboard() {
        openDashboardAndLogin();

     // FIX: Logic Update
        // Checking "isLaunchDashboardLinkDisplayed" usually returns TRUE only if we still on the landing page (Login Failed). 
        // If Login is successful, we expect that link to disappear and a "Welcome" element to appear.
        
        try {
            // Using the welcome-text locator from the DashboardTests
            WebElement welcomeText = waitTB.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='welcome-text']")));
            
            Assert.assertTrue(welcomeText.isDisplayed(), "Login failed! Welcome text not visible.");
        } catch (Exception e) {
            Assert.fail("Login failed or Welcome element not found within timeout.");
        }
    }
}
