package com.bren.qa.helper;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.bren.qa.base.Base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ScrollHelper extends Base {

    public static void scrollDown(){
  
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }
    
    public static void scrollUp(){
    	  
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.2); 
        int scrollEnd = (int) (dimension.getHeight() * 0.5);

        new TouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }

    public static void scrollDownUntil(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
				+ "scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
	}
    public static void scrollNClick(WebElement el){
        int retry = 0;
        System.out.println("retry");
        while(retry <= 5){
            try{
            	System.out.print("try section");
                el.click();
                break;
            }catch (Exception e){
            	System.out.print("catch section");
                scrollDown();
                retry++;
            }
        }
    }
}
