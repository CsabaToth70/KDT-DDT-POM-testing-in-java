package KDT.operation;

import java.time.Duration;
import java.util.Properties;

import junit.framework.AssertionFailedError;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UIOperation {
    WebDriver driver;

    public UIOperation(WebDriver driver) {
        this.driver = driver;
    }

    public void perform(Properties p, String operation, String objectName, String objectType, String value) throws Exception {
        System.out.println("");
        switch (operation.toUpperCase()) {
            case "CLICK":
                //Perform click
                driver.findElement(this.getObject(p, objectName, objectType)).click();
                break;
            case "SETTEXT":
                //Set text on control
                driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
                break;
            case "GOTOURL":
                //Get url of application
                driver.get(p.getProperty(value));
                break;
            case "GETTEXT":
                //Get text of an element
                driver.findElement(this.getObject(p, objectName, objectType)).getText();
                break;
            case "WAIT":
                //Wait for visibility of the element
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5, 1));
                wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p, objectName, objectType)));
                break;
            case "CANCEL":
                //Cancel an element e.g. a popped up window
                WebElement adCloseButton = driver.findElement(this.getObject(p, objectName, objectType));
                adCloseButton.click();
                break;
            case "ASSERTTEXT":
                //Get text of an element
                assertEquals(value, driver.findElement(this.getObject(p, objectName, objectType)).getText());
                break;
            case "ASSERTVALUE":
                //Get text of an element
                assertEquals(value, driver.findElement(this.getObject(p, objectName, objectType)).getAttribute("value"));
                break;
            default:
                break;
        }
    }

    /**
     * Find element BY using object type and value
     *
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(Properties p, String objectName, String objectType) throws Exception {
        //Find by xpath
        if (objectType.equalsIgnoreCase("XPATH")) {

            return By.xpath(p.getProperty(objectName));
        }
        //find by class
        else if (objectType.equalsIgnoreCase("CLASSNAME")) {

            return By.className(p.getProperty(objectName));

        }
        //find by name
        else if (objectType.equalsIgnoreCase("NAME")) {

            return By.name(p.getProperty(objectName));

        }
        //Find by css
        else if (objectType.equalsIgnoreCase("CSS")) {

            return By.cssSelector(p.getProperty(objectName));

        }
        //find by link
        else if (objectType.equalsIgnoreCase("LINK")) {

            return By.linkText(p.getProperty(objectName));

        }
        //find by partial link
        else if (objectType.equalsIgnoreCase("PARTIALLINK")) {

            return By.partialLinkText(p.getProperty(objectName));

        }//find by id
        else if (objectType.equalsIgnoreCase("ID")) {

            return By.id(p.getProperty(objectName));

        } else {
            throw new Exception("Wrong object type");
        }
    }
}
