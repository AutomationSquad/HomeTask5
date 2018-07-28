package lesson7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTrackerTest extends BaseTest {

    @Test
    public void test(){
        driver.get("https://qa.reports.spd-ukraine.com/");

        By name = By.name("username");
        driver.findElement(name).sendKeys("aleksanderukrch@gmail.com");
        driver.findElement(By.name("password")).sendKeys("ambr525");
        driver.findElement(By.cssSelector("[role='submit']")).click();

        By calendar = By.xpath("//*[contains(text(), 'Calendar')]");
        getElementWait().withMessage("Failed to find 'Calendar' element")
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(calendar));

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close")));
        driver.findElement(By.cssSelector(".close")).click();
        getElementWait()
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".modal-dialog"))));
        driver.findElement(calendar).click();

        By requestDayStatus = By.xpath("//*[contains(text(), 'Request day status')]");
        getElementWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                requestDayStatus));
        getElementWait().until(ExpectedConditions.elementToBeClickable(requestDayStatus));
        driver.findElement(requestDayStatus).click();

        /*getElementWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary")));
        driver.findElement(By.cssSelector(".btn-primary")).click();
        getElementWait()
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".error-alert"))));
        driver.findElement(By.xpath("//*[contains(text(), 'Request day status')]")).click();*/

        By dateTo = By.cssSelector("[placeholder='Date to']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(
                dateTo));
        driver.findElement(dateTo).sendKeys("2018-07-27");


        By dateFrom = By.cssSelector("[placeholder='Date from']");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(
                dateFrom));
        driver.findElement(dateFrom).sendKeys("2018-07-25");

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

        Assertions.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '2018-07-25 - 2018-07-27')]")).isDisplayed(), "The request isn't shown!!");

        getElementWait()
                .withMessage("'Success' message is not dispayed.")
                .until((ExpectedCondition<Boolean>) input ->
                        driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
        getElementWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".success-alert")));

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Remove']")));
        WebElement currentRemoveIcon = driver.findElements(By.xpath("//*[@title='Remove']")).get(driver.findElements(By.xpath("//*[@title='Remove']")).size() - 1);
        currentRemoveIcon.click();
    }

    @Test
    public void testNamePhone(){

        driver.get("https://qa.reports.spd-ukraine.com/");
        By name = By.name("username");
        driver.findElement(name).sendKeys("aleksanderukrch@gmail.com");
        driver.findElement(By.name("password")).sendKeys("ambr525");
        driver.findElement(By.cssSelector("[role='submit']")).click();

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close")));
        driver.findElement(By.cssSelector(".close")).click();
        getElementWait()
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".modal-dialog"))));

        By profile = By.cssSelector(".dropdown");
        By link = By.linkText("Profile");
        By name2 = By.cssSelector("input[data-bind*='name']");
        By phone = By.cssSelector("input[data-bind*='phone']");
        By save = By.cssSelector(".btn.btn-success");
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(profile));
        driver.findElement(profile).click();
        driver.findElement(link).click();
        getElementWait().until(ExpectedConditions.elementToBeClickable(name2));
        driver.findElement(name2).clear();
        driver.findElement(name2).sendKeys("Sasha");
        getElementWait().until(ExpectedConditions.elementToBeClickable(phone));
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys("0983966738");
        driver.findElement(save).click();

        getElementWait()
                .withMessage("'Success' message is not dispayed.")
                .until((ExpectedCondition<Boolean>) input ->
                        driver.findElement(By.cssSelector(".success-alert")).isDisplayed());
        getElementWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".success-alert")));
        assertEquals("0983966738", driver.findElement(phone).getAttribute("value"));
    }

    @Test
    public void task(){

        driver.get("https://qa.reports.spd-ukraine.com/");
        By name = By.name("username");
        driver.findElement(name).sendKeys("aleksanderukrch@gmail.com");
        driver.findElement(By.name("password")).sendKeys("ambr525");
        driver.findElement(By.cssSelector("[role='submit']")).click();

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close")));
        driver.findElement(By.cssSelector(".close")).click();
        getElementWait()
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".modal-dialog"))));

        By addTask = By.cssSelector(".btn.form-control");
        By link = By.linkText("Daily View");

        getElementWait()
                .ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(link));
        driver.findElement(link).click();

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close")));
        driver.findElement(By.cssSelector(".close")).click();
        getElementWait()
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".modal-dialog"))));

        getElementWait()
                .until(ExpectedConditions.visibilityOfElementLocated(addTask));
        driver.findElement(addTask).click();

        By description = By.cssSelector("[placeholder='Task description']");
        By time = By.cssSelector("[placeholder='Time, h']");
        By save = By.cssSelector(".btn-danger");
        By save1 = By.cssSelector(".glyphicon");
        driver.findElement(description).clear();
        driver.findElement(description).sendKeys("First Task");
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(save));
        driver.findElement(save).click();
        driver.findElement(time).clear();
        driver.findElement(time).sendKeys("7");
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(save1));
        driver.findElement(save1).click();
        assertEquals("First Task", driver.findElement(description).getAttribute("value"));
    }
}

