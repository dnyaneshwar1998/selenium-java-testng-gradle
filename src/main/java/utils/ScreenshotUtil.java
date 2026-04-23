package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {
    private ScreenshotUtil() {
    }

    public static String captureBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static Path captureToFile(WebDriver driver, Path targetPath) {
        try {
            Files.createDirectories(targetPath.getParent());
            Path source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            return Files.copy(source, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to save screenshot: " + targetPath, exception);
        }
    }
}
