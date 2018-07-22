import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


class TimeTrackerTest extends BaseTest {
    @Test
    void testFromLecture() {
        // VARIABLE-LOCATORS
        By calendar = By.xpath("//*[contains(text(), 'Calendar')]");
        By requestDayStatus = By.xpath("//*[contains(text(), 'Request day status')]");
        By dateFrom = By.cssSelector("[placeholder='Date from']");
        By dateTo = By.cssSelector("[placeholder='Date to']");
        By dateStatusButton = By.id("s2id_autogen3");
        By dayStatusDropDwn = By.id("select2-drop");
        By dayStatusVacation = By.xpath("//*[@class='select2-result-label' and text()='Vacation']");
        By requestButton = By.cssSelector(".btn-success");
        By requestShowing = By.xpath("//*[contains(text(), '2018-08-10 - 2018-08-20')]");
        By removeIcon = By.xpath("//*[@title='Remove']");

        getElementWait().withMessage("Failed to find 'Calendar' element")
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(calendar));

        closeUnlockedWeekModal(calendar);

        getElementWait().until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(requestDayStatus));

        closeInternalServerErrorModal(requestDayStatus);

        getElementWait()
                .until(ExpectedConditions.visibilityOfElementLocated(dateFrom));
        driver.findElement(dateFrom).sendKeys("2018-08-10");
        getElementWait()
                .until(ExpectedConditions.visibilityOfElementLocated(dateTo));
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

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(removeIcon));
        WebElement currentRemoveIcon = driver.findElements(removeIcon).get(driver.findElements(removeIcon).size() - 1);
        currentRemoveIcon.click();
        getElementWait()
                .until(ExpectedConditions.invisibilityOf(currentRemoveIcon));

        try {
            getElementWait()
                    .withMessage("The request isn't deleted!!")
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                    .until((ExpectedConditions.presenceOfElementLocated((By) currentRemoveIcon)));
        } catch (ClassCastException ignored) {
        }

    }

    @Test
    void testTaskAddingAndDeleting() {
        // VARIABLE-LOCATORS
        By addTaskButton = By.cssSelector(".btn.form-control");
        By taskNumberField = By.cssSelector(".text-uppercase.input-sm");
        By taskDescriptionField = By.cssSelector(".ui-autocomplete-input");
        By taskTimeField = By.cssSelector(".col-xs-11.form-control.input-sm");
        By saveAllButton = By.cssSelector(".btn-danger");
        By removeIcon = By.cssSelector(".glyphicon-remove");
        By confirmModalButton = By.cssSelector(".confirm");
        By confirmDeleteModalWindow = By.cssSelector(".modal-backdrop");
        By cancelDeleteButton = By.cssSelector(".cancel");

        closeUnlockedWeekModal(addTaskButton);

        driver.findElements(taskNumberField).get(driver.findElements(taskNumberField).size() - 1).sendKeys("TEST");
        driver.findElements(taskDescriptionField).get(driver.findElements(taskDescriptionField).size() - 1).sendKeys("TEST DESCRIPTION");
        driver.findElements(taskTimeField).get(driver.findElements(taskTimeField).size() - 1).sendKeys("0.1");
        getElementWait()
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(saveAllButton));
        driver.findElement(saveAllButton).click();

        WebElement currentRemoveIcon = driver.findElements(removeIcon).get(driver.findElements(removeIcon).size() - 1);
        getElementWait()
                .until(ExpectedConditions.invisibilityOfElementLocated(confirmDeleteModalWindow));
        currentRemoveIcon.click();
        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(driver.findElements(cancelDeleteButton).get(driver.findElements(cancelDeleteButton).size() - 1)));
        driver.findElements(confirmModalButton).get(driver.findElements(confirmModalButton).size() - 1).click();


        try {
            getElementWait()
                    .withMessage("The task isn't deleted!!")
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                    .until((ExpectedConditions.presenceOfElementLocated((By) currentRemoveIcon)));
        } catch (ClassCastException ignored) {
        }
    }


    @Test
    void changingDataInTheProfile() {
        By accountMenu = By.cssSelector(".dropdown");
        By profileLink = By.xpath("//a[@href = '/profile']");
        By nameField = By.xpath("//input[contains(@data-bind, 'value: name')]");
        By phoneField = By.xpath("//input[contains(@data-bind, 'value: phone')]");
        By saveButton = By.xpath("//span[contains(@class, 'glyphicon-save')]/parent::*");
        String newName = "BoFot";
        String newPhone = "0000000000";

        getElementWait().withMessage("Failed to find account menu")
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(accountMenu));

        closeUnlockedWeekModal(accountMenu);
        driver.findElement(profileLink).click();

        getElementWait()
                .until(ExpectedConditions.elementToBeClickable(nameField));

        driver.findElement(nameField).sendKeys(Keys.chord(Keys.CONTROL, "a"), newName);
        driver.findElement(phoneField).sendKeys(Keys.chord(Keys.CONTROL, "a"), newPhone);
        driver.findElement(saveButton).click();

        getElementWait()
                .withMessage("'Success' message is not dispayed.")
                .until((ExpectedCondition<Boolean>) input ->
                        driver.findElement(successMessage).isDisplayed());
        getElementWait().until(ExpectedConditions.invisibilityOfElementLocated(successMessage));

        Assertions.assertEquals(driver.findElement(phoneField).getAttribute("value"), newPhone, "The phone isn't changed");
        Assertions.assertEquals(driver.findElement(nameField).getAttribute("value"), newName, "The name isn't changed");


    }
}