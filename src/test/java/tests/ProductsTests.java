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
	        // 1. Login and open dashboard
	        openDashboardAndLogin();

	        // 2. Navigate to Products
	        DashboardPage dash = new DashboardPage(driverTB);
	        dash.clickProducts();

	        // 3. Validate Products page loaded
	        ProductsPage products = new ProductsPage(driverTB);
	        Assert.assertTrue(products.isItemMgtHeadDisplayed(),"Failed to navigate to Products page or header not visible!");

	        System.out.println("Successfully navigated to Products page at: " + getCurrentDateTime());

	        String commonBarcode = "DUP" + System.currentTimeMillis();
	        String imgPath = System.getProperty("user.dir") + "/test-data/images/Christmas desktop wallpaper.jpeg";

	        // 4. Add first item
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
	                "2026-12-28",
	                imgPath
	        );

	        Assert.assertTrue(products.isItemPresentInTable("Original Item", commonBarcode), "Original item was not added!");

	        // 5. Try adding duplicate item
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
	                "2026-12-28",
	                imgPath
	        );

	        SoftAssert softAssert = new SoftAssert();

	        boolean dupErrorDisplayed = products.isDuplicateBarcodeErrorDisplayed();
	        boolean dupAdded = products.isItemPresentInTable("Duplicate Item", commonBarcode);

	        if (dupAdded && !dupErrorDisplayed) {
	            System.out.println("❌ BUG: System allows duplicate barcode products!");
	        }

	        softAssert.assertTrue(dupErrorDisplayed, "Duplicate barcode validation message NOT shown!");

	        softAssert.assertAll();
	    }
        
	
	@Test(description="Verify widgets update after adding item/s")
	public void widgetUpdateAfterAddingAnItem() {
		// 1. Login and open dashboard
        openDashboardAndLogin();

        // 2. Navigate to Products
        DashboardPage dash = new DashboardPage(driverTB);
        dash.clickProducts();

        // 3. Validate Products page loaded
        ProductsPage products = new ProductsPage(driverTB);
        Assert.assertTrue(products.isItemMgtHeadDisplayed(),"Failed to navigate to Products page or header not visible!");

        System.out.println("Successfully navigated to Products page at: " + getCurrentDateTime());
        
        // previous total items count.
        int totalItemsPreviously = products.getTotalItemsCount();
        
        String barcode = "WID"+System.currentTimeMillis();
        String imgPath2 = System.getProperty("user.dir") + "/test-data/images/perfume.jpeg";
        
        //add new item
        products.fillAddItemsForm(
        		"Widget Test Item", 
        		barcode, 
        		"Snacks", 
        		"prima (kothume)", 
        		100, 
        		150, 
        		10, 
        		2, 
        		1, 
        		"2026-12-31",
        		imgPath2);
        
        Assert.assertTrue(products.isWidgetItemPresentInTable(barcode), "BUG: Widget item not added.");
        
        //after(new) total item count
        int totalItemsNew = products.getTotalItemsCount();
        
        Assert.assertTrue(totalItemsNew > totalItemsPreviously, "BUG: Total Items widget's total item count did not update after adding a new item.");
        	
	}
	
}
