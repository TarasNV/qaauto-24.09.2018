package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.Homepage;
import page.LoginPage;
import page.SearchPage;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchTest extends BaseTest{

    /**
     * PreConditions:
     * - Open new Browser
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that Login page is loaded
     * - Log in with credentials
     * - Verify that page.Homepage is loaded
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
        Assert.assertTrue(homepage.isPageLoaded(), "page.Homepage is not loaded");

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
