import org.openqa.selenium.By;
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
    @Test(dataProvider = "searchTermsDataProvider")
    public void basicSearchTest( String searchTerm){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Homepage homepage = loginPage.login("taras.nadtochii@gmail.com", "Taratest");
        Assert.assertTrue(homepage.isPageLoaded(), "Homepage is not loaded");

        SearchPage searchPage = homepage.performSearch(searchTerm);

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(searchPage.isPageLoaded(), "Search page was not loaded");

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='search-result__wrapper']"));
        Assert.assertTrue(searchPage.isTenResultsPerPage(searchResults), "Search results number on the first page is not 10");

        Assert.assertTrue(searchPage.isSearchItemContainSearchTerm(searchResults, searchTerm), "Not all search results contains search term.");
    }
    }
