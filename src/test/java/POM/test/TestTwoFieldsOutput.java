package POM.test;

import POM.pageFactory.TwoFieldsOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTwoFieldsOutput {
    WebDriver driver;

    @BeforeEach
    public void setup(){
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
    void closingTestingProcess(){
        driver.close();
    }

    @Test
    public void testTwoFields_withSmallPositiveIntegers() throws IOException {
        String numberFirst = "4";
        String numberSecond = "3";
        String expectedText = "7";
        TwoFieldsOutput objInputPage = new TwoFieldsOutput(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSimpleMenu();
        objInputPage.enterNumbers(numberFirst, numberSecond);
        objInputPage.clickSumButton();
        String resultNumber = objInputPage.getResultNumber();
        assertTrue(resultNumber.equals(expectedText));
    }

    @Test
    public void testTwoFields_withPositiveLargeNumbers() throws IOException {
        String numberFirst = "40000000000000000000000000000000000000000000000000000000000";
        String numberSecond = "30000000000000000000000000000000000000000000000000000000000";
        String expectedText = "7e+58";
        TwoFieldsOutput objInputPage = new TwoFieldsOutput(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSimpleMenu();
        objInputPage.enterNumbers(numberFirst, numberSecond);
        objInputPage.clickSumButton();
        String resultNumber = objInputPage.getResultNumber();
        assertTrue(resultNumber.equals(expectedText));
    }

    @Test
    public void testTwoFields_checkWithNegativeNumber() throws IOException {
        String numberFirst = "-4";
        String numberSecond = "3";
        String expectedText = "-1";
        TwoFieldsOutput objInputPage = new TwoFieldsOutput(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSimpleMenu();
        objInputPage.enterNumbers(numberFirst, numberSecond);
        objInputPage.clickSumButton();
        String resultNumber = objInputPage.getResultNumber();
        assertTrue(resultNumber.equals(expectedText));
    }

    @Test
    public void testTwoFields_checkWithInvalidNumber() throws IOException {
        String numberFirst = "a";
        String numberSecond = "3";
        String expectedText = "NaN";
        TwoFieldsOutput objInputPage = new TwoFieldsOutput(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSimpleMenu();
        objInputPage.enterNumbers(numberFirst, numberSecond);
        objInputPage.clickSumButton();
        String resultNumber = objInputPage.getResultNumber();
        assertTrue(resultNumber.equals(expectedText));
    }

    @Test
    public void testTwoFields_checkWithDecimalFractionNumber() throws IOException {
        String numberFirst = "3,14";
        String numberSecond = "5";
        String expectedText = "8";
        TwoFieldsOutput objInputPage = new TwoFieldsOutput(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSimpleMenu();
        objInputPage.enterNumbers(numberFirst, numberSecond);
        objInputPage.clickSumButton();
        String resultNumber = objInputPage.getResultNumber();
        assertTrue(resultNumber.equals(expectedText));
    }

}
