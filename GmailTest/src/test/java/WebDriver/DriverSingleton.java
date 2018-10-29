package WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private DriverSingleton(){}

    public static WebDriver getDriver()
    {
        if (null == driver){
            try{
                System.setProperty("webdriver.chrome.driver", "D:/data/chromedriver_win32/chromedriver.exe");
                driver=new ChromeDriver();
            }
            catch (Exception e){}
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver()
    {
        driver.quit();
        driver = null;
    }
}
