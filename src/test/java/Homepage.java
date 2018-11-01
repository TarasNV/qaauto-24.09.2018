import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static java.lang.Thread.sleep;

public class Homepage {

    private WebDriver webDriver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;
    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout]")
    private WebElement signOutButton;
    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement SearchLine;

    public Homepage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isProfileNavItemDisplayed(){
        return profileNavItem.isDisplayed();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && isProfileNavItemDisplayed();
    }

    public SearchPage performSearch(String searchTerm){
        SearchLine.sendKeys(searchTerm);
        SearchLine.sendKeys(Keys.ENTER);
        return new SearchPage(webDriver);
    }



}
