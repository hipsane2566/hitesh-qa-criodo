package demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookMyShowImageURLs {
    WebDriver driver;

    public BookMyShowImageURLs(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getImageUrls() {
        // Navigate to URL https://in.bookmyshow.com/explore/home/chennai
        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        // Search for all the images URL under Recommended movies by h4 element Using
        // Locator "XPath" //h2[text()='Recommended
        // Movies']/parent::div/parent::div/following::div[3]/descendant::img
        List<WebElement> imagElements = driver.findElements(By
                .xpath("//h2[text()='Recommended Movies']/parent::div/parent::div/following::div[3]/descendant::img"));
        List<String> imageUrls = new ArrayList<>();

        // loops through the searched URL and get attributes of each urls
        // urls.getAttribute("src")
        for (WebElement element : imagElements) {
            String imageUrl = element.getAttribute("src");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                imageUrls.add(imageUrl);
            }
        }
        // Print the img urls
        return imageUrls;

    }

    public String getPremiereMovietName() {
        try {

            // Search for the name of 2nd item in the premiere list and get text Using
            // Locator "XPath"
            // //h2[contains(text(),'Premieres')]/ancestor::div[3]/following::div[1]/descendant::a[2]/descendant::div[8]
            // | .getText()
            WebElement nameOfMovie = driver.findElement(By.xpath(
                    "//h2[contains(text(),'Premieres')]/ancestor::div[3]/following::div[1]/descendant::a[2]/descendant::div[8]"));
            return nameOfMovie.getText();
        } catch (Exception e) {
            System.out.println("Unable to find the element: " + e.getStackTrace());
        }
        return null;
    }

    public String getPremiereMovieLang() throws InterruptedException {

        try {
            // Search for the language of 2nd item in the premiere list and get text Using
            // Locator "XPath"
            // //h2[contains(text(),'Premieres')]/ancestor::div[3]/following::div[1]/descendant::a[2]/descendant::div[10]
            // | .getText()
            WebElement languageOfMovie = driver.findElement(By.xpath(
                    "//h2[contains(text(),'Premieres')]/ancestor::div[3]/following::div[1]/descendant::a[2]/descendant::div[10]"));
            // Print both the name and language of 2nd item in the premiere list

            return languageOfMovie.getText();
        } catch (Exception e) {
            System.out.println("Unable to find the element: " + e.getStackTrace());
        }
        return null;
    }
}
