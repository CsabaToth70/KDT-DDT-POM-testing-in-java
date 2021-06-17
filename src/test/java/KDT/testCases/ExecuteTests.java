package KDT.testCases;

import KDT.operation.ReadObject;
import KDT.operation.UIOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ExecuteTests {
    WebDriver webdriver = new ChromeDriver();
    ReadObject object = new ReadObject();
    Properties allObjects = object.getObjectRepository();
    UIOperation operation = new UIOperation(webdriver);

    JavascriptExecutor js = (JavascriptExecutor) webdriver;
    Map<String, Object> vars = new HashMap<String, Object>();

    public ExecuteTests() throws IOException {
    }

    @BeforeEach
    public void setup() throws Exception {
        webdriver.manage().window().maximize();
        operation.perform(allObjects, "goToUrl", null, null, "baseUrl");
        try {
            operation.perform(allObjects, "wait", "startingPopUp", "id", null);
            operation.perform(allObjects, "cancel", "startingPopUp", "id", null);
        } catch (Exception e) {
            System.out.println("Deviation during test process preparation. Starting Pop up window was not displayed, " +
                    "and the testing setup could not cancel it.");
        }

    }

    @AfterEach
    public void closingTestingProcess() {
        webdriver.close();
    }

    @Test
    public void navigation() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
    }

    @Test
    public void singleFieldAndButton() throws Exception {
        String expectedText = "Message in a bottle";
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
        operation.perform(allObjects, "click", "singleInput", "id", null);
        operation.perform(allObjects, "setText", "singleInput", "id", expectedText);
        operation.perform(allObjects, "click", "messageButton", "css", null);
        operation.perform(allObjects, "assertText", "messageResult", "id", expectedText);
    }

    @Test
    void twoFieldsOutputTest_checkWithPositiveSmallNumbers() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
        operation.perform(allObjects, "settext", "firstNumber", "id", "4");
        operation.perform(allObjects, "settext", "secondNumber", "id", "3");
        operation.perform(allObjects, "click", "sumButton", "css", null);
        operation.perform(allObjects, "wait", "result", "id", null);
        operation.perform(allObjects, "assertText", "result", "id", "7");
    }

    @Test
    void twoFieldsOutputTest_checkWithPositiveLargeNumbers() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
        operation.perform(allObjects, "settext", "firstNumber", "id", "40000000000000000000000000000000000000000000000000000000000");
        operation.perform(allObjects, "settext", "secondNumber", "id", "30000000000000000000000000000000000000000000000000000000000");
        operation.perform(allObjects, "click", "sumButton", "css", null);
        operation.perform(allObjects, "wait", "result", "id", null);
        operation.perform(allObjects, "assertText", "result", "id", "7e+58");
    }

    @Test
    void twoFieldsOutputTest_checkWithNegativeNumber() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
        operation.perform(allObjects, "settext", "firstNumber", "id", "-4");
        operation.perform(allObjects, "settext", "secondNumber", "id", "3");
        operation.perform(allObjects, "click", "sumButton", "css", null);
        operation.perform(allObjects, "wait", "result", "id", null);
        operation.perform(allObjects, "assertText", "result", "id", "-1");
    }

    @Test
    void twoFieldsOutputTest_checkWithInvalidInput() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
        operation.perform(allObjects, "settext", "firstNumber", "id", "1");
        operation.perform(allObjects, "settext", "secondNumber", "id", "a");
        operation.perform(allObjects, "click", "sumButton", "css", null);
        operation.perform(allObjects, "wait", "result", "id", null);
        operation.perform(allObjects, "assertText", "result", "id", "NaN");
    }

    @Test
    void singleCheckboxTest() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "checkbox", "link", null);
        operation.perform(allObjects, "click", "singleCheck", "id", null);
        operation.perform(allObjects, "assertText", "singleResult", "id",
                "Success - Check box is checked");
    }

    @Test
    void multipleCheckboxTest_withFilledInThreeBoxes() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "checkbox", "link", null);
        operation.perform(allObjects, "click", "multipleCheck1", "css", null);
        operation.perform(allObjects, "click", "multipleCheck2", "css", null);
        operation.perform(allObjects, "click", "multipleCheck3", "css", null);
        operation.perform(allObjects, "assertValue", "multipleResult", "id", "Check All");
    }

    @Test
    void multipleCheckboxTest_withFilledInAllBoxes() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "checkbox", "link", null);
        operation.perform(allObjects, "click", "multipleCheck1", "css", null);
        operation.perform(allObjects, "click", "multipleCheck2", "css", null);
        operation.perform(allObjects, "click", "multipleCheck3", "css", null);
        operation.perform(allObjects, "click", "multipleCheck4", "css", null);
        operation.perform(allObjects, "assertValue", "multipleResult", "id", "Uncheck All");
    }

    @Test
    void multipleCheckboxTest_togetherWithFilledInSimpleCheckbox_withFilledInThreeBoxes() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "checkbox", "link", null);
        operation.perform(allObjects, "click", "singleCheck", "id", null);
        operation.perform(allObjects, "click", "multipleCheck1", "css", null);
        operation.perform(allObjects, "click", "multipleCheck2", "css", null);
        operation.perform(allObjects, "click", "multipleCheck3", "css", null);
        operation.perform(allObjects, "assertValue", "multipleResult", "id", "Check All");
    }

    @Test
    void multipleCheckboxTest_togetherWithFilledInSimpleCheckbox_withFilledInAllBoxes() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "checkbox", "link", null);
        operation.perform(allObjects, "click", "singleCheck", "id", null);
        operation.perform(allObjects, "click", "multipleCheck1", "css", null);
        operation.perform(allObjects, "click", "multipleCheck2", "css", null);
        operation.perform(allObjects, "click", "multipleCheck3", "css", null);
        operation.perform(allObjects, "click", "multipleCheck4", "css", null);
        operation.perform(allObjects, "assertValue", "multipleResult", "id", "Uncheck All");
    }

    @Test
    public void selectList() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "dropdown", "link", null);
        operation.perform(allObjects, "click", "select", "id", null);
        operation.perform(allObjects, "click", "wednesday", "xpath", null);
        operation.perform(allObjects, "assertText", "dayResult", "css", "Day selected :- Wednesday");
    }

    @Test
    public void radioButtons_maleAnd05() throws Exception {
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "radio", "link", null);
        operation.perform(allObjects, "click", "male", "xpath", null);
        operation.perform(allObjects, "click", "0-5", "xpath", null);
        operation.perform(allObjects, "click", "radiobutton", "css", null);
        operation.perform(allObjects, "assertText", "radioResult", "css", "Sex : Male\nAge group: 0 - 5");
    }


}
