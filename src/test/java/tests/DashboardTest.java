package tests;

import static org.assertj.core.api.Assertions.assertThat;

import base.BaseTest;
import driver.DriverManager;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {
    @Test(description = "Verify navigation to blog page")
    public void shouldNavigateToDashboard() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.clickBlogLink();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        assertThat(dashboardPage.getHeading()).containsIgnoringCase("Blog");
    }
}
