import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    /**
     * Preconditions:
     * - Open FF browser
     *
     * Scenario:
     * - Navigate to https://linkedin.com
     * - Verify that login page is loaded
     * - Enter userEmail into user email field
     * - Enter userPassword into user password field
     * - Click signIn button
     * - Verify that home page is loaded
     *
     * Postconditions:
     * - Close FF browser
     */
    @Test // set annotation
    public void successfullLoginTest(){

        WebDriver webDriver = new FirefoxDriver();

        webDriver.get("https://www.linkedin.com/");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Login page URL is wrong.");

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        boolean isPresentSignInButton = webDriver.findElements(By.xpath("//input[@id='login-submit']")).size() > 0;
        Assert.assertEquals(isPresentSignInButton, true, "There is no Sign in button on the page");


        WebElement loginNameField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement loginPassField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        String loginName = "taras.nadtochii@gmail.com";
        String loginPassword = "Taratest";
        loginNameField.sendKeys(loginName);
        loginPassField.sendKeys(loginPassword);
        signInButton.click();

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Homepage URL is wrong");




        webDriver.quit();

    }

}
