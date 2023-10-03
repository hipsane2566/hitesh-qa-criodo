package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class PostOnLinkedIn {
    WebDriver driver;

    public PostOnLinkedIn(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToApp(String username, String password)
            throws StaleElementReferenceException, InterruptedException {

        // Enter the URL https://in.linkedin.com/
        driver.get("https://in.linkedin.com/");

        // Enter the email id Using Locator "Name" "session_key"
        WebElement usernameTxt = driver.findElement(By.name("session_key"));
        usernameTxt.sendKeys(username);

        // Enter the password Using Locator "Name" "session_password"
        WebElement passwordTxt = driver.findElement(By.name("session_password"));
        passwordTxt.sendKeys(password);

        // Click on Sign in button Using Locator "XPath"
        // //button[contains(text(),'Signin')]
        WebElement signBtn = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
        signBtn.click();

        // Wait for page to load
        Thread.sleep(3000);
    }

    public int getCountOfProfileView() {
        try {
            int profileView_count;
            // Get the count of the Who's viewed your profile by tagname Using Locator
            // "XPath" //a[@href='/me/profile-views/']/descendant::strong
            WebElement viewedProfile = driver
                    .findElement(By.xpath("//a[@href='/me/profile-views/']/descendant::strong"));

            String count = viewedProfile.getText();
            profileView_count = Integer.parseInt(count);
            return profileView_count;
        } catch (StaleElementReferenceException e) {
            WebElement viewedProfile = driver
                    .findElement(By.xpath("//a[@href='/me/profile-views/']/descendant::strong"));
            String count = viewedProfile.getText();
            System.out.println("Unable to find the element for Profile view count" + count);
        }
        return 0;

    }

    public int getCountOfImpression() {
        try {
            int impressionView_count;
            // Get the count of the Impressions of your post by tagname Using Locator
            // "XPath" //a[@href='/me/impression-views/']/descendant::strong
            WebElement viewedImpression = driver
                    .findElement(By.xpath("//a[@href='/me/profile-views/']/following::li/descendant::strong"));
            String count = viewedImpression.getText();
            impressionView_count = Integer.parseInt(count);
            return impressionView_count;
        } catch (StaleElementReferenceException e) {
            WebElement viewedImpression = driver
                    .findElement(By.xpath("//a[@href='/me/profile-views/']/following::li/descendant::strong"));
            System.out.println("Impression of your post option is not visible" + viewedImpression);
            return 0;
        }
    }

    public Boolean createPost(String storyText) throws StaleElementReferenceException, InterruptedException {
        Boolean status = false;
        try {
            String successToasrMsgBox;
            // Click on search for post button Using Locator "XPath" //span[text ()= 'Start
            // a post']
            WebElement startPostBtn = driver.findElement(By.xpath("//span[text ()= 'Start a post']"));
            startPostBtn.click();

            // Click on share option Using Locator "XPath"
            // //h2[@id='share-to-linkedin-modal__header']/button
            WebElement shareOpt = driver.findElement(By.xpath("//h2[@id='share-to-linkedin-modal__header']/button"));
            shareOpt.click();

            // Select the connection only radio option Using Locator "ID"
            // sharing-shared-generic-list-radio-CONNECTIONS_ONLY
            WebElement connectRadio = driver.findElement(By.id("CONNECTIONS_ONLY"));
            connectRadio.click();

            Thread.sleep(2000);

            // Click on done button Using Locator "XPath" //span[text()='Done']
            WebElement doneBtn = driver.findElement(By.xpath("//span[text()='Done']"));
            doneBtn.click();

            // Enter the text stories Using Locator "XPath"
            // //p/parent::div[@class='ql-editor ql-blank']
            WebElement enterTxtArea = driver.findElement(By.xpath("//p/parent::div[@class='ql-editor ql-blank']"));
            enterTxtArea.sendKeys(storyText);

            // Click on post button Using Locator "XPath" //span[text()='Post']
            WebElement postBtn = driver.findElement(By.xpath("//span[text()='Post']"));
            postBtn.click();

            // Verify success toast message "Post scuessful." is displayed Using Locator
            // "XPath" //p[@class='artdeco-toast-item__message']/span
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//p[@class='artdeco-toast-item__message']/span")));
            WebElement toastMsgBox = driver.findElement(By.xpath("//p[@class='artdeco-toast-item__message']/span"));
            successToasrMsgBox = toastMsgBox.getText();
            if (!successToasrMsgBox.equalsIgnoreCase("Post successful.")) {
                return status;
            }
        } catch (Exception e) {
            System.out.println("Failed to create the post " + e.getStackTrace());
            return status;
        }
        return !status;

    }

}