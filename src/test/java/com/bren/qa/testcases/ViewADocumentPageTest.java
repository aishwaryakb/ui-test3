package com.bren.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.DifferentDocumentCategoriesPage;
import com.bren.qa.pages.DocumentsPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.pages.ViewADocumentPage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class ViewADocumentPageTest extends Base {
    int count = 0;
    int maxTries = 3;
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	DocumentsPage docsPage;
	ViewADocumentPage viewADocumentPage;
	DifferentDocumentCategoriesPage differentDocumentCategoriesPage;
	DifferentDocumentCategoriesPage differentDocumentCategoriesPageNext;
	
	public ViewADocumentPageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    ExtentReport.testInitialization(m);
	    while(true) {
	        try{
        		initialization();
        		launchPage = new LaunchPage();
        		loginPage = launchPage.clickSignInButton();
        		otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
        		Thread.sleep(8000);
        		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
                	driver.findElementByXPath("//*[@text = 'Enter OTP']");
        		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp").toString());
        		differentDocumentCategoriesPage = myHomePage.clickDocuments();
        		Thread.sleep(5000);
        		differentDocumentCategoriesPageNext = differentDocumentCategoriesPage.clickNextDocument();
        		
        		docsPage = differentDocumentCategoriesPage.clickDocument();
        		Thread.sleep(5000);
        		viewADocumentPage = docsPage.clickOnOneDoc();
        		Thread.sleep(8000);
        		viewADocumentPage.clickMoreOptions();
        		return;
	        }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
	}
     @Test(priority = 2, alwaysRun=true)
	public void documentDownloadVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		String expectedToastMessage = "Downloading please wait..";
		viewADocumentPage.clickDownload();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		String  actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualToastMessage, expectedToastMessage);
		ExtentManager.getExtentTest().log(Status.PASS, "Document downloaded successfully");
	}
	@Test(priority = 3, alwaysRun=true)
	public void documentShareVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		viewADocumentPage.clickShare();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean shareTitle = driver.findElementByXPath("//*[@text = 'Share']").isDisplayed();
		Assert.assertTrue(shareTitle, "Not working share option");
		ExtentManager.getExtentTest().log(Status.PASS, "Able to share the document");
	}
	@Test(priority = 4, alwaysRun=true)
	public void documentPrintVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		viewADocumentPage.clickPrint();
		Thread.sleep(10000);
		System.out.println(driver.getPageSource());
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		boolean selectAPrinterTitle = driver.findElementByXPath("//*[@text = 'Select a printer']").isDisplayed();
		Assert.assertTrue(selectAPrinterTitle, "Able to print the document");
	}
}
