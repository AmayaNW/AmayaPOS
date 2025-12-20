package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	WebDriver driverPP;
	WebDriverWait waitPP;
	
	@FindBy(xpath = "//h1[normalize-space()='Item Management']")
	WebElement itemMgtHead;
	
	public ProductsPage(WebDriver driverTB) {
		this.driverPP = driverTB;
		this.waitPP = new WebDriverWait(driverPP, Duration.ofSeconds(5));
		PageFactory.initElements(driverPP, this);
	}
	
	public boolean isItemMgtHeadDisplayed() {
		try {
			waitPP.until(ExpectedConditions.visibilityOf(itemMgtHead));
			return itemMgtHead.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
}
