import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomeBackPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@class='btn__primary--large from__button--floating']")
    private WebElement alertMessage;

    public WelcomeBackPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                //&& webDriver.getTitle().contains("Sign In to LinkedIn")
                && isLargeSignInButtonDisplayed();
    }

    public boolean isLargeSignInButtonDisplayed(){
        return alertMessage.isDisplayed();
    }

}
