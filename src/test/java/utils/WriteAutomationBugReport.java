package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteAutomationBugReport {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/TestResults/AmayaPOS_TestCases.xlsx";

    private static final String SHEET_NAME = "AutomationBugReport";

    public static synchronized void logBug(String testCaseID, String testName, String failureMsg, String screenshotPath) {
    	System.out.println("WRITING BUG TO FILE: " + new File(FILE_PATH).getAbsolutePath());


        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                throw new RuntimeException("File not found. Create AmayaPOS_TestCases.xlsx manually.");
            }

            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(SHEET_NAME);

            // Create sheet only if missing
            if (sheet == null) {
                sheet = workbook.createSheet(SHEET_NAME);

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Bug ID");
                header.createCell(1).setCellValue("Test Case ID");
                header.createCell(2).setCellValue("Test Name");
                header.createCell(3).setCellValue("Failure Message");
                header.createCell(4).setCellValue("Screenshot Path");
                header.createCell(5).setCellValue("Timestamp");
            }

            // Append row safely
            int lastRowNumber = sheet.getPhysicalNumberOfRows();
            Row nextRow = sheet.createRow(lastRowNumber);


            nextRow.createCell(0).setCellValue("AUTO-BUG-" + System.currentTimeMillis());
            nextRow.createCell(1).setCellValue(testCaseID);
            nextRow.createCell(2).setCellValue(testName);
            nextRow.createCell(3).setCellValue(failureMsg);
            nextRow.createCell(4).setCellValue(screenshotPath);
            nextRow.createCell(5).setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            // Write back
            fis.close();

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            workbook.close();

            System.out.println("✔ Bug logged to Excel: " + testName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
