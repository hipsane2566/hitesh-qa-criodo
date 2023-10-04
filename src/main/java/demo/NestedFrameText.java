package demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NestedFrameText {
    WebDriver driver;

    public NestedFrameText(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getFrameText() {
        List<String> framesText = new ArrayList<>();
        // Navigate to the URL https://the-internet.herokuapp.com/nested_frames
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Switch to top frame Using Locator "Name" frame-top
        driver.switchTo().frame("frame-top");
        // Switch to left frame Using Locator "Name" frame-left
        driver.switchTo().frame("frame-left");

        // Get the text and print the text Using Locator "Tag Name" body
        WebElement leftText = driver.findElement(By.tagName("body"));
        framesText.add(leftText.getText());

        // Switch to parent frame content
        driver.switchTo().parentFrame();
        // Switch to middle frame Using Locator "Name" frame-middle]
        driver.switchTo().frame("frame-middle");

        // Get the text and print the text Using Locator "Tag Name" body
        WebElement middleText = driver.findElement(By.id("content"));
        framesText.add(middleText.getText());

        // Switch to parent frame content
        driver.switchTo().parentFrame();
        // Switch to right frame Using Locator "Name" frame-middle]
        driver.switchTo().frame("frame-right");

        // Get the text and print the text Using Locator "Tag Name" body
        WebElement rightText = driver.findElement(By.tagName("body"));
        framesText.add(rightText.getText());

        // Switch to defaultContent switchTo().defaultContent()
        driver.switchTo().defaultContent();
        // Switch to bottom frame Using Locator "XPath" //frame[@src='/frame_bottom']
        driver.switchTo().frame("frame-bottom");

        // Get the text and print the text Using Locator "Tag Name" body
        WebElement bottomText = driver.findElement(By.tagName("body"));
        framesText.add(bottomText.getText());

        // Switch to defaultContent switchTo().defaultContent()
        driver.switchTo().defaultContent();
        return framesText;
    }

}
