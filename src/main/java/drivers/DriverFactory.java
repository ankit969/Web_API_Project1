package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverFactory() {
		//prevent instantiation
	}
	
	
	/* ------- Driver init -------- */
	
	public static void initDriver() {
		if(driver.get() != null) {
			return;
		}
		
		String browser = ConfigReader.getBrowser().toLowerCase();
		boolean isGrid = ConfigReader.isGridEnabled();
		
		try {
			if(isGrid) {
				log.info("Initializing RemoteWebDriver for browser: {]", browser);
				driver.set(createRemoteDriver(browser));
			} else {
				log.info("Initializing Local WebDriver for browser: {}", browser);
				driver.set(createLocalDriver(browser));
			}
			configureDriver();
		} catch (Exception e) {
			log.error("Failed to initialize WebDriver", e);
			throw new RuntimeException();
		}
	}
	
	
	
	/* ------- Local Driver -------- */
	
	private static WebDriver createLocalDriver(String browser) {
		switch(browser) {
			case "firefox":
				return new FirefoxDriver(getFirefoxOptions());
			case "edge":
				return new EdgeDriver(getEdgeOptions());
			case "chrome":
				default:
					return new ChromeDriver(getChromeOptions());
		}
	}
	
	
	/* ----------- Grid Driver --------- */
	
	private static WebDriver createRemoteDriver(String browser) throws Exception{
		URL gridUrl = new URL(ConfigReader.getGridUrl());
		
		switch (browser) {
		 case "firefox":
			 return new RemoteWebDriver(gridUrl, getFirefoxOptions());
		 case "edge":
			 return new RemoteWebDriver(gridUrl, getEdgeOptions());
		 case "chrome":
			 default:
				 return new RemoteWebDriver(gridUrl, getChromeOptions());
		}
	}
	
	
	
	/* ------- Options ------- */
	
	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		return options;
	}
	
	private static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--width=1920");
		options.addArguments("--height=1080");
		return options;
	}
	
	private static EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
		return options;
	}
	
	
	/* -------- COnfiguration ---------- */
	
	private static void configureDriver() {
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
	}
	
	
	/* ---------- Get / Quit ---------- */
	
	public static WebDriver getDriver() {
		if(driver.get() == null) {
			throw new IllegalStateException("Driver is not initialized. Call initDriver() first.");
		}
		return driver.get();
	}
	
	public static void quitDriver() {
		if(driver.get() != null) {
			log.info("Quitting WebDriver");
			driver.get().quit();
			driver.remove();
		}
	}

	

}
