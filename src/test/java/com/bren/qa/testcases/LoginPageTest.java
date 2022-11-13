package com.bren.qa.testcases;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;

public class LoginPageTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	public LoginPageTest() {
		super();
	}
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		initialization();
		launchPage = new LaunchPage();
		Thread.sleep(2000);
		loginPage = launchPage.clickSignInButton();
	}
	@Test(priority = 1)
	public void loginPageContentsVerification() {
		boolean isLoginPageDisplayed = loginPage.isLoginPageIsDisplayed();
		Assert.assertTrue(isLoginPageDisplayed,"Login Page is not  Displayed after sign-in");	
		ExtentManager.getExtentTest().log(Status.PASS, "Login Page is Displayed");
		boolean isUseEmailOptionIsDisplayed = loginPage.useEmailOptionIsDisplayed();
		Assert.assertTrue(isUseEmailOptionIsDisplayed,"Use email option is not  Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Use email option is Displayed");
		boolean isMobileInputFieldIsDisplayed = loginPage.mobileInputFieldIsDisplayed();
		Assert.assertTrue(isMobileInputFieldIsDisplayed,"Mobile input field is not  Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile input field is Displayed");
		boolean isSendOtpEnabled = loginPage.isSendOtpEnabled();
		Assert.assertTrue(isSendOtpEnabled, "SEND-OTP button is not enabled");
		ExtentManager.getExtentTest().log(Status.PASS, "SEND-OTP button is enabled");
		otpVerificationPage = loginPage.inputNumber(prop.get("number").toString());
	}
	@Test(priority = 2)
	public void acceptOnlyNumbersByMobileInputFieldVerification() {
		int length = loginPage.getLengthOfInput("abcd");
		boolean canInput = (length > 0) ? true : false;
		Assert.assertTrue(canInput, "User can enter characters in the mobile field, which is not acceptable");
		ExtentManager.getExtentTest().log(Status.PASS, "Only accepts numbers");
	}
	@Test(priority = 3)
	public void validateMessageForNotRegisteredMobileNumbers() {
		loginPage.notRegisteredLoginAttempt(prop.getProperty("non-registered-MobileNumber"));
	}
	@Test(priority = 4)
	public void validateInvalidMobileInput() {
		boolean isAnyInvalidMobileNumberMessage = loginPage.inputInvalidMobileNumber(prop.getProperty("invalid-number"));
		Assert.assertTrue(isAnyInvalidMobileNumberMessage, "Not Showing Invalid mobile number message");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing -Invalid mobile number- message for invalid mobile-number");	
	}
	@Test(priority = 5)
	public void validateUseMailOption() throws InterruptedException {
		otpVerificationPage = loginPage.loginViaMail(prop.getProperty("email"));
		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("email_otp"));
		Assert.assertTrue(myHomePage.myHomeIsDisplayed(), "Mail login was not successful");
		ExtentManager.getExtentTest().log(Status.PASS, "Login with Mail was successful");
	}
	@AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}