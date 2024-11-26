package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.MyExtentReport;
import utilities.Utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener  {
    ExtentReports extentReport;
    ExtentTest extentTest;
    public RemoteWebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest=extentReport.createTest(result.getName());
        extentTest.log(Status.INFO,result.getName()+" is Started Executing");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,result.getName()+" is Executed Successfully");
        System.out.println(result.getName()+" is Executed  got Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        RemoteWebDriver driver;
        String destinationScreenshot;
        try {
            driver = (RemoteWebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        destinationScreenshot= Utilities.captureScreenshot(driver, result.getName());
        extentTest.addScreenCaptureFromPath(destinationScreenshot);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL, result.getName()+"got Failed");


    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.SKIP, result.getName()+" is Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL, result.getName()+"got onTestFailedButWithinSuccessPercentage");

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL, result.getName()+"got onTestFailedWithTimeout");
        extentTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver, result.getName()));


    }

    @Override
    public void onStart(ITestContext context) {
        try {
            extentReport= MyExtentReport.generateExtentReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        try {
            System.out.println(System.getProperty("user.dir")+"/src/main/java/reports/ExtendReport/extent.html");
            Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"/src/main/java/reports/ExtendReport/extent.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
