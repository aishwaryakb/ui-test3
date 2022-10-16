package com.bren.qa.report;
import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
	ExtentManager(){}
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ExtentTest getExtentTest() {
		return test.get();	
	}
	public static void setExtentTest(ExtentTest extentTest) {
		test.set(extentTest);
	}
	public static void unload() {
		test.remove();
	}
}