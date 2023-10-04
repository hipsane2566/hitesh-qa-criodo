package demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Set;

public class WindowHandle {
    WebDriver driver;

    public WindowHandle(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnTryBtn() {
        // Navigate to the URL
        // https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        // Switch to iframe switchTo.frame("iframeResult")
        driver.switchTo().frame("iframeResult");

        // Click on try it button Using Locator "XPath" //button[contains(text(),'Try
        // it')]
        WebElement tryBtn = driver.findElement(By.xpath("//button[contains(text(),'Try it')]"));
        tryBtn.click();
    }

    public List<String> getPageUrl() {
        // Get the list of window handles //button[contains(text(),'Try it')]
        List<String> newWindowDetails = new ArrayList<>();
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to child window switctTo().window(childWindows)
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Get the current page URL getCurrentURL()
        String webPageUrl = driver.getCurrentUrl();
        newWindowDetails.add(webPageUrl);
        String webPageTitle = driver.getTitle();
        newWindowDetails.add(webPageTitle);

        return newWindowDetails;
    }

    // public String getPageTitle() {
    // String webPageTitle = driver.getTitle();
    // return webPageTitle;
    // }
    // Get the title of current page getTItle()
    // Take the screenshot of the current page getScreenshotAs(OutputType.File)
    // Close the child window driver.close()
    // Switch back to main window switctTo().window(parentWindows)
}
