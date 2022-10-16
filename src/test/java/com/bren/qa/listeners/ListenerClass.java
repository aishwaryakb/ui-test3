package com.bren.qa.listeners;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.bren.qa.report.ExtentLogger;
import com.bren.qa.report.ExtentReport;

public class ListenerClass implements ITestListener, ISuiteListener {
	public void onStart(ISuite result) {
	}
	public void onStart(ITestResult result) {
	}
	public void onTestSuccess(ITestResult result) {		
	}
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getThrowable());
	}
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getName() + " is skipped");
	}
	public void onStart(ITestContext result) {
	}
	public void onFinish(ITestContext result) {
	}
	public void onFinish(ISuite result) {
		ExtentReport.teardownReports();
	}
}