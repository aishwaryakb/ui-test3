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
import com.bren.qa.pages.ApartmentDetailPage;
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.ReferAndEarnFormPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class ReferAndEarnFormPageTest extends Base{
	String referAndEarnDescreption = "Refer people to buy an apartment with Bren and "
			+ "earn a reward when the referred person buys an apartment";
	String alreadyExistingReferalMessage = "The contact information that you shared already exists in our system. "
			+ "We appreciate your time";
	String expectedPageHeading = "Refer a friend";
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	ApartmentsListPage apartmentsListPage;
	ApartmentDetailPage apartmentDetailPage;
	ReferAndEarnFormPage referAndEarnFormPage;
	
	public ReferAndEarnFormPageTest() {
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
		Thread.sleep(8000);
		driver.findElementByXPath("//*[@text = 'About this property']");
		apartmentsListPage = myHomePage.clickApartmentsTab();
		Thread.sleep(5000);
		apartmentDetailPage = apartmentsListPage.clickOnZaharaByBrenProject();

		
		ScrollHelper.scrollUntil("REFER A FRIEND");
		
        referAndEarnFormPage = apartmentDetailPage.clickReferAndEarnButton();
        
		
}
	
	@Test(priority = 1, alwaysRun=true)
	public void verifyFieldsInReferAFriendForm() {
		
		
		Assert.assertEquals(referAndEarnFormPage.referAFriendTitle.getAttribute("text"), expectedPageHeading);
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a Friend Heading is verified");
		
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
	
		Assert.assertTrue(referAndEarnFormPage.isProjectNameTitleIsDisplayed(), "Project Name Title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Name Title is Displayed");
		ScrollHelper.scrollDown();
		Assert.assertTrue(referAndEarnFormPage.isProjectNameFieldIsDisplayed(), "Project Input Field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Input Field is Displayed");
		
		ScrollHelper.scrollDown();
		Assert.assertTrue(referAndEarnFormPage.isReferAFriendButtonIsDisplayed(), "Refer A Friend Button isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer a Friend Button is Displayed");
		
	}
	@Test(priority = 3, alwaysRun=true)
    public void verifyUserCanSubmitTheFormOnlyAfterEnteringAllTheFieldsInTheForm() {
	    ScrollHelper.scrollDownUntil("Refer friend");
        referAndEarnFormPage.referAFriendButton.click();
        ExtentManager.getExtentTest().log(Status.INFO, "Clicked on submit button without filling any of the fields");
        Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter first name']").isDisplayed(), "First Name isn't mandatory");
        ExtentManager.getExtentTest().log(Status.PASS, "First Name is mandatory");
        Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter last name']").isDisplayed(), "Last Name isn't mandatory");
        ExtentManager.getExtentTest().log(Status.PASS, "Last Name is mandatory");
        ScrollHelper.scrollDown();
        Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter mobile number']").isDisplayed(), "Mobile Number isn't mandatory");
        ExtentManager.getExtentTest().log(Status.PASS, "Mobile Number is mandatory");
    }

	@Test(priority = 4, alwaysRun=true)
	public void verifyUserIsGettingConfirmationScreenOrAlreadyExistingReferalAfterClickingOnTheReferFriendOption() throws InterruptedException {
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
        Assert.assertTrue(referAndEarnFormPage.isConfirmationScreenOrExistingReferalMessageIsDisplayed(firstNameFieldValue), "User isn't Getting Confirmation Screen after clicking on the Refer Friend option");
        ExtentManager.getExtentTest().log(Status.PASS, "User is Getting Confirmation Screen or referal message after clicking on the Refer Friend option");
	}	
}
