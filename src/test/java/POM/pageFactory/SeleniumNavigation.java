package POM.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumNavigation {
    WebDriver driver;

    @FindBy(linkText = "Input Forms")
    WebElement inputMenu;

    @FindBy(linkText = "Simple Form Demo")
    WebElement simpleMenu;

    public SeleniumNavigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickInputForms() {
        inputMenu.click();
    }

    public void clickSimpleMenu() {
        simpleMenu.click();
    }

}
