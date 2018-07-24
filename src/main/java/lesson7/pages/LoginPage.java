package lesson7.pages;

import lesson7.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage {

    private static final String login = "d.palamarchuk@spd-ukraine.com";
    private static final String password = "HjREq6dc";
    private static final BaseTest base = new BaseTest();
    private  WebDriver driver;

    private static final By usernameField = By.cssSelector("input[name='username']");
    private static final By passwordField = By.cssSelector("input[name='password']");
    private static final By loginButton = By.cssSelector("button[role='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void login() {
        base.getElementWait(500).until(ExpectedConditions.presenceOfElementLocated(usernameField)).sendKeys(login);
        //base.driver.findElement(usernameField).sendKeys(login);
        base.driver.findElement(passwordField).sendKeys(password);
        base.driver.findElement(loginButton).click();

        //to remove the popup
        base.getElementWait(100)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.close")));
        base.driver.findElement(By.cssSelector("button.close")).click();
    }

}
