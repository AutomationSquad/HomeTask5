package lesson7;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileTest extends BaseTest {

    @Test
    public void test() {

        By userName = By.xpath("//*[@class='dropdown']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(
                userName));
        driver.findElement(userName).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Profile')]")).click();
        By changePassButton = By.cssSelector(".btn-warning");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(
                changePassButton));

        String nameString = "Tanya";
        String phoneString = "906532";

        By nameField = By.xpath(".//span[text()='Name']//following::input[1]");
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(nameString);

        By phoneField = By.xpath(".//span[text()='Name']//following::input[5]");
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phoneString);

    //    driver.findElement(By.xpath(".//button[text()=' Change password ']")).click();

        driver.findElement(By.xpath(".//button[contains(@class, 'btn-success')]")).click();

        By successAlert = By.cssSelector(".success-alert");

        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(successAlert));

        getElementWait()
                .until(ExpectedConditions.invisibilityOfElementLocated(successAlert));


        assertEquals(nameString, driver.findElement(nameField).getText(),
                "Name is not saved");

        assertEquals(phoneString, driver.findElement(phoneField).getText(),
                "Phone is not saved");

    }
}
