package com.bren.qa.testcases;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;
import com.bren.qa.pages.HowToEarnRewardsPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.ReferAndEarnFormPage;
import com.bren.qa.pages.RewardsPage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class RewardsPageTest extends Base {
	String referAndEarnDescreption = "Refer people to buy an apartment with Bren and "
			+ "earn a reward when the referred person buys an apartment";
	String alreadyExistingReferalMessage = "The contact information that you shared already exists in our system. "
			+ "We appreciate your time";
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	MultipleApartmentHomePage myHomePage;
	RewardsPage rewardsPage;
	ReferAndEarnFormPage  referAndEarnFormPage;
	HowToEarnRewardsPage howToEarnRewardsPage;
	String expectedPageHeading = "Your referrals";

	public RewardsPageTest() {
		super();
	}
	@BeforeMethod
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    ExtentReport.testInitialization(m);
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text = 'Enter OTP']");
		myHomePage = otpVerificationPage.inputOtpForMultupleApartmentAccount(prop.getProperty("multpleApartmentsOwnerOtp").toString());
		rewardsPage = myHomePage.clickRewardsPage();
		Thread.sleep(5000);
	}
	
	@Test(priority = 2, alwaysRun=true)
	public void verifyTheFieldsInTheScreenOpeningWhenTheUserClicksOnTheRewardsTab() {
		Assert.assertTrue(rewardsPage.createTicketIcon.isDisplayed(),"Create A Ticket Icon is not Added to Navbar inside Apartments Tab");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Create A Ticket Icon is Added to Navbar inside Apartments Tab");
		
		Assert.assertEquals(rewardsPage.yourReferralsTitle.getAttribute("text"), expectedPageHeading, "Yours-referrals title isn't as expected");
		ExtentManager.getExtentTest().log(Status.PASS, "Heading verified");
		Assert.assertTrue(rewardsPage.yourReferralsTitle.isDisplayed(), "Your referrals title isn't visible");
		ExtentManager.getExtentTest().log(Status.PASS, "Your referrals title is visible");
		Assert.assertTrue(rewardsPage.referAFriendButton.isDisplayed(), "Refer a friend button isn't visible");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a friend button is visible");
		Assert.assertTrue(rewardsPage.referralCard.isDisplayed(), "Referral Card isn't visible");
		ExtentManager.getExtentTest().log(Status.PASS, "Referral Card is visible");
		ExtentManager.getExtentTest().log(Status.INFO, "Verified that rewards screen is opening when clicking on the rewards Tab");
		
	}
	@Test(priority = 3, alwaysRun=true)
	public void verifyThatTheReferAfriendFormIsOpeningWhenTheUserClicksOnTheReferAfriendOptionFromTheRewardsScreen() throws InterruptedException {
		referAndEarnFormPage = rewardsPage.clickReferAFriendButton();
		Assert.assertTrue(referAndEarnFormPage.isFormDescreptionIsDisplayed(), "Refer a Friend Form isn't opening");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a Friend Form is visible");	
	}
	@Test(priority = 4, alwaysRun=true)
	public void verifyTheFieldsInTheReferAFriendForm() {
		referAndEarnFormPage = rewardsPage.clickReferAFriendButton();
		Assert.assertTrue(referAndEarnFormPage.isReferAFriendTitleIsDisplayed(), "Refer A Friend Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a Friend Title is Displayed");
			
		Assert.assertTrue(referAndEarnFormPage.isFormDescreptionIsDisplayed(), "Refer A Friend Form Descreption is'nt Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a Friend Form Descreption is Displayed");
		
		Assert.assertTrue(referAndEarnFormPage.isFirstNameTitleIsDisplayed(), "First Name Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "First Name Title is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isFirstNameFieldIsDisplayed(), "First Name Input Field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "First Name Input Field is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isLastNameTitleIsDisplayed(), "Last Name Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Last Name Title is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isLastNameFieldIsDisplayed(), "Last Name Input Field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Last Name Input Field is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isEmailAddressTitleIsDisplayed(), "Email Address Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Address Title is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isEmailAddressFieldIsDisplayed(), "Email Address Input Field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Address Input Field is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isMobileNumberTitleIsDisplayed(), "Mobile Number Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Number Title is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isMobileNumberFieldIsDisplayed(), "Mobile Number Input Field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Number Input Field is Displayed");
	    ScrollHelper.scrollDown();
		Assert.assertTrue(referAndEarnFormPage.isProjectNameTitleIsDisplayed(), "Project Name Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Name Title is Displayed");
	
		Assert.assertTrue(referAndEarnFormPage.isProjectNameFieldIsDisplayed(), "Project Input Field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Input Field is Displayed");
		
		ScrollHelper.scrollDown();
		Assert.assertTrue(referAndEarnFormPage.isReferAFriendButtonIsDisplayed(), "Refer A Friend Button isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a Friend Button is Displayed");
		
	}
		
	@Test(priority = 5, alwaysRun=true)
	public void verifyUserIsGettingConfirmationScreenOrAlreadyExistingReferalAfterClickingOnTheReferFriendOption() throws InterruptedException {
		referAndEarnFormPage = rewardsPage.clickReferAFriendButton();
		Thread.sleep(5000);
		referAndEarnFormPage.firstNameField.sendKeys(prop.getProperty("referAFriendFirstName"));
		Thread.sleep(5000);
		String firstNameFieldValue = referAndEarnFormPage.firstNameField.getAttribute("text");
		referAndEarnFormPage.lastNameField.sendKeys(prop.getProperty("referAFriendLastName"));
		Thread.sleep(5000);
		referAndEarnFormPage.emailAddressField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		Thread.sleep(5000);
		referAndEarnFormPage.mobileNumberField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
		ScrollHelper.scrollDown();
		referAndEarnFormPage.projectNameField.click();
		referAndEarnFormPage.projectNameListItem.click();
		Assert.assertTrue(referAndEarnFormPage.isConfirmationScreenOrExistingReferalMessageIsDisplayed(firstNameFieldValue), "User isn't Getting Confirmation Screen after clicking on the Refer Friend option");
		ExtentManager.getExtentTest().log(Status.PASS, "User is Getting Confirmation Screen or referal message after clicking on the Refer Friend option");
		
	}
	@Test(priority = 6, alwaysRun=true)
	public void verifyThatTheScreenWithReferralStepsIsOpeningWhenTheUserClickOnTheIconLabelledAsYourReferrals() {
		howToEarnRewardsPage = rewardsPage.clickOnYourReferralsIcon();
		String expectedHowToEarnRewardsTitle = "How to earn rewards";
		Assert.assertEquals(howToEarnRewardsPage.howToEarnRewardsTitle.getAttribute("text"), expectedHowToEarnRewardsTitle, "Heading of How-to-earn-Rewards Page isnt as expected");
		ExtentManager.getExtentTest().log(Status.PASS, "How-to-earn-rewards Title Is Verified");
		
		Assert.assertTrue(howToEarnRewardsPage.howToEarnRewardsTitle.isDisplayed(), "How-to-earn-rewards Title Isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "How-to-earn-rewards Title Is Displayed");
		
		Assert.assertTrue(howToEarnRewardsPage.referAFriendstep1.isDisplayed(), "Refer A Friend step1  Isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer A Friend step 1 Is Displayed");
		
		Assert.assertTrue(howToEarnRewardsPage.howToEarnRewardsTitle.isDisplayed(), "Refer A Friend step2 Isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer A Friend step 2 Is Displayed");
		
		Assert.assertTrue(howToEarnRewardsPage.howToEarnRewardsTitle.isDisplayed(), "Refer A Friend step3 Isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer A Friend step 3 Title Is Displayed");
		
		ExtentManager.getExtentTest().log(Status.INFO, "Verified that the screen with referral steps is opening when the user click on the icon labelled as \"Your referrals\"");
		
	}
	@Test(priority = 7, alwaysRun=true)
	public void verifyThatTheRewardsTabContainsTheListOfAlreadySubmittedReferralIfThereIsAny() {
	
		Assert.assertTrue(rewardsPage.referralCard.isDisplayed(), "Rewards Tab isn't showing existing Referrals");
		ExtentManager.getExtentTest().log(Status.PASS, "Rewards Tab is showing existing Referrals");
		Assert.assertTrue(rewardsPage.nameInReferralCard.isDisplayed(), "Name isn't showing inside Referral Card");
		ExtentManager.getExtentTest().log(Status.PASS, " Name is showing inside Referral Card");
		Assert.assertTrue(rewardsPage.emailIdInReferralCard.isDisplayed(), "EmailId isn't showing inside  Referral Card");
		ExtentManager.getExtentTest().log(Status.PASS, " Email Id is showing inside Referral Card");
		Assert.assertTrue(rewardsPage.mobileNumberInReferralCard.isDisplayed(), "Mobile number isn't showing inside  Referral Card");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile number is showing inside Referral Card");
		Assert.assertTrue(rewardsPage.projectTitleInReferralCard.isDisplayed(), "Project isn't showing inside  Referral Card");
		ExtentManager.getExtentTest().log(Status.PASS, "Project is showing inside Referral Card");
		Assert.assertTrue(rewardsPage.statusTitleInReferralCard.isDisplayed(), "Status isn't showing inside Referral Card");
		ExtentManager.getExtentTest().log(Status.PASS, "Status is showing inside Referral Card");
	}
	
	@Test(priority = 8, alwaysRun=true) 
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromRewardsTab() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.homePageVerification();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Rewards Tab");
	}
}
