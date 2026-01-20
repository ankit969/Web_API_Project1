package pages;

import org.openqa.selenium.WebDriver;
import config.ConfigReader;
import drivers.DriverFactory;
import utils.ActionUtils;
import utils.WaitUtils;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected WaitUtils wait;
	protected ActionUtils actions;
    
    protected BasePage() {
    	this.driver = DriverFactory.getDriver();
    	this.wait = new WaitUtils(driver, ConfigReader.getExplicitWait());
    	this.actions = new ActionUtils(driver, wait);
    }
    

}
