package utils;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
	
	private static final Logger log = LogManager.getLogger(ActionUtils.class);
	private final JavascriptExecutor js;
	
	public JavaScriptUtils(WebDriver driver) {
		this.js = (JavascriptExecutor)driver;
	}
	
	public void jsClick(WebElement element) {
		log.info("JS click on element");
		js.executeScript("arguments[0].click();", element);
	}
	
	public void scrollIntoView(WebElement element) {
		log.info("Scrolling into view");
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public Object executeScript(String script, Object... args) {
		log.info("Executing script");
		return js.executeScript(script, args);
	}
	
	

}
