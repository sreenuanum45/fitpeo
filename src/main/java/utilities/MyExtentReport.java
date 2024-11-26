package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class MyExtentReport {

    private static ExtentReports extentReport;
    private static ExtentSparkReporter sparkReporter;

    // Initialize and configure the Extent Report
    public static ExtentReports generateExtentReport() throws IOException {

        // Set up the report path using the correct separator for the OS
        String reportPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator
                + "reports" + File.separator + "ExtendReport" + File.separator + "extent.html";

        // Create the report directory if it doesn't exist
        File reportDir = new File(reportPath).getParentFile();
        if (!reportDir.exists()) {
            reportDir.mkdirs();  // Creates directories if they don't exist
        } else if (reportDir.isDirectory() && reportDir.list().length > 0) {
            org.apache.commons.io.FileUtils.cleanDirectory(reportDir);
        } else {
            org.apache.commons.io.FileUtils.forceDelete(reportDir);
        }
        // Create an ExtentReports object
        extentReport = new ExtentReports();
        // Set up the SparkReporter for generating the HTML report
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("fitpeo Report");
        sparkReporter.config().setReportName("fitpeo Test Report");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().enableOfflineMode(true);
        sparkReporter.getReportObserver().onComplete();
        sparkReporter.filter().statusFilter().getConfigurer().statusFilter().getConfigurer().reporter();
        sparkReporter.config().getOfflineMode();
        sparkReporter.getExecuted().getAndSet(true);


        // Attach the reporter to the ExtentReports instance
        extentReport.attachReporter(sparkReporter);
        // Load the configuration properties
        Properties config = new Properties();
        File configFile = new File(System.getProperty("user.dir") + "/src/main/java/config/config-"+ getEnv() +".properties");
        try (FileInputStream fis = new FileInputStream(configFile)) {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Add system info to the report
        extentReport.setSystemInfo("Application URL", config.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", config.getProperty("browserName"));
        extentReport.setSystemInfo("Username", config.getProperty("Email"));
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("OS Version", System.getProperty("os.version"));
        extentReport.setSystemInfo("Environment", config.getProperty("environment"));
        extentReport.setSystemInfo("Browser Version", config.getProperty("browserVersion"));


        // Return the ExtentReports object for use in your tests
        return extentReport;
    }

    // Create a new test in the report
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = extentReport.createTest(testName, description);
        return test;
    }

    // Capture a screenshot for failed tests
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Capture screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path to save the screenshot
        String screenshotPath = System.getProperty("user.dir")+ File.separator + "screenshots" + File.separator+ screenshotName + ".png";

        // Create the destination file
        File destination = new File(screenshotPath);

        try {
            // Copy the screenshot to the destination
            org.apache.commons.io.FileUtils.copyFile(screenshot, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    // Add logs to the report
    public static void logTestStatus(ExtentTest test, String status, String message, WebDriver driver) {
        switch (status.toUpperCase()) {
            case "PASS":
                test.pass(message);
                break;
            case "FAIL":
                // If the test fails, capture the screenshot and add it to the report
                String screenshotPath = captureScreenshot(driver, "FailedTest_" + System.currentTimeMillis());
                test.fail(message).addScreenCaptureFromPath(screenshotPath);
                break;
            case "SKIP":
                test.skip(message);
                break;
            case "INFO":
                test.info(message);
                break;
            case "WARNING":
                test.warning(message);
                break;
            case "FATAL":
                test.addScreenCaptureFromPath(message);
                break;
            default:
                test.info(message);
                break;
        }
    }

    // Finalize the report
    public static void finalizeReport() {
        extentReport.flush();
        System.out.println("Extent report generated successfully!");
    }

    public static void main(String[] args) {
        try {
            // Generate the report
            ExtentReports extentReport = generateExtentReport();
            // Example of creating a test and logging
            ExtentTest test = createTest("Fitpeo Test", "Fitpeo testing");
            // Simulate a passing test
            logTestStatus(test, "PASS", "Login test passed successfully!", null);
            // Simulate a failed test (take screenshot on failure)
            logTestStatus(test, "FAIL", "Login test failed! Screenshot captured.", null);
            // Pass your WebDriver instance
            // Finalize the report
            finalizeReport();

        } catch (IOException e) {
            System.err.println("Failed to generate the extent report: " + e.getMessage());
        }
    }
    private static String getEnv() {
        String env = System.getProperty("env");
        if (env == null || env.isEmpty()) {
            env = "dev"; // default environment
        }

        return env;
    }
}
