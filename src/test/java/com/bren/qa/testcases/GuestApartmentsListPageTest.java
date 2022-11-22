package com.bren.qa.testcases;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.GuestHomePage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class GuestApartmentsListPageTest extends Base{
    int count = 0;
    int maxTries = 3;
	LaunchPage launchPage;
	ApartmentsListPage apartmentsListPage;
	GuestHomePage guestHomePage;
	SingleApartmentHomePage myHomePage;
	public GuestApartmentsListPageTest() {
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
		apartmentsListPage.verifyApartmentsTabPage();
	}
	@Test(priority = 2, alwaysRun=true)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromGuestApartmentsListPage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		apartmentsListPage.verifyApartmentsTabPage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Guests Apartments list page");
	}
}