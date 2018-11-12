package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Base page object for all pages.
 */
public abstract class BasePage {
    WebDriver webDriver;
    static GMailService gMailService;

    protected String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    protected String getCurrentTitle() {
        return webDriver.getTitle();
    }

    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected boolean isUrlContains(String partialUrl, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }
        protected void assertElementIsVisible(WebElement webElement, int timeOutInSec , String message){
            try {
                waitUntilElementVisible(webElement, timeOutInSec);
            } catch (TimeoutException e) {
                throw new AssertionError(message);
            }
        }


    public abstract boolean isPageLoaded();

}