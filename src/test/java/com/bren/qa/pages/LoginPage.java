package com.bren.qa.pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.TapHelper;
import com.bren.qa.report.ExtentManager;

public class LoginPage extends Base {
	@FindBy(xpath = "//*[@text = 'Login']")
	WebElement loginTitle;
	@FindBy(xpath = "//*[@text = 'Mobile']")
	WebElement mobileInputField;
	@FindBy(xpath = "//*[@text = 'Email']")
	WebElement emailInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '0']")
	WebElement mobileInputField1;
	@FindBy(xpath = "//*[@text = 'Enter your registered mobile / use email']")
	WebElement useEmailOption;
	@FindBy(xpath = "//*[@text = 'SEND OTP']")
	WebElement sendOtpButton;
	@FindBy(xpath = "//*[@text = 'Please enter a valid mobile number']")
	WebElement invalidMobileNumberMessage;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	public OtpVerificationPage inputNumber(String number) {
		String expectedToastMessage = "OTP is sent successfully";
		mobileInputField.sendKeys(number);
		int textLength = mobileInputField1.getAttribute("text").length();
		boolean canInput = (textLength > 0) ? true : false;
		Assert.assertTrue(canInput, "User cant enter mobile number in the mobile Input field ");
		ExtentManager.getExtentTest().log(Status.PASS, "User can enter mobile number in the mobile field");
		sendOtpButton.click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		String  actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(expectedToastMessage, actualtoastMessage,"Not showing -'otp is sent successfully' message");
		ExtentManager.getExtentTest().log(Status.PASS, "otp-is-sent-successfully message is verified");
		return new OtpVerificationPage();
	}
	public OtpVerificationPage enterNumber(String number) {
		mobileInputField.sendKeys(number);
		sendOtpButton.click();
		return new OtpVerificationPage();
	}
	public OtpVerificationPage loginViaMail(String mailId) {
		TapHelper.tapElementAt(useEmailOption, 0.8, 0.6);
		WebElement emailInputField = driver.findElementByXPath("//*[@text = 'Email']");
		emailInputField.sendKeys(mailId);
		sendOtpButton.click();
		return new OtpVerificationPage();
	}
	public void notRegisteredLoginAttempt(String number) {
		String expectedToastMessage = "There is no booking associated with the given mobile. Please contact Bren "
																					+ "customer care for support.";
		mobileInputField.sendKeys(number);
		sendOtpButton.click();
		String  actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(expectedToastMessage, actualtoastMessage,"Not showing -'There is no booking associated "
																		+ "with the given Email ID....' message");
		ExtentManager.getExtentTest().log(Status.PASS, "The message -'There is no booking associated with the given "
																		+ "mobile....' message verified");
	}
	public int getLengthOfInput(String input) {
		mobileInputField.sendKeys(input);
		int textLength = mobileInputField1.getAttribute("text").length();
		return textLength;
	}
	public EmailLoginPage clickOnUseMail() {
		TapHelper.tapElementAt(useEmailOption, 0.8, 0.6);
		return new EmailLoginPage();
	}
	public boolean inputInvalidMobileNumber(String number) {
		mobileInputField.sendKeys(number);
		sendOtpButton.click();
		boolean isAnyInvalidMobileNumberMessage = invalidMobileNumberMessage.isDisplayed();
		return isAnyInvalidMobileNumberMessage;	
	}
	public boolean isSendOtpEnabled() {
		return sendOtpButton.isEnabled();	
	}
	public boolean isLoginPageIsDisplayed() {
		return loginTitle.isDisplayed();
	}
	public boolean useEmailOptionIsDisplayed() {
		return useEmailOption.isDisplayed();
	}
	public boolean mobileInputFieldIsDisplayed() {
		return mobileInputField.isDisplayed();
	}
}