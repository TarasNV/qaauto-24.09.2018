import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameInput;

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

    public RequestPasswordResetSubmitPage sendURL(){
        userNameInput.sendKeys("taras.nadtochii@gmail.com");
        resetPasswordSubmitButton.click();
        return new RequestPasswordResetSubmitPage(webDriver);
    }

}
