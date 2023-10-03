package demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class BookMyShowHyperLinks {

    WebDriver driver;

    public BookMyShowHyperLinks(WebDriver driver) {
        this.driver = driver;
    }

    public int count_Hyperlinks() {

        // Enter the URL https://in.bookmyshow.com/explore/home/chennai
        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        // Search for all the hyperlinks in current page by tagname "a" Using Locator
        // "Tag Name" "a"
        List<WebElement> hyperlinks = driver.findElements(By.tagName("a"));

        int total_hyperlinks = 0;
        // Count the hyperlinks
        for (int i = 0; i < hyperlinks.size(); i++) {
            total_hyperlinks++;
        }

        return total_hyperlinks;
    }
}