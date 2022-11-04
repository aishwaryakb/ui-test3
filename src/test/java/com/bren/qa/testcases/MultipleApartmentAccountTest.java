package com.bren.qa.testcases;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.ApartmentFloorPlansPage;
import com.bren.qa.pages.DifferentDocumentCategoriesPage;
import com.bren.qa.pages.DocumentsPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.pages.ViewADocumentPage;
import com.bren.qa.report.ExtentManager;

public class MultipleApartmentAccountTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	MultipleApartmentHomePage myHomePage;
	SingleApartmentHomePage apartmentDetailsPage;
	DocumentsPage docsPage;
	ViewADocumentPage viewADocumentPage;
	ApartmentFloorPlansPage apartmentFloorPlansPage;
	DifferentDocumentCategoriesPage differentDocumentCategoriesPage;
	public MultipleApartmentAccountTest() {
		super();
	}
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text = 'Enter OTP']");
		myHomePage = otpVerificationPage.inputOtpForMultupleApartmentAccount(prop.getProperty("multpleApartmentsOwnerOtp").toString());
	}
// 	@Test(priority = 1)
	public void verifyHomePageOfMultipleApartmentsOwner() {
		Assert.assertTrue(myHomePage.createTicketIcon.isDisplayed(),"Create A Ticket Icon is not Added to Navbar inside home page");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Create A Ticket Icon is Added to Navbar inside home page");
		
		myHomePage.homePageVerification();
		
	}
	
// 	@Test(priority = 2)
	public void verifyViewMoreOptionOpensApartmentDetails() {
		apartmentDetailsPage = myHomePage.clickapartmentMoreDetails();
		Assert.assertTrue(apartmentDetailsPage.aboutThisPropertySectionIsDisplayed(), "View more option doesn't opens Apartment Details");
		ExtentManager.getExtentTest().log(Status.PASS, "View more option opens Apartment Details");
	}
	
// 	@Test(priority = 3)
	public void clickOnTheCardOpensApartmentDetails() {
		apartmentDetailsPage = myHomePage.clickapartmentMoreDetails();
		Assert.assertTrue(apartmentDetailsPage.aboutThisPropertySectionIsDisplayed(), "View more option doesn't opens Apartment Details");
		ExtentManager.getExtentTest().log(Status.PASS, "View more option opens Apartment Details");
	}
	
// 	@Test(priority = 5)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromMultipleApartmentsHomePage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.homePageVerification();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Multiple apartments Home Page");
	}
	
// 	@Test(priority = 6)
	public void documentsViewVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
        docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(5000);
		viewADocumentPage = docsPage.clickOnOneDoc();
		Thread.sleep(8000);
		boolean isDocDisplayed = viewADocumentPage.isDocumentDisplayed();
		Assert.assertTrue(isDocDisplayed, "Document isn't opened");
		
	}
	
// 	@Test(priority = 7)
	public void documentDownloadVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
        docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(3000);
		docsPage.clickDownload();
		driver.findElementByXPath("//*[@text = 'Allow']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		String  actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		System.out.print(actualtoastMessage);
		Thread.sleep(20000);
		String fileName = driver.findElementByXPath("//android.widget.TextView[@index = '1']").getAttribute("text");
		
		byte[] fileBase64 = driver.pullFile("/storage/emulated/0/Android/data/com.brencorp.play.mybren/files/Download/"+fileName);
		Assert.assertTrue(fileBase64.length > 0, "Document wasnt downloaded successfully");
		ExtentManager.getExtentTest().log(Status.PASS, "Document downloaded successfully");
	}
// 	@Test(priority = 8)
	public void documentShareVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
	    docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(5000);
		docsPage.clickShare();
		driver.findElementByXPath("//*[@text = 'Allow']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean shareTitle = driver.findElementByXPath("//*[@text = 'Share']").isDisplayed();
		Assert.assertTrue(shareTitle, "Not working share option");
		ExtentManager.getExtentTest().log(Status.PASS, "Able to share the document");
		
	}
// 	@Test(priority = 9)
	public void documentPrintVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
	    docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(5000);
		docsPage.clickPrint();
		driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
		boolean selectAPrinterTitle = driver.findElementByXPath("//*[@text = 'Select a printer']").isDisplayed();
		Assert.assertTrue(selectAPrinterTitle, "Able to print the document");
	}
	
	@Test(priority = 1)
	public void floorPlanViewAndDownloadVerification() throws IOException, InterruptedException {
		apartmentFloorPlansPage =myHomePage.clickFloorPlans();
		Thread.sleep(3000);
		Assert.assertTrue(apartmentFloorPlansPage.planView.isDisplayed(), "Floor plan isn't visible");
		ExtentManager.getExtentTest().log(Status.PASS, "floor plan is visible on screen");
		apartmentFloorPlansPage.clickDownload();
		driver.findElementByXPath("//*[@text = 'Download image file']").click();
// 		System.out.print(driver.findElementByXPath("//android.widget.Button[@index = '0']").getAttribute("text"));
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		String  actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		System.out.print(actualtoastMessage);
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.findElementByXPath("//*[@content-desc = 'Info']").click();
	
		String fileNamelabel = driver.findElementByXPath("//*[@resource-id = 'com.google.android.apps.photos:id/label']").getAttribute("text");
		String fileName = fileNamelabel.substring(fileNamelabel.indexOf("Download/")+9);
		byte[] fileBase64 = driver.pullFile("/storage/emulated/0/Android/data/com.brencorp.play.mybren/files/Download/"+fileName);
		Assert.assertTrue(fileBase64.length > 0, "Document wasnt downloaded successfully");
		ExtentManager.getExtentTest().log(Status.PASS, "Document downloaded successfully");
	}
// 	@Test(priority = 2)
	public void floorPlanShareVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		apartmentFloorPlansPage = myHomePage.clickFloorPlans();
		apartmentFloorPlansPage.clickShare();
		driver.findElementByXPath("//*[@text = 'Share image file']").click();
		driver.findElementByXPath("//*[@text = 'Allow']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean shareTitle = driver.findElementByXPath("//*[@text = 'Share']").isDisplayed();
		Assert.assertTrue(shareTitle, "Not working share option");
		ExtentManager.getExtentTest().log(Status.PASS, "Able to share the document");
	}
// 	@Test(priority = 4)
	public void floorPlanPrintVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		apartmentFloorPlansPage = myHomePage.clickFloorPlans();
		apartmentFloorPlansPage.clickPrint();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		boolean selectAPrinterTitle = driver.findElementByXPath("//*[@text = 'Select a printer']").isDisplayed();
		Assert.assertTrue(selectAPrinterTitle, "Able to print the document");
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
