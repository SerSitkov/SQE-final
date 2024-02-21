package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class CheckoutItemTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        // Disabling Chrome's "Save address?" popup that appears after filling address fields
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Checkout an item")
    public void checkoutTest() throws InterruptedException {

        String expectedOrderConfirmationMsg = "Your order has been successfully processed!";

        // Opening the e-shop item page
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        // Adding an item to the cart
        driver.findElement(By.id("add-to-cart-button-28")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();

        // Providing agreement and proceeding to the checkout page
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        driver.findElement(By.xpath("//input[@value='Checkout as Guest']")).click();
        Thread.sleep(2000);

        // Checkout page / Billing Address / Filling required fields
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Fname");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Lname");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("em@mail.com");
        driver.findElement(By.id("BillingNewAddress_CountryId")).click();
        driver.findElement(By.xpath("//*[@id='BillingNewAddress_CountryId']/option[222]")).click();
        driver.findElement(By.xpath("//div[@id='checkout-step-billing']")).click();
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Білгородська Народна");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Щєбякіно");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("2024");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("+38");
        driver.findElement(By.xpath("//input[@onclick='Billing.save()']")).click();
        Thread.sleep(2000);

        // Checkout page / Shipping Address
        driver.findElement(By.xpath("//input[@onclick='Shipping.save()']")).click();
        Thread.sleep(2000);

        // Checkout page / Shipping Method
        driver.findElement(By.xpath("//input[@class='button-1 shipping-method-next-step-button']")).click();
        Thread.sleep(2000);

        // Checkout page / Payment Method
        driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']")).click();
        Thread.sleep(2000);

        // Checkout page / Payment Information
        driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
        Thread.sleep(2000);

        // Checkout page / Confirm Order
        driver.findElement(By.xpath("//input[@value='Confirm']")).click();
        Thread.sleep(2000);

        // Thank you page / Asserting
        String actualOrderConfirmationMsg = driver.findElement(By.xpath("//strong[normalize-space()]")).getText();

        Assertions.assertEquals(expectedOrderConfirmationMsg, actualOrderConfirmationMsg);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }
}
