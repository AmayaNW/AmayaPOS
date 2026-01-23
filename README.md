# LankaPOS - Pharmacy Point-of-Sale System: UI Automation and QA Analysis
## Project Overview
This repository contains UI automation tests and QA analysis performed on a point-of-sale system. The goal of this project is to automate user flows, validate data integrity, UI state consistency, and business-critical calculations that often pass manual testing.

## Testing Scope
1. UI Automation using,
```text
     Selenium WebDriver,
     TestNG
```
3. Page Object Model (POM)
4. Failure listener with,
```text
     Screesnshot capture,
     Automated .xlsx bug reporting
```
6. Negative and edge-case testing
7. Widget and table data validation

## Key Automation Findings (Missed in Manual Testing)

1. Products Page Widgets Not Updating
Widgets such as,
```text
   ~Total Items
   ~Total Items In Stock
   ~Inventory Value
   ~Potential Profit
```
It did not update the values after adding new products after 21 items.

3. Newly Added Products Not Visible in Table
```text
~Product table remained limited to 21 rows
~No pagination or "View more" functionality
~Newly added items were not displayed
```

5. Search Functionality Broken
Search bar failed to work for,
```text
   ~Item names
   ~Categories
   ~Barcodes
   ~Suppliers
```
No filtering logic was applied.

7. Duplicate Barcode Allowed
```text
     ~System allowed to add multiple products with the same barcode
     ~No frontend or backend validation triggered
```
Critical issue for inventory accuracy.

9. UI Fragility Exposed by Automation
```text
     ~Nested popups cause UI hangs
     ~Forms did not always close or refresh correctly
     ~State inconsistencies appeared under repeated actions
```
These issues never occurred in manual tesing but were consistently detected via automation.

## QA Insight
----Automation testing revealed hidden system weaknesses.----
Local deployment will not eleminate
```text
     ~UI state bugs
     ~Logic flows
     ~Data consistency issues
```
These issues are environment-independent and must be addressed to ensure system reliability.

## Tech Stack
```text
1. Language: Java (Version: 21.0.8)
2. Automation Tool: Selenium WebDriver (Version 4.38.0)
3. Testing Framework: TestNG
4. Build Tool: Maven
5. Architecture: Page-Object-Model (POM)
6. Excel Reporting: Apache POI
7. Version Control: Git
```

## Project Structure
```text
├── src/
│   ├── main/java  
│   └── test/java
│       └── listeners
|       |-- pages
|       ├── tests
|       └── utils
├── Screenshots/
├── TestResults/
├── testng.xml
└── README.md
```
## Evidence
```text
~Screenshots are catured automatically on test failures
~Excel-based automation bug report generated per run
(Artifacts are excluded from version control via .gitignore.)
```

## Artifacts
[Test Cases and Automation Bug Report](https://docs.google.com/spreadsheets/d/1jWUtwWyuhQ5r6D6dnKXt8P-mnz368UGUjkZkLXo0P6A/edit?gid=0#gid=0)

