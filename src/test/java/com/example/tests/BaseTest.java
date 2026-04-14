package com.example.tests;

import com.example.framework.config.ConfigManager;
import com.example.framework.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.createDriver();
        DriverManager.getDriver().get(ConfigManager.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
