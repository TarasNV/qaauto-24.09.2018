import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement loginNameField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement loginPassField;

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

    public Homepage login(String userEmail, String userPassword){

        loginNameField.sendKeys(userEmail);
        loginPassField.sendKeys(userPassword);
        signInButton.click();

        return new Homepage(webDriver);
    }

}
