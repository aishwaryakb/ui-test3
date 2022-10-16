package com.bren.qa.report;
import java.util.Objects;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	public static ExtentReports extent;
	public static void initReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Bren-First Automation Report");
		extent.attachReporter(spark);
	}
	public static void teardownReports() {
		extent.flush();
	}
	public static void createTest(String  testcaseName, String className) {
		ExtentManager.setExtentTest(extent.createTest(testcaseName, className));
	}
}

