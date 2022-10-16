package com.bren.qa.pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.TapHelper;
import com.bren.qa.report.ExtentManager;

public class EmailLoginPage extends Base{
	Actions action = new Actions(driver);
	@FindBy(xpath = "//*[@text = 'Login']")
	WebElement loginTitle;
	@FindBy(xpath = "//*[@text = 'Email']")
	WebElement emailInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '1']")
	WebElement emailInputField1;
	@FindBy(xpath = "//*[@text = 'Enter your registered email / use mobile']")
	WebElement useMobileOption;
	@FindBy(xpath = "//*[@text = 'SEND OTP']")
	WebElement sendOtpButton;
	@FindBy(xpath = "//*[@text = 'Please enter a valid email']")
	WebElement invalidEmailMessage;

	public EmailLoginPage() {
		PageFactory.initElements(driver, this);
	}
	public OtpVerificationPage inputMail(String mailId) {
		String expectedToastMessage = "OTP sent on Email successfully";
		emailInputField.sendKeys(mailId);
		sendOtpButton.click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		String  actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualtoastMessage, expectedToastMessage,"Not showing -'otp is sent successfully' message");
		ExtentManager.getExtentTest().log(Status.PASS, "'otp-is-sent-successfully' message is verified");
		return new OtpVerificationPage();	
	}
	public OtpVerificationPage loginViaMobile(String number) {
		TapHelper.tapElementAt(useMobileOption, 0.8, 0.6);
		WebElement mobileInputField = driver.findElementByXPath("//*[@text = 'Mobile']");
		mobileInputField.sendKeys(number);
		sendOtpButton.click();
		return new OtpVerificationPage();
	}
	public void notRegisteredLoginAttempt(String mailId) {
		String expectedToastMessage = "There is no booking associated with the given Email ID. Please contact Bren customer care for support.";
		emailInputField.sendKeys(mailId);
		sendOtpButton.click();
		String  actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualtoastMessage, expectedToastMessage, "Not showing -'There is no booking associated "
																		+ "with the given Email ID....' message");
		ExtentManager.getExtentTest().log(Status.PASS, "The message -'There is no booking associated with the given"
																		+ " Email ID....' message verified");
	}
	public boolean inputInvalidEmail(String mailId) {
		emailInputField.sendKeys(mailId);
		sendOtpButton.click();
		boolean isAnyInvalidMailMessage = invalidEmailMessage.isDisplayed();
		return isAnyInvalidMailMessage;	
	}
	public boolean isSendOtpEnabled() {
		return sendOtpButton.isEnabled();	
	}
	public boolean isLoginPageIsDisplayed() {
		return loginTitle.isDisplayed();
	}
	public boolean useMobileOptionIsDisplayed() {
		return useMobileOption.isDisplayed();
	}
	public boolean emailInputFieldIsDisplayed() {
		return emailInputField.isDisplayed();
	}
}