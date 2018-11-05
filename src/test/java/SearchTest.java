import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.naming.directory.SearchResult;

import java.util.List;

import static java.lang.Thread.sleep;

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
    @DataProvider
    public Object[][] searchTermsDataProvider() {
        return new Object[][]{
                {"HR"}
        };
    }
    @Test
    public void basicSearchTest(){
        String searchTerm = "HR";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Homepage homepage = loginPage.login("taras.nadtochii@gmail.com", "Taratest");
        Assert.assertTrue(homepage.isPageLoaded(), "Homepage is not loaded");

        SearchPage searchPage = homepage.search(searchTerm);

        Assert.assertTrue(searchPage.isPageLoaded(), "Search page was not loaded");

        Assert.assertEquals(searchPage.getSearchItemsNumber(), 10, "Search results number on the first page is not 10");

        List<String> searchResultsList = searchPage.getSearchResultsList();

        for (String searchResult : searchResultsList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm "+searchTerm+" not found in:\n"+searchResult);
            }

        }
    }
