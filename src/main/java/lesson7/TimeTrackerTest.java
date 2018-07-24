package lesson7;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTrackerTest extends BaseTest {

    @Test
    public void test() {
        driver.get("https://qa.reports.spd-ukraine.com/");

        By name = By.name("username");

        driver.findElement(name).sendKeys("o.strohetska@spd-ukraine.com");
        driver.findElement(By.name("password")).sendKeys("hmyCFlqd");

        driver.findElement(By.cssSelector("[role='submit']")).click();

        By calendar = By.xpath("//*[contains(text(), 'Calendar')]");
        getElementWait().withMessage("Failed to find 'Calendar' element")
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(calendar));

        driver.findElement(calendar).click();
        By confirmModal = By.xpath("//button[@class='confirm btn btn-primary' and text()='Ok']");
        getElementWait().until(ExpectedConditions.elementToBeClickable(confirmModal));
        driver.findElement(confirmModal).click();
        getElementWait().until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(confirmModal)));
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
        getElementWait().until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-alert"))));
        assertEquals("Vacation", driver.findElement(By.xpath("//div[@class='text-center']/span")).getText());

        By removeButton = By.xpath("//button[@class='btn btn-default btn-link btn-sm']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(removeButton));
        getElementWait().until(ExpectedConditions.elementToBeClickable(removeButton));

    }

        @Test
        public void test1(){
            driver.get("https://qa.reports.spd-ukraine.com/");

            By name = By.name("username");

            driver.findElement(name).sendKeys("o.strohetska@spd-ukraine.com");
            driver.findElement(By.name("password")).sendKeys("hmyCFlqd");

            driver.findElement(By.cssSelector("[role='submit']")).click();
         By profile = By.xpath("//ul//li[@class='dropdown']/a");
         getElementWait().until(ExpectedConditions.elementToBeClickable(profile));
         driver.findElement(profile).click();
            driver.findElement(By.xpath("//*[@href='/profile']")).click();

         By username = By.xpath("//input[contains(@data-bind, 'value: name')]");
         getElementWait().until(ExpectedConditions.elementToBeClickable(username));
         driver.findElement(username).clear();
            driver.findElement(username).sendKeys("Oksana");

         By phone = By.xpath("//input[contains(@data-bind, 'value: phone')]");
            driver.findElement(phone).clear();
            driver.findElement(phone).sendKeys("123456789");

          By save = By.xpath("//button[@class='btn btn-success col-xs-3 pull-right']");
          driver.findElement(save).click();
            getElementWait()
                    .withMessage("'Success' message is not dispayed.")
                    .until((ExpectedCondition<Boolean>) input ->
                            driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
            assertEquals("Oksana", driver.findElement(username).getText());
            assertEquals("123456789", driver.findElement(phone).getText());

        }
        @Test
        public void test2() {
            driver.get("https://qa.reports.spd-ukraine.com/");

            By name = By.name("username");

            driver.findElement(name).sendKeys("o.strohetska@spd-ukraine.com");
            driver.findElement(By.name("password")).sendKeys("hmyCFlqd");

            driver.findElement(By.cssSelector("[role='submit']")).click();

            By weeklyView = By.xpath("//a[@href='#weekly']");
            getElementWait().until(ExpectedConditions.elementToBeClickable(weeklyView));
            driver.findElement(weeklyView).click();

            By addTask = By.xpath("//*[@class='task-control-group']/button");
            getElementWait().ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(addTask));
            driver.findElement(addTask).click();

            By description = By.xpath("//input[@title='Task description']");
            getElementWait().until(ExpectedConditions.elementToBeClickable(description));
            driver.findElement(description).clear();
            driver.findElement(description).sendKeys("Issue clarification");
            By time = By.xpath("//input[@placeholder='Time, h']");
            driver.findElement(time).sendKeys("2");
            By saveButton = By.xpath("//button[@text()='Save all tasks']");
            driver.findElement(saveButton).click();
            assertEquals("Issue clarification", driver.findElement(description).getText());

        }



}
