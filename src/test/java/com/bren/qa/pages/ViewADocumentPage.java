package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class ViewADocumentPage extends Base{
	
	@FindBy(className = "android.widget.RelativeLayout")
	WebElement pdfDocElement;
	@FindBy(className = "android.widget.ImageView")
	WebElement imageDocElement;
	@FindBy(xpath = "//*[@content-desc = 'shareDownloadPrintOptions']")
	WebElement shareDownloadPrintOptions;	
	@FindBy(xpath = "//*[@text = 'Download']")
	WebElement downloadBtn;
	@FindBy(xpath = "//*[@text = 'Share']")
	WebElement shareBtn;
	@FindBy(xpath = "//*[@text = 'Print']")
	WebElement printBtn;
	public ViewADocumentPage() {
		PageFactory.initElements(driver, this);
	}
	public void clickMoreOptions() {
		shareDownloadPrintOptions.click();
	}
	public boolean isDocumentDisplayed() {		
		if(driver.findElementsByClassName("android.widget.RelativeLayout").size() > 0 || 
				driver.findElementsByClassName("android.widget.ImageView").size() > 0) {
			return true;
		}
		else 
			return false;
	}
	public void clickDownload() {
		downloadBtn.click();
	}
	public void clickShare() {
		shareBtn.click();
	}
	public void clickPrint() {
		printBtn.click();
	}
}
