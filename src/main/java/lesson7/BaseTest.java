package lesson7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    public WebDriver driver;

    @BeforeEach
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public WebDriverWait getElementWait(int ms){
        return new WebDriverWait(driver, ms);
    }

    @AfterEach
    public void completeTest() {
        driver.quit();
    }
}
