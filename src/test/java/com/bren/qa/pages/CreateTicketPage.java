package com.bren.qa.pages;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CreateTicketPage extends Base {
	@FindBy(xpath = "//*[@text = 'Create a ticket']")
	public WebElement createATicketTab;
	@FindBy(xpath = "//*[@text = 'My tickets']")
	public WebElement myTicketsTab;
	@FindBy(xpath = "//*[@text = 'We love to hear from our customers. Create "
			+ "a ticket below. We aim to reply to all tickets within 24 hours during regular working hours.']")
	public WebElement createATicketDesc;
	
	@FindBy(xpath = "//*[@text = 'Ticket title']")
	public WebElement ticketTitle;
	@FindBy(xpath = "//android.widget.EditText[@index = '3']")
	public WebElement ticketTitleInputField;
	
	@FindBy(xpath = "//*[@text = 'Describe your update/issue']")
	public WebElement describeYourIssueTitle;
	@FindBy(xpath = "//android.widget.EditText[@index = '5']")
	public WebElement describeYourIssueInputField;
	
	@FindBy(xpath = "//*[@text = 'Issue type']")
	public WebElement issueTypeTitle;
	@FindBy(xpath = "//*[@resource-id = 'android_touchable_wrapper'][@index ='7']")
	public WebElement issueTypeDropDownList;
	@FindBy(xpath = "//*[@resource-id = 'android:id/text1'][@index = '1']")
	public WebElement issueTypeDropDownListElement;
	
	@FindBy(xpath = "//*[@text = 'Unit name']")
	public WebElement unitNameTitle;
	@FindBy(xpath = "//*[@resource-id = 'android_touchable_wrapper'][@index ='9']")
	public WebElement unitNameDropDownList;
	@FindBy(xpath = "//*[@resource-id = 'android:id/text1'][@index = '1']")
	public WebElement unitNameDropDownListElement;
	
	@FindBy(xpath = "//*[@text = 'Attachments (0)']")
	public WebElement attachmentsTitle;
	@FindBy(xpath = "//*[@text = 'Choose file']")
	public WebElement chooseFileButton;
	@FindBy(xpath = "//*[@content-desc = 'button']")
	public WebElement createATicketButton;
	
	@FindBy(xpath = "//*[@text = 'Please enter issue title']")
	public WebElement pleaseEnterIssueTitleValidationMessage;
	
	@FindBy(xpath = "//*[@text = 'Please enter issue description']")
	public WebElement pleaseEnterIssueDescriptionValidationMessage;
	
	@FindBy(xpath = "//*[@text = 'Please select a unit']")
	public WebElement pleaseSelectUnitValidationMessage;
	
	
	
	public CreateTicketPage() {
		PageFactory.initElements(driver, this);	
	}
	
	public TicketCreationSuccessPage clickCreateATicketButton() {
		createATicketButton.click();
		return new TicketCreationSuccessPage();
	}
	public void pullToRefresh(){

	    int deviceWidth = driver.manage().window().getSize().getWidth();
	    int deviceHeight = driver.manage().window().getSize().getHeight();
	    int midX = (deviceWidth / 2);
	    int midY = (deviceHeight / 2);
	    int bottomEdge = (int) (deviceHeight * 0.85f);
	    new TouchAction(driver)
	            .press(PointOption.point(midX,midY))
	            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	            .moveTo(PointOption.point(midX, bottomEdge))
	            .release().perform();
	}
	
	public MyTicketsPage clickMyTicketsTab() {
		myTicketsTab.click();
		return new MyTicketsPage();
	}
}
