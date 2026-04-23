package tests;

import static org.assertj.core.api.Assertions.assertThat;

import base.BaseTest;
import driver.DriverManager;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test(description = "Verify landing page header text")
    public void shouldDisplayLandingPageHeader() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        assertThat(loginPage.getHeaderText()).containsIgnoringCase("Selenium");
    }

    @Test(description = "Verify landing page title")
    public void shouldDisplayLandingPageTitle() {
        assertThat(DriverManager.getDriver().getTitle()).containsIgnoringCase("Selenium");
    }
}
