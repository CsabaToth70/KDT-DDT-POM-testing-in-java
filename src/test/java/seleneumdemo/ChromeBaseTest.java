package seleneumdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromeBaseTest {

    WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Map<String, Object> vars = new HashMap<String, Object>();

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
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
    @Order(1)
    void navigationTest_ClickOnSimpleFormDemo(){
//        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Input Forms")).click();
        driver.findElement(By.linkText("Simple Form Demo")).click();
    }

    @Test
    @Order(2)
    void singleFieldButton() {
        driver.findElement(By.cssSelector(".tree-branch:nth-child(1) > .glyphicon-chevron-right")).click();
        driver.findElement(By.linkText("Simple Form Demo")).click();
        driver.findElement(By.id("user-message")).click();
        driver.findElement(By.id("user-message")).sendKeys("Test message");
        driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    }

    @Test
    @Order(3)
    void twoFieldsOutputTest_checkWithPositiveSmallNumbers() {
        driver.findElement(By.linkText("Input Forms")).click();
        driver.findElement(By.linkText("Simple Form Demo")).click();
        driver.findElement(By.id("sum1")).sendKeys("4");
        driver.findElement(By.id("sum2")).sendKeys("3");
        driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("displayvalue")));
        assertEquals("7", driver.findElement(By.id("displayvalue")).getText());
    }

    @Test
    @Order(4)
    void twoFieldsOutputTest_checkWithPositiveLargeNumbers() {
        driver.findElement(By.linkText("Input Forms")).click();
        driver.findElement(By.linkText("Simple Form Demo")).click();
        driver.findElement(By.id("sum1")).sendKeys("40000000000000000000000000000000000000000000000000000000000");
        driver.findElement(By.id("sum2")).sendKeys("30000000000000000000000000000000000000000000000000000000000");
        driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("displayvalue")));
        assertEquals("7e+58", driver.findElement(By.id("displayvalue")).getText());
    }

    @Test
    @Order(5)
    void singleCheckboxTest() {
        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        assertEquals("Success - Check box is checked", driver.findElement(By.id("txtAge")).getText());
    }

    @Test
    @Order(6)
    void multipleCheckboxTest_withFilledInThreeBoxes() {
        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(3) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(4) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(5) .cb1-element")).click();
        String value = driver.findElement(By.id("check1")).getAttribute("value");
        assertEquals("Check All", value);
    }

    @Test
    @Order(7)
    void multipleCheckboxTest_withFilledInAllBoxes() {
        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(3) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(4) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(5) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(6) .cb1-element")).click();
        String value = driver.findElement(By.id("check1")).getAttribute("value");
        assertEquals("Uncheck All", value);
    }

    @Test
    @Order(8)
    void multipleCheckboxTest_togetherWithFilledInSimpleCheckbox_withFilledInThreeBoxes() {
        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(3) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(4) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(5) .cb1-element")).click();
        String value = driver.findElement(By.id("check1")).getAttribute("value");
        assertEquals("Check All", value);
    }

    @Test
    @Order(9)
    void multipleCheckboxTest_togetherWithFilledInSimpleCheckbox_withFilledInAllBoxes() {
        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(3) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(4) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(5) .cb1-element")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(6) .cb1-element")).click();
        String value = driver.findElement(By.id("check1")).getAttribute("value");
        assertEquals("Uncheck All", value);
    }
    @Test
    @Order(10)
    public void selectList() {
        driver.findElement(By.cssSelector(".tree-branch:nth-child(1) > .glyphicon-chevron-right")).click();
        driver.findElement(By.linkText("Select Dropdown List")).click();

        WebElement element = driver.findElement(By.id("select-demo"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).clickAndHold().perform();

        element = driver.findElement(By.id("select-demo"));
        builder = new Actions(driver);
        builder.moveToElement(element).perform();

        element = driver.findElement(By.id("select-demo"));
        builder = new Actions(driver);
        builder.moveToElement(element).release().perform();

        driver.findElement(By.id("select-demo")).click();

        WebElement dropdown = driver.findElement(By.id("select-demo"));
        dropdown.findElement(By.xpath("//option[. = 'Tuesday']")).click();

        driver.findElement(By.cssSelector("#select-demo > option:nth-child(4)")).click();
        assertEquals("Day selected :- Tuesday", driver.findElement(By.cssSelector(".selected-value")).getText());
        driver.findElement(By.id("select-demo")).click();

        dropdown = driver.findElement(By.id("select-demo"));
        dropdown.findElement(By.cssSelector("*[value='Wednesday']")).click();

        driver.findElement(By.cssSelector("#select-demo > option:nth-child(5)")).click();
        assertEquals("Day selected :- Wednesday", driver.findElement(By.cssSelector(".selected-value")).getText());
        driver.findElement(By.id("select-demo")).click();
        driver.findElement(By.xpath("//option[text()=\'Wednesday\']")).click();
        assertEquals("Day selected :- Wednesday", driver.findElement(By.cssSelector(".selected-value")).getText());
        driver.findElement(By.id("select-demo")).click();
        driver.findElement(By.xpath("//*[contains(text(),\'Wednes\')]")).click();
        assertEquals("Day selected :- Wednesday", driver.findElement(By.cssSelector(".selected-value")).getText());
        driver.findElement(By.id("select-demo")).click();
        driver.findElement(By.xpath("//*[text()=\'Wednesday\']")).click();
        assertEquals("Day selected :- Wednesday", driver.findElement(By.cssSelector(".selected-value")).getText());
    }

    @Test
    @Order(11)
    public void radioButtons() {
        driver.get("https://www.seleniumeasy.com/test/");
        driver.findElement(By.cssSelector("ul:nth-child(3) > .tree-branch:nth-child(1) > a")).click();
        driver.findElement(By.linkText("Radio Buttons Demo")).click();
        vars.put("counter", js.executeScript("return 1"));
        String varGender;
        for(int j=1; j <=2; j++){
            if(j == 1){
                varGender = "Male";
            } else {
                varGender = "Female";
            }
            driver.findElement(By.xpath("(//input[@name=\'gender\'])[" + j + "]")).click();

            driver.findElement(By.name("ageGroup")).click();
            driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
            assertEquals("Sex : " + varGender + "\nAge group: 0 - 5", driver.findElement(By.cssSelector(".groupradiobutton")).getText());

            driver.findElement(By.cssSelector("div:nth-child(3) > .radio-inline:nth-child(3) > input")).click();
            driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
            assertEquals("Sex : " + varGender + "\nAge group: 5 - 15", driver.findElement(By.cssSelector(".groupradiobutton")).getText());

            driver.findElement(By.cssSelector(".radio-inline:nth-child(4) > input")).click();
            driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
            assertEquals("Sex : " + varGender + "\nAge group: 15 - 50", driver.findElement(By.cssSelector(".groupradiobutton")).getText());
        }

    }
}
