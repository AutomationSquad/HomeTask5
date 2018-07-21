import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.event.DocumentEvent;

public class TimeTrackerTest extends BaseTest {

    @Test
    public void testFromLecture() {
        driver.get("https://qa.reports.spd-ukraine.com/");

        // VARIABLE LOCATORS
        By loginField = By.name("username");
        By passwordField = By.name("password");
        By submitButton = By.cssSelector("[role='submit']");
        By calendar = By.xpath("//*[contains(text(), 'Calendar')]");
        By closeModalButton = By.cssSelector(".close");
        By modalWindow = By.cssSelector(".modal-dialog");
        By requestDayStatus = By.xpath("//*[contains(text(), 'Request day status')]");
        By closeAlertButton = By.cssSelector(".btn-primary");
        By dateFrom = By.cssSelector("[placeholder='Date from']");
        By dateTo = By.cssSelector("[placeholder='Date to']");
        By dateStatusButton = By.id("s2id_autogen3");
        By dayStatusDropDwn = By.id("select2-drop");
        By dayStatusVacation = By.xpath("//*[@class='select2-result-label' and text()='Vacation']");
        By requestButton = By.cssSelector(".btn-success");
        By successMessage = By.cssSelector(".success-alert");
        By requestShowing = By.xpath("//*[contains(text(), '2018-08-10 - 2018-08-20')]");
        By removeIcons = By.xpath("//*[@title='Remove']");
        By errorWindow = By.cssSelector(".error-alert");

        driver.findElement(loginField).sendKeys("fot.bohdan.job@gmail.com");
        driver.findElement(passwordField).sendKeys("123456");
        driver.findElement(submitButton).click();


        getElementWait().withMessage("Failed to find 'Calendar' element")
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(calendar));

        // MODAL WINDOWS O_O
        try {
            driver.findElement(calendar).click();
        } catch (WebDriverException e) {

            getElementWait().until(ExpectedConditions.elementToBeClickable(closeModalButton));
            driver.findElement(closeModalButton).click();
            getElementWait().until(ExpectedConditions.invisibilityOf(driver.findElement(modalWindow)));
            driver.findElement(calendar).click();
        }

        getElementWait().until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(requestDayStatus));

        try {
            driver.findElement(requestDayStatus).click();
        } catch (WebDriverException e) {

            getElementWait().until(ExpectedConditions.elementToBeClickable(closeAlertButton));
            driver.findElement(closeAlertButton).click();
            getElementWait().until(ExpectedConditions.invisibilityOf(driver.findElement(errorWindow)));
            driver.findElement(requestDayStatus).click();
        }

        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(dateFrom));
        driver.findElement(dateFrom).sendKeys("2018-08-10");
        getElementWait().until(ExpectedConditions.visibilityOfElementLocated(dateTo));
        driver.findElement(dateTo).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2018-08-20");
        driver.findElement(dateStatusButton).click();
        getElementWait()
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(dayStatusDropDwn));
        driver
                .findElement(dayStatusDropDwn)
                .findElement(dayStatusVacation)
                .click();

        driver.findElement(requestButton).click();

        getElementWait()
                .withMessage("'Success' message is not dispayed.")
                .until((ExpectedCondition<Boolean>) input ->
                        driver.findElement(successMessage).isDisplayed());

        Assertions.assertTrue(driver.findElement(requestShowing).isDisplayed(), "The request isn't shown!!");

        getElementWait()
                .withMessage("'Success' message is not dispayed.")
                .until((ExpectedCondition<Boolean>) input ->
                        driver.findElement(successMessage).isDisplayed());
        getElementWait().until(ExpectedConditions.invisibilityOfElementLocated(successMessage));

        getElementWait().until(ExpectedConditions.elementToBeClickable(removeIcons));
        WebElement current = driver.findElements(removeIcons).get(driver.findElements(removeIcons).size() - 1);
        current.click();
        getElementWait().until(ExpectedConditions.invisibilityOf(current));
        try {
            getElementWait()
                    .withMessage("The request isn't deleted!!")
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                    .until((ExpectedConditions.presenceOfElementLocated((By) current)));
        } catch (ClassCastException e) {

        }

    }
}
