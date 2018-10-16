import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver webDriver;

    WebElement signInButton;
    WebElement loginNameField;
    WebElement loginPassField;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public void login(String userEmail, String userPassword){

        loginNameField.sendKeys(userEmail);
        loginPassField.sendKeys(userPassword);
        signInButton.click();

    }

    public void initElements(){
        signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        loginNameField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        loginPassField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
    }

}
