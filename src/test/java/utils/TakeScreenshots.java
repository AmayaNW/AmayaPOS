package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class TakeScreenshots {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // directory path
        String screenshotDir =
                System.getProperty("user.dir") + File.separator + "Screenshots";

        // folder exists?
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // full file path
        String screenshotPath =
                screenshotDir + File.separator + testName + "_" + time + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            Files.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
