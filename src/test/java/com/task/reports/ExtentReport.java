package com.task.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./target/extent-reports/extent-report.html");
        reporter.config().setReportName("Automation Practise Report");
        reporter.config().setDocumentTitle("Automation Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
