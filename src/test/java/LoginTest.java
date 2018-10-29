import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {

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
        Assert.assertTrue(homepage.isPageLoaded(), "Homepage is not loaded");
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
        LoginPage loginPage = new LoginPage(webDriver);
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
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageRemained = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPageRemained.isPageLoaded(), "Login Submit page is not loaded");
    }
/*
OLD REALISATION

    @Test
    public void blankPasswordLoginTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageBlankCred = loginPage.login("taras.nadtochii@gmail.com", "");
        Assert.assertTrue(loginPageBlankCred.isPageLoaded(), "Login page is not loaded");
    }

    @Test
    public void invalidPasswordLoginTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmitPage loginSubmitPage = loginPage.login("taras.nadtochii@gmail.com", "invalid_password");
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded");
    }

    //Invalid email address tests
    @Test
    public void noAtInEmailTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmitPage loginSubmitPage = loginPage.login("ab.c", "password");
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded");
    }

    @Test
    public void blankLoginNameTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageBlankCred = loginPage.login("", "password");
        Assert.assertTrue(loginPageBlankCred.isPageLoaded(), "Login page is not loaded");
    }

    @Test
    public void notRegisteredEmailTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmitPage loginSubmitPage = loginPage.login("asdasd@asd.s", "password");
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded");
    }
    */
}
