package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper {
	
	private WebDriver driver;
	private Actions actions;
	private WaitHelper waitHelper;
	
	public ActionHelper(WebDriver driver, WaitHelper waitHelper) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.waitHelper = waitHelper;
	}
	
	public void click(By locator) {
		waitHelper.waitForClickable(locator).click();
	}
	
	public void enter(By locator, String text) {
		WebElement el = waitHelper.waitForVisibility(locator);
		el.clear();
		el.sendKeys(text);
	}
	
	public void jsClick(By locator) {
		WebElement el = waitHelper.waitForVisibility(locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", el);
	}
	
	public void hover(By locator) {
		WebElement el = waitHelper.waitForVisibility(locator);
		actions.moveToElement(el).perform();
	}

}
