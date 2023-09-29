package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SearchAmazon {

WebDriver driver;
public SearchAmazon(WebDriver driver){
    this.driver = driver;
}

public Boolean searchAmazon(){
Boolean status = false;
// Enter the URL  https://www.google.com/
driver.get("https://www.google.com/");

// Search for the text in search textbox Using Locator "ID" "APjFqb" | sendKeys("Amazon")
WebElement searchTextBox = driver.findElement(By.id("APjFqb"));
searchTextBox.sendKeys("Amazon");

// Click on Google search button Using Locator "XPath" //div[@jsname='VlcLAe']//input[@value='Google Search']
WebElement googleSrchBtn = driver.findElement(By.xpath("//div[@jsname='VlcLAe']//input[@value='Google Search']"));
googleSrchBtn.click();

// Search for the content either "Amazon.in" or "Amazon.com" Using Locator "XPath" //a[@href='https://www.amazon.in/' or @href='https://www.amazon.com/']
WebElement searchAmazonLink = driver.findElement(By.xpath("//a[contains(@href,'https://www.amazon.in')] | //a[contains(@href,'https://www.amazon.in')]" ));
if(searchAmazonLink.isDisplayed()){
    return !status;
}
// Return True if any of the content is present either "Amazon.in" or "Amazon.com"  
// Close the browser 
return status;
}
 

}