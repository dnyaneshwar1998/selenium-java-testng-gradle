package com.example.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.framework.driver.DriverManager;
import com.example.framework.pages.SeleniumHomePage;
import org.testng.annotations.Test;

public class SampleUiTest extends BaseTest {
    @Test(description = "Verify Selenium home page header text")
    public void shouldDisplaySeleniumHeader() {
        SeleniumHomePage homePage = new SeleniumHomePage(DriverManager.getDriver());
        assertThat(homePage.getHeaderText()).containsIgnoringCase("Selenium");
    }

    @Test(description = "Verify Selenium home page title")
    public void shouldDisplaySeleniumTitle() {
        assertThat(DriverManager.getDriver().getTitle()).containsIgnoringCase("Selenium");
    }
}
