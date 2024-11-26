package utilities;

import constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.time.Duration;

public class Utilities {
    private static final Logger logger = LogManager.getLogger(Utilities.class);

    // Generate a unique email with a timestamp
    public static String generateEmailTimestamp() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "anumandlasreenu" + timestamp + "@gmail.com";
    }

    // Retrieve test data from Excel file
    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/java/testdata/testdata.xlsx";
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i + 1);
                for (int j = 0; j < colCount; j++) {
                    data[i][j] = row.getCell(j).toString();
                }
            }
            return data;
        } catch (IOException e) {
            logger.error("Failed to read test data from Excel file", e);
            throw e;
        }
    }
    // Capture a screenshot
    public static String captureScreenshot(RemoteWebDriver driver, String testName) {
        String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + testName + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot for test: " + testName, e);
            return null;
        }
        return screenshotPath;
    }

    // Initialize WebDriver
    public static WebDriver initializeDriver(String browserChoice) {
        WebDriver driver;
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
                return null;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOADING_TIME));
        return driver;
    }

    // Load properties file
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Failed to load properties file: " + filePath, e);
        }
        return properties;
    }
}
