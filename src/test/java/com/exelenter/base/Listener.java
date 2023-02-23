package com.exelenter.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.exelenter.utils.CommonMethods;
import com.exelenter.utils.Constants;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    ExtentSparkReporter reporter;
    ExtentReports reports;
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: "+result.getName());

        test = reports.createTest(result.getName());
        test.pass("Test Case Passed:  "+result.getName());

        //optionally you can capture screenshots here, for each success test case (not recommended!)
        test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("PASS/"+result.getName()));

        test.log(Status.PASS, "Test Passed. This is coming from the log status");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: "+result.getName());

        test =reports.createTest(result.getName());
        test.fail("Test Case Failed. "+result.getName());
        test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("FAILS/"+result.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: "+result.getName());

        test=reports.createTest(result.getName());
        test.skip("Test Case Skipped. "+result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("<==== Test Started ====> "+context.getName()+" | "+context.getStartDate());

         //Adding reports (start tracking & recording  when test started). Extentreports Library is required.
        reporter =new ExtentSparkReporter(Constants.REPORT_FILEPATH);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Exelenter Test Reports");         //just like the page title
        reporter.config().setReportName("Exelenter Project Test Reports");  //top right corner of the dashboard
        reports =new ExtentReports();
        reports.attachReporter(reporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n************************\n<==== Test Finished ====> "+context.getName()+" | "+context.getEndDate());
        reports.flush();      //erases previous data and start new one.
    }
}
