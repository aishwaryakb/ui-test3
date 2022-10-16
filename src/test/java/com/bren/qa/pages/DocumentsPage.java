package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class DocumentsPage extends Base{

	@FindBy(xpath = "//*[@text = 'Documents']")
	public WebElement documentsTitle;
	@FindBy(xpath = "//*[@resource-id = 'iconIcon']")
	public WebElement docIconElement;
	@FindBy(xpath = "//*[@content-desc = 'Download']")
	public WebElement downloadIcon;
	@FindBy(xpath = "//*[@content-desc = 'Share']")
	public WebElement shareIcon;
	@FindBy(xpath = "//*[@content-desc = 'Print']")
	public WebElement printIcon;
	@FindBy(id = "//*[@text = 'Clear all']")
	public WebElement cleaNotificationBtn;
	public DocumentsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public ViewADocumentPage clickOnOneDoc() {
		docIconElement.click();
		return new ViewADocumentPage();
	}
	public void clickDownload() {
		downloadIcon.click();
	}
	public void clickShare() {
		shareIcon.click();
	}
	public void clickPrint() {
		printIcon.click();
	}
	public void clearNotification() {
		cleaNotificationBtn.click();
	}
}
