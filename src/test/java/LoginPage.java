import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {

   private WebDriver webDriver;

    private WebElement signInButton;
    private WebElement loginNameField;
    private WebElement loginPassField;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();
    }

    public void login(String userEmail, String userPassword){

        loginNameField.sendKeys(userEmail);
        loginPassField.sendKeys(userPassword);
        signInButton.click();

    }

    private void initElements(){
        signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        loginNameField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        loginPassField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
    }

}
