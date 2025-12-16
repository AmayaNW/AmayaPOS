package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	WebDriver driverDBP;
	
	WebDriverWait waitDBP = new WebDriverWait(driverDBP, Duration.ofSeconds(10));
	
	@FindBy(xpath = "//a[normalize-space()='LAUNCH DASHBOARD']")
	private WebElement launchDashboardLink;
	
	public DashboardPage(WebDriver driverTB) {
		this.driverDBP = driverTB;
		PageFactory.initElements(driverDBP, this);
	}
	
	/**
	 * Helper method for assertions in test cases.
	 * @return true if the dashboard link is visible, false otherwise.
	 */
	public boolean isLaunchDashboardLinkDisplayed() {
		waitDBP.until(ExpectedConditions.elementToBeClickable(launchDashboardLink));
		return launchDashboardLink.isDisplayed();
	}
	
	public void clickLaunchDashboard() {
		waitDBP.until(ExpectedConditions.elementToBeClickable(launchDashboardLink));
		launchDashboardLink.click();
	}

}