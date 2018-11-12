package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.Homepage;
import page.LoginPage;
import page.LoginSubmitPage;

import static java.lang.Thread.sleep;

public class LoginTest extends BaseTest{


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

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "taras.nadtochii@gmail.com", "Taratest" },
                { "TARAS.nadtochii@gmail.com", "Taratest" },
                { " taras.nadtochii@gmail.com ", "Taratest" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfullLoginTest(String userEmail, String userPassword){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Homepage homepage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homepage.isPageLoaded(), "page.Homepage is not loaded");
    }


    @DataProvider
    public Object[][] loginSubmitPageDataProvider() {
        return new Object[][]{
                { "taras.nadtochii@gmail.com", "TaratestINVALID", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                { "not.existing.email.address.taratest@gmail.com", "Taratest", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                { "taras.nadtochiigmail.com", "Taratest", "Укажите действительный адрес эл. почты.", ""},
                { "taras.nadtochii@gmail.com", "test", "", ""}
        };
    }

    @Test(dataProvider = "loginSubmitPageDataProvider")
    public void validationMessageOnInvalidEmailPasswordTest(String userEmail,
                                                            String userPassword,
                                                            String emailValidationMessage,
                                                            String passwordValidationMessage){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded");

        Assert.assertEquals(loginSubmitPage.getAlertMessageText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert message text is wrong");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessage(), emailValidationMessage, "Email validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessage(), passwordValidationMessage, "Password validation message is wrong");
    }

    @DataProvider
    public Object[][] blankCredsDataProvider() {
        return new Object[][]{
                { "taras.nadtochii@gmail.com", ""},
                { "", "Taratest"}
        };
    }
    @Test(dataProvider = "blankCredsDataProvider")
    public void remainOnLoginPageNegativeTest(String userEmail, String userPassword){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageRemained = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPageRemained.isPageLoaded(), "Login Submit page is not loaded");
    }
}
