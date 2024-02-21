package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class SortingItemsTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check the sorting feature")
    public void sortingItemsTest() throws InterruptedException {

        // Opening the e-shop product listing page
        driver.get("https://demowebshop.tricentis.com/apparel-shoes");

        // Default state of sorted items
        List<WebElement> defaultItemsSort = driver.findElements(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']/div[@class='master-wrapper-main']/div[@class='center-2']/div[@class='page category-page']/div[@class='page-body']/div[@class='product-grid']/div"));

        Thread.sleep(2000);

        // Extract item text
        List<String> defaultItemTexts = new ArrayList<>();
        for (WebElement item : defaultItemsSort) {
            defaultItemTexts.add(item.getText());
        }

        // Changing product sorting
        driver.findElement(By.id("products-orderby")).click();
        driver.findElement(By.xpath("//*[@id='products-orderby']/option[4]")).click();

        // Changed state of sorted items
        List<WebElement> changedItemsSort = driver.findElements(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']/div[@class='master-wrapper-main']/div[@class='center-2']/div[@class='page category-page']/div[@class='page-body']/div[@class='product-grid']/div"));

        List<String> changedItemTexts = new ArrayList<>();
        for (WebElement item : changedItemsSort) {
            changedItemTexts.add(item.getText());
        }

        Assertions.assertNotEquals(defaultItemTexts, changedItemTexts);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }
}


