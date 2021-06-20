package POM.test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import POM.pageFactory.SeleniumNavigation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestNavigation {
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
    public void test_navigation_is_works_well() throws IOException {
        SeleniumNavigation objInputPage = new SeleniumNavigation(driver);
        objInputPage.clickInputForms();
        objInputPage.clickSimpleMenu();
    }


}
