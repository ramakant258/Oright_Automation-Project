package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static Properties Loc = new Properties();
    public static Properties Loc2=new Properties();
    public static FileReader fr;
    public static FileReader fr1;
    public static FileReader fr2;
    
    @BeforeMethod
    public void setUp() throws IOException {
        try {
            if (driver == null) {
                System.out.println("The path is: " + System.getProperty("user.dir"));
                fr = new FileReader("C:\\Users\\dell\\Downloads\\SeleniumAutomationFramework\\Oright_Project\\src\\test\\resources\\configfiles\\config.properties");
                fr1 = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\locators.properties");
                fr2=  new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\userMaster.properties");
                prop.load(fr);
                Loc.load(fr1); 
                Loc2.load(fr2);
            }

            String browser = prop.getProperty("browser");
            if (browser == null) {
                throw new IllegalArgumentException("Browser is not specified in the config file");
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            String testUrl = prop.getProperty("testurl");
            if (testUrl == null) {
                throw new IllegalArgumentException("Test URL is not specified in the config file");
            }

            driver.get(testUrl);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Setup failed", e);
        }
    }
    
    @AfterMethod
    public void tearDown() {    
    	
    //  if (driver != null) {
     //    driver.quit();
    //   }
    } 
}
