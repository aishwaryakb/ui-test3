package com.bren.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.report.ExtentManager;

public class GuestHomePage extends Base{
	@FindBy(xpath = "//*[@text = 'Apartments']")
	public WebElement apartmentsTab;
	@FindBy(xpath = "//*[@text = 'Support']")
	public WebElement supportTab;
	@FindBy(xpath = "//*[@text = 'Profile']")
	public WebElement profileTab;
	@FindBy(xpath = "//*[@text = 'Projects']")
	WebElement projectsTitle;
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement getInTouchFormIcon;
	public GuestHomePage() {
		PageFactory.initElements(driver, this);	
	}
	public void verifyGuestHomePage() {	
		Assert.assertTrue(apartmentsTab.isDisplayed(), "Apartments-Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartments Tab is Displayed");
		Assert.assertTrue(supportTab.isDisplayed(), "Support-Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Support-Tab is Displayed");
		Assert.assertTrue(profileTab.isDisplayed(), "Profile-Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Profile-Tab is Displayed");
	}
	
	public ApartmentsListPage clickApartmentsTab() {
		apartmentsTab.click();
		return new ApartmentsListPage(); 
	}
	
	public GetInTouchFormPage clickOnSupportTab() {
		supportTab.click();
		return new GetInTouchFormPage();
	}
	public GuestProfilePage clickOnProfileTab() {
		profileTab.click();
		return new GuestProfilePage();
	}
	public boolean isProjectTitleIsDisplayed() {
		return projectsTitle.isDisplayed();
	}
}

