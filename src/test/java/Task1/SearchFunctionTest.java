package Task1;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchFunctionTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Searching function")
    public void searchingFunction() throws InterruptedException {


        //Opening the EPAM site
        driver.get("https://www.epam.com/");
        driver.findElement(By.xpath("//span[@class='search-icon dark-iconheader-search__search-icon']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='new_form_search']")));
        element.sendKeys("AI");
        driver.findElement(By.xpath("//span[@class='bth-text-layer']")).click();
        Thread.sleep(2000);

        List<WebElement> subTitleElements = driver.findElements(By.className("search-results__description"));
        List<WebElement> titleElements = driver.findElements(By.className("search-results__title"));
        //Example of lambda implementations;
//        elements.forEach(p ->
//            Assertions.assertTrue(p.getText().contains("AI")));
//        Assertions.assertTrue(subTitleElements.parallelStream().allMatch(p -> p.getText().contains("AI")));
        int trueCounter = 0;
        for (int i = 0; i < titleElements.size(); i++) {
            boolean isAIInTitle = titleElements.get(i).getText().contains("AI");
            boolean isAIInSubTitle = subTitleElements.get(i).getText().contains("AI");
            trueCounter = isAIInTitle || isAIInSubTitle ? trueCounter + 1 : trueCounter;
        }
        Assertions.assertEquals(trueCounter, titleElements.size(),
                String.format("Search result title and subtitle don't contain search request. Expected titleElements.size(), actual %s", titleElements.size(), trueCounter));

        boolean searchResult = driver.findElement(By.xpath("//div[2]//div[4]//section[1]//div[3]//article[1]//h3[1]")).isDisplayed();

        System.out.println("Search results are displayed: " + searchResult);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
//WebElement searchResults = (WebElement) driver.findElements(By.cssSelector(".search-results__title-link"));
//getText()