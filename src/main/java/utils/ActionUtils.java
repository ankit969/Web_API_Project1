package utils;

import org.openqa.selenium.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionUtils {
	
	private static final Logger log = LogManager.getLogger(ActionUtils.class);
	private final WebDriver driver;
	private final WaitUtils wait;
	
	public ActionUtils(WebDriver driver, WaitUtils wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void click(By locator) {
		log.info("Clicking: {}", locator);
		wait.waitForClickable(locator);
	}
	
	public void safeClick(By locator) {
		try {
			click(locator);
		}catch(ElementClickInterceptedException e) {
			log.warn("Click intercepted, using JS click: {}", locator);
			new JavaScriptUtils(driver).jsClick(driver.findElement(locator));
		}
	}
	
	public void scrollAndClick(By locator) {
		try {
			log.info("Scrolling to and clicking: {}", locator);
			WebElement element = wait.waitForVisibility(locator);
			
			//scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			
			//click after scroll
			wait.waitForClickable(locator);
			element.click();
		} catch (ElementClickInterceptedException e) {
			log.warn("Click intercepted after scroll, using JS click: {}", locator);
			new JavaScriptUtils(driver).jsClick(driver.findElement(locator));
		}
	}
	
	public void enterText(By locator, String text) {
		log.info("Typing into {} -> {}", locator, text);
		WebElement el = wait.waitForVisibility(locator);
		el.clear();
		el.sendKeys(text);
	}
	
	public String getText(By locator) {
		log.info("Getting text of {}", locator);
		return wait.waitForVisibility(locator).getText();
	}
	
	public List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}
	
	public boolean isDisplayed(By locator) {
		try {
			return wait.waitForVisibility(locator).isDisplayed();
		}catch(Exception e) {
			log.warn("Element not displyed: {}", locator);
			return false;
		}
	}
	
	public List<String> getTexts(By locator) {
	    log.info("Getting texts of elements: {}", locator);
	    List<WebElement> elements = wait.waitForAllVisible(locator);

	    return elements.stream()
	            .map(WebElement::getText)
	            .map(String::trim)
	            .toList();
	}

}
