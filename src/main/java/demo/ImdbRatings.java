package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ImdbRatings {
    WebDriver driver;

    public ImdbRatings(WebDriver driver) {
        this.driver = driver;
    }

    public String getHighestRatedMovie() {
        // navigate to URL "https://www.imdb.com/chart/top"
        driver.get("https://www.imdb.com/chart/top");

        // Get the sort option by select method Using Locator "ID" sort-by-selector
        // |dropdown
        // Select the IMDB rating option from sort by dropdown
        // dropdown.selectByValue("USER_RATING")
        selectSortBy("USER_RATING");

        // Get higest rating move name on top Using Locator "XPath"
        // //ul[contains(@class, 'ipc-metadata-list')]/li[1]/descendant::h3
        WebElement movieName = driver
                .findElement(By.xpath("//ul[contains(@class, 'ipc-metadata-list')]/li[1]/descendant::h3"));

        return extractMovieTitle(movieName.getText());
    }

    public int getNoOfMovies() throws InterruptedException {
        int totalCount = 0;
        // Get the count of movies listed in table Using Locator "XPath"
        // //ul[contains(@class, 'ipc-metadata-list')]/li | WebElements
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);
        List<WebElement> moviesList = driver.findElements(By.xpath("//ul[contains(@class, 'ipc-metadata-list')]/li"));
        for (int i = 0; i < moviesList.size(); i++) {
            totalCount++;
        }
        return totalCount;
    }

    public String getOldestMovie() {

        // Select the IMDB release date option from sort by dropdown
        // dropdown.selectByValue("RELEASE_DATE")
        selectSortBy("RELEASE_DATE");

        // Click on the sort by direction Using Locator "ID" swap-sort-order-button
        clickOnSortOption();

        // Get the name of oldest movie listed on IMDB Using Locator "XPath"
        // //ul[contains(@class, 'ipc-metadata-list')]/li[1]/div[2]/descendant::h3
        WebElement movieName = driver
                .findElement(By.xpath("//ul[contains(@class, 'ipc-metadata-list')]/li[1]/div[2]/descendant::h3"));
        return extractMovieTitle(movieName.getText());
    }

    public String getRecentMovie() {

        // Click on the sort by direction Using Locator "ID" swap-sort-order-button
        clickOnSortOption();

        // Get the name of most recent movie listed on IMDB Using Locator "XPath"
        // //ul[contains(@class, 'ipc-metadata-list')]/li[1]/div[2]/descendant::h3
        WebElement movieName = driver
                .findElement(By.xpath(" //ul[contains(@class, 'ipc-metadata-list')]/li[1]/div[2]/descendant::h3"));
        return extractMovieTitle(movieName.getText());
    }

    public String getMostUserRatingMovie() {

        // Select the IMDB Number of rating option from sort by dropdown
        // dropdown.selectByValue("USER_RATING_COUNT")
        selectSortBy("USER_RATING_COUNT");

        // Get the name of most user rated movie Using Locator "XPath"
        // //ul[contains(@class, 'ipc-metadata-list')]/li[1]/div[2]/descendant::h3
        WebElement movieName = driver
                .findElement(By.xpath("//ul[contains(@class, 'ipc-metadata-list')]/li[1]/div[2]/descendant::h3"));
        return extractMovieTitle(movieName.getText());

    }

    public void selectSortBy(String value) {
        WebElement sortDropdown = driver.findElement(By.id("sort-by-selector"));
        Select select = new Select(sortDropdown);
        select.selectByValue(value);
    }

    public void clickOnSortOption() {
        WebElement clickOnSort = driver.findElement(By.id("swap-sort-order-button"));
        clickOnSort.click();
    }

    public String extractMovieTitle(String title) {
        int indexOfSpaceAfterDot = title.indexOf(". ") + 2;
        String movieTitle = title.substring(indexOfSpaceAfterDot);
        return movieTitle;
    }
}
