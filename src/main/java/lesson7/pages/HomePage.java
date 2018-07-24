package lesson7.pages;

import lesson7.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    private static final BaseTest base = new BaseTest();

    private static final By trackTimePage = By.cssSelector("a[href='/trackTime']");
    private static final By weeklyView = By.cssSelector("a[href='#weekly']");
    private static final By addNewTaskButton = By.cssSelector("div.current-day + div button"); //ugly
    private static final By taskDescription = By.cssSelector("div.current-day + div button");
    private static final By taskTime = By.cssSelector("div.current-day + div button");
    private static final By saveTasksButton = By.cssSelector("div.current-day + div button");


    public static final void saveNewTask(String task, String timeSpent) {
        base.getElementWait(500).until(ExpectedConditions.presenceOfElementLocated(trackTimePage)).click();
        closePopup();
        base.getElementWait(500).until(ExpectedConditions.presenceOfElementLocated(weeklyView)).click();
        closePopup();
        base.getElementWait(500).until(ExpectedConditions.presenceOfElementLocated(addNewTaskButton)).click();
        base.driver.findElement(taskDescription).sendKeys(task);
        base.driver.findElement(taskTime).sendKeys(task);
        base.driver.findElement(saveTasksButton).click();
        base.getElementWait(500)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(saveTasksButton)));
    }

    public static final void navigateToProfile() {
        Actions action = new Actions(base.driver);
        WebElement menu = base.driver.findElement(By.cssSelector("a[data-toggle='dropdown']"));
        WebElement profile = base.driver.findElement(By.cssSelector("a[href='/profile']"));
        action.moveToElement(menu).moveToElement(profile).click().build().perform();
    }


    public static final void closePopup() {
        base.getElementWait(10)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.close")));
        base.driver.findElement(By.cssSelector("button.close")).click();
    }
}
