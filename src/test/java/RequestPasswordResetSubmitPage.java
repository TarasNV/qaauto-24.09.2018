import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendURLButton;

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isResendURLButtonDisplayed(){
        return resendURLButton.isDisplayed();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
               // && webDriver.getTitle().contains("LinkedIn")
                && isResendURLButtonDisplayed();
    }
}
