package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import config.ConfigReader;

import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseClass {

    private static final String BASE_URL = ConfigReader.getProperty("url");

    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();


    @BeforeMethod
    public void setup() {
        initDriver();         
        configureTimeouts();  
        launchApp();          
    }

    /**
     * This method is executed after each test method.
     * It quits the WebDriver and cleans up the ThreadLocal storage.
     */
    @AfterMethod
    public void tearDown() {
//        quitDriver();         // Quit and remove driver and wait from ThreadLocal
    }

    // ====================== Utility Methods ======================

    /**
     * Initializes the ChromeDriver, sets it in ThreadLocal, and creates an explicit wait object.
     */
    private void initDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();

        WebDriver chosenDriver;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chosenDriver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                chosenDriver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                chosenDriver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("❌ Unsupported browser in config: " + browser);
        }

        driver.set(chosenDriver);
        wait.set(new WebDriverWait(chosenDriver, Duration.ofSeconds(15)));

        getDriver().manage().window().maximize();

        System.out.println("✅ Browser launched: " + browser);
    }

    private void configureTimeouts() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));   // Implicit wait
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));  // Page load timeout
    }
    private void launchApp() {
        getDriver().get(BASE_URL);  // Open the application in browser
    }

    private void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();  // Close browser
            driver.remove();     // Clear ThreadLocal driver reference
            wait.remove();       // Clear ThreadLocal wait reference
        }
    }

    // ====================== Accessor Methods ======================

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait getWait() {
        return wait.get();
    }
    
    public static String captureScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String path = System.getProperty("user.dir") + "/test-output/screenshots/" + testName+"_"+timeStamp + ".png";
        try {
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
        return path;
    }
}
