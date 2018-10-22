import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {
    private WebDriver webDriver;

    private WebElement passwordMessage;
    private WebElement nameMessage;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isPageLoadedWithPasswordMsg(){

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                && webDriver.getTitle().equals("Sign In to LinkedIn")
                && isPasswordMessageDisplayed();
    }
    public boolean isPageLoadedWithNameMsg(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                && webDriver.getTitle().equals("Sign In to LinkedIn")
                && isNameMessageDisplayed();
    }

    public boolean isPasswordMessageDisplayed(){
        return passwordMessage.isDisplayed();
    }
    public boolean isNameMessageDisplayed(){
        return nameMessage.isDisplayed();
    }


    private void initElements(){
        passwordMessage = webDriver.findElement(By.xpath("//div[@id='error-for-password']"));
        nameMessage = webDriver.findElement(By.xpath("//div[@id='error-for-username']"));
    }
}
