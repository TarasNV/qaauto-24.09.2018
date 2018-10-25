import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//div[@id='control_gen_1']")
    private WebElement alertMessage;

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

}
