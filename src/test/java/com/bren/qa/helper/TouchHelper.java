package com.bren.qa.helper;

import java.time.Duration;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import com.bren.qa.base.Base;

public class TouchHelper extends Base{
	
	
	public static void doubleTapOnElement(WebElement e) {
		PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
	    Rectangle rect = e.getRect();
		
		PointOption pointOptionStart;	
		pointOptionStart = PointOption.point(rect.x + rect.width - 100,
                rect.y + rect.height / 2);
   
        TouchAction action =new TouchAction(driver);
        action.press(pointOptionStart)
                    .release()
//                    .perform()
//                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(50)))
                    .press(pointOptionStart)
                    .release()
                    .perform();
	}
}
