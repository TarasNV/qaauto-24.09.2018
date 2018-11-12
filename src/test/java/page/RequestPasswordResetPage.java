package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import static java.lang.Thread.sleep;

/**
 * Linked in Request password reset page object class
 */

public class RequestPasswordResetPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameInput;

    /**
     * Constructor for RequestPasswordResetPage.
     *
     * @param webDriver - driver instance from tests.
     */

    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isResetPasswordSubmitButtonDissplayed(){

        return resetPasswordSubmitButton.isDisplayed();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/request-password-reset")
                && webDriver.getTitle().contains("LinkedIn")
                && isResetPasswordSubmitButtonDissplayed();
    }

    /*public PasswordResetSubmitPage sendURL(){
        userNameInput.sendKeys("taras.nadtochii@gmail.com");
        resetPasswordSubmitButton.click();
        return new PasswordResetSubmitPage(webDriver);

    }*/

    public PasswordResetSubmitPage findAccount(String userEmail) {
        //gMailService.connect();
        userNameInput.sendKeys(userEmail);
        resetPasswordSubmitButton.click();

        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "security-noreply@linkedin.com";
        String messageFrom = "taras.nadtochii@gmail.com";

        GMailService gMailService = new GMailService();
        gMailService.connect();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        return new PasswordResetSubmitPage(webDriver);
    }

}
