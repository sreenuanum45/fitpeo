package utilities.WebsiteUtlity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JavaScriptExceutorUtlity {

    public static void clickOnElement(RemoteWebDriver driver, WebElement element){

        driver.executeScript("arguments[0].click();", element);
    }
    public static void scrollIntoView(RemoteWebDriver driver, WebElement element){
        driver.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static void scrollBy(RemoteWebDriver driver, int x, int y){
        driver.executeScript("window.scrollBy("+x+","+y+")");
    }
    public static void scrollByPixel(RemoteWebDriver driver, int x, int y){
        driver.executeScript("window.scrollBy("+x+","+y+")");
    }
    public static void scrollByPercentage(RemoteWebDriver driver, int x, int y){
        driver.executeScript("window.scrollBy("+x+","+y+")");
    }

    public static void alertWith(RemoteWebDriver driver){
        driver.executeAsyncScript("window.setTimeout(function(){alert('Hello World')},1000)");
    }
    public static void SendKeys(RemoteWebDriver driver, WebElement element, String value){
        driver.executeScript("arguments[0].setAttribute('value','"+value+"')", element);
    }
    public static void clearElement(RemoteWebDriver driver, WebElement element){
        driver.executeScript("arguments[0].clear();", element);
    }

    public static void scrollToElement(RemoteWebDriver driver, WebElement element){
        driver.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static void scrollToPage(RemoteWebDriver driver){
        driver.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    public static Object scrollingPositionYaxis(RemoteWebDriver driver ){
       return driver.executeScript("return window.pageYOffset");
    }
    public static Object scrollingPositionXaxis(RemoteWebDriver driver ){
        return driver.executeScript("return window.pageXOffset");
    }
    public static void scrollingToPageElementToView(RemoteWebDriver driver, WebElement element){

        driver.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static void scrollingPageEnd(RemoteWebDriver driver){
        driver.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    public static void scrollingPageIntialPosition(RemoteWebDriver driver){
        driver.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }
    public static void scrollingPageSideBySide(RemoteWebDriver driver){
        driver.executeScript("window.scrollTo(0,document.body.scrollWidth)");
    }

    /*public static void ZoomingPage(RemoteWebDriver driver,int size){
        driver.executeScript("document.body.style.zoom='"+size+"%'");
    }*/



}
