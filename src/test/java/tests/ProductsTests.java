package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		
		// 3. validate products page header
		ProductsPage products = new ProductsPage(driverTB);
        Assert.assertTrue(products. isItemMgtHeadDisplayed(), "Failed to navigate to Products page or Header not found!");
        
        String commonBarcode = "DUP" + System.currentTimeMillis(); // to generate a unique barcode for this run.
        
        System.out.println("Successfully navigated to Products page at: " + getCurrentDateTime());
        
        // 4. validate Add Items button is clickable.
      //  Assert.assertTrue(products.addItemsBtnClickability(), "Failed to click Add Items. Button is not clickable.");
        
        String imgPath = System.getProperty("user.dir")+"/test-data/images/Christmas desktop wallpaper.jpeg";
        
        // 5. Add a new item. (1)
        System.out.println("Adding first item from the barcode.");
        products.fillAddItemsForm(
        		"Original Item",
        		commonBarcode,
        		"Snacks",
        		"prima (kothume)",
        		120,
        		180,
        		20,
        		3,
        		2,
        		"2025-12-28",
        		imgPath
        );
        Assert.assertTrue(products.isItemPresentInTable("Original Item", commonBarcode), "First Item was not added.");
        System.out.println("First item added successfully at " + getCurrentDateTime());
        
        // 6. Add a duplicate item with the same barcode.
        System.out.println("Adding a duplicate item with the same barcode.");
        products.fillAddItemsForm(
                "Duplicate Item",
                commonBarcode,
                "Snacks",
                "prima (kothume)", 
                120,
                180,
                20,
                3,
                2, 
                "2025-12-28",
                imgPath);
        
        // 7. checking for duplicate barcode validation. (used softAssert)
        SoftAssert softAssert = new SoftAssert();
        boolean dupErrorDisplayed = products.isDuplicateBarcodeErrorDisplayed();
        boolean dupAdded = products.isItemPresentInTable("Duplicate Item", commonBarcode);
        
        if (dupAdded && !dupErrorDisplayed) {
            System.out.println("BUG FOUND: System allows adding products with duplicate barcode!");
        } else {
            softAssert.assertTrue(dupErrorDisplayed, "Expected duplicate barcode validation message not displayed.");
        }

        softAssert.assertAll();
        
        // Cleanup: Close form so next tests aren't blocked
      //  products.closeForm();
        
        
	}
	
}
