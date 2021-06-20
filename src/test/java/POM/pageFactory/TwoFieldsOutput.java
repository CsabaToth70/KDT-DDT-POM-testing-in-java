package POM.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TwoFieldsOutput {

    WebDriver driver;

    @FindBy(linkText = "Input Forms")
    WebElement inputMenu;

    @FindBy(linkText = "Simple Form Demo")
    WebElement simpleMenu;

    @FindBy(id="sum1")
    WebElement firstNumber;

    @FindBy(id="sum2")
    WebElement secondNumber;

    @FindBy(css=".btn:nth-child(3)")
    WebElement sumButton;

    @FindBy(id="displayvalue")
    WebElement result;

    public TwoFieldsOutput(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickInputForms() {
        inputMenu.click();
    }

    public void clickSimpleMenu() {
        simpleMenu.click();
    }

    public void enterNumbers(String numberFirst, String numberSecond){
        firstNumber.sendKeys(numberFirst);
        secondNumber.sendKeys(numberSecond);
    }

    public void clickSumButton(){
        sumButton.click();
    }

    public String getResultNumber(){
        return result.getText();
    }

}
