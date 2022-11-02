package com.bren.qa.testcases;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;
import com.bren.qa.pages.DocumentsPage;
import com.bren.qa.pages.InteriorDesignPackagesListPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.ReferAndEarnFormPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;

public class SingleApartmentAccountTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	DocumentsPage docsPage;
	ReferAndEarnFormPage referAndEarnFormPage;
	InteriorDesignPackagesListPage interiorDesignPackagesListPage;
	String referAndEarnTitle = "Refer and earn";
	String interiorDesignTitle = "Explore interior design furnishings for your home";
	
	public SingleApartmentAccountTest() {
		super();
	}
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text = 'Enter OTP']");
		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp").toString());
	}
	@Test(priority = 1)
	public void verifyHomePageOfSingleApartmentOwner() {
		Assert.assertTrue(myHomePage.createTicketIcon.isDisplayed(),"Create A Ticket Icon is not Added to Navbar inside home page");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Create A Ticket Icon is Added to Navbar inside home page");
		myHomePage.verifySingleApartmentHomePage();
	}
	
	@Test(priority = 2)
	public void verifyWorkStatusList() throws InterruptedException {	
		myHomePage.clickProjectStatus();
		Thread.sleep(3000);	
		Assert.assertTrue(myHomePage.verifyAllStatusDisplayed(), "All status is displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "All status is displayed");
	}
	
	@Test(priority = 3)
	public void clickOnInteriorDesignCardsDisplaysAvailableDesignPackages() {
		myHomePage.scrollDownUntil("View options");
		interiorDesignPackagesListPage = myHomePage.clickInteriorDesignCard();
		Assert.assertTrue(interiorDesignPackagesListPage.interiorNameAndDescriptionSectionIsDisplayed(), "Click on Interior design cards isn't displaying available design packages");
		ExtentManager.getExtentTest().log(Status.PASS, "Click on Interior design cards, displaying available design packages");
	}
	
	@Test(priority = 4)
	public void clickOnViewOptionsDisplaysAvailableDesignPackages() {
		myHomePage.scrollDownUntil(referAndEarnTitle);
		interiorDesignPackagesListPage = myHomePage.clickViewOptions();
		Assert.assertTrue(interiorDesignPackagesListPage.interiorNameAndDescriptionSectionIsDisplayed(), "Click on click on View-Options isn't displaying available design packages");
		ExtentManager.getExtentTest().log(Status.PASS, "click on View-Options, displaying available design packages");
	}
	@Test(priority = 5)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromSingleApartmentPage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.verifySingleApartmentHomePage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Single Apartment Page");
	}
	@Test(priority = 6)
	public void verifyrReferAFriendOptionFromMyHomePageTest() throws InterruptedException {
		myHomePage.scrollDownUntil("Refer people to buy an apartment with Bren and earn a reward when the referred person buys an apartment");
		ScrollHelper.scrollDown();
		referAndEarnFormPage = myHomePage.clickReferAFriendButton();
		ExtentManager.getExtentTest().log(Status.INFO, "Clicked on Refer A Friend Button");
		referAndEarnFormPage.firstNameField.sendKeys(prop.getProperty("referAFriendFirstName"));
        Thread.sleep(5000);
        String firstNameFieldValue = referAndEarnFormPage.firstNameField.getAttribute("text");
        referAndEarnFormPage.lastNameField.sendKeys(prop.getProperty("referAFriendLastName"));
        Thread.sleep(5000);
        referAndEarnFormPage.emailAddressField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
        Thread.sleep(5000);
        referAndEarnFormPage.mobileNumberField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
        ScrollHelper.scrollDown();
        Assert.assertTrue(referAndEarnFormPage.isConfirmationScreenOrExistingReferalMessageIsDisplayed(firstNameFieldValue), "User isn't Getting Confirmation Screen after clicking on the Refer Friend option");
        ExtentManager.getExtentTest().log(Status.PASS, "User is Getting Confirmation Screen or referal message after clicking on the Refer Friend option");
        
//		referAndEarnFormPage.fillAndSubmitReferAFriendForm();
//		ExtentManager.getExtentTest().log(Status.PASS, "Submitted Refer A Friend Form");
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}