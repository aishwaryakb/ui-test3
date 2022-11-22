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
import com.bren.qa.pages.ApartmentDetailPage;
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class ApartmentsListPageTest extends Base {
    int count = 0;
    int maxTries = 3;
    LaunchPage launchPage;
    LoginPage loginPage;
    OtpVerificationPage otpVerificationPage;
    SingleApartmentHomePage myHomePage;
    ApartmentsListPage apartmentsListPage;
    ApartmentDetailPage apartmentDetailPage;
    
    
    
    public ApartmentsListPageTest() {
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
                otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
                Thread.sleep(8000);
                driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
                driver.findElementByXPath("//*[@text = 'Enter OTP']");
                myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp").toString());
                Thread.sleep(8000);
                driver.findElementByXPath("//*[@text = 'About this property']");
                apartmentsListPage = myHomePage.clickApartmentsTab();
                return;
            }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
        
    }
    @Test(priority = 1, alwaysRun=true)
    public void verifyApartmentTabPage() {
        Assert.assertTrue(apartmentsListPage.createTicketIcon.isDisplayed(),"Create A Ticket Icon is not Added to Navbar inside Apartments Tab");
        ExtentManager.getExtentTest().log(Status.PASS, "Verified that Create A Ticket Icon is Added to Navbar inside Apartments Tab");
        apartmentsListPage.verifyApartmentsTabPage();
    }
    
    @Test(priority = 2, alwaysRun=true)
    public void verifyApartmentDetailPageIsOpeningWhenClickingOnTheCard() {
        apartmentDetailPage = apartmentsListPage.clickOnProjectCard();
        Assert.assertTrue(apartmentDetailPage.viewGalleryButtonIsDisplayed(), "View Gallery Button isn't displaying");
        ExtentManager.getExtentTest().log(Status.PASS, "View Gallery Button is  displaying");
        ExtentManager.getExtentTest().log(Status.INFO, "Apartment Detail Page is opened when clicking on the card");
    
    }
    @Test(priority = 3, alwaysRun=true)
    public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromMultipleApartmentListsPage() throws InterruptedException {
        driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
        myHomePage.verifySingleApartmentHomePage();
        ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
                + " Screen When clicking on the Brens Icon from Multiple Apartments Details screen");
    }

}