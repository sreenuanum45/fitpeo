package pages;

import constants.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;
import utilities.WebsiteUtlity.JavaScriptExceutorUtlity;

import java.util.List;

public class Revenue_CalculatorPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(xpath = "//input[@type='range']")
    private WebElement slider;
    @FindBy(xpath = "//input[@type='number']")
    private WebElement sliderValueInput;
    @FindBy(xpath = "//p[text()='Total Individual Patient/Month']//following::p[1]")
    private WebElement sliderValueInThePage;
    @FindBy(xpath = "(//input[@type='checkbox'])")
    private List<WebElement> checkbox;
    @FindBy(xpath = "//p[contains(text(), 'CPT-') and not(contains(text(), 'Required CPT-'))]")
    private List<WebElement> cptCodes;
    @FindBy(xpath = "//div[@role='button']//span")
    private List<WebElement>Selected_cptCodes;
    @FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month:']//p")
    private WebElement totalRecurringReimbursement;
    @FindBy(xpath = "//div[starts-with(@class,'MuiBox-root')]//span[starts-with(@class,'MuiSlider-root MuiSlider')]")
    private WebElement sliderbar;

    public Revenue_CalculatorPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSlider() {
        waitUtilities.fluentWaitForElement(slider, Constants.timeout, Constants.pollingTime);
        return slider;
    }

    public void setSliderValueInput(String value) {
        waitUtilities.fluentWaitForElement(sliderValueInput, Constants.timeout, Constants.pollingTime);
        waitUtilities.fluentWaitForElement(slider, Constants.timeout, Constants.pollingTime).clear();
        waitUtilities.fluentWaitForElement(slider, Constants.timeout, Constants.pollingTime).sendKeys(value);
    }

    public WebElement getSliderValue() {
        waitUtilities.fluentWaitForElement(sliderValueInput, Constants.timeout, Constants.pollingTime);
        return sliderValueInput;
    }

    public String getSliderValueInThePage() {
        waitUtilities.fluentWaitForElement(sliderValueInThePage, Constants.timeout, Constants.pollingTime);
        return sliderValueInThePage.getText();
    }

    public List<WebElement> getCheckbox() {
        try {
            waitUtilities.fluentWait(this.checkbox, Constants.timeout, Constants.pollingTime);

        } catch (TimeoutException e) {
System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
        return checkbox;
    }
    public void InputSliderValue(String value) {
        waitUtilities.fluentWaitForElement(sliderValueInput, Constants.timeout, Constants.pollingTime).clear();
        waitUtilities.fluentWaitForElement(sliderValueInput, Constants.timeout, Constants.pollingTime).sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE);
        waitUtilities.fluentWaitForElement(sliderValueInput, Constants.timeout, Constants.pollingTime).sendKeys(value);
    }
    public List<WebElement> getCPTCodes() {
        waitUtilities.fluentWait(this.cptCodes, Constants.timeout, Constants.pollingTime);
        return waitUtilities.fluentWait(this.cptCodes, Constants.timeout, Constants.pollingTime);
    }
    public List<WebElement> SelctedCpt_codes(){
        waitUtilities.fluentWait(this.Selected_cptCodes,Constants.timeout,Constants.pollingTime);
                return waitUtilities.fluentWait(this.Selected_cptCodes,Constants.timeout,Constants.pollingTime);
    }
    public WebElement getTotalRecurringReimbursement(){
        waitUtilities.fluentWait(this.totalRecurringReimbursement,Constants.timeout,Constants.pollingTime);
        return waitUtilities.fluentWait(this.totalRecurringReimbursement,Constants.timeout,Constants.pollingTime);
    }
    public Boolean isTotalRecurringReimbursementDisplayed(){
        waitUtilities.fluentWait(this.totalRecurringReimbursement,Constants.timeout,Constants.pollingTime);
        JavaScriptExceutorUtlity.scrollingToPageElementToView(driver,waitUtilities.fluentWait(this.totalRecurringReimbursement,Constants.timeout,Constants.pollingTime));
        return waitUtilities.fluentWait(this.totalRecurringReimbursement,Constants.timeout,Constants.pollingTime).isDisplayed();
    }
    public WebElement getSliderBar(){
        waitUtilities.fluentWait(this.sliderbar,Constants.timeout,Constants.pollingTime);
        return waitUtilities.fluentWait(this.sliderbar,Constants.timeout,Constants.pollingTime);
    }
}


