import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new FirefoxDriver();
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
    @Test // set annotation
    public void successfullLoginTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);// создали экземпляр класса
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login("taras.nadtochii@gmail.com", "Taratest");
        Homepage homepage = new Homepage(webDriver);
        Assert.assertTrue(homepage.isPageLoaded(), "Homepage is not loaded");
    }

    @Test
    public void blankPasswordLoginTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login("taras.nadtochii@gmail.com", "");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
    }

    @Test
    public void invalidPasswordLoginTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login("taras.nadtochii@gmail.com", "invalid_password");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
        Assert.assertTrue(loginSubmitPage.isPageLoadedWithPasswordMsg(), "Login Submit page is not loaded");
    }

    //Invalid email address tests
    @Test
    public void noAtInEmailTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login("ab.c", "password");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
        Assert.assertTrue(loginSubmitPage.isPageLoadedWithNameMsg(), "Login Submit page is not loaded");
    }

    @Test
    public void blankLoginNameTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login("", "password");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
    }

    @Test
    public void notRegisteredEmailTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login("asdasd@asd.s", "password");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
        Assert.assertTrue(loginSubmitPage.isPageLoadedWithNameMsg(), "Login Submit page is not loaded");
    }
}
