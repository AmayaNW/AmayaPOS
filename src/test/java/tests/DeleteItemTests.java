package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ProductsPage;
import utils.TestBase;

public class DeleteItemTests extends TestBase {
	@Test(description = "Verify deleting an item/product after browser alert.")
	public void deleteProductConfirmTest() {
		
		openDashboardAndLogin();
        
        DashboardPage dash = new DashboardPage(driverTB);
        dash.clickProducts();
        
        ProductsPage products = new ProductsPage(driverTB);
        Assert.assertTrue(products.isItemMgtHeadDisplayed(), "Not on Products page!");
        
        String barcode = "439996211280"; //existing barcode
        
        Assert.assertTrue(products.isProductPresent(barcode), "Precondition failed: Product not found before delete");
        
        products.clickDeleteByBarcode(barcode);
        products.confirmDelete();
        
        Assert.assertFalse(products.isProductPresent(barcode), "BUG: The item is still present in the table after delete confirmation.");
        
        System.out.println("Item deleted successfully.");
	}

}
