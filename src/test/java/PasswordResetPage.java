import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public PasswordResetPage(WebDriver webDriver) {
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
