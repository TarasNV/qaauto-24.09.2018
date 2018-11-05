import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

import java.util.List;

public class ResetPasswordTest {
    WebDriver webDriver;
    LoginPage loginPage;


    @BeforeMethod
    public void beforeMethod(){
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open new Browser
     * - Navigate to linkedin.com
     *
     * Steps:
     * - Click Forgot Password button
     * - Verify that Request Password Reset page is opened
     * - Enter User email address into userEmail field
     * - Click resetPasswordSubmitButton button
     * - Enter captcha if it is displayed
     * - Verify that Password Reset Submit page is opened
     * - Go via received to email URL
     * - Verify that Password Reset Submit Page is opened
     * - Enter new password
     * - Populate confirm password with the same value
     * - Click resetPasswordSubmitButton
     * - Verify that Password Reset Page is opened
     * - Click resetPasswordSubmitButton
     * - Verify that Hompage is opened
     *
     * PostConditions:
     * - Close browser
     */
    @Test
    public void basicSearchTest(){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        RequestPasswordResetPage requestPasswordResetPage = loginPage.resetPassword();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "Request Password Reset");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.sendURL();
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "Request Password Reset Submit page is not loaded");

        try {
            sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PasswordResetSubmitPage passwordResetSubmitPage = new PasswordResetSubmitPage(webDriver);
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "Password Reset Submit Page is not loaded");

        PasswordResetPage passwordResetPage = passwordResetSubmitPage.confirmResetPassword();

        Assert.assertTrue(passwordResetPage.isPageLoaded(), "Password Reset page is not loaded");
        Homepage homepage = passwordResetPage.resetPassword();

        Assert.assertTrue(homepage.isPageLoaded(), "Homepage is not loaded");

    }
}
