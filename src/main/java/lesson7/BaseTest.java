package lesson7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void prepare() {
       System.setProperty("webdriver.chrome.driver", "/Users/tanya/Documents/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa.reports.spd-ukraine.com/");
        By name = By.name("username");
        driver.findElement(name).sendKeys("tatyana.dovhan@gmail.com");
        driver.findElement(By.name("password")).sendKeys("4mohWUCg");
        driver.findElement(By.cssSelector("[role='submit']")).click();

    }

    public WebDriverWait getElementWait(){
        return new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
