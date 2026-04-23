package base;

import driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public abstract class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.createDriver();
        DriverManager.getDriver().get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
