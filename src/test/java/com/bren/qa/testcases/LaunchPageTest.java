package com.bren.qa.testcases;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.GuestHomePage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.report.ExtentManager;

public class LaunchPageTest extends Base{
	LaunchPage launchPage;
	LoginPage loginPage;
	GuestHomePage guestHomePage;
	public LaunchPageTest() {
		super();
	}
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException{
		initialization();
		launchPage = new LaunchPage();
	}
	@Test(priority = 1)
	public void validateLaunchPageContents() throws InterruptedException {	
	    launchPage.swipeLaunchScreenImages();
        ExtentManager.getExtentTest().log(Status.INFO, "sliding of launch screen images");
		boolean isLogoDisplayed = launchPage.validateLogoDisplayed();
		Assert.assertTrue(isLogoDisplayed,"Bren logo isnt Displayed");	
		ExtentManager.getExtentTest().log(Status.PASS, "Logo is Displayed");
		boolean isSignInButtonDisplayed = launchPage.validateSignInPageDisplayed();
		Assert.assertTrue(isSignInButtonDisplayed, "Sign-In Page isnt Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Sign-In Page Is Displayed");
		boolean isContinueAsGuestDisplayed = launchPage.validateContinueAsGuestDisplayed();
		Assert.assertTrue(isContinueAsGuestDisplayed,"Continue-as-guest button isnt displayed");	
		ExtentManager.getExtentTest().log(Status.PASS, "Continue-as-guest button is displayed");		
	}
	
	@Test(priority = 2)
	public void continueAsGuestOptionVerification() throws InterruptedException {
		guestHomePage = launchPage.clickContinueAsGuest();
		ExtentManager.getExtentTest().log(Status.PASS, "clicked on the button Continue-as-Guest");
		Thread.sleep(3000);
		guestHomePage.verifyGuestHomePage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified Guest Login");		
	}
	
	@Test(priority = 3)
	public void verifyLandingPageForTheAppIsApartmentsForTheGuestUser() throws InterruptedException {
		guestHomePage = launchPage.clickContinueAsGuest();
		Thread.sleep(3000);
		Assert.assertTrue(guestHomePage.isProjectTitleIsDisplayed(), "Landing Page for the app isn't apartments for Guest User");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Landing Page for the app is apartments for Guest User");
	}
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}
