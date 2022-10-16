package com.bren.qa.helper;
import java.time.Duration;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import com.bren.qa.base.Base;
import com.google.common.collect.ImmutableList;

public class TapHelper extends Base{
	public static void tapAtPoint(Point point) {
	    PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
	    Sequence tap = new Sequence(input, 0);
	    tap.addAction(input.createPointerMove(Duration.ZERO, Origin.viewport(), point.x, point.y));
	    tap.addAction(input.createPointerDown(MouseButton.LEFT.asArg()));
	    tap.addAction(new Pause(input, Duration.ofMillis(200)));
	    tap.addAction(input.createPointerUp(MouseButton.LEFT.asArg()));
	    driver.perform(ImmutableList.of(tap));
	}
	public static void tapElementAt(WebElement el, double xPct, double yPct) {
	    Rectangle elRect = el.getRect();
	    Point point = new Point(
	        elRect.x + (int)(elRect.getWidth() * xPct),
	        elRect.y + (int)(elRect.getHeight() * yPct)
	    );
	    tapAtPoint(point);
	}
}
