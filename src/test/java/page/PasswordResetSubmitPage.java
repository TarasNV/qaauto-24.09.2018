package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Linked in Password Reset Submit page object class
 */

public class PasswordResetSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendURLButton;

    /**
     * Constructor for PasswordResetSubmitPage.
     *
     * @param webDriver - driver instance from tests.
     */

    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isResendURLButtonDisplayed(){
        return resendURLButton.isDisplayed();
    }

    public boolean isPageLoaded(){
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
               // && webDriver.getTitle().contains("LinkedIn")
                && isResendURLButtonDisplayed();
    }
    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "linkedin.tst.yanina@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        //String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        //System.out.println("Content: " + message);

        //System.out.println(resetPasswordLink);
        // webDriver.get(resetPasswordLink);


        //ToDO:
        return new SetNewPasswordPage(webDriver);
    }
}
