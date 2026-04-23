package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.FrameworkConstants;
import driver.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
    private static final ExtentReports EXTENT = createExtentReports();
    private static final ThreadLocal<ExtentTest> TEST_NODE = new ThreadLocal<>();
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static ExtentReports createExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.EXTENT_REPORT_FILE);
        sparkReporter.config().setDocumentTitle("UI Automation Report");
        sparkReporter.config().setReportName("Test Execution Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Framework", "Selenium + TestNG + Gradle");
        return extentReports;
    }

    @Override
    public void onStart(ITestContext context) {
        EXTENT.setSystemInfo("Suite", context.getSuite().getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = EXTENT.createTest(result.getMethod().getMethodName())
                .assignCategory(result.getTestClass().getName())
                .info("Started at: " + LocalDateTime.now().format(TIME_FORMAT));
        TEST_NODE.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST_NODE.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = TEST_NODE.get();
        extentTest.log(Status.FAIL, result.getThrowable());
        attachScreenshot(extentTest);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST_NODE.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
        TEST_NODE.remove();
    }

    private void attachScreenshot(ExtentTest extentTest) {
        try {
            WebDriver driver = DriverManager.getDriver();
            String screenshot = ScreenshotUtil.captureBase64(driver);
            extentTest.addScreenCaptureFromBase64String(screenshot, "Failure Screenshot");
        } catch (Exception exception) {
            extentTest.log(Status.WARNING, "Could not capture screenshot: " + exception.getMessage());
        }
    }
}
