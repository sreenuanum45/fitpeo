package tests;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import base.BaseClass;
import exceptions.InvalidInputException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.Revenue_CalculatorPage;
import utilities.WebsiteUtlity.JavaScriptExceutorUtlity;
import utilities.WebsiteUtlity.RangeSliderUtility;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class FitPeoAutomation_1  extends BaseClass {
    public RemoteWebDriver driver;
    public HomePage homePage;
    public Revenue_CalculatorPage revenueCalculatorPage;
    public RangeSliderUtility rangeSliderUtility;
    ATUTestRecorder atu;
    Date d;
    SimpleDateFormat sf;
    public FitPeoAutomation_1() {
        super();
    }
    @BeforeSuite
    public void beforeSuite() throws IOException, ATUTestRecorderException {
        sf = new SimpleDateFormat("dd-MMM-YYY-hh-ss");
        d = new Date();
        String vp = "target//" + sf.format(d);
        atu = new ATUTestRecorder(vp, true);
        atu.start();
    }

    @BeforeMethod
    public void setup() throws ElementClickInterceptedException, IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplication(config.getProperty("browserName"));
        homePage = new HomePage(driver);
        revenueCalculatorPage = new Revenue_CalculatorPage(driver);
        rangeSliderUtility = new RangeSliderUtility();
    }

    @Test(priority = 1)
    public void VerifyTheAdjustOfSlider() {
  Assert.assertEquals(driver.getTitle(), "Remote Patient Monitoring (RPM) - fitpeo.com", "Home page open successful");
        homePage.ClickOnRevenueCalculatorTab();
        driver.executeScript("arguments[0].scrollIntoView();", revenueCalculatorPage.getSlider());
        HashMap<String, String> sliderState = rangeSliderUtility.getElementState(revenueCalculatorPage.getSlider());
        System.out.println(sliderState);
        WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].value = 820;", slider);
        slider.sendKeys(Keys.RETURN);  // Optional: trigger change event
        try {
            // Navigate to the page containing the file upload element
            Dimension dimension = new Dimension(935, 620);
            driver.manage().window().setSize(dimension);
           WebElement element =revenueCalculatorPage.getSliderBar();
            int targetValue = 820;
            // Get the width of the slider track (we assume it's horizontal)
            int sliderWidth = element.getSize().getWidth();
            int sliderHeight = element.getSize().getHeight();
            System.out.println(sliderHeight);
            // Calculate the position of the target value
            int targetPosition = (int) ((targetValue / 300.0) * sliderWidth);
            System.out.println(targetPosition);
            // Create an Actions object to perform the dragging
            Actions actions = new Actions(driver);
            int minValue = 0; // Replace with the actual minimum value of the slider
            int maxValue = 2000; // Replace with the actual maximum value of the slider
            int desiredValue = 820;
            double result = ((desiredValue - 1000) / 6.6667);
            // Round the result to the nearest integer
            int x = (int) Math.round(result);
            // Calculate the offset to move the slider
            int xOffset = (int) ((double) (desiredValue - minValue) / (maxValue - minValue) * sliderWidth);
            actions.clickAndHold(element).moveByOffset(x, 0).release().perform();
            js.executeScript("arguments[0].style.right = '20px'; arguments[0].dispatchEvent(new Event('input'));", element);
            driver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void verifyTheSliderValueAsInput() throws InterruptedException {
        homePage.ClickOnRevenueCalculatorTab();
        String inputvalue = "560";
        JavaScriptExceutorUtlity.scrollBy(driver, 0, 100);
        Assert.assertTrue(revenueCalculatorPage.getSlider().isDisplayed());
        try {
            revenueCalculatorPage.InputSliderValue(inputvalue);
        }catch (InvalidInputException e){
            System.out.println(e.getMessage());
        }
        String value = revenueCalculatorPage.getSlider().getAttribute("value");
        Assert.assertEquals(value, inputvalue, "Slider works fine as expected");
        Assert.assertEquals(revenueCalculatorPage.getSliderValueInThePage(), value, "Slider works fine as expected");
        String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
        List<WebElement> ll = revenueCalculatorPage.getCPTCodes();
        for (int i = 0; i < ll.size(); i++) {
            for (int j = 0; j < cptCodes.length; j++) {
                if (cptCodes[j].toString().equals(ll.get(i).getText())) {
                    revenueCalculatorPage.getCheckbox().get(i).click();
                }
            }
        }
        List<WebElement> selectedCptCodes = revenueCalculatorPage.SelctedCpt_codes();
        for (WebElement selectedCptCode : selectedCptCodes) {
            for (String s : cptCodes) {
                if (s.equals(selectedCptCode.getText())) {
                    Assert.assertTrue(true, "Selected CPT codes are matching");
                }
            }
        }
        Assert.assertTrue(revenueCalculatorPage.isTotalRecurringReimbursementDisplayed());
        Assert.assertEquals(revenueCalculatorPage.getTotalRecurringReimbursement().getText(), "$75600", "Total Recurring Reimbursement is matching as expected");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @AfterSuite
    public void StopRecording() throws ATUTestRecorderException {
        atu.stop();

    }
}



