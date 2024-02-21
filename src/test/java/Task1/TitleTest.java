package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TitleTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
    }

    @Test
    @DisplayName("Checking the title test")
    public void checkingTitle() {

        String expectedTitle = "EPAM | Software Engineering & Product Development Services";

        //Opening the EPAM site
        driver.get("https://www.epam.com/");

        //Checking the current theme mode
        String actualTitle = driver.getTitle();

        Assertions.assertEquals(expectedTitle, actualTitle);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }
}


