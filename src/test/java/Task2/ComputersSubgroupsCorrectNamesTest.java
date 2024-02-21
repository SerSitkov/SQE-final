package Task2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ComputersSubgroupsCorrectNamesTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check that ‘Computers’ group has 3 sub-groups with correct names")
    public void subGroupsTest() throws InterruptedException {

        // Setting data for checking
        String desktop = "Desktops";
        String notebooks = "Notebooks";
        String accessories = "Accessories";

        // Opening the e-shop login page
        driver.get("https://demowebshop.tricentis.com/");

        // Finding the element and hovering it over
        WebElement computersElementToHover = driver.findElement(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']/div[@class='header-menu']/ul[@class='top-menu']/li[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(computersElementToHover).perform();
        Thread.sleep(3000);

        // Finding and getting manes of subgroups
        WebElement desktopElementToHover = driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//li[1]"));
        actions.moveToElement(desktopElementToHover).perform();
        Thread.sleep(1000);
        String desktopText = driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//li[1]")).getText();

        WebElement notebooksElementToHover = driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//li[2]"));
        actions.moveToElement(notebooksElementToHover).perform();
        Thread.sleep(1000);
        String notebooksText = driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//li[2]")).getText();

        WebElement accessoriesElementToHover = driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//li[3]"));
        actions.moveToElement(accessoriesElementToHover).perform();
        Thread.sleep(1000);
        String accessoriesText = driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//li[3]")).getText();

        // Checking if the subgroups displayed with the correct names
        Assertions.assertEquals(desktop, desktopText);
        Assertions.assertEquals(notebooks, notebooksText);
        Assertions.assertEquals(accessories, accessoriesText);


    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
