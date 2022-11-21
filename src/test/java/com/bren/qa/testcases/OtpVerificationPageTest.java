package com.bren.qa.testcases;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class OtpVerificationPageTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	
	public OtpVerificationPageTest() {
		super();
	}
	@BeforeMethod
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    ExtentReport.testInitialization(m);
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
		Thread.sleep(8000);
	}
	@Test(priority = 1, alwaysRun=true)
	public void otpPageContentsVerification() {
		boolean isInfoMessageDisplayed = otpVerificationPage.infoMessageIsDisplayed();
		Assert.assertTrue(isInfoMessageDisplayed, "Not Showing Info message");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing Info-Message");
		boolean isOtpFieldDisplayed = otpVerificationPage.otpFieldIsDisplayed();
		Assert.assertTrue(isOtpFieldDisplayed, "Not Showing OTP entering field");
		ExtentManager.getExtentTest().log(Status.PASS, "OTP field is displayed");
		boolean isEditOptionDisplayed = otpVerificationPage.verifyEditOptionIsDisplayed();
		Assert.assertTrue(isEditOptionDisplayed, "Not Showing Edit Option");
		ExtentManager.getExtentTest().log(Status.PASS, "Edit Option is displayed");
		boolean isVerifyButtonDisplayed = otpVerificationPage.verifyButtonIsDisplayed();
		Assert.assertTrue(isVerifyButtonDisplayed, "Not Showing VERIFY button");
		ExtentManager.getExtentTest().log(Status.PASS, "VERIFY button is displayed");
	}
	@Test(priority = 2, alwaysRun=true)
	public void verifySuccessfulSignIn() throws InterruptedException {
		myHomePage = otpVerificationPage.inputOtp(prop.get("otp").toString());
		Assert.assertTrue(myHomePage.myHomeIsDisplayed(), "Login wasn't successfull");
		ExtentManager.getExtentTest().log(Status.PASS, "User Logged in Successfully");
	}
	@Test(priority = 3, alwaysRun=true)
	public void validateEnablingOfVerifyButton() throws InterruptedException {	
		boolean isEnabled = otpVerificationPage.isVerifyButtonEnabled();
		Assert.assertFalse(isEnabled, "Verify-Button is enabled before entering 6 digits");
		ExtentManager.getExtentTest().log(Status.PASS, "Verify-Button is still disabled for until 6 digits");
		otpVerificationPage.enterOtp("666666");
		boolean isEnabledForSixDigits = otpVerificationPage.isVerifyButtonEnabled();
		Assert.assertTrue(isEnabledForSixDigits, "Verify-Button is still disabled after entering 6 digits");
		ExtentManager.getExtentTest().log(Status.PASS, "Verify-Button is enabled after entering 6 digits");
	}
	@Test(priority = 5, alwaysRun=true)
	public void verifyEditOption() {
		Assert.assertTrue(otpVerificationPage.verifyEditOption(),"Edit option is'nt verified");
		ExtentManager.getExtentTest().log(Status.PASS, "Edit Option is Verified");
	}
	@Test(priority = 6, alwaysRun=true)
	public void verifyInvalidOtpMessage() {
		boolean isAnyInvalidOtpMessage = otpVerificationPage.inputInvalidOtp("000000");
		Assert.assertTrue(isAnyInvalidOtpMessage, "Not Showing Invalid OTP message");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing Invalid-OTP message for invalid otp");
	}
	@Test(priority = 7, alwaysRun=true)
	public void verifyResendOtpButton() throws InterruptedException {
	    driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean isResendOtpDisplayed = otpVerificationPage.verifyResendOtp();
		Assert.assertTrue(isResendOtpDisplayed, "Resend-Otp option isnt available after 45 seconds");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing Resend-OTP option after 45 seconds");
	}
}
