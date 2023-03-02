
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ResolverTest_HomePage {
    WebDriver driver;
    ArrayList<String> arrayOfItemNames = new ArrayList<String>();

    public ResolverTest_HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id=\"inputEmail\"]")
    WebElement inputEmailTextBox_LoginTest1;

    @FindBy(css = "#inputPassword")
    WebElement inputPasswordTextBox_LoginTest1;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement signInButton_LoginTest1;

    @FindBy(xpath = "//*[contains(text(),\"List Item\")]")
    List<WebElement> listOfItems_ListItemTest2;

    @FindBy(xpath = "//button[@id=\"dropdownMenuButton\"]")
    WebElement defaultOption_OptionTest3;

    @FindBy(xpath = "//a[@class=\"dropdown-item\"]")
    List<WebElement> listOfOptions_OptionTest3;

    @FindBy(css = "#test-4-div .btn-primary")
    WebElement firstButton_ButtonTest4;

    @FindBy(css = "#test-4-div .btn-secondary")
    WebElement secondButton_ButtonTest4;

    @FindBy(xpath = "//button[@id=\"test5-button\"]")
    WebElement button_ButtonTest5;

    @FindBy(css = ".alert-success")
    WebElement successMessage_ButtonTest5;

    @FindBy(xpath = "//table[@class=\"table table-bordered table-dark\"]//tbody//tr")
    List<WebElement> tableRowSize_TableTest6;

    @FindBy(xpath = "//table[@class=\"table table-bordered table-dark\"]//tbody//td")
    List<WebElement> tableColumnSize_TableTest6;

    public boolean test1() throws InterruptedException {
        boolean flag = false;
        if (inputEmailTextBox_LoginTest1.isDisplayed()) {
            inputEmailTextBox_LoginTest1.sendKeys("any@gmail.com");
            if (inputPasswordTextBox_LoginTest1.isDisplayed()) {
                inputPasswordTextBox_LoginTest1.sendKeys("NewPassword");
                if (signInButton_LoginTest1.isDisplayed()) {
                    signInButton_LoginTest1.click();
                    System.out.println("In Test1, email address, password input field and login button are present and you have now entered a new set of email and password ");
                    flag = true;
                }
            }

        }
        return flag;
    }

    public int test2(int listId, String expectedItemValue) throws InterruptedException {
        List<String> itemValueText = new ArrayList<String>();
        List<String> badgeValueText = new ArrayList<String>();
        int badgeValue1 = 0;
        for (int i = 0; i < listOfItems_ListItemTest2.size(); i++) {
            arrayOfItemNames.add(listOfItems_ListItemTest2.get(i).getText());
        }

        for (int j = 0; j < arrayOfItemNames.size(); j++) {
            String item = arrayOfItemNames.get(j).trim();
            itemValueText.add(item.substring(0, item.length() - 2));
            badgeValueText.add(item.substring(item.length() - 1).trim());
        }

        if (arrayOfItemNames.size() >= (listId - 1)) {
            String itemValue = itemValueText.get(listId - 1);
            if (itemValue.equalsIgnoreCase(expectedItemValue)) {
                System.out.println("In Test2, it is validated that the "+ listId+"nd items value is set to: "+ itemValue);
                String badgeValue = badgeValueText.get(listId - 1);
                badgeValue1 = Integer.valueOf(badgeValue);
            }
        }
        return badgeValue1;
    }

    public String test3(int option) throws InterruptedException {
        Actions a = new Actions(driver);
        a.scrollToElement(defaultOption_OptionTest3);
        a.moveToElement(defaultOption_OptionTest3);
        String defaultOption = defaultOption_OptionTest3.getText();
        defaultOption_OptionTest3.click();
        listOfOptions_OptionTest3.get(option - 1).click();
        return defaultOption;
    }

    public boolean test4() throws InterruptedException {
        boolean flag = false;
        if (firstButton_ButtonTest4.isEnabled() && !secondButton_ButtonTest4.isEnabled()) {
            flag = true;
        }
        return flag;
    }

    public String test5() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(button_ButtonTest5));
        button_ButtonTest5.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage_ButtonTest5));
        return successMessage_ButtonTest5.getText();
    }

    public String test6(int row, int column) throws InterruptedException {
        String value = null;
        if (row <= tableRowSize_TableTest6.size() && column <= tableColumnSize_TableTest6.size()) {
            String s = "//table[@class=\"table table-bordered table-dark\"]//tbody//tr[" + (row+1) + "]//td[" + (column+1) + "]";
            value= driver.findElement(By.xpath(s)).getText();
        }return  value;
    }

}
