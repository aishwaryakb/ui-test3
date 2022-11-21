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
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.GetInTouchFormPage;
import com.bren.qa.pages.GuestHomePage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class GuestGetInTouchFormTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	GuestHomePage guestHomePage;
	GetInTouchFormPage getInTouchFormPage;
	ApartmentsListPage apartmentsListPage;

	public GuestGetInTouchFormTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException{
	    ExtentReport.testInitialization(m);
		initialization();
		launchPage = new LaunchPage();
		guestHomePage = launchPage.clickContinueAsGuest();
		getInTouchFormPage = guestHomePage.clickOnSupportTab();
		apartmentsListPage = new ApartmentsListPage();
		Thread.sleep(3000);
	}
	@Test(priority = 1, alwaysRun=true)
	public void verifyFieldsInGetInTouchForm() throws InterruptedException {
		Assert.assertTrue(getInTouchFormPage.getInTouchFormIcon.isDisplayed(),"Get-In-Touch Icon is not Added to Navbar inside SupportTab");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Get-In-Touch Icon is Added to Navbar inside SupportTab");
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
		Assert.assertTrue(getInTouchFormPage.yesCheckbox.isDisplayed(), "Request a Site visit 'yes' checkbox isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Request-a-site-visit 'yes' checkbox is Displayed");
		Assert.assertTrue(getInTouchFormPage.noCheckbox.isDisplayed(), "Request a Site visit 'No' checkbox isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Request-a-site-visit 'No' checkbox is Displayed");
		Assert.assertTrue(getInTouchFormPage.submitButton.isDisplayed(), "Submit button isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Submit button is Displayed");
	}
	@Test(priority = 2, alwaysRun=true)
	public void verifyTheFieldsInTheGetInTouchFormAreEditable() {
		getInTouchFormPage.firstNameInputField.sendKeys("firstname");
		System.out.println(driver.findElementsByClassName("android.widget.EditText").get(0).getAttribute("text"));
		Assert.assertTrue(driver.findElementsByClassName("android.widget.EditText").get(0).getAttribute("text").length() > 0, "FirstName Input field isn't Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "First Name Input field is editable");
	
		getInTouchFormPage.lastNameInputField.sendKeys("last name");
		System.out.println(driver.findElementsByClassName("android.widget.EditText").get(1).getAttribute("text"));
		Assert.assertTrue(driver.findElementsByClassName("android.widget.EditText").get(1).getAttribute("text").length() > 0, "LastName Input field isn't Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Last Name Input field is editable");
	
		getInTouchFormPage.mobileInputField.sendKeys("2020200202");
		System.out.println(driver.findElementsByClassName("android.widget.EditText").get(2).getAttribute("text"));
		Assert.assertTrue(driver.findElementsByClassName("android.widget.EditText").get(2).getAttribute("text").length() > 0, "Mobile Input field isn't Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Input field is editable");
		
		getInTouchFormPage.emailInputField.sendKeys("mail@gmail.com");
		System.out.println(driver.findElementsByClassName("android.widget.EditText").get(3).getAttribute("text"));
		Assert.assertTrue(driver.findElementsByClassName("android.widget.EditText").get(3).getAttribute("text").length() > 0, "Email Input field isn't Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Input field is editable");
			
		getInTouchFormPage.projectName.click();
		String itemClicked = getInTouchFormPage.projectNameListItem.getAttribute("text");
		getInTouchFormPage.projectNameListItem.click();
		String selectedProject = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked, selectedProject, "Project name field isnt editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name field is editable");
	
	}
	@Test(priority = 3, alwaysRun=true)
	public void  verifyTheUserCanSelectAnyProjectNameFromTheProjectNameField() throws InterruptedException {		
		getInTouchFormPage.projectName.click();
		String itemClicked1 = driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='1']").getAttribute("text");
		driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='1']").click();
		Thread.sleep(3000);
		String selectedProject1 = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked1, selectedProject1, "Project name isn't selectable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is selectable");
		
		getInTouchFormPage.projectName.click();
		String itemClicked2 = driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='2']").getAttribute("text");
		driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='2']").click();
		Thread.sleep(3000);
		String selectedProject2 = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked2, selectedProject2, "Project name isn't selectable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is selectable");
		
		getInTouchFormPage.projectName.click();
		String itemClicked3 = driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='3']").getAttribute("text");
		driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='3']").click();
		Thread.sleep(3000);
		String selectedProject3 = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked3, selectedProject3, "Project name isn't selectable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is selectable");
		
		getInTouchFormPage.projectName.click();
		String itemClicked4 = driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='4']").getAttribute("text");
		driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='4']").click();
		Thread.sleep(3000);
		String selectedProject4 = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked4, selectedProject4, "Project name isn't selectable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is selectable");
		
		getInTouchFormPage.projectName.click();
		String itemClicked5 = driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='5']").getAttribute("text");
		driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='5']").click();
		Thread.sleep(3000);
		String selectedProject5 = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked5, selectedProject5, "Project name isn't selectable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is selectable");
	
		getInTouchFormPage.projectName.click();
		String itemClicked6 = driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='6']").getAttribute("text");
		driver.findElementByXPath("//*[@resource-id = 'android:id/text1'][@index='6']").click();
		Thread.sleep(3000);
		String selectedProject6 = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		Assert.assertEquals(itemClicked6, selectedProject6, "Project name isn't selectable");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is selectable");
		
	}
	
	@Test(priority = 4, alwaysRun=true)
	public void verifyUserCanSubmitTheFormOnlyAfterEnteringAllTheFieldsInTheForm() {
		getInTouchFormPage.submitButton.click();
		ExtentManager.getExtentTest().log(Status.INFO, "Clicked on submit button without filling any of the fields");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter first name']").isDisplayed(), "First Name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "First Name is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter last name']").isDisplayed(), "Last Name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Last Name is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter mobile number']").isDisplayed(), "Mobile Number isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Number is mandatory");
		Assert.assertTrue(driver.findElementByXPath("//*[@text ='Please enter project name']").isDisplayed(), "Project name isn't mandatory");
		ExtentManager.getExtentTest().log(Status.PASS, "Project name is mandatory");
	}
	
	@Test(priority = 5, alwaysRun=true)
	public void verifyUserIsGettingConfirmationScreenOrAlreadyExistingReferalAfterSubmittingGetInTouchFormOption() throws InterruptedException {
		
		getInTouchFormPage.firstNameInputField.sendKeys(prop.getProperty("referAFriendFirstName"));
		Thread.sleep(5000);
		String firstNameFieldValue = driver.findElementsByClassName("android.widget.EditText").get(0).getAttribute("text");
		getInTouchFormPage.lastNameInputField.sendKeys(prop.getProperty("referAFriendLastName"));
		Thread.sleep(5000);
		getInTouchFormPage.emailInputField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		Thread.sleep(5000);
		getInTouchFormPage.mobileInputField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
		ScrollHelper.scrollDown();
		getInTouchFormPage.projectName.click();
		getInTouchFormPage.projectNameListItem.click();
		Assert.assertTrue(getInTouchFormPage.isConfirmationScreenOrExistingReferalMessageIsDisplayed(firstNameFieldValue), "User isn't Getting Confirmation Screen after "
				+ "submitting the form");
		ExtentManager.getExtentTest().log(Status.PASS, "User is Getting Confirmation Screen after submitting the form");
		
	}
	
	@Test(priority = 6, alwaysRun=true)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromGetInTouchForm() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		apartmentsListPage.verifyApartmentsTabPage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Get In Touch Form screen");
	}
	
		
	@Test(priority = 7, alwaysRun=true)
	public void verifyThatGetInTouchFormIsOpeningWhenTheUserClickOnTheIconFromNavbar() {
		getInTouchFormPage.getInTouchFormIcon.click();
		Assert.assertTrue(getInTouchFormPage.getInTouchFormTitle.isDisplayed(), "GET-IN-TOUCH form is not opening when clicking on the icon from Nav bar");
		ExtentManager.getExtentTest().log(Status.PASS, "verified that Get-In-Touch Form is opening when the user click on the icon from Navbar");
	}
}
