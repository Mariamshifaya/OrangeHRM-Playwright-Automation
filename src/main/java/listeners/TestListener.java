package listeners;

import base.BaseClass;
import com.aventstack.extentreports.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Execution Started");
    }

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        String screenshot =
                ScreenshotUtil.captureScreenshot(
                        BaseClass.getPage(),
                        result.getMethod().getMethodName());

        try {

            test.addScreenCaptureFromPath(screenshot);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

    }

}