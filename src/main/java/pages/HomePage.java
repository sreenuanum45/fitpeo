package pages;

import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

public class HomePage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(xpath = "//div[text()='Home']//parent::a")
    private WebElement homeTab;
    @FindBy(xpath="//button[text()='Accept']")
    private WebElement acceptButton;
    @FindBy(xpath = "//div[text()='Revenue Calculator']//parent::a")
    private WebElement revenueCalculatorTab;
    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void ClickOnRevenueCalculatorTab(){
        waitUtilities.fluentWait(revenueCalculatorTab, Constants.timeout, Constants.pollingTime).click();
    }
    public void ClickOnTheAcceptButton(){
        //use javascript executor
        driver.executeScript("arguments[0].scrollIntoView();",acceptButton);
       driver.executeScript("arguments[0].click();",acceptButton);
    }
}
