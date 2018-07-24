package lesson7;

import lesson7.pages.HomePage;
import lesson7.pages.LoginPage;
import lesson7.pages.Profile;
import org.junit.jupiter.api.Test;


public class TimeTrackerTest extends BaseTest {

    @Test
    public void testCaseTask1() {
        driver.navigate().to("https://qa.reports.spd-ukraine.com/");
        LoginPage.login();
        HomePage.navigateToProfile();
        Profile.changePhone("+380005875847");
        Profile.changeName("NewName");
        Profile.saveAll();
    }

    @Test
    public void testCaseTask2() {
        driver.navigate().to("https://qa.reports.spd-ukraine.com/");
        LoginPage.login();
        HomePage.closePopup();
        HomePage.saveNewTask("Task", "2");
    }

}
