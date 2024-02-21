package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ChangingNumberOfItemsTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Changing the number of items on the page")
    public void numberOfItemsTest() throws InterruptedException {

        int expectedNumberOfItemsOnPage = 12;

        // Opening the e-shop product listing page
        driver.get("https://demowebshop.tricentis.com/apparel-shoes");

        // Locating the element holding display count of items per page
        driver.findElement(By.id("products-pagesize")).click();

        // Selecting another display count
        driver.findElement(By.xpath("//*[@id=\"products-pagesize\"]/option[3]")).click();
        Thread.sleep(2000);

        // Retrieve the number of items on page
        List<WebElement> listNumberOfItemsOnPage = driver.findElement(By.xpath("//div[@class='product-grid']")).findElements(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']/div[@class='master-wrapper-main']/div[@class='center-2']/div[@class='page category-page']/div[@class='page-body']/div[@class='product-grid']/div"));
        int actualNumberOfItemsOnPage = listNumberOfItemsOnPage.size();

        Assertions.assertEquals(expectedNumberOfItemsOnPage, actualNumberOfItemsOnPage);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
