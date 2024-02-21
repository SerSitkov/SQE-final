package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SwitchingLocationListByRegionTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Checking the locations list")
    public void switchingLocation() throws InterruptedException {
        //Opening the EPAM site
        driver.get("https://www.epam.com/");

        WebElement regionList = driver.findElement(By.xpath("(//div[@role='tablist'])"));
        List<WebElement> regions = regionList.findElements(By.tagName("a"));
        Thread.sleep(2000);

        //Print out each region
        System.out.println("Regions on the site:");
        for (WebElement region : regions) {
            System.out.println(region.getText());
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement ourLocationsAnchor = driver.findElement(By.xpath("//span[@class='museo-sans-light'][contains(text(),'Our')]"));
        js.executeScript("arguments[0].scrollIntoView();", ourLocationsAnchor);

        driver.findElement(By.xpath("//a[normalize-space()='EMEA']")).click();
        js.executeScript("arguments[0].scrollIntoView();", ourLocationsAnchor);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[normalize-space()='APAC']")).click();
        js.executeScript("arguments[0].scrollIntoView();", ourLocationsAnchor);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[normalize-space()='AMERICAS']")).click();
        Thread.sleep(2000);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
