package com.bren.qa.helper;

import java.time.Duration;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;

import com.bren.qa.base.Base;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeHelper extends Base {

		public static void swipe(WebElement e) {
		    PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
		    Rectangle rect = e.getRect();
			
			PointOption pointOptionStart, pointOptionEnd;	
			pointOptionStart = PointOption.point(rect.x + rect.width - 100,
	                rect.y + rect.height / 2);
	        pointOptionEnd = PointOption.point(rect.x + 100,
	                rect.y + rect.height / 2);
	        
//	        action.press(pointOptionStart)
//            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2)))
//            .moveTo(pointOptionEnd)
//            .release()
//            .perform();
	        
	        TouchAction action =new TouchAction(driver);
	        MultiTouchAction multiaction = new MultiTouchAction(driver);
	        multiaction.add(action.press(pointOptionStart).waitAction(WaitOptions.waitOptions(Duration.ofMillis(400))).moveTo(pointOptionEnd).release());
	        multiaction.perform();
		}
}
