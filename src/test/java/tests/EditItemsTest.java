package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashboardPage;
import pages.ProductsPage;
import utils.TestBase;

public class EditItemsTest extends TestBase{
	@Test(description = "Verify that the edit functionality works correctly with nested popups, duplicate data, and system crashes/hangs handeling.", timeOut = 120*1000)
	public void testEditProductsFlow() {
		// 1. login and go to dashboard
		openDashboardAndLogin();
				
		// 2. click the products link
		DashboardPage dash = new DashboardPage(driverTB);
		dash.clickProducts();
				
		// 3. validate products page header
		ProductsPage products = new ProductsPage(driverTB);
		Assert.assertTrue(products. isItemMgtHeadDisplayed(), "Failed to navigate to Products page or Header not found!");
		
		SoftAssert softAssert = new SoftAssert();
		
		//---PRE CONDITION: Use an existing product---
		String productID = "26";
		String originalBarcode = "DUP1766667095389";
		
		System.out.println("Opening edit popup for product barcode: " + originalBarcode);
		products.clickEditByProductID(productID);
		
		//---Verify that the correct popup opened.---
		try {
            Assert.assertTrue(products.addItemsFormPopup.isDisplayed(), "Edit popup did not open.");
            System.out.println("Edit popup opened successfully.");
        } catch (Exception e) {
            softAssert.fail("Edit popup failed to open: " + e.getMessage());
        }
		
		//---wait until the edit modal ready.---
		products.waitForEditModalReady();

		
		//---Verify that the previously added data are preserved.---
		String currentName = products.getItemName();
		if(currentName == null || currentName.isEmpty()) {
			System.out.println("DEFECT: Name field is empty on edit popup.");
			softAssert.fail("BUG: Original data not preserved on edit popup.");
		}else {
			System.out.println("Original product name: " + currentName);
		}
		
		//---Nested popups interaction (add new cat/sup)---
		String newCategory = "Edit Cat" + System.currentTimeMillis();
		String newSupplier = "Edit Sup" + System.currentTimeMillis();
		
		try {
			System.out.println("Adding new category via Edit Item popup.");
			products.addNewCategory(newCategory, "Edit category description");
			
			System.out.println("Adding new supplier via Edit Item popup.");
			products.addNewSupplier(newSupplier, "Amaya Tech", "0112223334", "contact@amaya.lk", "No 12, Colombo");
			
			System.out.println("Nested popups held successfully without crashing or hanging.");
		}catch (Exception e) {
			System.out.println("CRITICAL BUG: System crashed/hung when submiting the nested popup forms.");
			softAssert.fail("CRITICAL: Edit popup failed with nested popups: " + e.getMessage());
		}
		
		//---Attempting to set a duplicate barcode---
		System.out.println("Attempting to set a duplicate barcode");
		String duplicateBarcode = originalBarcode;
		products.jsSetValue(products.customBarcodeInput, duplicateBarcode);
		
		//Save the edited barcode
		try {
			products.jsClick(products.saveProductBtn);
			
			//check whether the system has validations for duplicate barcodes.
			boolean dupErrorDisplayed = products.isDuplicateBarcodeErrorDisplayed();
			
			if(!dupErrorDisplayed) {
				System.out.println("BUG: Edit allowed for duplicate barcode!");
                softAssert.fail("System allowed duplicate barcode during edit.");
			}else {
				System.out.println("Duplicate barcode validation correctly work inside the Edit popup.");
			}
		}catch(Exception e) {
			softAssert.fail("Exception while saving edited product: " + e.getMessage());
		}
		
		//---Successfully modify the other fields---
		try {
            String newName = currentName + " - Edited";
            products.jsSetValue(products.itemNameInput, newName);

            int newPrice = 999;
            products.jsSetValue(products.sellingPriceInput, String.valueOf(newPrice));

            products.jsClick(products.saveProductBtn);
            System.out.println("Edited product saved successfully.");

            // Verify that the table updates correctly after saving the edits/updates
            boolean updatedInTable = products.isItemPresentInTable(newName, originalBarcode);
            softAssert.assertTrue(updatedInTable, "Edited product not updated correctly in the table.");
        } catch (Exception e) {
            softAssert.fail("Failed to edit and save product: " + e.getMessage());
        }
		
		//---CLEANUP: Close the form if it is still open---
		try {
            products.closeForm();
        } catch (Exception e) {
            System.out.println("Edit form already closed or cleanup failed: " + e.getMessage());
        }

        System.out.println("Edit Product test execution finished. Asserting all results...");
        softAssert.assertAll();
		
	}
	
