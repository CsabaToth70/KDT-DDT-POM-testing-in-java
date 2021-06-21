package POM.test;

import POM.pageFactory.Checkbox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCheckbox {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.seleniumeasy.com/test/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5, 1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("at-cv-lightbox-close")));
        WebElement adCloseButton = driver.findElement(By.id("at-cv-lightbox-close"));
        adCloseButton.click();
    }

    @AfterEach
    void closingTestingProcess() {
        driver.close();
    }

    @Test
    public void testSingleCheckbox() {
        String expected = "Success - Check box is checked";
        Checkbox objInputPage = new Checkbox(driver);
        objInputPage.clickInputForms();
        objInputPage.clickCheckboxMenu();
        objInputPage.clickSingleCheck();
        String singleResultText = objInputPage.getSingleResult();
        assertTrue(singleResultText.equals(expected));
    }

    @Test
    public void testMultipleCheckbox_withFilledInThreeBoxes() {
        String expected = "Check All";
        Checkbox objInputPage = new Checkbox(driver);
        objInputPage.clickInputForms();
        objInputPage.clickCheckboxMenu();
        objInputPage.clickMultipleCheck_1();
        objInputPage.clickMultipleCheck_2();
        objInputPage.clickMultipleCheck_3();
        String multipleResultText = objInputPage.getMultipleResult();
        assertTrue(multipleResultText.equals(expected));
    }

    @Test
    public void testMultipleCheckbox_withFilledInAllBoxes() {
        String expected = "Uncheck All";
        Checkbox objInputPage = new Checkbox(driver);
        objInputPage.clickInputForms();
        objInputPage.clickCheckboxMenu();
        objInputPage.clickMultipleCheck_1();
        objInputPage.clickMultipleCheck_2();
        objInputPage.clickMultipleCheck_3();
        objInputPage.clickMultipleCheck_4();
        String multipleResultText = objInputPage.getMultipleResult();
        assertTrue(multipleResultText.equals(expected));
    }

    @Test
    public void testMultipleCheckbox_withFilledInSimpleCheckboxAndThreeMultipleBoxes() {
        String expected = "Check All";
        Checkbox objInputPage = new Checkbox(driver);
        objInputPage.clickInputForms();
        objInputPage.clickCheckboxMenu();
        objInputPage.clickSingleCheck();
        objInputPage.clickMultipleCheck_1();
        objInputPage.clickMultipleCheck_2();
        objInputPage.clickMultipleCheck_3();
        String multipleResultText = objInputPage.getMultipleResult();
        assertTrue(multipleResultText.equals(expected));
    }

    @Test
    public void testMultipleCheckbox_withFilledInSimpleCheckboxAndAllMultipleBoxes() {
        String expected = "Uncheck All";
        Checkbox objInputPage = new Checkbox(driver);
        objInputPage.clickInputForms();
        objInputPage.clickCheckboxMenu();
        objInputPage.clickSingleCheck();
        objInputPage.clickMultipleCheck_1();
        objInputPage.clickMultipleCheck_2();
        objInputPage.clickMultipleCheck_3();
        objInputPage.clickMultipleCheck_4();
        String multipleResultText = objInputPage.getMultipleResult();
        assertTrue(multipleResultText.equals(expected));
    }

}
