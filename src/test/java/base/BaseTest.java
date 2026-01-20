package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import drivers.DriverFactory;


public abstract class BaseTest {
	
	/* ==== Common reusable methods ==== */
	
	protected void initDriver() {
		DriverFactory.initDriver();
	}
	
	protected void quitDriver() {
		DriverFactory.quitDriver();
	}
	
	/* ===== TDD Life cycle ==== */
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initDriver();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		quitDriver();
	}

}
