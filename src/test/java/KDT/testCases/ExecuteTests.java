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
        operation.perform(allObjects, "wait", "startingPopUp", "id", null );
        operation.perform(allObjects, "cancel", "startingPopUp", "id", null );
    }

    @AfterEach
    public void closingTestingProcess(){
        webdriver.close();
    }

    @Test
    public void testNavigation() throws Exception{
        operation.perform(allObjects, "click", "inputMenu", "link", null);
        operation.perform(allObjects, "click", "simpleMenu", "link", null);
    }

}
