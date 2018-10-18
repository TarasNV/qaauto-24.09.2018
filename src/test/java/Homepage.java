import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {

    WebDriver webDriver;

    WebElement profileNavItem;

    public Homepage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public void initElements(){
        profileNavItem = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }
}
