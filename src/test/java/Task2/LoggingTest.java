package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoggingTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check logging a user")
    public void loggingUserTest() throws InterruptedException {

        // Setting data for logging
        String email = "em@mail.com";
        String password = "123456";

        // Opening the e-shop login page
        driver.get("https://demowebshop.tricentis.com/login");

        // Fill in login fields
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);

        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        Thread.sleep(2000);

        // Checking if the current user are logged in
        driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']")).click();

        String loggedEmailText = driver.findElement(By.id("Email")).getAttribute("value");

        Assertions.assertEquals(email, loggedEmailText);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }
}
