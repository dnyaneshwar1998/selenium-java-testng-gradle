package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public abstract class BasePage {
    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(10);

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        WaitUtils.waitForClickable(driver, element, DEFAULT_WAIT).click();
    }

    protected String getText(WebElement element) {
        return WaitUtils.waitForVisible(driver, element, DEFAULT_WAIT).getText();
    }
}
