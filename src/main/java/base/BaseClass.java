package base;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    public RemoteWebDriver driver;
    public Properties config;
    public Properties dataProp;

    public BaseClass() {
        loadProperties();
    }

    private void loadProperties() {
        config = loadPropertyFile(System.getProperty("user.dir") + "/src/main/java/config/config-" + getEnv() + ".properties");
        dataProp = loadPropertyFile(System.getProperty("user.dir") + "/src/main/java/testdata/testdata.properties");
    }

    private String getEnv() {
        String env = System.getProperty("env");
        if (env == null || env.isEmpty()) {
            env = "dev"; // default environment
        }
        logger.info("Running tests in " + env + " environment.");
        return env;
    }

    private Properties loadPropertyFile(String filePath) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            prop.load(fis);
        } catch (IOException e) {
            logger.error("Failed to load properties file: {}", filePath, e);
        }
        return prop;
    }

    public RemoteWebDriver initializeBrowserAndOpenApplication(String browserChoice) {
        setupDriver(browserChoice);
        if (driver == null) {
            logger.error("Driver initialization failed. Exiting...");
            return null;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait", "10"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(config.getProperty("page.load.timeout", "30"))));
        driver.get(config.getProperty("url"));
        return driver;
    }

    private void setupDriver(String browserChoice) {
        switch (browserChoice.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.error("Invalid browser choice: " + browserChoice);
                break;
        }
    }

    // Fluent Wait Utility Method
    public WebElement fluentWait(WebElement element, int timeout, int pollingTime) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return element;
            }
        });
    }
    public static void waitForElementToBeClickable(RemoteWebDriver driver, WebElement element) {
        Wait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))    // Max wait time
                .pollingEvery(Duration.ofSeconds(5))    // Frequency of polling
                .ignoring(TimeoutException.class)       // Ignore TimeoutException
                .ignoring(ElementClickInterceptedException.class); // Ignore the ElementClickInterceptedException

        try {
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    // Attempt to click the element once it is clickable
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        return element;
                    }
                    return null;
                }
            });
        } catch (ElementClickInterceptedException e) {
            // Handle specific scenario for element click interception
            System.out.println("Element is intercepted by another element. Retrying...");
            // You can add additional logic here to handle retry or log the issue
        }
    }

    public WebElement fluentWaitForElement(WebElement element, int timeout, int pollingTime) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);

        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public List<WebElement> fluentWait(List<WebElement> elements, int timeout, int pollingTime) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);

        return wait.until(new Function<RemoteWebDriver, List<WebElement>>() {
            @Override
            public List<WebElement> apply(RemoteWebDriver driver) {
                for (WebElement element : elements) {
                    if (!element.isDisplayed()) {
                        return null;
                    }
                }
                return elements;
            }
        });
    }

}
