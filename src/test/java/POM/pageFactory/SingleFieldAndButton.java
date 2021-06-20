package POM.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingleFieldAndButton {

    WebDriver driver;

    @FindBy(linkText = "Input Forms")
    WebElement inputMenu;

    @FindBy(linkText = "Simple Form Demo")
    WebElement simpleMenu;

    @FindBy(id = "user-message")
    WebElement singleInput;

    @FindBy(css = ".btn:nth-child(2)")
    WebElement messageButton;

    @FindBy(id = "display")
    WebElement messageResult;

    public SingleFieldAndButton(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickInputForms() {
        inputMenu.click();
    }

    public void clickSimpleMenu() {
        simpleMenu.click();
    }

    public void setInputText(String strSingleInput) {
        singleInput.sendKeys(strSingleInput);
    }

    public void clickMessageButton() {
        messageButton.click();
    }

    public String getDisplayedMessage(){
        return messageResult.getText();
    }

    public void typeSingleText(String strSingleInput) {
        this.setInputText(strSingleInput);
        this.clickMessageButton();
    }
}
