package reports;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/*
 * singleton class to manage ExtentReports life cycle
 */
public class ExtentManager {
	
	private static ExtentReports extent;
	
	private ExtentManager() {
		//prevent instantiation
	}
	
	/*
	 * Returns singleton ExtentReports instance
	 */
	public static synchronized ExtentReports getExtent() {
		if(extent == null) {
			extent = createInstance();
		}
		return extent;
	}
	
	/*
	 * Create and configure ExtentReports
	 */
	private static ExtentReports createInstance() {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		
		String reportDir = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "extent";
		
		//Ensure directory exists
		new File(reportDir).mkdirs();
		
		String reportPath = reportDir + File.separator + "ExtentReport_" + timestamp + ".html";
		
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setReportName("Automation Execution Report");
		spark.config().setDocumentTitle("Selenium TDD + BDD Framework");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java", System.getProperty("java.version"));
		extent.setSystemInfo("User", System.getProperty("user.name"));
		
		return extent;
	}
	
	public static synchronized void flush() {
		if(extent != null) {
			extent.flush();
		}
	}

}
