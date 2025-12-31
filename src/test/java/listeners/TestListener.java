package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.TakeScreenshots;
import utils.TestBase;
import utils.WriteAutomationBugReport;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("LISTENER TRIGGERED FOR: " + result.getName());

    	
        String testCaseID = result.getMethod().getDescription();
        String testName = result.getName();
        String failureMessage = result.getThrowable() != null ? result.getThrowable().toString() : "Unknown Error";
        String screenshotPath = TakeScreenshots.captureScreenshot(TestBase.getDriver(), testName);

        try {
            WriteAutomationBugReport.logBug(testCaseID, testName, failureMessage, screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
