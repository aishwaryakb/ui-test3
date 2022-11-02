package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class InteriorDesignConfirmationScreenPage extends Base{
	
	@FindBy(xpath = "//*[@resource-id = 'iconIcon']")
	WebElement icon;
	
	public InteriorDesignConfirmationScreenPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean confirmationTextIsDisplayed(String interiorname) {
	    String actualConfirmationMessage = "We have registered your interest in the interior package "+interiorname+". We will get in touch with you to take this forward soon";
		return driver.findElementByXPath("//*[@text = '"+actualConfirmationMessage+"']").isDisplayed();
	}
}
