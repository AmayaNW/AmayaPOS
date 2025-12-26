package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashboardPage;
import pages.ProductsPage;
import utils.TestBase;

public class AddItemInsidePopupsTests extends TestBase {

    @Test(description = "Verify nested popups for Category and Supplier: Persistence, Functionality, Duplicates, and crash detection.", timeOut = 120*1000) // miliseconds
    public void testNestedPopupBehaviors() {
        openDashboardAndLogin();
        
        DashboardPage dash = new DashboardPage(driverTB);
        dash.clickProducts();
        
        ProductsPage products = new ProductsPage(driverTB);
        Assert.assertTrue(products.isItemMgtHeadDisplayed(), "Not on Products page!");

        SoftAssert softAssert = new SoftAssert();
        String initialItemName = "Persistence Test Item";
        String sharedCategoryName = "UniqueCat" + System.currentTimeMillis();
        String sharedSupplierName = "UniqueSup" + System.currentTimeMillis();
        boolean isSystemStable = true;

        // --- SCENARIO 1: CATEGORY POPUP ---
        System.out.println("--- SCENARIO 1: CATEGORY POPUP ---");
        try {
            products.clickAddItemsBtn();
            products.setItemName(initialItemName);
            products.addNewCategory(sharedCategoryName, "Description for Category");
            
            // waiting for the system stability after the popup closed. If the system keeps 
            //loading here, the timeout (in the test desc) captures it.
            waitTB.until(driverNP -> {
            	JavascriptExecutor js = (JavascriptExecutor) driverNP;
            	return js.executeScript("return document.readyState").equals("complete");
            });
            
            String currentName = products.getItemName();
            if(currentName == null || currentName.isEmpty()) {
            	System.out.println("DEFECT: Form data cleared after Category popup.");
                softAssert.fail("BUG: Form data lost after Category popup.");
                isSystemStable = false;
            }
            
        }catch (Exception e) {
            System.out.println("CRITICAL BUG: System crashed or hung after category popup.");
            softAssert.fail("CRITICAL: System hung/infinite refresh detected after category popup.");
            isSystemStable = false;
            
            // stopping the test class early if the UI breaks
            softAssert.assertAll();
            return;
        }

        // --- SCENARIO 2: SUPPLIER POPUP ---
        // Only attempt this if the system didn't completely die in step 1
        if (isSystemStable) {
            System.out.println("\n--- SCENARIO 2: SUPPLIER POPUP ---");
            try {
                products.addNewSupplier(sharedSupplierName, "Amaya Tech", "0112223334", "contact@amaya.lk", "No 12, Colombo");

                String currentName = "";
                try { 
                	currentName = products.getItemName(); 
                	}catch (Exception e) {}

                if (currentName == null || currentName.isEmpty()) {
                    System.out.println("DEFECT FOUND: Form cleared/closed after adding Supplier.");
                    softAssert.fail("BUG: Form data lost after Supplier popup.");
                    
                    isSystemStable = performRecovery(products, initialItemName); // recovery
                }

                // Checking for duplicates only if the recovery passed.
                if (isSystemStable) {
                    System.out.println("Checking for duplicate supplier...");
                    products.addNewSupplier(sharedSupplierName, "Amaya Tech", "0112223334", "contact@amaya.lk", "No 12, Colombo");
                }

            } catch (Exception e) {
                System.out.println("CRITICAL FAILURE in Supplier Scenario: " + e.getMessage());
                softAssert.fail("CRITICAL: Supplier popup interaction failed/crashed.");
            }
        } else {
            System.out.println("SKIPPING Supplier Scenario due to previous critical failure.");
            softAssert.fail("BLOCKED: Supplier tests skipped due to Category popup critical crash.");
        }

        System.out.println("\nTest execution finished. Asserting all results...");
        softAssert.assertAll();
    }

    
     // Attempting to refresh the page and reopen the form.
     //returns true if recovery successful, false if system is totally broken.
     
    private boolean performRecovery(ProductsPage products, String name) {
        try {
            System.out.println("Attempting System Recovery (Refresh)...");
            driverTB.navigate().refresh();
            waitTB.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Add Items']")));
            
            // Re-initialize logic after refresh
            products.clickAddItemsBtn();
            products.setItemName(name);
            System.out.println("Recovery Successful.");
            return true;
        } catch (Exception e) {
            System.out.println("Recovery FAILED. System is unresponsive.");
            return false;
        }
    }
}