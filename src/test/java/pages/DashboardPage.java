package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driverDBP;
	
	@FindBy(xpath = "//a[normalize-space()='LAUNCH DASHBOARD']")
	private WebElement launchDashboardLink;
	
	public DashboardPage(WebDriver driverTB) {
		this.driverDBP = driverTB;
		PageFactory.initElements(driverDBP, this);
	}
	
	public void clickLaunchDashboard() {
		launchDashboardLink.click();
	}

}
