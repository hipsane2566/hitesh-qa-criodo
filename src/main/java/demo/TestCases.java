package demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import org.openqa.selenium.WebDriver;
///

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        SearchAmazon srchAmamzon = new SearchAmazon(driver);
        Boolean status = srchAmamzon.searchAmazon();
        if (!status) {
            System.out.println("Search results do not contain amazon.in or amazon.com");
        }
        System.out.println("Search results contain amazon.in or amamzon.com");
        System.out.println("End Test case: testCase01");
    }

    public void testCase02() {
        int totalNoOfHyperlinks;
        System.out.println("Start Test case: testCase02");
        BookMyShowHyperLinks hyperlinks = new BookMyShowHyperLinks(driver);
        totalNoOfHyperlinks = hyperlinks.count_Hyperlinks();
        System.out.println("The total no of hyperlinks present in given webpage are: " + totalNoOfHyperlinks);
        System.out.println("End Test case: testCase02");
    }

    //
    public void testCase03() throws InterruptedException {
        int profileViewCount;
        int impressionViewCount;
        Boolean status;
        String postMessage = "This is for demo post";

        System.out.println("Start Test case: testCase03");
        PostOnLinkedIn postonlinkedin = new PostOnLinkedIn(driver);

        // Login to application
        postonlinkedin.loginToApp("hiteshpatiyal1566@gmail.com", "Pa$$w.rd");

        // Get count of profil view and impression view and print it.
        profileViewCount = postonlinkedin.getCountOfProfileView();
        impressionViewCount = postonlinkedin.getCountOfImpression();
        System.out.println("Count of `Who's viewed your profile`: " + profileViewCount);
        System.out.println("Count of 'Impressions of your post': " + impressionViewCount);

        // Verify the post is sharing with `Connections only` and confirm if it gets
        // posted
        status = postonlinkedin.createPost(postMessage);
        if (!status) {
            System.out.println("Failed to create post");
        } else {
            System.out.println("Post get successfully posted");
        }

        System.out.println("End Test case: testCase03");
    }

}
