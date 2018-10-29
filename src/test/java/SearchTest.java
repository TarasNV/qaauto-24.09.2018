import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
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
     * Scenario:
     * - Verify that Login page is loaded
     * - Log in with credentials
     * - Verify that Homepage is loaded
     * - Enter 'searchTerm' into search field and press Return key.
     * - Verify that Search page is loaded.
     * - Verify number of search results is equal to 10
     * - Verify each result item contains 'searchTerm'
     *
     * PostConditions:
     * - Close Browser
     */
    @Test
    public void basicSearchTest(){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Homepage homepage = loginPage.login("taras.nadtochii@gmail.com", "Taratest");
        Assert.assertTrue(homepage.isPageLoaded(), "Homepage is not loaded");
    }
    }
