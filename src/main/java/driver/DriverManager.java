package driver;

import constants.FrameworkConstants;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void createDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless", false);
        WebDriver driver = DriverFactory.createDriver(browser, headless);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                ConfigReader.getInt("implicit.wait.seconds", FrameworkConstants.DEFAULT_IMPLICIT_WAIT_SECONDS)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                ConfigReader.getInt("page.load.timeout.seconds", FrameworkConstants.DEFAULT_PAGE_LOAD_TIMEOUT_SECONDS)));

        if (headless) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call createDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
