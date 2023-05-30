package Managers;

import Enums.DriverType;
import Enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class AllDriverManager {
    private WebDriver webDriver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public AllDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigFileReader().getEnvironment();
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--headless", "--window-size=1644,868");
                chromeOptions.addArguments("--incognito");
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                chromeOptions.merge(cap);
                webDriver = new ChromeDriver(chromeOptions);
                webDriver.manage().window().maximize();
                break;
            case FIREFOX:

                break;
            case EDGE:

                break;
            case SAFARI:

                break;
        }
        long time = FileReaderManager.getInstance().getConfigFileReader().getTime();

        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);
        return webDriver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("Remote web driver is not yet implemented");
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL:
                webDriver = createLocalDriver();
                break;
            case REMOTE:
                webDriver = createRemoteDriver();
                break;
        }
        return webDriver;
    }

    public WebDriver getDriver() {
        if (webDriver == null) webDriver = createDriver();
        return webDriver;
    }

    public void closeDriver() {
        webDriver.close();
        webDriver.quit();
    }
}
