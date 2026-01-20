package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitUtils {
	
	private static final Logger log = LogManager.getLogger(WaitUtils.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    public WaitUtils(WebDriver driver, int timeoutSeconds) {
    	this.driver = driver;
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }
    
    public WebElement waitForVisibility(By locator) {
    	log.info("Waiting for visibility: {}", locator);
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    public WebElement waitForClickable(By locator) {
    	log.info("Waiting for Clickable: {}", locator);
    	return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public boolean waitForInvisibility(By locator) {
    	log.info("Waiting for Invisibillity: {}", locator);
    	return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    public void waitForPageLoadComplete() {
    	log.info("Waiting for page load complete");
    	wait.until((Function<WebDriver, Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    
    public WebElement fluentWait(By locator, int timeoutSec, int pollingSec) {
    	log.info("Fleunt wait for: {}", locator);
    	return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSec)).pollingEvery(Duration.ofSeconds(pollingSec)).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
