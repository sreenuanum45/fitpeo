/*
package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener; // Correct import for the WebDriverEventListener
import org.openqa.selenium.support.events.WebDriverListener;

public class MyRemoteWebdriverListener implements WebDriverListener {

    // Alert events
    @Override
    public void beforeAlertAccept(RemoteWebDriver driver) {
        System.out.println("Alert is about to be accepted.");
    }

    @Override
    public void afterAlertAccept(RemoteWebDriver driver) {
        System.out.println("Alert has been accepted.");
    }

    @Override
    public void beforeAlertDismiss(RemoteWebDriver driver) {
        System.out.println("Alert is about to be dismissed.");
    }

    @Override
    public void afterAlertDismiss(RemoteWebDriver driver) {
        System.out.println("Alert has been dismissed.");
    }

    // Navigation events
    @Override
    public void beforeNavigateTo(String url, RemoteWebDriver driver) {
        System.out.println("Navigating to: " + url);
    }

    @Override
    public void afterNavigateTo(String url, RemoteWebDriver driver) {
        System.out.println("Navigated to: " + url);
    }

    @Override
    public void beforeNavigateBack(RemoteWebDriver driver) {
        System.out.println("Navigating back");
    }

    @Override
    public void afterNavigateBack(RemoteWebDriver driver) {
        System.out.println("Navigated back");
    }

    @Override
    public void beforeNavigateForward(RemoteWebDriver driver) {
        System.out.println("Navigating forward");
    }

    @Override
    public void afterNavigateForward(RemoteWebDriver driver) {
        System.out.println("Navigated forward");
    }

    // Find element events
    @Override
    public void beforeFindBy(By by, RemoteWebDriver driver) {
        System.out.println("Looking for element: " + by);
    }

    @Override
    public void afterFindBy(By by, RemoteWebDriver driver) {
        System.out.println("Found element: " + by);
    }

    // Click events
    @Override
    public void beforeClickOn(WebElement element, RemoteWebDriver driver) {
        System.out.println("Clicking on element: " + element);
    }

    @Override
    public void afterClickOn(WebElement element, RemoteWebDriver driver) {
        System.out.println("Clicked on element: " + element);
    }

    // Value change events
    @Override
    public void beforeChangeValueOf(WebElement element, RemoteWebDriver driver) {
        System.out.println("Changing value of element: " + element);
    }

    @Override
    public void afterChangeValueOf(WebElement element, RemoteWebDriver driver) {
        System.out.println("Changed value of element: " + element);
    }

    // Script execution events
    @Override
    public void beforeScript(String script, RemoteWebDriver driver) {
        System.out.println("Before executing script: " + script);
    }

    @Override
    public void afterScript(String script, RemoteWebDriver driver) {
        System.out.println("After executing script: " + script);
    }

    // Exception event
    @Override
    public void onException(Throwable throwable, RemoteWebDriver driver) {
        System.out.println("Exception occurred: " + throwable.getMessage());
    }
}
*/
