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

        WebElement america = driver.findElement(By.xpath("//a[normalize-space()='AMERICAS']"));
        Assertions.assertTrue(america.isDisplayed());
        List<WebElement> americaElements = driver.findElements(By.className("locations-viewer-23__country-btn"));
        Assertions.assertTrue(americaElements.stream().anyMatch(p -> p.getAttribute("data-country-title").equalsIgnoreCase("Canada")));

        WebElement emea = driver.findElement(By.xpath("//a[normalize-space()='EMEA']"));
        Assertions.assertTrue(emea.isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='EMEA']")).click();
        js.executeScript("arguments[0].scrollIntoView();", ourLocationsAnchor);
        Thread.sleep(2000);
        List<WebElement> emeaElements = driver.findElements(By.className("locations-viewer-23__country-btn"));
        Assertions.assertTrue(emeaElements.stream().anyMatch(p -> p.getAttribute("data-country-title").equalsIgnoreCase("Austria")));

        WebElement apac = driver.findElement(By.xpath("//a[normalize-space()='APAC']"));
        Assertions.assertTrue(apac.isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='APAC']")).click();
        js.executeScript("arguments[0].scrollIntoView();", ourLocationsAnchor);
        Thread.sleep(2000);
        List<WebElement> apacElements = driver.findElements(By.className("locations-viewer-23__country-btn"));
        Assertions.assertTrue(apacElements.stream().anyMatch(p -> p.getAttribute("data-country-title").equalsIgnoreCase("Australia")));
    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}