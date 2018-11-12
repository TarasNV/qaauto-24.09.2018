package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Linked in Successfull Password Reset page object class
 */

public class SuccessfulPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    /**
     * Constructor for SuccessfulPasswordResetPage.
     *
     * @param webDriver - driver instance from tests.
     */

    public SuccessfulPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isResetPasswordSubmitButtonDisplayed(){
        return resetPasswordSubmitButton.isDisplayed();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/password-reset-submit")
                && webDriver.getTitle().contains("LinkedIn")
                && isResetPasswordSubmitButtonDisplayed();
    }

    public Homepage resetPassword(){

        resetPasswordSubmitButton.click();
        return new Homepage(webDriver);
    }


}
