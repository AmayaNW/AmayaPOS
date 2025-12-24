package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	WebDriver driverPP;
	WebDriverWait waitPP;
	
	@FindBy(xpath = "//h1[normalize-space()='Item Management']")
	WebElement itemMgtHead;
	
	@FindBy(xpath = "//button[normalize-space()='Add Items']")
	WebElement addItemsBtn;
	
	@FindBy(xpath = "//form[@id='productForm']")
	WebElement addItemsFormPopup;
	
	@FindBy(xpath = "//h5[@id='productModalLabel']")
	WebElement addItemsLabel;
	
	@FindBy(xpath = "//input[@id='name']")
	WebElement itemNameInput;
	
	@FindBy(xpath = "//input[@id='barcode']")
	WebElement customBarcodeInput;
	
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
	WebElement addCatBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Add Category']")
	WebElement catAddCancelBtn;
	
	@FindBy(xpath = "//select[@id='supplier_id']")
	WebElement supplierDropDown;
	
	@FindBy(xpath = "//button[@class='btn btn-outline-secondary add-supplier-btn']")
	WebElement addNewSupplierBtn;
	
	@FindBy(xpath = "//div[@id='supplierModal']//form[@class='modal-content']")
	WebElement addNewSupplierPopup;
	
	@FindBy(xpath = "//h5[@id='supplierModalLabel']")
	WebElement sunPopupLabel;
	
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
	WebElement addSupBtn;
	
	@FindBy(xpath = "//div[@id='supplierModal']//button[@type='button'][normalize-space()='Cancel']")
	WebElement cancelSupBtn;
	
	@FindBy(xpath = "//input[@id='cost_price']")
	WebElement costPriceInput;
	
	@FindBy(xpath = "//input[@id='price']")
	WebElement sellingPriceInput;
	
	@FindBy(xpath = "//input[@id='quantity']")
	WebElement stockQuantityInput;
	
	@FindBy(xpath = "//input[@id='tax_rate']")
	WebElement taxRateInput;
	
	@FindBy(xpath = "//input[@id='tax_rate']")
	WebElement minOrderQuantityInput;
	
	@FindBy(xpath = "//input[@id='expire_date']")
	WebElement expiryDateInput;
	
	@FindBy(xpath = "//button[normalize-space()='Save Product']")
	WebElement saveProductBtn;
	
	@FindBy(xpath = "//form[@id='productForm']//button[@type='button'][normalize-space()='Cancel']")
	WebElement cancelProductBtn;
	
	@FindBy(xpath = "//div[@class='image-upload-container']")
	WebElement imageUpload;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-striped table-hover']")
	WebElement productTable;
	List<WebElement> pTableRows;
	
	WebElement duplicateErrorMessage;
	
	
	 
	public ProductsPage(WebDriver driverTB) {
		this.driverPP = driverTB;
		this.waitPP = new WebDriverWait(driverPP, Duration.ofSeconds(30));
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
			addItemsBtn.click();
		}else {
			System.out.println("Add Items button is not clickable.");
		}
	}
	
	public void selectCategory(String expCat) {
		waitPP.until(ExpectedConditions.elementToBeClickable(categoryDropDown)).click();
		Select categories = new Select(categoryDropDown);
		categories.selectByVisibleText(expCat);
	//	List<WebElement> categoryList = categories.getOptions();
	/*	waitPP.until(ExpectedConditions.visibilityOfAllElements(categoryList));
		
		boolean catFound = false;
		int categoryCount=0;
		
		for(WebElement category: categoryList) {
			String actCat = category.getText();
			categoryCount++;
			
			if(actCat.equalsIgnoreCase(expCat)) {
				category.click();
				catFound = true;
				break;
			}
		} */
	}
	
	public void selectSupplier(String expSup) {
		waitPP.until(ExpectedConditions.elementToBeClickable(supplierDropDown));
		Select suppliers = new Select(supplierDropDown);
		suppliers.selectByVisibleText(expSup);
	//	List<WebElement> supplierList = suppliers.getOptions();
	/*	waitPP.until(ExpectedConditions.visibilityOfAllElements(supplierList));
		
		boolean supFound = false;
		int supplierCount = 0;
		
		for(WebElement supplier: supplierList) {
			String actSup = supplier.getText();
			supplierCount++;
			
			if(actSup.equalsIgnoreCase(expSup)) {
				supplier.click();
				supFound = true;
				break;
			}
		} */
	}
	
	public void uploadImage(String imgPath) {
		try {
			imageUpload.sendKeys(imgPath);
		}catch(Exception e) {
			System.out.println("Optional image upload is skipped or failed. Verify path: " + imgPath);
		}
	}
	
	public boolean fillAddItemsForm(String itemName, String custBarcode, String cat, String sup, int costPrice, 
			int sellPrice, int stock, int tax, int minOrQty, String expDate, String imgPath) {
		if(addItemsBtnClickability()) {
			clickAddItemsBtn();
			
			waitPP.until(ExpectedConditions.visibilityOf(addItemsFormPopup));
			
			boolean labelDisplay = addItemsLabel.isDisplayed();
			
			if(labelDisplay) {
				System.out.println("Successfully opened the Add Items form.");
				
				itemNameInput.sendKeys(itemName);				
				customBarcodeInput.sendKeys(custBarcode);
			//	System.out.println("Category options count: " + categoryList.size());				
				selectCategory(cat);
				selectSupplier(sup);
				costPriceInput.sendKeys(String.valueOf(costPrice));
				sellingPriceInput.sendKeys(String.valueOf(sellPrice));
				stockQuantityInput.sendKeys(String.valueOf(stock));
				taxRateInput.sendKeys(String.valueOf(tax));
				minOrderQuantityInput.sendKeys(String.valueOf(minOrQty));
				expiryDateInput.sendKeys(expDate);
		   //   upload image if the path is provided.-optional
				if(imgPath != null && imgPath.trim().isEmpty()) {
					uploadImage(imgPath);
				}
				
				saveProductBtn.click();
				
				try {
                    waitPP.until(ExpectedConditions.invisibilityOf(addItemsFormPopup));
                    System.out.println("Item adding successful.");
                    return true;
                } catch (Exception e) {
                    System.out.println("Form did not close. Check for validation errors on the page.");
                    return false;
                }
				
			}else {
				System.out.println("The form failed to open.");
			}
		}
		return false;
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
}
