package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GWD {
    private static WebDriver driver;

    public static WebDriver getDriver(){

        if (driver==null) {
            Logger.getLogger("").setLevel(Level.SEVERE);
            System.setProperty(ChromeDriverService.CHROME_DRIVER_APPEND_LOG_PROPERTY,"true");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            //todo firefox
           // WebDriverManager.firefoxdriver().setup();
            //driver=new FirefoxDriver();
        }
        return driver;

    }
    public static void quitDriver() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(driver!=null)
        driver.quit();
        driver=null;
    }


}
