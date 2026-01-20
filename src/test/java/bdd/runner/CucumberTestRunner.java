package bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"bdd.stepdefinitions", "bdd.hooks"},
		plugin = {"pretty", "html:target/cucumber-report.html"},
		monochrome = true
		)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
	
	
	
}

