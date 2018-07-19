package lesson7;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TimeTrackerTest extends BaseTest {

    @Test
    public void test(){
        driver.get("https://qa.reports.spd-ukraine.com/");

        By name = By.name("username");

        driver.findElement(name).sendKeys("e.voloshyn@spd-ukraine.com");
        driver.findElement(By.name("password")).sendKeys("JMSgzZVz");

        driver.findElement(By.cssSelector("[role='submit']")).click();

        By calendar = By.xpath("//*[contains(text(), 'Calendar')]");
        getElementWait().withMessage("Failed to find 'Calendar' element")
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(calendar));

        driver.findElement(calendar).click();

        By requestDayStatus = By.xpath("//*[contains(text(), 'Request day status')]");
        getElementWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                requestDayStatus));

        driver.findElement(requestDayStatus).click();

        By dateFrom = By.cssSelector("[placeholder='Date from']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(
                dateFrom));
        driver.findElement(dateFrom).sendKeys("2018-08-10");

        By dateTo = By.cssSelector("[placeholder='Date to']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(
                dateTo));
        driver.findElement(dateTo).sendKeys("2018-08-18");

        driver.findElement(By.id("s2id_autogen3")).click();

        By dayStatusSelect = By.id("select2-drop");
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(dayStatusSelect));
        driver
                .findElement(dayStatusSelect)
                .findElement(By.xpath("//*[@class='select2-result-label' and text()='Vacation']"))
                .click();

        driver.findElement(By.cssSelector(".btn-success")).click();

        getElementWait()
                .withMessage("'Success' message is not dispayed.")
                .until((ExpectedCondition<Boolean>) input ->
                driver.findElement(By.cssSelector(".success-alert")).isDisplayed());


//        Actions builder = new Actions(driver);
//        builder.moveToElement(By.id("someIm")).clickAndHold().build().perform();
    }
}
