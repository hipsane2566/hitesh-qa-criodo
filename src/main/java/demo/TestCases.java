package demo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        BookMyShowImageURLs bookMyshowImgUrls = new BookMyShowImageURLs(driver);
        List<String> urls = bookMyshowImgUrls.getImageUrls();
        for (String url : urls) {
            System.out.println("Image URL: " + url);
        }

        String nameOfMovie = bookMyshowImgUrls.getPremiereMovietName();
        String languageOfMovie = bookMyshowImgUrls.getPremiereMovieLang();
        System.out.println("Name of the movie: " + nameOfMovie + "\n Language of the movie" + languageOfMovie);
        System.out.println("End Test case: testCase04");
    }

    public void testCase05() {
        System.out.println("Start Test case: testCase05");
        NestedFrameText frameTxt = new NestedFrameText(driver);
        List<String> framesText = frameTxt.getFrameText();

        for (String frametext : framesText) {
            System.out.println("The frame text is: " + frametext);
        }
        // for (int i = 0; i < framesText.size(); i++) {
        // switch (i) {
        // case 0:
        // System.out.println("The top left frame text is: " + framesText.get(0));
        // break;
        // case 1:
        // System.out.println("The top middle frame text is: " + framesText.get(1));
        // break;
        // case 2:
        // System.out.println("The top right frame text is: " + framesText.get(2));
        // break;
        // case 3:
        // System.out.println("The bottom frame text is: " + framesText.get(2));
        // break;
        // default:
        // System.out.println("No text");
        // }
        // }
        System.out.println("End Test case: testCase05");

    }

    public void testCase06() throws InterruptedException {

        System.out.println("Start Test case: testCase06");
        ImdbRatings imdb = new ImdbRatings(driver);

        // get highest rated movie name on IMDB rating
        String highestRatedMovie = imdb.getHighestRatedMovie();
        System.out.println("The IMDB highest rated movie is: " + highestRatedMovie);

        // get the no of movies listed in the table
        int noOfMovies = imdb.getNoOfMovies();
        System.out.println("The number of movies listed in table are: " + noOfMovies);

        // get the oldest movie in the list
        String oldestReleaseMovie = imdb.getOldestMovie();
        System.out.println("The oldest movie in the list is: " + oldestReleaseMovie);

        // get the recent movie in the list
        String recentReleaseMovie = imdb.getRecentMovie();
        System.out.println("The recent movie on the list is: " + recentReleaseMovie);

        // get the most user ratings movie in the list
        String mostUserRatingMovie = imdb.getMostUserRatingMovie();
        System.out.println("The most user ratings movie is: " + mostUserRatingMovie);
        System.out.println("End Test case: testCase06");
    }

    public void testCase07() {
        System.out.println("Start Test case: testCase07");
        WindowHandle windowhandle = new WindowHandle(driver);
        // Click on try it button
        windowhandle.clickOnTryBtn();

        // Get new browser window page URL
        List<String> pageInfo = windowhandle.getPageUrl();

        System.out.println("New Window Page URL: " + pageInfo.get(0));
        System.out.println("New Window Page: " + pageInfo.get(1));

        //

        try {
            TakesScreenshot ts = ((TakesScreenshot) driver);
            File src = ts.getScreenshotAs(OutputType.FILE);
            File trg = new File(System.getProperty("user.dir") + "/OutputFile/ss1.png");
            FileUtils.copyFile(src, trg);
            System.out.println("Screenshot captured and saved to: " + trg);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        // System.out.println(pageTitle);
        System.out.println("Start Test case: testCase07");
    }
}
