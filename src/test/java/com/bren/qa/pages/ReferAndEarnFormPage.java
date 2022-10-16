package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;
import com.bren.qa.report.ExtentManager;

public class ReferAndEarnFormPage extends Base{
	String alreadyExistingReferalMessage = "The contact information that you shared already exists in our system. "
			+ "We appreciate your time";
	@FindBy(xpath = "//android.view.View[@text = 'Refer a friend']")
	public WebElement referAFriendTitle;
	@FindBy(xpath = "//*[@text ='Enter the details of your friend and a Bren customer support "
			+ "executive will get in touch with them']")
	public WebElement formDescreption;
	@FindBy(xpath = "//*[@text ='First name']")
	public WebElement firstNameTitle;
	@FindBy(xpath = "//android.widget.EditText[@index ='2']")
	public WebElement firstNameField;
	@FindBy(xpath = "//*[@text ='Last name']")
	public WebElement lastNameTitle;
	@FindBy(xpath = "//android.widget.EditText[@index ='4']")
	public WebElement lastNameField;
	@FindBy(xpath = "//*[@text ='Email address']")
	public WebElement emailAddressTitle;
	@FindBy(xpath = "//android.widget.EditText[@index ='6']")
	public WebElement emailAddressField;
	@FindBy(xpath = "//*[@text ='Mobile number']")
	public WebElement mobileNumberTitle;
	@FindBy(xpath = "//android.widget.EditText[@index ='0']")
	public WebElement mobileNumberField;
	@FindBy(xpath = "//*[@text ='Project name']")
	public WebElement projectNameTitle;
	@FindBy(xpath = "//*[@resource-id = 'text_input']")
	public WebElement projectNameField;
	@FindBy(xpath = "//*[@resource-id = 'android:id/text1'][@index='1']")
	public WebElement projectNameListItem;
	@FindBy(xpath = "//android.widget.TextView[@text = 'Refer friend']")
	public WebElement referAFriendButton;
	@FindBy(xpath ="//[@content-desc = 'Go back']")
	public WebElement goBackButton;
	
	public ReferAndEarnFormPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean isReferAFriendTitleIsDisplayed() {
		return referAFriendTitle.isDisplayed();
	}
	public boolean isFormDescreptionIsDisplayed() {
		return formDescreption.isDisplayed();
	}
	public boolean isFirstNameTitleIsDisplayed() {
		return firstNameTitle.isDisplayed();
	}
	public boolean isFirstNameFieldIsDisplayed() {
		return firstNameField.isDisplayed();
	}
	public boolean isLastNameTitleIsDisplayed() {
		return lastNameTitle.isDisplayed();
	}
	public boolean isLastNameFieldIsDisplayed() {
		return lastNameField.isDisplayed();
	}
	public boolean isEmailAddressTitleIsDisplayed() {
		return emailAddressTitle.isDisplayed();
	}
	public boolean isEmailAddressFieldIsDisplayed() {
		return emailAddressField.isDisplayed();
	}
	public boolean isMobileNumberTitleIsDisplayed() {
		return mobileNumberTitle.isDisplayed();
	}
	public boolean isMobileNumberFieldIsDisplayed() {
		return mobileNumberField.isDisplayed();
	}
	public boolean isProjectNameTitleIsDisplayed() {
		return projectNameTitle.isDisplayed();
	}
	public boolean isProjectNameFieldIsDisplayed() {
		return projectNameField.isDisplayed();
	}
	public boolean isReferAFriendButtonIsDisplayed() {
		return referAFriendButton.isDisplayed();
	}
	public void fillAndSubmitReferAFriendForm() throws InterruptedException {
		Thread.sleep(5000);
		firstNameField.sendKeys(prop.getProperty("referAFriendFirstName"));
		Thread.sleep(5000);
		lastNameField.sendKeys(prop.getProperty("referAFriendLastName"));
		Thread.sleep(5000);
		emailAddressField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		Thread.sleep(5000);
		mobileNumberField.sendKeys(prop.getProperty("referAFriendExistingMobileNumber"));
		ScrollHelper.scrollDown();
		referAFriendButton.click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name"), alreadyExistingReferalMessage, "Not showing the message 'The contact information that you shared already exists in our system'");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing the message 'The contact information that you shared already exists in our system");
		
	}
	public boolean isConfirmationScreenOrExistingReferalMessageIsDisplayed(String firstNameFieldValue) {
		String referAFriendConfirmationMessage = "A Bren customer support executive will get in touch with "+firstNameFieldValue+ " soon";
		String alreadyExistingReferalMessage = "The contact information that you shared already exists in our system. We appreciate your time";
		referAFriendButton.click();
		try {
			if(driver.findElementByXPath("//*[@text ='"+referAFriendConfirmationMessage+"']").isDisplayed())
				return true;
		}
		catch(Exception e) {
			try {
				ScrollHelper.scrollDown();
				referAFriendButton.click();
				if (driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name").equals(alreadyExistingReferalMessage))
				{ 
					return true;
				}
			}
			catch(Exception e2) {
				return false;
			}
			
		}
		return false;
	    	
	}
}
