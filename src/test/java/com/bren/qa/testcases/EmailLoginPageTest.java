package com.bren.qa.testcases;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.EmailLoginPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class EmailLoginPageTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	EmailLoginPage emailLoginPage;	
	public EmailLoginPageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    ExtentReport.testInitialization(m);
		initialization();
		launchPage = new LaunchPage();
		Thread.sleep(2000);
		loginPage = launchPage.clickSignInButton();
		emailLoginPage = loginPage.clickOnUseMail();
	}
	@Test(priority = 1, alwaysRun=true)
	public void emailLoginPageContentsVerification() {
		boolean isLoginPageDisplayed = emailLoginPage.isLoginPageIsDisplayed();
		Assert.assertTrue(isLoginPageDisplayed,"Login Page is not  Displayed after sign-in");
		ExtentManager.getExtentTest().log(Status.PASS, "Login Page is Displayed");
		boolean isUseEmailOptionIsDisplayed = emailLoginPage.useMobileOptionIsDisplayed();
		Assert.assertTrue(isUseEmailOptionIsDisplayed,"Use-Mobile option is not  Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Use-Mobile option is Displayed");
		boolean isMobileInputFieldIsDisplayed = emailLoginPage.emailInputFieldIsDisplayed();
		Assert.assertTrue(isMobileInputFieldIsDisplayed,"Email input field is not  Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email input field is Displayed");
		boolean isSendOtpEnabled = emailLoginPage.isSendOtpEnabled();
		Assert.assertTrue(isSendOtpEnabled, "SEND-OTP button is not enabled");
		ExtentManager.getExtentTest().log(Status.PASS, "SEND-OTP button is enabled");
		otpVerificationPage = emailLoginPage.inputMail(prop.get("email").toString());
		ExtentManager.getExtentTest().log(Status.PASS, "Mail ID entered");
	}
	@Test(priority = 2, alwaysRun=true)
	public void validateMessageForNotRegisteredEmailIDs() {
		emailLoginPage.notRegisteredLoginAttempt(prop.getProperty("non-registered-MailId"));
	}
	@Test(priority = 3, alwaysRun=true)
	public void validateInvalidEmailId() {
		boolean isAnyInvalidEmailMessage = emailLoginPage.inputInvalidEmail(prop.getProperty("invalid-mailId"));
		Assert.assertTrue(isAnyInvalidEmailMessage, "Not Showing Invalid Email message");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing -Invalid Email-message for invalid mail-Id");
	}
	@Test(priority = 4, alwaysRun=true)
	public void validateUseMobileOption() throws InterruptedException {
		otpVerificationPage = emailLoginPage.loginViaMobile(prop.getProperty("number"));
		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp"));
		Assert.assertTrue(myHomePage.myHomeIsDisplayed(), "Mail login was not successful");
	}
}