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

    public WebDriverWait getElementWait(){
        return new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
