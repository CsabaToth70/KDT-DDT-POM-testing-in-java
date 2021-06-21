package POM.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkbox {

    WebDriver driver;

    @FindBy(linkText = "Input Forms")
    WebElement inputMenu;

    @FindBy(linkText = "Checkbox Demo")
    WebElement checkbox;

    @FindBy(id = "isAgeSelected")
    WebElement singleCheck;

    @FindBy(id = "txtAge")
    WebElement singleResult;

    @FindBy(css = ".checkbox:nth-child(3) .cb1-element")
    WebElement multipleCheck_1;

    @FindBy(css = ".checkbox:nth-child(4) .cb1-element")
    WebElement multipleCheck_2;

    @FindBy(css = ".checkbox:nth-child(5) .cb1-element")
    WebElement multipleCheck_3;

    @FindBy(css = ".checkbox:nth-child(6) .cb1-element")
    WebElement multipleCheck_4;

    @FindBy(id = "check1")
    WebElement multipleResult;

    public Checkbox(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickInputForms() {
        inputMenu.click();
    }

    public void clickCheckboxMenu() {
        checkbox.click();
    }

    public void clickSingleCheck() {
        singleCheck.click();
    }

    public void clickMultipleCheck_1() {
        multipleCheck_1.click();
    }

    public void clickMultipleCheck_2() {
        multipleCheck_2.click();
    }

    public void clickMultipleCheck_3() {
        multipleCheck_3.click();
    }

    public void clickMultipleCheck_4() {
        multipleCheck_4.click();
    }

    public String getSingleResult() {
        return singleResult.getText();
    }

    public String getMultipleResult() {
        return multipleResult.getAttribute("value");
    }


}
