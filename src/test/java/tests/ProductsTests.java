package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ProductsPage;
import utils.TestBase;

public class ProductsTests extends TestBase {
	@Test(description = "Verify navigation to Products page from dashboard.") 
	public void testNavigateToProducts() {
		// 1. login and go to dashboard
		openDashboardAndLogin();
		
		// 2. click the products link
		DashboardPage dash = new DashboardPage(driverTB);
		dash.clickProducts();
		
		//3. validate products page header
		ProductsPage products = new ProductsPage(driverTB);
        Assert.assertTrue(products. isItemMgtHeadDisplayed(), "Failed to navigate to Products page or Header not found!");
        
        System.out.println("Successfully navigated to Products page at: " + getCurrentDateTime());
	}
}
