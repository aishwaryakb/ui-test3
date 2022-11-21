package com.bren.qa.testcases;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.DocumentsPage;
import com.bren.qa.pages.InteriorDesignConfirmationScreenPage;
import com.bren.qa.pages.InteriorDesignDetailPage;
import com.bren.qa.pages.InteriorDesignPackagesListPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;
public class InteriorDesignDetailPageTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	DocumentsPage docsPage;
	InteriorDesignPackagesListPage interiorDesignPackagesListPage;
	InteriorDesignDetailPage interiorDesignDetailPage;
	InteriorDesignConfirmationScreenPage interiorDesignConfirmationScreenPage;
	String referAndEarnTitle = "Refer and earn";
	String interiorDesignTitle = "Explore interior design furnishings for your home";
	String expectedPageHeading = "Interior design details";
	
	public InteriorDesignDetailPageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    ExtentReport.testInitialization(m);
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text = 'Enter OTP']");
		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp").toString());
		myHomePage.scrollDownUntil(referAndEarnTitle);
		interiorDesignPackagesListPage = myHomePage.clickViewOptions();
		interiorDesignDetailPage =interiorDesignPackagesListPage.clickViewMoreDetails();
		Thread.sleep(3000);
	}
	@Test(priority = 1, alwaysRun=true)
	public void verifyInteriorDesignPackageCardContents() { 
		Assert.assertEquals(interiorDesignDetailPage.pageHeading.getAttribute("text"), expectedPageHeading);
		ExtentManager.getExtentTest().log(Status.PASS, "Heading verified");
		Assert.assertTrue(interiorDesignDetailPage.interiorImagesIsDisplayed(), "Interior-Images isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior-Image is displayed");
		
		Assert.assertTrue(interiorDesignDetailPage.interiorPackageListCardIsDisplayed(), "Interior Design Card isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior Design Card is displayed");
		
		Assert.assertTrue(interiorDesignDetailPage.interiorNameAndDescriptionIsDisplayed(), "Interior Name And Description isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior Name And Description is displayed");
		
		Assert.assertTrue(interiorDesignDetailPage.interiorDetailsIsDisplayed(), "Interior-Details isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior details is displayed");
		
		Assert.assertTrue(interiorDesignDetailPage.iamInterestedBtnIsDisplayed(), "I-am-Interested-Button isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "I-am-Interested-Button is displayed");
		
	}
	// verify confirmation screen
	@Test(priority = 2, alwaysRun=true)
	public void verifyConfirmationScreenAfterclickOnIAmInterstedButton() {
	    String expectedInteriorName = driver.findElementByXPath("//*[@resource-id = 'interiorName']").getAttribute("text");
		interiorDesignConfirmationScreenPage = interiorDesignDetailPage.clickIAmInterested();
		Assert.assertTrue(interiorDesignConfirmationScreenPage.confirmationTextIsDisplayed(expectedInteriorName), "Confirmation-Text isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Confirmation-Text is Showing");
	}
	
	@Test(priority = 3, alwaysRun=true)
	public void swipeInteriorImagesTest() {
		interiorDesignDetailPage.swipeInteriorImages();
		ExtentManager.getExtentTest().log(Status.INFO, "All Interior images swiped");
	}
}
