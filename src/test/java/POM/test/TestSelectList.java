package POM.test;

import POM.pageFactory.Checkbox;
import POM.pageFactory.SelectList;
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

public class TestSelectList {
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
    public void selectList_setMonday(){
        String expected = "Day selected :- Monday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnMonday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

    @Test
    public void selectList_setTuesday(){
        String expected = "Day selected :- Tuesday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnTuesday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

    @Test
    public void selectList_setWednesday(){
        String expected = "Day selected :- Wednesday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnWednesday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

    @Test
    public void selectList_setThursday(){
        String expected = "Day selected :- Thursday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnThursday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

    @Test
    public void selectList_setFriday(){
        String expected = "Day selected :- Friday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnFriday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

    @Test
    public void selectList_setSaturday(){
        String expected = "Day selected :- Saturday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnSaturday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

    @Test
    public void selectList_setSunday(){
        String expected = "Day selected :- Sunday";
        SelectList objInputPage = new SelectList(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSelectListMenu();
        objInputPage.clickSelectArrow();
        objInputPage.clickOnSunday();
        String dayResult = objInputPage.getDayResult();
        assertTrue(dayResult.equals(expected));
    }

}
