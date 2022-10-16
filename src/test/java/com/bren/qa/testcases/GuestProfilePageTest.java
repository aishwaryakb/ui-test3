package com.bren.qa.testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.GetInTouchFormPage;
import com.bren.qa.pages.GuestHomePage;
import com.bren.qa.pages.GuestProfilePage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.report.ExtentManager;

public class GuestProfilePageTest extends Base{
	LaunchPage launchPage;
	LoginPage loginPage;
	GuestHomePage guestHomePage;
	GetInTouchFormPage getInTouchFormPage;
	GuestProfilePage guestProfilePage;
	OtpVerificationPage otpVerificationPage;
	MultipleApartmentHomePage myHomePage;
	ApartmentsListPage apartmentsListPage;

	public GuestProfilePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException{
		initialization();
		launchPage = new LaunchPage();
		guestHomePage = launchPage.clickContinueAsGuest();
		guestProfilePage = guestHomePage.clickOnProfileTab();
		apartmentsListPage = new ApartmentsListPage();
	}
	@Test(priority = 1)
	public void verifyTheDetailsInTheProfileTab() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertTrue(guestProfilePage.getInTouchFormIcon.isDisplayed(),"Get-In-Touch Icon is not Added to Navbar inside Profile tab");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Get-In-Touch Icon is Added to Navbar inside Profile Tab");
		Assert.assertTrue(guestProfilePage.yourProfileTitle.isDisplayed(), "Your profile title isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Your profile title is verified");
		Assert.assertTrue(guestProfilePage.yourProfileDescreption.isDisplayed(), "Your profile Descreption isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Your profile Descreption is verified");
		Assert.assertTrue(guestProfilePage.loginButton.isDisplayed(), "Login Button isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Login Button is verified");
	}
	
	@Test(priority = 2)
	public void verifyThatTheLoginScreenIsOpeningWhenUserClicksOnTheLoginFromProfileAndCanLoginToTheirProfileWithCredentials() throws InterruptedException {

		loginPage = guestProfilePage.clickOnLoginButton();
		Thread.sleep(3000);
		Assert.assertTrue(loginPage.isLoginPageIsDisplayed(),"Login Page is not  Displayed after Clicks On the Login From Profile");	
		ExtentManager.getExtentTest().log(Status.PASS, "Login Page is Displayed after Clicks On the Login From Profile");
		otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
		ExtentManager.getExtentTest().log(Status.INFO,"Number is Inputed");
		Thread.sleep(8000);
		myHomePage = otpVerificationPage.inputOtp2(prop.getProperty("multpleApartmentsOwnerOtp").toString());
		ExtentManager.getExtentTest().log(Status.INFO,"Otp entered");
		myHomePage.verifyOwnerHomePage();
	}
	@Test(priority = 3)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromGuestProfilePage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		apartmentsListPage.verifyApartmentsTabPage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Guest Profile Page");
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}