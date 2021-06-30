package POM.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectList {
    WebDriver driver;

    @FindBy(linkText = "Input Forms")
    WebElement inputMenu;

    @FindBy(linkText = "Select Dropdown List")
    WebElement dropdownList;

    @FindBy(id = "select-demo")
    WebElement select;

    @FindBy(xpath = "//*[text()='Monday']")
    WebElement monday;

    @FindBy(xpath = "//*[text()='Tuesday']")
    WebElement tuesday;

    @FindBy(xpath = "//*[text()='Wednesday']")
    WebElement wednesday;

    @FindBy(xpath = "//*[text()='Thursday']")
    WebElement thursday;

    @FindBy(xpath = "//*[text()='Friday']")
    WebElement friday;

    @FindBy(xpath = "//*[text()='Saturday']")
    WebElement saturday;

    @FindBy(xpath = "//*[text()='Sunday']")
    WebElement sunday;

    @FindBy(css = ".selected-value")
    WebElement dayResult;

    public SelectList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickInputForms() {
        inputMenu.click();
    }

    public void clickSelectListMenu() {
        dropdownList.click();
    }

    public void clickSelectArrow() {
        select.click();
    }

    public void clickOnMonday() {
        monday.click();
    }

    public void clickOnTuesday() {
        tuesday.click();
    }

    public void clickOnWednesday() {
        wednesday.click();
    }

    public void clickOnThursday() {
        thursday.click();
    }

    public void clickOnFriday() {
        friday.click();
    }

    public void clickOnSaturday() {
        saturday.click();
    }

    public void clickOnSunday() {
        sunday.click();
    }

    public String getDayResult() {
        return dayResult.getText();
    }

}
