package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        driver.findElement(By.xpath("//input[@id='new_form_search']")).sendKeys("AI");
        driver.findElement(By.xpath("//span[@class='bth-text-layer']")).click();
        Thread.sleep(2000);

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