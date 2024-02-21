package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class DownloadingReportTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Checking download ability and correctness of the file name and extension")
    public void downloadReportTest() throws InterruptedException {

        String expectedFileName = "EPAM_Corporate_Overview_Q4_EOY.pdf";

        //Opening the EPAM site
        driver.get("https://www.epam.com/about");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

        driver.findElement(By.xpath("//span[@class='button__content button__content--desktop'][normalize-space()='DOWNLOAD']")).click();

        Thread.sleep(5000);

        File file = new File("C://Users//Serhii_Sitkovskyi//Downloads//EPAM_Corporate_Overview_Q4_EOY.pdf");
        String downloadedFileName = file.getName();

        Assertions.assertEquals(expectedFileName, downloadedFileName);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
