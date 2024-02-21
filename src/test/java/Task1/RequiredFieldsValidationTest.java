package Task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RequiredFieldsValidationTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetup() {
        driver = new ChromeDriver(); //"new FirefoxDriver()" - for using Firefox Browser
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check required fields validation")
    public void requiredFieldsValidation() throws InterruptedException {

        //Opening the EPAM site
        driver.get("https://www.epam.com/about/who-we-are/contact");
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

        WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
        Thread.sleep(2000);

        submitButton.click();
        Thread.sleep(2000);

        WebElement firstNameError = driver.findElement(By.xpath("//*[@id=\"_content_epam_en_about_who-we-are_contact_jcr_content_content-container_section_section-par_form_constructor_user_first_name-error\"]/span"));
        WebElement lastNameError = driver.findElement(By.xpath("//*[@id=\"_content_epam_en_about_who-we-are_contact_jcr_content_content-container_section_section-par_form_constructor_user_last_name-error\"]/span"));
        WebElement emailError = driver.findElement(By.xpath("//*[@id=\"_content_epam_en_about_who-we-are_contact_jcr_content_content-container_section_section-par_form_constructor_user_email-error\"]/span"));
        WebElement phoneError = driver.findElement(By.xpath("//*[@id=\"_content_epam_en_about_who-we-are_contact_jcr_content_content-container_section_section-par_form_constructor_user_phone-error\"]/span"));
        //WebElement howDidYouHearAboutEPAMError = driver.findElement(By.xpath("//span[@aria-expanded='true']//span[@role='textbox']"));
        //div[@class='dropdown-list__input form-component__input']//span[@class='validation-text'][normalize-space()='This is a required field']
        //WebElement consentCheckError = driver.findElement(By.xpath("//label[@for='new_form_gdprConsent_33e5a285-63a1-4c40-b486-fd2d32b265bd']"));
        if (firstNameError.isDisplayed() && lastNameError.isDisplayed() && emailError.isDisplayed()
                && phoneError.isDisplayed()) {
            System.out.println("Validation for required fields is working correctly.");
        } else {
            System.out.println("Validation for required fields is not working correctly.");
        }

    }

    @AfterAll
    public static void browserClose() {
        if (driver != null) {
            driver.quit(); // closing WebDriver window
        }
    }

}
