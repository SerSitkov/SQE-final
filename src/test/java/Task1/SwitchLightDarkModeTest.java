package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchLightDarkModeTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Switching the theme mode")
    public void switchingMode() throws InterruptedException {
        //Opening the EPAM site
        driver.get("https://www.epam.com/");

        // Switching theme mode to opposite
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[2]/div[1]/header/div/div/section/div")).click();
        Thread.sleep(2000);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}