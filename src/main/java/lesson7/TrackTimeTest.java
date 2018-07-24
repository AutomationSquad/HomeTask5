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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackTimeTest extends BaseTest {

    @Test
    public void test() {

        By weekly = By.xpath(".//a[contains(@href, 'weekly')]");

        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(weekly));

        driver.findElement(weekly).click();

        By addTaskButton = By.xpath(".//*[@class='task-control-group']");

        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(addTaskButton));

        driver.findElement(addTaskButton).click();

        By descriptionField = By.xpath(".//*[@title='Task description']");
        By timeField = By.xpath(".//*[@placeholder='Time, h']");
        By saveTask = By.xpath(".//button[contains(@class, 'btn-danger')]");

        driver.findElement(descriptionField).clear();
        ;
        driver.findElement(descriptionField).sendKeys("Home work");
        driver.findElement(timeField).clear();
        driver.findElement(timeField).sendKeys("5");
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(saveTask));
        driver.findElement(saveTask).click();
        driver.navigate().refresh();
        By taskRow = By.xpath("//*[@class='row task-row']");
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(taskRow));

        assertEquals("Home work", driver.findElement(descriptionField).getAttribute("value"), "Task is not saved");
    }

}