package com.task;

import com.aventstack.extentreports.Status;
import com.task.driver.DriverManager;
import com.task.reports.ExtentReport;
import com.task.reports.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

public class TestListener extends BaseTest implements ITestListener {
    @Override
    public void onStart(ITestContext iTestContext) {

    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentReport.extentReports.flush();
        DriverManager.quit();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        String methodName=getTestMethodName(iTestResult);
        ExtentReportManager.startTest(methodName,methodName);
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(DriverManager.getDriver())).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
