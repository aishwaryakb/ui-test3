package com.bren.qa.report;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.bren.qa.utils.ScreenshotUtil;

public final class ExtentLogger {
	public ExtentLogger() {}
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}
	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}
	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}
	public static void fail(Throwable throwable) {
		ExtentManager.getExtentTest().fail(throwable, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getBase64Image()).build());
		
	}
}
