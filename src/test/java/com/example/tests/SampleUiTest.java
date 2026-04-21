package com.example.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.framework.driver.DriverManager;
import com.example.framework.pages.SeleniumHomePage;
import org.testng.annotations.Test;

public class SampleUiTest extends BaseTest {
    @Test(description = "Verify Selenium home page header text")
    public void shouldDisplaySeleniumHeader() throws InterruptedException {
        System.out.println("Creating home page object");
        SeleniumHomePage homePage = new SeleniumHomePage(DriverManager.getDriver());
        Thread.sleep(5000);
        System.out.println("Getting header text");
        System.out.println(homePage.getHeaderText());
        assertThat(homePage.getHeaderText()).containsIgnoringCase("Selenium");
        homePage.clickBlogLink();
        assertThat(homePage.getHeaderText()).containsIgnoringCase("Blog");
    }

    @Test(description = "Verify Selenium home page title")
    public void shouldDisplaySeleniumTitle() {
        assertThat(DriverManager.getDriver().getTitle()).containsIgnoringCase("Selenium");
    }


}
