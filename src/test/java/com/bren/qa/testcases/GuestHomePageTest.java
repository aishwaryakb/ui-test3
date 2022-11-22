package com.bren.qa.testcases;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;
import com.bren.qa.pages.ApartmentDetailPage;
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.GetInTouchFormPage;
import com.bren.qa.pages.GuestHomePage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.ReferAndEarnFormPage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class GuestHomePageTest extends Base {
    int count = 0;
    int maxTries = 3;
	String referAndEarnDescreption = "Refer people to buy an apartment with Bren and "
			+ "earn a reward when the referred person buys an apartment";
	String alreadyExistingReferalMessage = "The contact information that you shared already exists in our system. "
			+ "We appreciate your time";
	LaunchPage launchPage;
	LoginPage loginPage;
	GuestHomePage guestHomePage;
	GetInTouchFormPage getInTouchFormPage;
	ApartmentsListPage apartmentsListPage;
	ApartmentDetailPage apartmentDetailPage;
	ReferAndEarnFormPage referAndEarnFormPage;
	public GuestHomePageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException{
	    ExtentReport.testInitialization(m);
	    while(true) {
            try{
	            
        		initialization();
        		launchPage = new LaunchPage();
        		guestHomePage = launchPage.clickContinueAsGuest();
        		apartmentsListPage = new ApartmentsListPage();
        		getInTouchFormPage = new GetInTouchFormPage();
        		getInTouchFormPage = new GetInTouchFormPage();
        		apartmentDetailPage = new ApartmentDetailPage();
        		return;
            }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
		
	}
	@Test(priority = 1, alwaysRun=true)
	public void verifyThatGetInTouchFormIsOpeningWhenUserClickOnSupportsTabFromFooter() {
		getInTouchFormPage = guestHomePage.clickOnSupportTab();
		Assert.assertTrue(getInTouchFormPage.getInTouchFormTitle.isDisplayed(), "verified that GetInTouch Form is Opening when user Click on Supports Tab from footer");
	}
	@Test(priority = 2, alwaysRun=true)
	public void verifyGetInTouchFormInApartmentScreen() throws InterruptedException {
		
		apartmentsListPage = guestHomePage.clickApartmentsTab();
		Thread.sleep(5000);
		Assert.assertTrue(guestHomePage.getInTouchFormIcon.isDisplayed(),"Get-In-Touch Icon is not Added to Navbar inside guests apartments tab");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Get-In-Touch Icon is Added to Navbar inside guests apartments tab");
		
		driver.findElementByXPath("//*[@resource-id = 'project']").click();
		ScrollHelper.scrollUntil("Request a site visit");
        Assert.assertTrue(getInTouchFormPage.getInTouchFormTitle.isDisplayed(), "GetInTouch Form title isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "GetInTouch Form title is Displayed");
		Assert.assertTrue(getInTouchFormPage.firstNameInputField.isDisplayed(), "First name Input field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "First name Input field is Displayed");
		Assert.assertTrue(getInTouchFormPage.lastNameInputField.isDisplayed(), "Last name Input field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Last name Input field is Displayed");
		Assert.assertTrue(getInTouchFormPage.emailInputField.isDisplayed(), "Email Input field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Input field is Displayed");
		Assert.assertTrue(getInTouchFormPage.mobileInputField.isDisplayed(), "Mobile Input field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Input field is Displayed");
		Assert.assertTrue(getInTouchFormPage.projectName.isDisplayed(), "Project Name Input field isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Name Input field is Displayed");
		ScrollHelper.scrollDown();
		Assert.assertTrue(getInTouchFormPage.yesCheckbox.isDisplayed(), "Request a Site visit 'yes' checkbox isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Request-a-site-visit 'yes' checkbox is Displayed");
		Assert.assertTrue(getInTouchFormPage.noCheckbox.isDisplayed(), "Request a Site visit 'No' checkbox isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Request-a-site-visit 'No' checkbox is Displayed");
		ScrollHelper.scrollDown();
		Assert.assertTrue(getInTouchFormPage.submitButton.isDisplayed(), "Submit button isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Submit button is Displayed");
			
	}
	@Test(priority = 3, alwaysRun=true)
	public void verifyUserCanSubmitTheFormOnlyAfterEnteringAllTheFieldsInTheForm() {
		getInTouchFormPage = guestHomePage.clickOnSupportTab();
		getInTouchFormPage.submitButton.click();
		ExtentManager.getExtentTest().log(Status.INFO, "Clicked on submit button withtout filling any of the fields");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter first name']").isDisplayed(), "First Name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "First Name is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter last name']").isDisplayed(), "Last Name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Last Name is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter mobile number']").isDisplayed(), "Mobile Number isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Number is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter project name']").isDisplayed(), "Project name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is mandatory");
	}
	@Test(priority = 4, alwaysRun=true)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromGuestHomePage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		apartmentsListPage.verifyApartmentsTabPage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Guests Home Page");
	}
}