package com.bren.qa.testcases;
import java.io.IOException;
import java.lang.reflect.Method;
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
import com.bren.qa.report.ExtentReport;

public class MultipleApartmentAccountTest extends Base {
    int count = 0;
    int maxTries = 3;
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	MultipleApartmentHomePage myHomePage;
	SingleApartmentHomePage apartmentDetailsPage;
	DocumentsPage docsPage;
	ViewADocumentPage viewADocumentPage;
	ApartmentFloorPlansPage apartmentFloorPlansPage;
	DifferentDocumentCategoriesPage differentDocumentCategoriesPage;
	DifferentDocumentCategoriesPage differentDocumentCategoriesPageNext;
	public MultipleApartmentAccountTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    while(true) {
            try{
	            ExtentReport.testInitialization(m);
        		initialization();
        		launchPage = new LaunchPage();
        		loginPage = launchPage.clickSignInButton();
        		otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
        		Thread.sleep(8000);
        		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
                driver.findElementByXPath("//*[@text = 'Enter OTP']");
        		myHomePage = otpVerificationPage.inputOtpForMultupleApartmentAccount(prop.getProperty("multpleApartmentsOwnerOtp").toString());
        		return;
            }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
	}
 	@Test(priority = 1, alwaysRun=true)
	public void verifyHomePageOfMultipleApartmentsOwner() {
		Assert.assertTrue(myHomePage.createTicketIcon.isDisplayed(),"Create A Ticket Icon is not Added to Navbar inside home page");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Create A Ticket Icon is Added to Navbar inside home page");
		
		myHomePage.homePageVerification();
		
	}
	
 	@Test(priority = 2, alwaysRun=true)
	public void verifyViewMoreOptionOpensApartmentDetails() {
		apartmentDetailsPage = myHomePage.clickapartmentMoreDetails();
		Assert.assertTrue(apartmentDetailsPage.aboutThisPropertySectionIsDisplayed(), "View more option doesn't opens Apartment Details");
		ExtentManager.getExtentTest().log(Status.PASS, "View more option opens Apartment Details");
	}
	
 	@Test(priority = 3, alwaysRun=true)
	public void clickOnTheCardOpensApartmentDetails() {
		apartmentDetailsPage = myHomePage.clickapartmentMoreDetails();
		Assert.assertTrue(apartmentDetailsPage.aboutThisPropertySectionIsDisplayed(), "View more option doesn't opens Apartment Details");
		ExtentManager.getExtentTest().log(Status.PASS, "View more option opens Apartment Details");
	}
	
 	@Test(priority = 4, alwaysRun=true)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromMultipleApartmentsHomePage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.homePageVerification();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Multiple apartments Home Page");
	}
	
 	@Test(priority = 5, alwaysRun=true)
	public void documentsViewVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
	    Thread.sleep(5000);
	    differentDocumentCategoriesPageNext = differentDocumentCategoriesPage.clickNextDocument();
        docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(5000);
		viewADocumentPage = docsPage.clickOnOneDoc();
		Thread.sleep(8000);
		boolean isDocDisplayed = viewADocumentPage.isDocumentDisplayed();
		Assert.assertTrue(isDocDisplayed, "Document isn't opened");
		
	}
	
	@Test(priority = 6, alwaysRun=true)
	public void documentDownloadVerification() throws IOException, InterruptedException {
	    String expectedToastMessage = "Downloading please wait..";
        
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
	    Thread.sleep(5000);
	    differentDocumentCategoriesPageNext = differentDocumentCategoriesPage.clickNextDocument();
        docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(3000);
		docsPage.clickDownload();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		String actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
        Assert.assertEquals(actualToastMessage, expectedToastMessage);
        
		ExtentManager.getExtentTest().log(Status.PASS, "Document downloaded successfully");
	}
 	@Test(priority = 7, alwaysRun=true)
	public void documentShareVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
	    Thread.sleep(5000);
	    differentDocumentCategoriesPageNext = differentDocumentCategoriesPage.clickNextDocument();
	    docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(5000);
		docsPage.clickShare();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean shareTitle = driver.findElementByXPath("//*[@text = 'Share']").isDisplayed();
		Assert.assertTrue(shareTitle, "Not working share option");
		ExtentManager.getExtentTest().log(Status.PASS, "Able to share the document");
		
	}
 	@Test(priority = 8, alwaysRun=true)
	public void documentPrintVerification() throws IOException, InterruptedException {
	    differentDocumentCategoriesPage = myHomePage.clickDocuments();
	    Thread.sleep(5000);
	    differentDocumentCategoriesPageNext = differentDocumentCategoriesPage.clickNextDocument();
	    docsPage = differentDocumentCategoriesPage.clickDocument();
		Thread.sleep(5000);
		docsPage.clickPrint();
		driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
		boolean selectAPrinterTitle = driver.findElementByXPath("//*[@text = 'Select a printer']").isDisplayed();
		Assert.assertTrue(selectAPrinterTitle, "Able to print the document");
	}
	
	@Test(priority = 9, alwaysRun=true)
	public void floorPlanViewAndDownloadVerification() throws IOException, InterruptedException {
	    String expectedToastMessage = "Downloading please wait..";
	    apartmentFloorPlansPage =myHomePage.clickFloorPlans();
		Thread.sleep(3000);
		Assert.assertTrue(apartmentFloorPlansPage.planView.isDisplayed(), "Floor plan isn't visible");
		ExtentManager.getExtentTest().log(Status.PASS, "floor plan is visible on screen");
		apartmentFloorPlansPage.clickDownload();
		driver.findElementByXPath("//*[@text = 'Download image file']").click();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		String  actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualToastMessage, expectedToastMessage);
	    ExtentManager.getExtentTest().log(Status.PASS, "Document downloaded successfully");
	        
	}
 	@Test(priority = 10, alwaysRun=true)
	public void floorPlanShareVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		apartmentFloorPlansPage = myHomePage.clickFloorPlans();
		apartmentFloorPlansPage.clickShare();
		driver.findElementByXPath("//*[@text = 'Share image file']").click();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean shareTitle = driver.findElementByXPath("//*[@text = 'Share']").isDisplayed();
		Assert.assertTrue(shareTitle, "Not working share option");
		ExtentManager.getExtentTest().log(Status.PASS, "Able to share the document");
	}
 	@Test(priority = 11, alwaysRun=true)
	public void floorPlanPrintVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		apartmentFloorPlansPage = myHomePage.clickFloorPlans();
		apartmentFloorPlansPage.clickPrint();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		boolean selectAPrinterTitle = driver.findElementByXPath("//*[@text = 'Select a printer']").isDisplayed();
		Assert.assertTrue(selectAPrinterTitle, "Able to print the document");
	}
}
	