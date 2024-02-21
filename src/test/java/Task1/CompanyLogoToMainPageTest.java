package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CompanyLogoToMainPageTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Checking that the Company logo on the header lead to the main page")
    public void companyLogoToMainPageTest() {

        String expectedLink = "https://www.epam.com/";

        //Opening the EPAM site
        driver.get("https://www.epam.com/about");
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

        driver.findElement(By.xpath("//a[@class='header__logo-container desktop-logo']//img[3]")).click();

        String currentLink = driver.getCurrentUrl();
        System.out.println("Current link: " + currentLink);

        Assertions.assertEquals(expectedLink, currentLink);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
