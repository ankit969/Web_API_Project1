
package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtils {
	
	private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);
	private final WebDriver driver;
	
	public ScreenshotUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public String capture(String testName) {
		
		if(driver == null) {
			log.warn("Driver is null, screenshot not captured");
			return null;
		}
		
		String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots";
		
		new File(screenshotDir).mkdirs();
		
		String filePath = screenshotDir + File.separator + testName + "_" + System.currentTimeMillis() + ".png";
		
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			Files.copy(src.toPath(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			log.info("Screenshot saved: {}", filePath);
			return filePath;
		} catch (IOException e) {
			log.error("Failed to capture screenshot", e);
			return null;
		}
	}

}
