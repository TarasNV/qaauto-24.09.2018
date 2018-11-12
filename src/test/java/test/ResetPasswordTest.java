package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest{

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
        String newPassword = "Test1357";
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        RequestPasswordResetPage requestPasswordResetPage = loginPage.resetPassword();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "Request Password Reset");

        PasswordResetSubmitPage passwordResetSubmitPage =
                requestPasswordResetPage.findAccount("taras.nadtochii@gmail.com");
        try {
            sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "Request Password Reset Submit page is not loaded");



        SetNewPasswordPage linkedinSetNewPasswordPage =
                passwordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(),
                "page.SetNewPasswordPage is not loaded.");

        SuccessfulPasswordResetPage successfulPasswordResetPage =
                linkedinSetNewPasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(successfulPasswordResetPage.isPageLoaded(),
                "page.SuccessfulPasswordResetPage is not loaded.");

        Homepage homePage =
                successfulPasswordResetPage.resetPassword();
        //sleep(180000);
        Assert.assertTrue(homePage.isPageLoaded(),
                "page.HomePage is not loaded.");

    }
}
