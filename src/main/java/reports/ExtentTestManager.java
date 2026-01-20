package reports;

import com.aventstack.extentreports.ExtentTest;

/*
 * Manages ExtentTest instances per thread. Works for TDD + BDD
 */
public final class ExtentTestManager {
	
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	private ExtentTestManager() {
		//prevent instantiation
	}
	
	//start a test node for the current thread
	public static synchronized void startTest(String testName) {
		ExtentTest extentTest = ExtentManager.getExtent().createTest(testName);
		test.set(extentTest);
	}
	
	//get current thread's ExtentTest
	public static synchronized ExtentTest getTest() {
		return test.get();
	}
	
	//Remove test from ThreadLocal
	public static synchronized void endTest() {
		test.remove();
	}

}
