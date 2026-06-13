package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class Baseclass {

    protected AndroidDriver thisdriver;
    protected AppiumDriver  driver;
    private   Properties    prop;

    // All locator property files
    public static Properties Logins;
    public static Properties Loc;
    public static Properties Loc1;
    public static Properties EDPU;
    public static Properties Delivery_address;
    public static Properties Registration;
    public static Properties FarmerRegistration;
    public static Properties Loc4;

    // Per-test Extent report
    public static ExtentReports extent;
    public static ExtentTest    test;
    private       String        reportPath;

    /* ==================== BEFORE EACH TEST ==================== */
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws IOException {

        /* --- Extent report for individual test --- */
        String testName = method.getName();
        String ts       = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

        new File(System.getProperty("user.dir") + "/reports").mkdirs();
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();

        reportPath = System.getProperty("user.dir") + "/reports/" + testName + "_" + ts + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Test: " + testName);
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester",      "QA Team");
        extent.setSystemInfo("Environment", "QA");
        test   = extent.createTest(testName);

        /* --- Load main config --- */
        prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config/config.properties"));

        /* --- Load all locator files --- */
        Logins = load("src/test/resources/locators/loginlocators.txt");
        Loc    = load("src/test/resources/locators/dashboard.txt");
        EDPU   = load("src/test/resources/locators/edpu.txt");
        Delivery_address = load("src/test/resources/locators/delivery_address.txt");
        Registration     = load("src/test/resources/locators/farmer_Registration.txt");
        FarmerRegistration = load("src/test/resources/locators/farmer_registration_new_ui.txt");
        Loc1   = load("src/test/resources/locators/milk_testing_for_transporter.txt");
        Loc4   = load("src/test/resources/locators/Testing_Dashboard_CC.txt");

        /* --- Init Appium Driver --- */
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName",          prop.getProperty("platformName",    "Android"));
        caps.setCapability("appium:automationName", prop.getProperty("automationName",  "UiAutomator2"));
        caps.setCapability("appium:deviceName",     prop.getProperty("deviceName",      "device"));
        caps.setCapability("appium:app",            prop.getProperty("app"));
        caps.setCapability("appium:newCommandTimeout",     3000);
        caps.setCapability("appium:endSessionWaitTimeout", 900);
        caps.setCapability("appium:autoGrantPermissions",  true);
        caps.setCapability("appium:dontStopAppOnReset",    true);
        caps.setCapability("appium:noReset",               true);

        try {
            URL url = new URL(prop.getProperty("serverurl", "http://127.0.0.1:4723/"));
            driver  = new AndroidDriver(url, caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
            test.pass("✅ Appium Driver initialized");
        } catch (Exception e) {
            test.fail("❌ Driver init failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /* ==================== AFTER EACH TEST ==================== */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String screenshotPath = captureScreenshot(result.getName());

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("❌ Test Failed: " + result.getName());
            if (result.getThrowable() != null) test.fail(result.getThrowable());
            if (screenshotPath != null)          test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test Passed: " + result.getName());
            if (screenshotPath != null)          test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("⚠️ Test Skipped: " + result.getName());
        }

        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
            test.info("🔒 App Closed");
        }
        extent.flush();
    }

    /* ==================== UTILITIES ==================== */

    /** Load a Properties file safely */
    private Properties load(String path) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(path));
        return p;
    }

    /** Take screenshot and return its path */
    public String captureScreenshot(String testName) {
        String ts   = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + ts + ".png";
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            if (test != null) test.warning("⚠️ Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}
