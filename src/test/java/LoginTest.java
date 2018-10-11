import org.openqa.selenium.WebDriver;
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
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Homepage URL is wrong.");
        //TODO: add asserts and steps

        webDriver.quit();

    }

}
