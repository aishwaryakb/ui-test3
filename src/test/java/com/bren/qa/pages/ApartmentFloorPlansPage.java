package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class ApartmentFloorPlansPage extends Base{
	
	
	@FindBy(xpath = "//*[@text = 'Plans']")
	public WebElement plansTitle;
	@FindBy(xpath = "//*[@resource-id = 'ImageView']")
	public WebElement planView;
	@FindBy(xpath = "//*[@text = 'Download']")
	public WebElement downloadButton;
	
	@FindBy(xpath = "//*[@text = 'Share']")
	public WebElement shareButton;
	@FindBy(xpath = "//*[@text = 'Print']")
	public WebElement printButton;
	
	public ApartmentFloorPlansPage() {
		PageFactory.initElements(driver, this);
	}
	public void clickDownload() {
		downloadButton.click();
	}
	public void clickShare() {
		shareButton.click();
	}
	public void clickPrint() {
		printButton.click();
	}
}
