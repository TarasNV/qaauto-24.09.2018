package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Linked in Set New Password page objecct class
 */

public class SetNewPasswordPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordInput;

    /**
     * Constructor for SetNewPasswordPage.
     *
     * @param webDriver - driver instance from tests.
     */

    public SetNewPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isConfirmPasswordInputDisplayed(){
        return confirmPasswordInput.isDisplayed();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/password-reset")
                && webDriver.getTitle().contains("LinkedIn")
                && isConfirmPasswordInputDisplayed();
    }

    public SuccessfulPasswordResetPage submitNewPassword(String newUserPassword) {
        newPasswordInput.sendKeys(newUserPassword);
        confirmPasswordInput.sendKeys(newUserPassword);
        resetPasswordSubmitButton.click();
        return new SuccessfulPasswordResetPage(webDriver);
    }
}
