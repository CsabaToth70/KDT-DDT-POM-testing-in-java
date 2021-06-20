package DDT.testCases;

import KDT.operation.ReadObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecuteDdtTests {
    ReadObject object = new ReadObject();
    Properties allObjects = object.getObjectRepository();
    WebDriver driver = new ChromeDriver();

    public ExecuteDdtTests() throws IOException {
    }

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
        driver.get(allObjects.getProperty("baseUrl"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5, 1));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(allObjects.getProperty("startingPopUp"))));
            WebElement adCloseButton = driver.findElement(By.id(allObjects.getProperty("startingPopUp")));
            adCloseButton.click();
        } catch (Exception e) {
            System.out.println(e + "\nDeviation during test process preparation. Starting Pop up window was not displayed, " +
                    "and the testing setup could not cancel it.");
        }
    }

    @AfterEach
    void closingTestingProcess() {
        driver.close();
    }

    @Test
    public void navigation() {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("simpleMenu"))).click();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataSingleField.csv", numLinesToSkip = 1)
    public void singleFieldAndButton(String textInput, String expected) {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("simpleMenu"))).click();
        driver.findElement(By.id(allObjects.getProperty("singleInput"))).click();
        driver.findElement(By.id(allObjects.getProperty("singleInput"))).sendKeys(textInput);
        driver.findElement(By.cssSelector(allObjects.getProperty("messageButton"))).click();
        assertEquals(expected, driver.findElement(By.id(allObjects.getProperty("messageResult"))).getText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataTwoFields.csv", numLinesToSkip = 1)
    public void twoFieldsOutputTest(String numA, String numB, String expected) {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("simpleMenu"))).click();
        driver.findElement(By.id(allObjects.getProperty("firstNumber"))).click();
        driver.findElement(By.id(allObjects.getProperty("firstNumber"))).sendKeys(numA);
        driver.findElement(By.id(allObjects.getProperty("secondNumber"))).click();
        driver.findElement(By.id(allObjects.getProperty("secondNumber"))).sendKeys(numB);
        driver.findElement(By.cssSelector(allObjects.getProperty("sumButton"))).click();
        assertEquals(expected, driver.findElement(By.id(allObjects.getProperty("result"))).getText());
    }

    @ParameterizedTest
    @CsvSource({"Success - Check box is checked"})
    void singleCheckboxTest(String expected) {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("checkbox"))).click();
        driver.findElement(By.id(allObjects.getProperty("singleCheck"))).click();
        assertEquals(expected, driver.findElement(By.id(allObjects.getProperty("singleResult"))).getText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataCheckbox.csv", numLinesToSkip = 1)
    void multipleCheckboxTest(String single, String check_1, String check_2, String check_3, String check_4, String expected) {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("checkbox"))).click();

        if (single != null && !single.equals("0")) {
            driver.findElement(By.id(allObjects.getProperty("singleCheck"))).click();
        }
        if (check_1 != null && !check_1.equals("0")) {
            driver.findElement(By.cssSelector(allObjects.getProperty("multipleCheck1"))).click();
        }
        if (check_2 != null && !check_2.equals("0")) {
            driver.findElement(By.cssSelector(allObjects.getProperty("multipleCheck2"))).click();
        }
        if (check_3 != null && !check_3.equals("0")) {
            driver.findElement(By.cssSelector(allObjects.getProperty("multipleCheck3"))).click();
        }
        if (check_4 != null && !check_4.equals("0")) {
            driver.findElement(By.cssSelector(allObjects.getProperty("multipleCheck4"))).click();
        }
        assertEquals(expected, driver.findElement(By.id(allObjects.getProperty("multipleResult"))).getAttribute("value"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataSelectList.csv", numLinesToSkip = 1)
    public void selectList(String day, String expected) {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("dropdown"))).click();
        driver.findElement(By.id(allObjects.getProperty("select"))).click();
        driver.findElement(By.xpath(allObjects.getProperty(day))).click();
        assertEquals(expected, driver.findElement(By.cssSelector(allObjects.getProperty("dayResult"))).getText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataRadioButtons.csv", numLinesToSkip = 1)
    public void radioButtons(String gender, String age, String expected) {
        driver.findElement(By.linkText(allObjects.getProperty("inputMenu"))).click();
        driver.findElement(By.linkText(allObjects.getProperty("radio"))).click();
        driver.findElement(By.xpath(allObjects.getProperty(gender.toLowerCase()))).click();
        if (age.equals("0 - 5")) {
            driver.findElement(By.xpath(allObjects.getProperty("0-5"))).click();
        } else if (age.equals("5 - 15")) {
            driver.findElement(By.xpath(allObjects.getProperty("5-15"))).click();
        } else {
            driver.findElement(By.xpath(allObjects.getProperty("15-"))).click();
        }
        driver.findElement(By.cssSelector(allObjects.getProperty("radiobutton"))).click();

        String[] parts = ((String) expected).split("/");
        String expGender = String.valueOf(parts[0]);
        String expAge = String.valueOf(parts[1]);
        assertEquals("Sex : " + expGender + "\nAge group: " + expAge, driver.findElement(By.cssSelector(allObjects.getProperty("radioResult"))).getText());
    }

}
