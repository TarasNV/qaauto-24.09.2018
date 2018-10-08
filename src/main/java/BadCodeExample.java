import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {

    public static void main(String args[]) throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://google.com");
        WebElement searchLine = webDriver.findElement(By.xpath("//input[@id='lst-ib']"));
        String searchTerm = "Selenium";
        searchLine.sendKeys(searchTerm);
        searchLine.sendKeys(Keys.ENTER);

        sleep(3000);

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));

        System.out.println("Search results count: " + searchResults.size());

       /* for (int i = 0; i < searchResults.size(); i++){
            WebElement item = searchResults.get(i);
            System.out.println(item.getText());
            if (item.getText().contains(searchTerm)){
                System.out.println("searTermm was found");
            } else {
                System.out.println("searchTers not found");
            }
            System.out.println("______________________________");
        }*/
        //class homework
        for (WebElement searchResult : searchResults){
            String searchResultText = searchResult.getText();
            System.out.println(searchResultText);
            if (searchResultText.toLowerCase().contains(searchTerm.toLowerCase())){
                System.out.println("SearchTerm " + searchTerm + " was found");
            } else {
                System.out.println("SearchTerm was not found");
            }
            System.out.println("__________________________________");
        }
    }
}



