package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckPoliciesListTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Checking the policies list")
    public void switchingMode() throws InterruptedException {
        // Setting up expected list of policies
        List<String> policiesExpected = Arrays.asList(
                "INVESTORS",
                "OPEN SOURCE",
                "PRIVACY POLICY",
                "COOKIE POLICY",
                "APPLICANT PRIVACY NOTICE",
                "WEB ACCESSIBILITY");


        // Opening the EPAM site
        driver.get("https://www.epam.com/");

        WebElement policyList = driver.findElement(By.xpath("//div[@class='policies-links-wrapper']"));
        List<WebElement> policies = policyList.findElements(By.tagName("li"));
        Thread.sleep(2000);

        // Print out each policy
        List<String> policiesActual = new ArrayList<>();
        for (WebElement policy : policies) {
            String policyActual = (policy.getText());
            policiesActual.add(policyActual);
            System.out.println(policyActual);
        }

        Assertions.assertEquals(policiesExpected, policiesActual);

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
