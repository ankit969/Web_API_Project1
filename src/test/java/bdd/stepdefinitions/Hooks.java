package bdd.stepdefinitions;

import base.BaseTest;
import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import reports.ExtentTestManager;

public class Hooks{
	
	@Before
	public void beforeScenario(Scenario scenario) {
		//Initialize driver for BDD
		DriverFactory.initDriver();
		
		//Start Extent Test
		ExtentTestManager.startTest(scenario.getName());
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
			ExtentTestManager.getTest().fail("Scenario Failed: " + scenario.getName());
		}
		ExtentTestManager.endTest();
		
		//Quit driver
		DriverFactory.quitDriver();
	}

}
