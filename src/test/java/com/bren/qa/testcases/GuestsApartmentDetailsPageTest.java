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
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class GuestsApartmentDetailsPageTest extends Base {
    int count = 0;
    int maxTries = 3;
	LaunchPage launchPage;
	ApartmentsListPage apartmentsListPage;
	GuestHomePage guestHomePage;
	SingleApartmentHomePage myHomePage;
	ApartmentDetailPage apartmentDetailPage;
	GetInTouchFormPage getInTouchFormPage;
	public GuestsApartmentDetailsPageTest() {
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    ExtentReport.testInitialization(m);
	    while(true) {
            try{
	            
        		initialization();
        		launchPage = new LaunchPage();
        		guestHomePage = launchPage.clickContinueAsGuest();
        		Thread.sleep(4000);
        		apartmentsListPage = guestHomePage.clickApartmentsTab();
        		apartmentDetailPage = apartmentsListPage.clickOnZaharaByBrenProject();
        		getInTouchFormPage = new GetInTouchFormPage();
        		return;
            }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
		
	}
	@Test(priority = 1, alwaysRun=true)
	public void verifyTheUserCanSubmitTheFormOnlyAfterEnteringAllTheFieldsInTheForm() {
		ScrollHelper.scrollUntil("Submit");
		getInTouchFormPage.submitButton.click();
		ExtentManager.getExtentTest().log(Status.INFO, "Clicked on submit button withtout filling any of the fields");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter first name']").isDisplayed(), "First Name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "First Name is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter last name']").isDisplayed(), "Last Name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Last Name is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter mobile number']").isDisplayed(), "Mobile Number isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Number is mandatory");
	}
	@Test(priority = 2, alwaysRun=true)
	public void verifyUserIsGettingConfirmationScreenOrAlreadyExistingReferalAfterSubmittingGetInTouchFormOption() throws InterruptedException {
		ScrollHelper.scrollUntil("Submit");
		getInTouchFormPage.firstNameInputField.sendKeys(prop.getProperty("referAFriendFirstName"));
		Thread.sleep(5000);
		String firstNameFieldValue = driver.findElementsByClassName("android.widget.EditText").get(0).getAttribute("text");
		getInTouchFormPage.lastNameInputField.sendKeys(prop.getProperty("referAFriendLastName"));
		Thread.sleep(5000);
		getInTouchFormPage.emailInputField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		Thread.sleep(5000);
		getInTouchFormPage.mobileInputField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
		ScrollHelper.scrollDown();

		Assert.assertTrue(getInTouchFormPage.isConfirmationScreenOrExistingReferalMessageIsDisplayed(firstNameFieldValue), "User isn't Getting Confirmation Screen after "
				+ "submitting the form");
		ExtentManager.getExtentTest().log(Status.PASS, "User is Getting Confirmation Screen after submitting the form");
		
	}
}