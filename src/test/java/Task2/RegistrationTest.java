package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.github.javafaker.Faker;

public class RegistrationTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check registering a user")
    public void registeringUserTest() throws InterruptedException {

        //Generating random data for registration
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        //Opening the e-shop registration page
        driver.get("https://demowebshop.tricentis.com/register");

        // Fill in registration form
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        WebElement registerButton = driver.findElement(By.id("register-button"));
        Thread.sleep(2000);
        registerButton.click();

        WebElement completedRegistrationText = driver.findElement(By.xpath("//div[@class='result']"));
        System.out.println("Registration complete: " + completedRegistrationText.isDisplayed());

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
