package com.bren.qa.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.OwnerProfilePage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;

public class OwnerProfilePageTest extends Base {

	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	MultipleApartmentHomePage myHomePage;
	SingleApartmentHomePage singleApartmentHomePage;
	OwnerProfilePage ownerProfilePage;
	
	public OwnerProfilePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
		Thread.sleep(8000);
		myHomePage = otpVerificationPage.inputOtp2(prop.getProperty("multpleApartmentsOwnerOtp").toString());
		ownerProfilePage = myHomePage.clickProfileTab();
		singleApartmentHomePage = new SingleApartmentHomePage();
		
	}
	@Test(priority = 1)
	public void verifyDetailsInProfilePage() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(ownerProfilePage.profilePicture.isDisplayed(), "Profile Picture isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Profile Picture is displayed");
		Assert.assertTrue(ownerProfilePage.nameTitle.isDisplayed(), "Name Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Name Title is displayed");
		Assert.assertTrue(ownerProfilePage.nameInputField.isDisplayed(), "Name Input Field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Name Input Field is displayed");
		
		Assert.assertTrue(ownerProfilePage.emailAddressTitle.isDisplayed(), "Email Address Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Address Title is displayed");
		Assert.assertTrue(ownerProfilePage.emailInputField.isDisplayed(), "Email Input Field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Input Field is displayed");
		
		Assert.assertTrue(ownerProfilePage.phoneNumberTitle.isDisplayed(), "Phone Number Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Phone Number Title is displayed");
		Assert.assertTrue(ownerProfilePage.mobileInputField.isDisplayed(), "Mobile Input Field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Input Field  is displayed");
		Assert.assertTrue(ownerProfilePage.countryCodeInputField.isDisplayed(), "Country code Input field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Country code Input field is displayed");
		
		Assert.assertTrue(ownerProfilePage.logOutButton.isDisplayed(), "Log Out Button isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "LogOut Button is displayed");
	}
	
	@Test(priority = 2)
	public void verifyThatTheNameEmailAddressAndPhoneNumberFieldsAreNonEditable() throws InterruptedException {
		Thread.sleep(5000);
		String preSetFieldValue;
		String currentFieldValue = "";
		
		preSetFieldValue = ownerProfilePage.nameInputField.getAttribute("text");
		try {
			ownerProfilePage.nameInputField.sendKeys(prop.getProperty("referAFriendFirstName"));
		}
		catch(InvalidElementStateException e) {
			currentFieldValue = ownerProfilePage.nameInputField.getAttribute("text");
		}
		Assert.assertTrue(currentFieldValue.equals(preSetFieldValue), "Name Input field is Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Name Input field non editable");
		
		
		
		
		
		preSetFieldValue = ownerProfilePage.emailInputField.getAttribute("text");
		try {
			ownerProfilePage.emailInputField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		}
		catch(InvalidElementStateException e) {
			currentFieldValue = ownerProfilePage.emailInputField.getAttribute("text");
		}
		Assert.assertTrue(currentFieldValue.equals(preSetFieldValue), "Email Input field is Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Input field non editable");
		
		
		
		
		preSetFieldValue = ownerProfilePage.mobileInputField.getAttribute("text");
		try {
			ownerProfilePage.mobileInputField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
		}
		catch(InvalidElementStateException e) {
			currentFieldValue = ownerProfilePage.mobileInputField.getAttribute("text");
		}
		Assert.assertTrue(currentFieldValue.equals(preSetFieldValue), "Mobile Input field is Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Input field non editable");
		
	}
	@Test(priority = 3)
	public void verifyThatTheUserCanLogOuTheAccountByClickingOnTheLogoutButton() {
		
		ownerProfilePage.logOutButton.click();
		ExtentManager.getExtentTest().log(Status.INFO, "clicked on logout button");
		Assert.assertTrue(launchPage.continueAsGuestButton.isDisplayed(), "Logout wasn't successfull");
		ExtentManager.getExtentTest().log(Status.PASS, "Logout was successful");
		
	}
	@Test(priority = 5)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromOwnerProfilePage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.homePageVerification();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Owner Profile Page");
	}
	
	@Test(priority = 5)
	public void verifyThatUserCanUpdateProfilePictureFromTheProfileScreenIfThereIsAnExistingOne() throws InterruptedException {
		String expectedToastMessage = "Customer's Profile Photo updated successfully";
		Thread.sleep(4000);
		WebElement parrentScrollView = driver.findElementByClassName("android.widget.ScrollView");
		parrentScrollView.findElement(By.xpath("//*[@resource-id = 'RNE__Image']")).click();
		driver.findElementByXPath("//*[@text = 'Camera']").click();
		driver.findElementByXPath("//*[@text = 'Allow']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//*[@content-desc = 'Shutter']").click();
		driver.findElementByXPath("//*[@content-desc = 'Done']").click();
		String actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualToastMessage, expectedToastMessage);
	}
	@Test(priority = 6)
	public void verifyThatUserCanUpdateProfilePictureFromTheProfileScreenIfThereIsNoExistingOne() throws InterruptedException {
		String expectedToastMessage = "Customer's Profile Photo updated successfully";
		Thread.sleep(4000);
		WebElement parrentScrollView = driver.findElementByClassName("android.widget.ScrollView");
		parrentScrollView.findElement(By.xpath("//*[@resource-id = 'RNE__Image']")).click();
		driver.findElementByXPath("//*[@text = 'Delete Photo']").click();
		Thread.sleep(4000);
		parrentScrollView.findElement(By.xpath("//*[@resource-id = 'RNE__Image']")).click();
		driver.findElementByXPath("//*[@text = 'Camera']").click();
		driver.findElementByXPath("//*[@text = 'Allow']").click();
		driver.findElementByXPath("//*[@content-desc = 'Shutter']").click();
		driver.findElementByXPath("//*[@content-desc = 'Done']").click();
		String actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualToastMessage, expectedToastMessage);
	}
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}