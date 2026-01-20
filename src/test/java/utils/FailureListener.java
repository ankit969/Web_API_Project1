package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import drivers.DriverFactory;
import reports.ExtentManager;
import reports.ExtentTestManager;



/*
 * TestNG Listener for handling failures, screenshots and reporting
 */
public class FailureListener implements ITestListener{
	
	private static final Logger log = LogManager.getLogger(FailureListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		log.info("START - {}", result.getName());
		
		//create test only if not already created (important for retry)
		if(ExtentTestManager.getTest() == null) {
			ExtentTestManager.startTest(result.getName());
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("PASS - {}", result.getName());
		
		if(ExtentTestManager.getTest() != null) {
			ExtentTestManager.getTest().pass("Test passed");
			ExtentTestManager.endTest();
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		log.error("FAIL - {}", result.getName(), result.getThrowable());
		
		//SAFETY: create test if retry caused it to be null
		if(ExtentTestManager.getTest() == null) {
			ExtentTestManager.startTest(result.getName());
		}
		
		String screenshotPath = new ScreenshotUtils(DriverFactory.getDriver()).capture(result.getName());
		
		if(screenshotPath != null) {
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
		}
		
		ExtentTestManager.getTest().fail(result.getThrowable());
		ExtentTestManager.endTest();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		log.info("FINISH - Test Execution");
		ExtentManager.flush();
	}

}
