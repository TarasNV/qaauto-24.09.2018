import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordInput;

    public PasswordResetSubmitPage(WebDriver webDriver) {
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

    public PasswordResetPage confirmResetPassword(){
        newPasswordInput.sendKeys("Taratest100392");
        confirmPasswordInput.sendKeys("Taratest100392");
        resetPasswordSubmitButton.click();
        return new PasswordResetPage(webDriver);
    }
}
