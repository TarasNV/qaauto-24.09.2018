import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@data-control-name='all_filters']")
    private WebElement allFiltersButton;
    @FindBy(xpath = "//div[@class='search-result__wrapper']")
    private WebElement searchItem;
    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    public List<WebElement> searchResults;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results/all/")
                && webDriver.getTitle().contains("LinkedIn")
                && isAllFiltersButtonDisplayed();
    }

    public boolean isAllFiltersButtonDisplayed(){
        return allFiltersButton.isDisplayed();
    }

    public int getSearchItemsNumber() {
        return searchResults.size();
    }

    public List<String> getSearchResultsList(){
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", searchResult);
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;
    }
}
