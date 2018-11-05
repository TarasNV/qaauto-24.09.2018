import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage{

    private WebDriver webDriver;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement loginNameField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement loginPassField;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    public boolean isSignInButtonDisplayed(){

        return signInButton.isDisplayed();
    }

    public Homepage loginToHomepage(String userEmail, String userPassword){

        loginNameField.sendKeys(userEmail);
        loginPassField.sendKeys(userPassword);
        signInButton.click();
        return new Homepage(webDriver);
    }

    public <T> T login(String userEmail, String userPassword){

        loginNameField.sendKeys(userEmail);
        loginPassField.sendKeys(userPassword);
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (webDriver.getCurrentUrl().contains("/feed")){
            return (T) new Homepage(webDriver);
        }
        if (webDriver.getCurrentUrl().contains("/uas/login-submit")){
            return (T) new LoginSubmitPage(webDriver);
        } else {
            return (T) new LoginPage(webDriver);
        }
    }

    public RequestPasswordResetPage resetPassword(){

        forgotPasswordButton.click();
        return new RequestPasswordResetPage(webDriver);
    }

    public LoginSubmitPage loginToSubmitPage(String userEmail, String userPassword){

        loginNameField.sendKeys(userEmail);
        loginPassField.sendKeys(userPassword);
        signInButton.click();
        return new LoginSubmitPage(webDriver);
    }
}
