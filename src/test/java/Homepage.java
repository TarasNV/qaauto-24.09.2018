import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Homepage {

    private WebDriver webDriver;

    private WebElement profileNavItem;

    public Homepage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isProfileNavItemDisplayed(){
        return profileNavItem.isDisplayed();
    }

    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && isProfileNavItemDisplayed();
    }
    private void initElements(){
        profileNavItem = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }
}
