package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddingItemToCartTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check adding an item to the Cart")
    public void itemToCartTest() throws InterruptedException {

        // Opening the e-shop item page
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        // Getting an item title which will be added to the cart
        WebElement itemTitleElement = driver.findElement(By.xpath("//h1[@itemprop='name']"));
        String itemTitleText = itemTitleElement.getText();

        // Adding to the cart > to the shopping page
        driver.findElement(By.id("add-to-cart-button-28")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();

        String itemInCartText = driver.findElement(By.xpath("//td[@class='product']/a")).getText();
        Thread.sleep(2000);


        // Checking if the item appears in the cart
        Assertions.assertEquals(itemTitleText, itemInCartText);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
