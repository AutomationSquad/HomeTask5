package lesson7.pages;

import lesson7.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Profile {

    private static final BaseTest base = new BaseTest();

    private static final By phoneField = By.xpath("//input[contains(@data-bind, 'phone')]");
    private static final By nameField = By.xpath("//input[contains(@data-bind, 'name')]");
    private static final By saveButton = By.cssSelector("button.btn-success");

    public static final void changeName(String name) {
        base.getElementWait(2000).until(ExpectedConditions.presenceOfElementLocated(nameField));
        base.driver.findElement(nameField).clear();
        base.driver.findElement(nameField).sendKeys(name);
    }

    public static final void changePhone(String phone) {
        base.getElementWait(2000).until(ExpectedConditions.presenceOfElementLocated(phoneField));
        base.driver.findElement(phoneField).clear();
        base.driver.findElement(phoneField).sendKeys(phone);
    }

    public static final void saveAll() {
        base.driver.findElement(saveButton).click();
        base.getElementWait(200)
                .withMessage("Success message is not displayed.")
                .until((ExpectedCondition<Boolean>) input ->
                        base.driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
    }

}
