import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@data-control-name='all_filters']")
    private WebElement allFiltersButton;
    @FindBy(xpath = "//div[@class='search-result__wrapper']")
    private WebElement searchItem;
    //@FindBy(xpath = "//div[@class='search-result__wrapper']")
    //private List<WebElement> searchResults;

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

    public boolean isTenResultsPerPage(List<WebElement> searchResults){
        if (searchResults.size() == 10){
            return true;
        } else {
            return false;
        }
    }

    public boolean isSearchItemContainSearchTerm(List<WebElement> searchResults, String searchTerm){
        for (WebElement searchResult : searchResults){
            String searchResultText = searchResult.getText();
            boolean searchResultFlag = true;
            if (searchResultText.toLowerCase().contains(searchTerm.toLowerCase())){
                 searchResultFlag = true;
                 return searchResultFlag;
            } else {
                searchResultFlag = false;
                return searchResultFlag;
            }

        }
        if (true){
            return true;
        }else {
            return false;
        }
    }

}
