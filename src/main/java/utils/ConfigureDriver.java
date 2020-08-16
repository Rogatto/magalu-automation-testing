package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ConfigureDriver {

    public WebDriver driverSetUp(String endpointDocker, DesiredCapabilities capabilities, String className) throws MalformedURLException {

        WebDriver driver;

        URL remoteWebDriverUrl = new URL(endpointDocker);
        driver = new RemoteWebDriver(remoteWebDriverUrl, capabilities);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
