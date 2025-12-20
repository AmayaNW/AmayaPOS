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
	private WebDriverWait wait;
	
	@FindBy(xpath = "//a[normalize-space()='LAUNCH DASHBOARD']")
	private WebElement launchDashboardLink;
	
	public DashboardPage(WebDriver driverTB) {
		this.driverDBP = driverTB;
		this.wait = new WebDriverWait(driverDBP, Duration.ofSeconds(5));
		PageFactory.initElements(driverDBP, this);
	}
	
	/**
	 * Helper method for assertions in test cases.
	 * @return true if the dashboard link is visible, false otherwise.
	 */
	public boolean isLaunchDashboardLinkDisplayed() {
		wait.until(ExpectedConditions.elementToBeClickable(launchDashboardLink));
		return launchDashboardLink.isDisplayed();
	}
	
	public void clickLaunchDashboard() {
		wait.until(ExpectedConditions.elementToBeClickable(launchDashboardLink));
		launchDashboardLink.click();
	}
	
	// to click "Products" nav-link
	@FindBy(xpath = "//a[@href='products.php'][@class='nav-link text-white ']")
	private WebElement productsLink;
	
	public void clickProducts() {
		wait.until(ExpectedConditions.elementToBeClickable(productsLink));
		productsLink.click();
	}

}