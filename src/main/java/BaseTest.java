import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    WebDriver driver;
    By successMessage = By.cssSelector(".success-alert");

    @BeforeEach
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa.reports.spd-ukraine.com/");
        By loginField = By.name("username");
        By passwordField = By.name("password");
        By submitButton = By.cssSelector("[role='submit']");
        driver.findElement(loginField).sendKeys("fot.bohdan.job@gmail.com");
        driver.findElement(passwordField).sendKeys("123456");
        driver.findElement(submitButton).click();
    }

    WebDriverWait getElementWait() {
        return new WebDriverWait(driver, 10);
    }

    // MODAL WINDOWS O_O
    void closeUnlockedWeekModal(By locator) {


        By closeModalButton = By.cssSelector(".close");
        By modalWindow = By.cssSelector(".modal-dialog");

        try {
            driver.findElement(locator).click();
        } catch (WebDriverException e) {

            getElementWait()
                    .until(ExpectedConditions.elementToBeClickable(closeModalButton));
            driver.findElement(closeModalButton).click();
            getElementWait()
                    .until(ExpectedConditions.invisibilityOf(driver.findElement(modalWindow)));
            driver.findElement(locator).click();
        }
    }

    void closeInternalServerErrorModal(By locator) {

        By errorWindow = By.cssSelector(".error-alert");
        By closeAlertButton = By.cssSelector(".btn-primary");

        try {
            driver.findElement(locator).click();
        } catch (WebDriverException e) {

            getElementWait()
                    .until(ExpectedConditions.elementToBeClickable(closeAlertButton));
            driver.findElement(closeAlertButton).click();
            getElementWait()
                    .until(ExpectedConditions.invisibilityOf(driver.findElement(errorWindow)));
            driver.findElement(locator).click();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
