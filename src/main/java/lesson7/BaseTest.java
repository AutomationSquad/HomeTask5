package lesson7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeEach
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public WebDriverWait getElementWait() {
        return new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    public static void logIn() {
        driver.get("https://qa.reports.spd-ukraine.com/");

        By name = By.name("username");

        driver.findElement(name).sendKeys("e.voloshyn@spd-ukraine.com");
        driver.findElement(By.name("password")).sendKeys("JMSgzZVz");

        driver.findElement(By.cssSelector("[role='submit']")).click();
    }
}
