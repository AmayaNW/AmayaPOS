package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	WebDriver driverPP;
	WebDriverWait waitPP;
	JavascriptExecutor jse;
	
	@FindBy(xpath = "//h1[normalize-space()='Item Management']")
	WebElement itemMgtHead;
	
	@FindBy(xpath = "//button[normalize-space()='Add Items']")
	WebElement addItemsBtn;
	
	@FindBy(xpath = "//form[@id='productForm']")
	public WebElement addItemsFormPopup;
	
	@FindBy(xpath = "//h5[@id='productModalLabel']")
	WebElement addItemsLabel;
	
	@FindBy(xpath = "//input[@id='name']")
	public WebElement itemNameInput;
	
	@FindBy(xpath = "//input[@id='barcode']")
	public WebElement customBarcodeInput;
	
	@FindBy(xpath = "//button[normalize-space()='Generate']")
	WebElement autoBarcodeGenerateBtn;
	
	@FindBy(xpath = "//button[@type='button'][normalize-space()='Print Barcode']")
	WebElement printBarcodeBtn;
	
	@FindBy(xpath = "//select[@id='category_id']")
	WebElement categoryDropDown;
	
	@FindBy(xpath = "//button[@class='btn btn-outline-secondary add-category-btn']")
	WebElement addCategoryBtn;
	
	@FindBy(xpath = "//div[@id='categoryModal']//form[@class='modal-content']")
	WebElement addNewCategoryForm; //frame
	
	@FindBy(xpath = "//h5[@id='categoryModalLabel']")
	WebElement catPopupLabel;
	
	@FindBy(xpath = "//input[@id='category_name']")
	WebElement categoryName;
	
	@FindBy(xpath = "//textarea[@id='category_description']")
	WebElement categoryDesc;
	
	@FindBy(xpath = "//button[normalize-space()='Add Category']")
	WebElement saveCatBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Add Category']")
	WebElement catAddCancelBtn;
	
	@FindBy(xpath = "//select[@id='supplier_id']")
	WebElement supplierDropDown;
	
	@FindBy(xpath = "//button[@class='btn btn-outline-secondary add-supplier-btn']")
	WebElement addNewSupplierBtn;
	
	@FindBy(xpath = "//div[@id='supplierModal']//form[@class='modal-content']")
	WebElement addNewSupplierPopup;
	
	@FindBy(xpath = "//h5[@id='supplierModalLabel']")
	WebElement supPopupLabel;
	
	@FindBy(xpath = "//input[@id='supplier_name']")
	WebElement supNameInput;
	
	@FindBy(xpath = "//input[@id='company_name']")
	WebElement compNameInput;
	
	@FindBy(xpath = "//input[@id='contact_number']")
	WebElement contNumberInput;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement supEmailInput;
	
	@FindBy(xpath = "//textarea[@id='address']")
	WebElement supAddInput;
	
	@FindBy(xpath = "//button[normalize-space()='Add Supplier']")
	WebElement saveSupBtn;
	
	@FindBy(xpath = "//div[@id='supplierModal']//button[@type='button'][normalize-space()='Cancel']")
	WebElement cancelSupBtn;
	
	@FindBy(xpath = "//input[@id='cost_price']")
	WebElement costPriceInput;
	
	@FindBy(xpath = "//input[@id='price']")
	public WebElement sellingPriceInput;
	
	@FindBy(xpath = "//input[@id='quantity']")
	WebElement stockQuantityInput;
	
	@FindBy(xpath = "//input[@id='tax_rate']")
	WebElement taxRateInput;
	
	@FindBy(xpath = "//input[@id='min_order_qty']")
	WebElement minOrderQuantityInput;
	
	@FindBy(xpath = "//input[@id='expire_date']")
	WebElement expiryDateInput;
	
	@FindBy(xpath = "//button[normalize-space()='Save Product']")
	public WebElement saveProductBtn;
	
	@FindBy(xpath = "//form[@id='productForm']//button[@type='button'][normalize-space()='Cancel']")
	WebElement cancelProductBtn;
	
	@FindBy(xpath = "//input[@id='product_image']")
	WebElement imageUpload;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-striped table-hover']//tbody/tr")
	//WebElement productTable;
	List<WebElement> pTableRows;
	
	WebElement duplicateErrorMessage;
	
	@FindBy(xpath = "//button[normalize-space()='Edit']")
	WebElement editBtn;
	
	private By deleteBtnBy = By.xpath(".//a[contains(@class,'btn-danger') and contains(.,'Delete')]");
	
	
	 
	public ProductsPage(WebDriver driverTB) {
		this.driverPP = driverTB;
		this.waitPP = new WebDriverWait(driverPP, Duration.ofSeconds(30));
		this.jse = (JavascriptExecutor) driverPP;
		PageFactory.initElements(driverPP, this);
	}
	
	// Reusable JS helper
	public void jsSetValue(WebElement element, String value) {
		jse.executeScript(
				"arguments[0].value = arguments[1];" +
				"arguments[0].dispatchEvent(new Event('input'));" +
				"arguments[0].dispatchEvent(new Event('change'));",
				element,
				value
		);
	}
	
	public void jsClick(WebElement element) {
		jse.executeScript("arguments[0].click();", element);
	}
	
	public void setItemName(String name) {
		jsSetValue(itemNameInput, name);
	}
	
	public String getItemName() {
		return itemNameInput.getAttribute("value");
	}
	
	public boolean isItemMgtHeadDisplayed() {
		try {
			waitPP.until(ExpectedConditions.visibilityOf(itemMgtHead));
			return itemMgtHead.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean addItemsBtnClickability() {
		// 1. click the button.	
		try {
			waitPP.until(ExpectedConditions.elementToBeClickable(addItemsBtn));
			return addItemsBtn.isEnabled();
		}catch (Exception e) {
			return false;
		}
	}
	
	public void clickAddItemsBtn() {
		if(addItemsBtnClickability()) {
			jse.executeScript("arguments[0].click()", addItemsBtn);
		}else {
			System.out.println("Add Items button is not clickable.");
		}
	}
	
	
	// Add new category TO the list
	public void addNewCategory(String name, String desc) {
		waitPP.until(ExpectedConditions.elementToBeClickable(addCategoryBtn));
		jsClick(addCategoryBtn);
		
		waitPP.until(ExpectedConditions.visibilityOf(addNewCategoryForm));
		jsSetValue(categoryName, name);
		jsSetValue(categoryDesc, desc);
		
		jsClick(saveCatBtn);
		waitPP.until(ExpectedConditions.invisibilityOf(addNewCategoryForm));
	}
	
	
	// Select category FROM the list
	public void selectCategory(String expCat) {
		waitPP.until(ExpectedConditions.elementToBeClickable(categoryDropDown)).click();
	//	Select categories = new Select(categoryDropDown);
	//	categories.selectByVisibleText(expCat);
		jse.executeScript(
		        "var select = arguments[0];" +
		        "for (var i = 0; i < select.options.length; i++) {" +
		        "  if (select.options[i].text.trim() === arguments[1]) {" +
		        "    select.selectedIndex = i;" +
		        "    select.dispatchEvent(new Event('change'));" +
		        "    break;" +
		        "  }" +
		        "}",
		        categoryDropDown,
		        expCat
		    );
	}
	
	
	// Add new supplier.
	public void addNewSupplier(String supName, String supCompany, String supPhone, String supEmail, String supAddress) {
		waitPP.until(ExpectedConditions.elementToBeClickable(addNewSupplierBtn));
		jsClick(addNewSupplierBtn);
		waitPP.until(ExpectedConditions.visibilityOf(addNewSupplierPopup));
		jsSetValue(supNameInput, supName);
		jsSetValue(compNameInput, supCompany);
		jsSetValue(contNumberInput, supPhone);
		jsSetValue(supEmailInput, supEmail);
		jsSetValue(supAddInput, supAddress);
		jsClick(saveSupBtn);
		waitPP.until(ExpectedConditions.invisibilityOf(addNewSupplierPopup));
	}
	
	
	
	// Select supplier from the list
	public void selectSupplier(String expSup) {
		waitPP.until(ExpectedConditions.elementToBeClickable(supplierDropDown));
	//	Select suppliers = new Select(supplierDropDown);
	//	suppliers.selectByVisibleText(expSup);
		jse.executeScript(
		        "var select = arguments[0];" +
		        "for (var i = 0; i < select.options.length; i++) {" +
		        "  if (select.options[i].text.trim() === arguments[1]) {" +
		        "    select.selectedIndex = i;" +
		        "    select.dispatchEvent(new Event('change'));" +
		        "    break;" +
		        "  }" +
		        "}",
		        supplierDropDown,
		        expSup
		    );	
	}
	
	public void uploadImage(String imgPath) {
		try {
			((JavascriptExecutor) driverPP)
	        .executeScript("arguments[0].style.display='block';", imageUpload);

	    imageUpload.sendKeys(imgPath);
	    
		}catch(Exception e) {
			System.out.println("Optional image upload is skipped or failed. Verify path: " + imgPath);
		}
	}
	
	public boolean fillAddItemsForm(String itemName, String custBarcode, String cat, String sup, int costPrice, 
			int sellPrice, int stock, int tax, int minOrQty, String expDate, String imgPath) {
		try {
	        clickAddItemsBtn();

	        waitPP.until(ExpectedConditions.visibilityOf(addItemsFormPopup));
	        waitPP.until(ExpectedConditions.visibilityOf(addItemsLabel));

	        // Text inputs
	        jsSetValue(itemNameInput, itemName);
	        jsSetValue(customBarcodeInput, custBarcode);

	        // Dropdowns
	        selectCategory(cat);
	        selectSupplier(sup);

	        // Numbers
	        jsSetValue(costPriceInput, String.valueOf(costPrice));
	        jsSetValue(sellingPriceInput, String.valueOf(sellPrice));
	        jsSetValue(stockQuantityInput, String.valueOf(stock));
	        jsSetValue(taxRateInput, String.valueOf(tax));
	        jsSetValue(minOrderQuantityInput, String.valueOf(minOrQty));

	        // Date
	        jsSetValue(expiryDateInput, expDate);

	        // Image upload
	        if (imgPath != null && !imgPath.trim().isEmpty()) {
	            imageUpload.sendKeys(imgPath);
	        }

	        // Save using JS
	        jse.executeScript("arguments[0].click()", saveProductBtn);

	        waitPP.until(ExpectedConditions.invisibilityOf(addItemsFormPopup));
	        return true;

	    } catch (Exception e) {
	        System.out.println("Failed to add item: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean isItemPresentInTable(String name, String barcode) {
		for(WebElement pTableRow: pTableRows) {
			if(pTableRow.getText().contains(barcode)) {
				System.out.println("Item added successfully.");
				return true;
			}
		}
		System.out.println("Item adding failed.");
		return false;
	}
	
	public boolean isDuplicateBarcodeErrorDisplayed() {
        try {
            waitPP.until(ExpectedConditions.visibilityOf(duplicateErrorMessage));
            return duplicateErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
	
	public void closeForm() {
        cancelProductBtn.click();
        waitPP.until(ExpectedConditions.invisibilityOf(addItemsFormPopup));
    }
	
	// Edit Item functionality
	// *Testing using product ID as the system can allow duplicate barcodes.
	//1. find edit button by product ID
	public void waitForEditModalReady() {
	    waitPP.until(ExpectedConditions.visibilityOf(addItemsFormPopup));

	    waitPP.until(driver ->!itemNameInput.getAttribute("value").isEmpty()
	    );

	    waitPP.until(ExpectedConditions.elementToBeClickable(categoryDropDown));
	    waitPP.until(ExpectedConditions.elementToBeClickable(addNewSupplierBtn));
	}

	public void clickEditByProductID(String productID) {
		WebElement editBtn = driverPP.findElement(By.xpath("//button[contains(@onclick,'\"product_id\":\"" + productID + "\"')]"));
		jsClick(editBtn);
		waitPP.until(ExpectedConditions.visibilityOf(addItemsFormPopup));
	}
	
	public void clickDeleteByBarcode(String barcode) {
		for(WebElement pTableRow: pTableRows) {
			if(pTableRow.getText().contains(barcode)) {
				WebElement itemDeleteBtn = pTableRow.findElement(deleteBtnBy);
				jse.executeScript("arguments[0].scrollIntoView({block:'center'});", itemDeleteBtn);
				jsClick(itemDeleteBtn);
				
				return;
			}
		}
		throw new RuntimeException("Delete button not found for barcode: " + barcode);
	}
	
	public void confirmDelete() {
		waitPP.until(ExpectedConditions.alertIsPresent());
		driverPP.switchTo().alert().accept();
	}
	
	public void cancelDelete() {
		waitPP.until(ExpectedConditions.alertIsPresent());
		driverPP.switchTo().alert().dismiss();
	}
	
	public boolean isProductPresent(String barcode) {
		for(WebElement pTableRow: pTableRows) {
			if(pTableRow.getText().contains(barcode)) {
				return true;
			}
		}
		
		return false;
	}
	

}
