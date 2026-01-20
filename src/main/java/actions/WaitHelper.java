package actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WaitHelper(WebDriver driver, int timeoutSec) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
	}
	
	public WebElement waitForVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public boolean waitForUrlContains(String fragment) {
		return wait.until(ExpectedConditions.urlContains(fragment));
	}
	
	public void waitForPageLoadComplete() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

}
