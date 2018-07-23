package lesson7;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TimeTrackerTest extends BaseTest {

    @Test
    public void testFromLecture() {
        String dateFromValue = "2018-07-25";
        String dateToValue = "2018-07-31";

        BaseTest.logIn();

        By calendar = By.xpath("//*[contains(text(), 'Calendar')]");
        getElementWait().withMessage("Failed to find 'Calendar' element").ignoring(NoSuchElementException.class, StaleElementReferenceException.class).until(ExpectedConditions.presenceOfAllElementsLocatedBy(calendar));

        driver.findElement(calendar).click();

        By errorMessage = By.xpath("//button[contains(text(),'Ok')]");
        getElementWait().ignoring(NoSuchElementException.class, StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMessage));
        driver.findElement(errorMessage).click();

        getElementWait().until(ExpectedConditions.invisibilityOfElementLocated(errorMessage));

        By requestDayStatus = By.xpath("//*[contains(text(), 'Request day status')]");
        getElementWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(requestDayStatus));

        driver.findElement(requestDayStatus).click();

        By dateFrom = By.cssSelector("[placeholder='Date from']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(dateFrom));

        driver.findElement(dateFrom).sendKeys(dateFromValue);

        By dateTo = By.cssSelector("[placeholder='Date to']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(dateTo));
        driver.findElement(dateTo).clear();
        driver.findElement(dateTo).sendKeys(dateToValue);
        driver.findElement(By.id("s2id_autogen3")).click();

        By dayStatusSelect = By.id("select2-drop");
        getElementWait().ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(dayStatusSelect));
        driver.findElement(dayStatusSelect).findElement(By.xpath("//*[@class='select2-result-label' and text()='Vacation']")).click();

        driver.findElement(By.cssSelector(".btn-success")).click();

        getElementWait().withMessage("'Success' message is not dispayed.").until((ExpectedCondition <Boolean>) input -> driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
        Assert.assertEquals("Request day status isn't saved.", dateFromValue + " - " + dateToValue, driver.findElement(By.xpath("//*[contains(@data-bind, 'text: __dateRange')]")).getText());

        By removeButton = By.xpath("//*[@title='Remove']");
        driver.findElement(removeButton).click();

        getElementWait().withMessage("'Success' message is not dispayed.").until((ExpectedCondition <Boolean>) input -> driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
        Assert.assertEquals("Request day status isn't deleted.", dateFromValue + " - " + dateToValue, driver.findElement(By.xpath("//*[contains(@data-bind, 'text: __dateRange')]")).getText());
    }


    @Test
    public void profileDetailsUpdatingTest() {
        By userMenuDropDown = By.xpath("//*[@class='glyphicon glyphicon-user']");
        By nameField = By.xpath("//input[contains(@data-bind, 'value: name')]");
        By phoneField = By.xpath("//input[contains(@data-bind, 'value: phone')]");
        String updatedName = "Eugenn";
        String updatedPhone = "0999123123";

        BaseTest.logIn();
        getElementWait().until(ExpectedConditions.elementToBeClickable(userMenuDropDown));

        driver.findElement(userMenuDropDown).click();
        driver.findElement(By.xpath("//*[@href='/profile']")).click();

        getElementWait().until(ExpectedConditions.elementToBeClickable(userMenuDropDown));

        getElementWait().until(ExpectedConditions.elementToBeClickable(nameField));
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(updatedName);

        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(updatedPhone);

        driver.findElement(By.xpath("//button[contains(@data-bind, 'click: $root.saveUser')]")).click();
        getElementWait().withMessage("'Success' message is not dispayed.").until((ExpectedCondition <Boolean>) input -> driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
        driver.navigate().refresh();
        getElementWait().until(ExpectedConditions.elementToBeClickable(nameField));
        getElementWait().until(ExpectedConditions.elementToBeClickable(phoneField));
        Assert.assertEquals("Name hasn't been updated", updatedName, driver.findElement(nameField).getAttribute("value"));
        Assert.assertEquals("Phone hasn't been updated", updatedPhone, driver.findElement(phoneField).getAttribute("value"));
    }

    @Test
    public void addNewTask() {
        By taskNameField = By.xpath("//input[contains(@data-bind, '__nameRealTime')]");
        By spentTimeField = By.xpath("//input[contains(@data-bind, '__spentTimeRealTime')]");
        By trackTimeButton = By.xpath("//a[.='Track Time']");
        By saveAllTasks = By.xpath("//*[.='Save all tasks']");

        String taskName = "Test task";
        String spentTime = "2.5";

        BaseTest.logIn();

        getElementWait().until(ExpectedConditions.elementToBeClickable(trackTimeButton));
        driver.findElement(trackTimeButton).click();
        driver.findElement(By.xpath("//a[.='Weekly View']")).click();
        driver.findElement(By.xpath("//*[@class='glyphicon glyphicon-plus']")).click();
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(taskNameField));
        driver.findElement(taskNameField).sendKeys(taskName);
        driver.findElement(spentTimeField).sendKeys(spentTime);

        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(saveAllTasks));
        driver.findElement(saveAllTasks).click();
        driver.navigate().refresh();

        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(taskNameField));
        Assert.assertEquals("Name hasn't been saved", taskName, driver.findElement(taskNameField).getAttribute("value"));
        Assert.assertEquals("Time hasn't been saved", spentTime, driver.findElement(spentTimeField).getAttribute("value"));


    }

}
