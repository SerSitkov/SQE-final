package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChangeLanguageTest {
    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Switching the language to UA")
    public void switchingLanguage() throws InterruptedException {
        // Opening the EPAM site
        driver.get("https://www.epam.com/");

        // Defining current language on site
        String currentLang = driver.findElement(By.xpath("/html")).getAttribute("lang");
        System.out.println("Current language: " + currentLang);

        // Changing language
        driver.findElement(By.xpath("//button[@class='location-selector__button']//span[@class='location-selector__button-language']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'location-selector-ui location-selector-ui-23 header__control')]//a[contains(@class,'location-selector__link')][contains(text(),'Україна')]")).click();
        Thread.sleep(5000);

        // Defining language after changing it
        String changedLang = driver.findElement(By.xpath("/html")).getAttribute("lang");
        System.out.println("Changed language: " + changedLang);

        // Asserting
        Assertions.assertNotEquals(currentLang, changedLang);
    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
