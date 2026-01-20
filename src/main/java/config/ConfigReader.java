package config;

import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
	
	private static final Logger log = LogManager.getLogger(ConfigReader.class);
	private static final Properties prop = new Properties();
	
	static {
		try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")){
			if(in != null) {
				prop.load(in);
				log.info("Loaded config.properties ");
			} else {
				log.warn("config.properties not found");
			}
		} catch (Exception e) {
			log.error("Error loading config.properties", e);
			throw new RuntimeException(e);
		}
	}
	
	private ConfigReader() {
		//prevent instantiation
	}
	
	//Generic
	public static String get(String key) {
		return prop.getProperty(key);
	}
	
	/* -- App -- */
	
	public static String getBaseUrl() {
		return get("base.url");
	}
	
	/* --- Timeouts --- */
	
	public static int getExplicitWait() {
		return Integer.parseInt(get("explicit.wait"));
	}
	
	public static int getImplicitWait() {
		return Integer.parseInt(get("implicit.wait"));
	}
	
	public static int getPageLoadTimeout() {
		return Integer.parseInt(get("page.load.timeout"));
	}
	
	/* --- Browser --- */
	
	public static String getBrowser() {
		return get("browser");
	}
	
	
	/* -- Grid -- */
	
	public static boolean isGridEnabled() {
		return Boolean.parseBoolean(get("grid.enabled"));
	}
	
	public static String getGridUrl() {
		return get("grid.url");
	}

}
