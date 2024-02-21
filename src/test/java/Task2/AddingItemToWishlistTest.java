package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddingItemToWishlistTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check adding an item to the Wishlist")
    public void itemToWishlistTest() throws InterruptedException {

        // Opening the e-shop item page
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        // Getting an item title which will be added to the wishlist
        String itemTitleText = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();

        // Adding an item to the wishlist
        driver.findElement(By.id("add-to-wishlist-button-28")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='Wishlist']")).click();

        String itemInWishlistText = driver.findElement(By.xpath("//td[@class='product']/a")).getText();
        Thread.sleep(2000);


        // Checking if the item appears in the wishlist
        Assertions.assertEquals(itemTitleText, itemInWishlistText);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
