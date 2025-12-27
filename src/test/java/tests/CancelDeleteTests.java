package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ProductsPage;
import utils.TestBase;

public class CancelDeleteTests extends TestBase {
	@Test(description = "Verify that the product is not deleted when delete confirmation is cancelled.")
	public void deleteProductCancelTest() {

		openDashboardAndLogin();
        
        DashboardPage dash = new DashboardPage(driverTB);
        dash.clickProducts();
        
        ProductsPage products = new ProductsPage(driverTB);
        Assert.assertTrue(products.isItemMgtHeadDisplayed(), "Not on Products page!");
	    String barcode = "DUP1766667095389";

	    Assert.assertTrue(
	        products.isProductPresent(barcode),
	        "Precondition failed: Product not found before delete"
	    );

	    products.clickDeleteByBarcode(barcode);
	    products.cancelDelete();

	    Assert.assertTrue(
	        products.isProductPresent(barcode),
	        "BUG: Product deleted even after cancelling confirmation"
	    );

	    System.out.println("Delete cancelled successfully, product remains.");
	}

}
