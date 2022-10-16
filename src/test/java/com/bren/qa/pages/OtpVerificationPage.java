package com.bren.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;
import com.bren.qa.helper.TapHelper;

public class OtpVerificationPage extends Base{
	String editOptionString = prop.getProperty("number")+"0Edit";
	Actions action = new Actions(driver);
	@FindBy(xpath = "//*[@text = 'OTP Verification']")
	WebElement otpVerificationTitle;
	@FindBy(xpath = "//*[@text = 'Enter OTP']")
	WebElement enterOTPField;
	@FindBy(xpath = "//*[@text = 'VERIFY']")
	WebElement verifyButton;
	@FindBy(xpath = "//*[@text = 'Invalid OTP']")
	WebElement invalidOTPButton;
	@FindBy(xpath = "//*[@text = 'An OTP has been sent to ']")
	WebElement infoMessage;
	@FindBy(xpath = "//*[@text = 'Resend OTP ']")
	WebElement resendOtp;
	public OtpVerificationPage() {
		PageFactory.initElements(driver, this);
	}
	public SingleApartmentHomePage inputOtp(String otp) {
		enterOTPField.click();
		action.sendKeys(otp).perform();
		verifyButton.click();
		return new SingleApartmentHomePage();
	}
	public MultipleApartmentHomePage inputOtp2(String otp) {
		enterOTPField.click();
		action.sendKeys(otp).perform();
		verifyButton.click();
		return new MultipleApartmentHomePage();
	}
	public boolean isVerifyButtonEnabled() {
		WebElement parrentContainer = driver.findElementsByXPath("//android.view.ViewGroup[@index = '4']").get(1);
		WebElement verifyButton1 = parrentContainer.findElement(By.xpath("//android.view.ViewGroup[@index = '5']"));
		WebElement enterOTPField1 = parrentContainer.findElement(By.xpath("//android.view.ViewGroup[@index = '4']"));
		String otp;
		for(int i=1;i<=5;i++) {	
			enterOTPField1.click();
			otp ="";
			for(int j=1;j<=i;j++)
				otp = otp+String.valueOf(i);
			System.out.print(otp+"  string otp  ");
			action.sendKeys(otp).perform();	
			boolean isEnabled = verifyButton1.isEnabled();
			if(isEnabled)
				return verifyButton1.isEnabled();
			else {
				for(int k=i;k>=0;k--)
					driver.getKeyboard().sendKeys(Keys.BACK_SPACE);  
			}
		}
		return false;
	}
	public boolean inputInvalidOtp(String otp) {
		enterOTPField.click();
		action.sendKeys(otp).perform();
		verifyButton.click();
		boolean isAnyInvalidOtpMessage = invalidOTPButton.isDisplayed();
		return isAnyInvalidOtpMessage;	
	}
	public boolean verifyResendOtp() {
		boolean isDisplayed =  resendOtp.isDisplayed();
		return isDisplayed;
	}
	public boolean verifyEditOption() {
		WebElement editOption = driver.findElementByXPath("//*[@text = '"+editOptionString+"']");
		TapHelper.tapElementAt(editOption, 0.4, 0.5);
		WebElement loginTitle = driver.findElementByXPath("//*[@text = 'Login']");
		return loginTitle.isDisplayed();
	}
	public void enterOtp(String otp) {
		enterOTPField.click();
		action.sendKeys(otp).perform();	
	}
	public boolean infoMessageIsDisplayed() {
		return infoMessage.isDisplayed();
	}
	public boolean otpFieldIsDisplayed() {
		return enterOTPField.isDisplayed();
	}
	public boolean verifyEditOptionIsDisplayed() {
		WebElement editOption = driver.findElementByXPath("//*[@text = '"+editOptionString+"']");
		return editOption.isDisplayed();
	}
	public boolean verifyButtonIsDisplayed() {
		return verifyButton.isDisplayed();
	}
	public boolean verifyButtonEnabled() {
		WebElement parrentContainer = driver.findElementsByXPath("//android.view.ViewGroup[@index = '4']").get(1);
		WebElement verifyButton1 = parrentContainer.findElement(By.xpath("//android.view.ViewGroup[@index = '5']"));
		return verifyButton1.isEnabled();
	}
}