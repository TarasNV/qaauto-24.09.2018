import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement emailValidationMessage;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement passwordValidationMessage;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                //&& webDriver.getTitle().contains("Sign In to LinkedIn")
                && isAlertMessageDisplayed();
    }

    public boolean isAlertMessageDisplayed(){

        return alertMessage.isDisplayed();
    }

    public boolean isInvalidPasswordValidationMessageDisplayed(){
        if(passwordValidationMessage.getText() == "Это неверный пароль. Повторите попытку или измените пароль"){
            return true;
        } else {
            return false;
        }
    }

    public String getAlertMessageText() {
        return alertMessage.getText();
    }

    public String getEmailValidationMessage() {
        return emailValidationMessage.getText();
    }

    public String getPasswordValidationMessage() {
        return passwordValidationMessage.getText();
    }
}