	@Test(description = "Verify user can update product category successfully")
	public void testEditProductCategory() {

	    openDashboardAndLogin();

	    DashboardPage dash = new DashboardPage(driverTB);
	    dash.clickProducts();

	    ProductsPage products = new ProductsPage(driverTB);
	    Assert.assertTrue(products.isItemMgtHeadDisplayed());

	    String productID = "26";
	    String newCategory = "Updated Category " + System.currentTimeMillis();

	    products.clickEditByProductID(productID);
	    products.addNewCategory(newCategory, "Updated via edit test");
	    products.selectCategory(newCategory);
	    products.jsClick(products.saveProductBtn);

	    Assert.assertTrue(products.isProductPresent("DUP1766667095389"),"Product missing after category update");
	}
	
	@Test(description = "Verify user can update product supplier successfully")
	public void testEditProductSupplier() {

	    openDashboardAndLogin();

	    DashboardPage dash = new DashboardPage(driverTB);
	    dash.clickProducts();

	    ProductsPage products = new ProductsPage(driverTB);
	    Assert.assertTrue(products.isItemMgtHeadDisplayed());

	    String productID = "26";
	    String newSupplier = "Updated Supplier " + System.currentTimeMillis();

	    products.clickEditByProductID(productID);
	    products.addNewSupplier(newSupplier, "Edit Corp", "0771234567",
	            "edit@supplier.lk", "Colombo");
	    products.selectSupplier(newSupplier);
	    products.jsClick(products.saveProductBtn);

	    Assert.assertTrue(products.isProductPresent("DUP1766667095389"),"Product missing after supplier update");
	}

	@Test(description = "Verify cancel edit does not save changes")
	public void testEditCancelDoesNotSave() {

	    openDashboardAndLogin();

	    DashboardPage dash = new DashboardPage(driverTB);
	    dash.clickProducts();

	    ProductsPage products = new ProductsPage(driverTB);
	    Assert.assertTrue(products.isItemMgtHeadDisplayed());

	    String productID = "26";
	    products.clickEditByProductID(productID);

	    String originalName = products.getItemName();
	    products.jsSetValue(products.itemNameInput, "SHOULD NOT SAVE");

	    products.closeForm(); // Cancel

	    Assert.assertFalse(
	        products.isItemPresentInTable("SHOULD NOT SAVE", "DUP1766667095389"),"BUG: Edit data saved after cancel"
	    );
	}

	@Test(description = "Verify edited data persists after page refresh")
	public void testEditDataPersistsAfterRefresh() {

	    openDashboardAndLogin();

	    DashboardPage dash = new DashboardPage(driverTB);
	    dash.clickProducts();

	    ProductsPage products = new ProductsPage(driverTB);
	    Assert.assertTrue(products.isItemMgtHeadDisplayed());

	    String productID = "26";
	    String updatedName = "Persistent Edit " + System.currentTimeMillis();

	    products.clickEditByProductID(productID);
	    products.jsSetValue(products.itemNameInput, updatedName);
	    products.jsClick(products.saveProductBtn);

	    driverTB.navigate().refresh();

	    Assert.assertTrue(
	        products.isItemPresentInTable(updatedName, "DUP1766667095389"),"Edited data did not persist after refresh"
	    );
	}


}
