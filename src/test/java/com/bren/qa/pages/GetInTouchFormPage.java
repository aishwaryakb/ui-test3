package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;

public class GetInTouchFormPage extends Base {
	@FindBy(xpath = "//*[@resource-id = 'selfReferralCardTitle']")
	public WebElement getInTouchFormTitle;
	@FindBy(xpath = "//android.widget.EditText[@text = 'First name']")
	public WebElement firstNameInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '2']")
	public WebElement firstNameInputField1;
	@FindBy(xpath = "//android.widget.EditText[@text = 'Last name']")
	public WebElement lastNameInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '4']")
	public WebElement lastNameInputField1;
	@FindBy(xpath = "//android.widget.EditText[@text = 'Mobile']")
	public WebElement mobileInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '6']")
	public WebElement mobileInputField1;
	@FindBy(xpath = "//android.widget.EditText[@text = 'Email']")
	public WebElement emailInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '8']")
	public WebElement emailInputField1;
	@FindBy(xpath = "//*[@resource-id = 'text_input']")
	public WebElement projectName;
	@FindBy(xpath = "//*[@resource-id = 'checkbox'][@index='7']")
	public WebElement yesCheckbox;
	@FindBy(xpath = "//*[@resource-id = 'checkbox'][@index='8']")
	public WebElement noCheckbox;
	@FindBy(xpath = "//*[@resource-id = 'android:id/text1'][@index='1']")
	public WebElement projectNameListItem;
	@FindBy(xpath = "//*[@text = 'Submit']")
	public WebElement submitButton;
	@FindBy(className = "android.widget.EditText")
	public WebElement editTextField;
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement getInTouchFormIcon;
	
	
	public GetInTouchFormPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean isGetInTouchFormTitleIsDisplayed() {
		return getInTouchFormTitle.isDisplayed();
	}
	public boolean isFirstNameInputFieldIsDisplayed() {
		return firstNameInputField.isDisplayed();
	}
	public boolean islastNameInputFieldIsDisplayed() {
		return lastNameInputField.isDisplayed();
	}
	public boolean isMobileInputFieldInputFieldIsDisplayed() {
		return mobileInputField.isDisplayed();
	}
	public boolean isprojectNameFieldIsDisplayed() {
		return projectName.isDisplayed();
	}
	public boolean isyesCheckboxFieldIsDisplayed() {
		return yesCheckbox.isDisplayed();
	}
	public boolean isnoCheckboxFieldIsDisplayed() {
		return noCheckbox.isDisplayed();
	}
	public boolean issubmitButtonIsDisplayed() {
		return submitButton.isDisplayed();
	}
	public boolean isConfirmationScreenOrExistingReferalMessageIsDisplayed(String firstNameFieldValue) {
		String referAFriendConfirmationMessage = "A Bren customer support executive will get in touch with "+firstNameFieldValue+ " soon";
		String alreadyExistingReferalMessage = "The contact information that you shared already exists in our system. We appreciate your time";
		submitButton.click();
		try {
			if(driver.findElementByXPath("//*[@text ='"+referAFriendConfirmationMessage+"']").isDisplayed())
				return true;
		}
		catch(Exception e) {
			try {
				ScrollHelper.scrollDown();
				submitButton.click();
				System.out.println(driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name"));
				System.out.println(alreadyExistingReferalMessage);
				if (driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name").equals(alreadyExistingReferalMessage))
				{ 
					System.out.println("inner if");
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
