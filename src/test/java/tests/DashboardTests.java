package tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
        
        List<WebElement> links = driverTB.findElements(By.tagName("a"));
        
        int totalLinksCount = links.size();
        int brokenLinksCount = 0;
        
        for(WebElement link: links) {
        	String hrefVal = link.getAttribute("href");
        	
        	if(hrefVal == null || hrefVal.isEmpty()) {
        		System.out.println("No values in href. Cannot check for stability.");
        	}
        	
        	try {
        		URL hrefURL = new URL(hrefVal);
        		HttpURLConnection connection = (HttpURLConnection) hrefURL.openConnection();
        		connection.connect();
        		
        		int responseCode = connection.getResponseCode();
        		
        		if(responseCode >= 400) {
        			brokenLinksCount++;
        			System.out.println(hrefVal + responseCode + "Link broken.");
        		}else {
        			System.out.println(hrefVal + responseCode + "Link not broken.");
        		}
        		
        	} catch(Exception e) {}
        }
        
        System.out.println("Total Links Count: " + totalLinksCount);
        System.out.println("Broken Links Count: " + brokenLinksCount);
        
    }
}
