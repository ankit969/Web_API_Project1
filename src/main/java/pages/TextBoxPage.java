package pages;

import org.openqa.selenium.By;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import config.ConfigReader;

public class TextBoxPage extends BasePage{
	
	private static final Logger log = LogManager.getLogger(TextBoxPage.class);
	
	//Locators
	 private final By locator_fullName = By.id("userName");
	    private final By locator_email = By.id("userEmail");
	    private final By locator_currentAddress = By.id("currentAddress");
	    private final By locator_permanentAddress = By.id("permanentAddress");
	    private final By locator_submitBtn = By.id("submit");
	    private final By locator_outputName = By.id("name");
	    private final By locator_outputEmail = By.id("email");
	    private final By locator_outputCurrentAddress = By.cssSelector("#output #currentAddress");
	    private final By locator_outputPermanentAddr = By.id("permanentAddress");
	    
	    public void open() {
	    	driver.get(ConfigReader.getBaseUrl() + "/text-box");
	    	wait.waitForPageLoadComplete();
	    }
	    
	    public void fillForm(String name, String mail, String currAddr, String permAddr) {
	    	actions.enterText(locator_fullName, name);
	    	actions.enterText(locator_email, mail);
	        actions.enterText(locator_currentAddress, currAddr);
	        actions.enterText(locator_permanentAddress, permAddr);
	    }
	    
	    public void submit() {
	        actions.scrollAndClick(locator_submitBtn);
	    }

	    public String getOutputName() {
	        return actions.getText(locator_outputName);
	    }
	    
	    public boolean isOutputDisplayed() {
	    	return actions.isDisplayed(locator_outputName);
	    }
	    
	    public boolean isEmailOutputDisplayed() {
	    	return actions.isDisplayed(locator_outputEmail);
	    }
	    
	    public String getOutputEmail() {
	    	return actions.getText(locator_outputEmail);
	    }
	    
	    public String getOutputAddress() {
	    	return actions.getText(locator_outputCurrentAddress);
	    }

}
