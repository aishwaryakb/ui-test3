package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.report.ExtentManager;

import io.appium.java_client.MobileBy;
public class ApartmentsListPage extends Base {

	@FindBy(xpath = "//*[@resource-id = 'project']")
	WebElement apartmentProjectCard;
	@FindBy(xpath = "//*[@text = 'Zahara By Bren']")
	WebElement zaharaByBrenProjectCard;
	@FindBy(xpath = "//*[@text = 'Projects']")
	WebElement projectsTitle;
	@FindBy(xpath = "//*[@resource-id = 'projectStatus']")
	WebElement projectStatus;
	@FindBy(xpath = "//*[@resource-id = 'projectNameAndDescription']")
	WebElement projectNameAndDescription;
	@FindBy(xpath = "//*[@resource-id = 'projectName']")
	WebElement projectName;
	@FindBy(xpath = "//*[@resource-id = 'commonHeader']")
	WebElement commonHeader;
	@FindBy(xpath = "//*[@resource-id = 'RNE__Image']")
	WebElement brenLogo;
	String zaharaByBrenProjectCardString = "Zahara By Bren";
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement createTicketIcon;
	
	
	public ApartmentsListPage() {
		PageFactory.initElements(driver, this);
	}
	
	public ApartmentDetailPage clickOnProjectCard() {
		apartmentProjectCard.click();
		return new ApartmentDetailPage();
	}
	public ApartmentDetailPage clickOnZaharaByBrenProject() {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
				+ "scrollIntoView(new UiSelector().textMatches(\"" +zaharaByBrenProjectCardString+ "\").instance(0))"));
		zaharaByBrenProjectCard.click();
		return new ApartmentDetailPage();
	}
	public void verifyApartmentsTabPage() {
//		Assert.assertTrue(projectsT);
		Assert.assertTrue(apartmentProjectCardIsDisplayed(), "Project Cards isnt displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Cards are displaying");
		Assert.assertTrue(projectsTitleIsDisplayed(), "projects Title isn't displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Title is displaying");
		Assert.assertTrue(projectStatusIsDisplayed(), "Project Status isnt displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Status is displaying");
		Assert.assertTrue(projectNameAndDescriptionIsDisplayed(), "Project Name And Description isn't displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Name And Description are displaying");
		Assert.assertTrue(projectNameIsDisplayed(), "Project Name isnt displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Name is displaying");
		Assert.assertTrue(commonHeaderIsDisplayed(), "Common Header isnt displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Common header is displaying");
		Assert.assertTrue(brenLogoIsDisplayed(), "Bren Logo isnt displaying");
		ExtentManager.getExtentTest().log(Status.PASS, "Bren Logo is displaying");
	}
	public boolean apartmentProjectCardIsDisplayed() {
		return apartmentProjectCard.isDisplayed();
	}
	public boolean projectsTitleIsDisplayed() {
		return projectsTitle.isDisplayed();
	}
	public boolean projectStatusIsDisplayed() {
		return projectStatus.isDisplayed();
	}
	public boolean projectNameAndDescriptionIsDisplayed() {
		return projectNameAndDescription.isDisplayed();
	}
	public boolean projectNameIsDisplayed() {
		return projectName.isDisplayed();
	}
	public boolean commonHeaderIsDisplayed() {
		return commonHeader.isDisplayed();
	}
	public boolean brenLogoIsDisplayed() {
		return brenLogo.isDisplayed();
	}
}
