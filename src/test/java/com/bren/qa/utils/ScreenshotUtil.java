package com.bren.qa.utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.bren.qa.base.Base;

public final class ScreenshotUtil {
	public static FILE getBase64Image() {
		return ((TakesScreenshot)Base.driver).getScreenshotAs(OutputType.FILE);
	}
}

