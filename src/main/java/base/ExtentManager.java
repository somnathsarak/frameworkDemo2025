package base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentManager is responsible for creating and configuring the ExtentReports instance.
 * It ensures a single report instance with timestamped naming and enhanced configuration.
 */
public class ExtentManager {

    // Singleton ExtentReports instance
    private static ExtentReports extent;

    /**
     * Returns the singleton ExtentReports instance.
     * Applies additional configurations like theme, chart visibility, etc.
     *
     * @return configured ExtentReports object
     */
    // Timestamp for dynamic and unique report naming
    public static  String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());

    // Report file path (customize directory as needed)
    public static String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timeStamp + ".html";
    public static ExtentReports getInstance() {

        if (extent == null) {
           

            // Create and configure the Spark reporter
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            // ===== Custom Report Configuration =====
            reporter.config().setReportName("Advanced Automation Execution Report");
            reporter.config().setDocumentTitle("Test Execution Summary");
            reporter.config().setTheme(Theme.DARK); // Options: DARK or STANDARD
            reporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            // Attach reporter to the main ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // ===== Custom System Info =====
            extent.setSystemInfo("Tester", "Somnath Sarak");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Platform", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extent;
    }
}
