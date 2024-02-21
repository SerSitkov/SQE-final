package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RemovingItemFromCartTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check removing an item from the Cart")
    public void itemFromCartTest() throws InterruptedException {

        // Opening the e-shop item page
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        // Getting an item title which will be checked that it removed from the cart
        String itemTitleText = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();

        // Adding item to the cart > navigating to the cart
        driver.findElement(By.id("add-to-cart-button-28")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();

        // Removing an item from the cart
        driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
        driver.findElement(By.xpath("//input[@name='updatecart']")).click();
        Thread.sleep(2000);

        String emptyCartText = driver.findElement(By.xpath("//div[@class='order-summary-content']")).getText();
        Thread.sleep(2000);


        // Checking if the item appears in the cart
        Assertions.assertNotEquals(itemTitleText, emptyCartText);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
