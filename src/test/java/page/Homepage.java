package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Linkedin Home page object class
 */

public class Homepage extends BasePage{

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;
    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout]")
    private WebElement signOutButton;
    @FindBy(xpath = "//input[contains(@aria-owns, 'results')]")
    private WebElement SearchLine;

    /**
     * Constructor for Homepage.
     *
     * @param webDriver - driver instance from tests.
     */

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

    /**
     * Method that populates search line with searchTerm and presses Enter
     *
     * @param searchTerm - key word that has been entered into search line
     */

    public SearchPage search(String searchTerm){
        SearchLine.sendKeys(searchTerm);
        SearchLine.sendKeys(Keys.ENTER);
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchPage(webDriver);
    }



}
