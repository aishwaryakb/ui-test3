package com.bren.qa.pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.report.ExtentManager;

import io.appium.java_client.MobileBy;
public class MultipleApartmentHomePage extends Base {
	@FindBy(xpath = "//*[@text = 'My Home']")
	WebElement myHomeTab;
	@FindBy(xpath = "//*[@text = 'Apartments']")
	WebElement apartmentsTab;
	@FindBy(xpath = "//*[@text = 'Rewards']")
	WebElement rewardsTab;
	@FindBy(xpath = "//*[@text = 'Profile']")
	WebElement profileTab;
	@FindBy(xpath = "//*[@text = 'Documents']")
	WebElement documentsButton;
	@FindBy(xpath = "//*[@text = 'Plans']")
	WebElement plansButton;
	@FindBy(xpath = "//*[@text = 'Your apartments']")
	public WebElement yourApartmentsTitle;
	@FindBy(xpath = "//*[@resource-id = 'apartmentTileDescriptionAndMoreDetails']")
	WebElement viewMoreDetails;
	@FindBy(xpath = "//*[@resource-id = 'apartmentTypeAndAddressView']")
	WebElement apartmentTypeAndAddressView;
	@FindBy(xpath = "//*[@resource-id = 'apartmentImage']")
	WebElement apartmentImage;
	@FindBy(xpath = "//*[@resource-id = 'commonHeader']")
	WebElement commonHeader;
	@FindBy(xpath = "//*[@resource-id = 'RNE__Image__children__container']")
	WebElement brenLogo;
	@FindBy(xpath = "//*[@resource-id = 'apartmentStatus']")
	WebElement apartmentStatus;
	@FindBy(xpath = "//*[@resource-id = 'apartmentName']")
	WebElement apartmentName;
	@FindBy(xpath = "//*[@resource-id = 'apartmentPriceAndArea']")
	WebElement apartmentPriceAndArea;
	@FindBy(xpath = "//*[@resource-id = 'apartmentArea']")
	WebElement apartmentArea;
	@FindBy(xpath = "//*[@resource-id = 'apartmentDescription']")
	WebElement apartmentDescription;
	@FindBy(xpath = "//*[@resource-id = 'apartmentType']")
	WebElement apartmentType;
	@FindBy(xpath = "//*[@resource-id = 'apartmentMoreDetails']")
	WebElement apartmentMoreDetails;
	@FindBy(xpath = "//*[@text = 'Plans']")
	WebElement floorPlansButton;
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement createTicketIcon;
	
	
	public MultipleApartmentHomePage() {
		PageFactory.initElements(driver, this);
	}
	public boolean myHomeIsDisplayed() {
		return myHomeTab.isDisplayed();
	}
	public void verifyOwnerHomePage() {
		
		Assert.assertTrue(myHomeTab.isDisplayed(), "MyHome -Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "MyHome -Tab is Displayed");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		boolean apartmentIsDisplayed =apartmentsTab.isDisplayed();
		Assert.assertTrue(apartmentIsDisplayed, "Apartments-Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartments Tab is Displayed");
		Assert.assertTrue(rewardsTab.isDisplayed(), "Rewards-Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Rewards-Tab is Displayed");
		Assert.assertTrue(profileTab.isDisplayed(), "Profile-Tab isn't showing");
		ExtentManager.getExtentTest().log(Status.PASS, "Profile-Tab is Displayed");
	}
	public DifferentDocumentCategoriesPage clickDocuments() {
		String documentsText = "Documents";
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
								+ "scrollIntoView(new UiSelector().textMatches(\"" + documentsText + "\").instance(0))"));
		documentsButton.click();
		return new DifferentDocumentCategoriesPage();
	}
	public ApartmentFloorPlansPage clickFloorPlans() {
		String plansText = "Plans";
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
								+ "scrollIntoView(new UiSelector().textMatches(\"" + plansText + "\").instance(0))"));
		plansButton.click();
		return new ApartmentFloorPlansPage();
	}
	public void homePageVerification() {
		String expectedYourApartmentsTitle = "Your apartments";
		Assert.assertEquals(yourApartmentsTitle.getAttribute("text"), expectedYourApartmentsTitle, "Yours-apartment title isn't as expected");
		ExtentManager.getExtentTest().log(Status.PASS, "Yours-apartment title is as expected");
		Assert.assertTrue(yourApartmentsTitleIsDisplayed(), "Not showing list of apartments as Home Page for "
												+ "Multiple Apartments Owner ");
		ExtentManager.getExtentTest().log(Status.PASS, "Showing list of apartments as Home Page  for Multiple Apartments Owner");
		Assert.assertTrue(commonHeaderIsDisplayed(), "Common-Header not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Common-Header is Displayed");
		
		Assert.assertTrue(brenLogoIsDisplayed(),"Bren Logo is not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Bren Logo is not Displayed");
		
		Assert.assertTrue(apartmentImageIsDisplayed(), "Apartment-Image not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Image is Displayed");
		
		Assert.assertTrue(apartmentStatusIsDisplayed(), "Apartment-Status not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Status is Displayed");
		
		Assert.assertTrue(apartmentNameIsDisplayed(), "Apartment-Name not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Common-Header is Displayed");

		Assert.assertTrue(apartmentTypeAndAddressViewIsDisplayed(), "Apartment-Type-And-Address-View not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Type-And-Address-View is Displayed");

		Assert.assertTrue(apartmentTypeIsDisplayed(), "Apartment-type not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-type is Displayed");

		Assert.assertTrue(apartmentPriceAndAreaIsDisplayed(), "Apartment-Price-And-Area not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Price-And-Area is Displayed");

		Assert.assertTrue(apartmentAreaIsDisplayed(), "Apartment-Area not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Area is Displayed");
		
		Assert.assertTrue(viewMoreDetailsIsDisplayed(), "View More details Section is not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "View More details Section is Displayed");

		Assert.assertTrue(apartmentDescriptionIsDisplayed(), "Apartment-Description not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Description is Displayed");
	}
	public SingleApartmentHomePage clickapartmentMoreDetails() {
		apartmentMoreDetails.click();
		return new SingleApartmentHomePage();
	}
	public RewardsPage clickRewardsPage() {
		rewardsTab.click();
		return new RewardsPage();
	}
	
	public CreateTicketPage clickCreateTicketButton() {
		createTicketIcon.click();
		return new CreateTicketPage();
		
	}
	public OwnerProfilePage clickProfileTab() {
		profileTab.click();
		return new OwnerProfilePage();
	}
	public SingleApartmentHomePage clickapartmentImage() {
		apartmentImage.click();
		return new SingleApartmentHomePage();
	}
	public boolean viewMoreDetailsIsDisplayed() {
		return viewMoreDetails.isDisplayed();
	}
	public boolean apartmentTypeAndAddressViewIsDisplayed() {
		return apartmentTypeAndAddressView.isDisplayed();
	}
	public boolean apartmentImageIsDisplayed() {
		return apartmentImage.isDisplayed();
	}
	public boolean commonHeaderIsDisplayed() {
		return commonHeader.isDisplayed();
	}
	public boolean brenLogoIsDisplayed() {
		return brenLogo.isDisplayed();
	}
	public boolean apartmentStatusIsDisplayed() {
		return apartmentStatus.isDisplayed();
	}
	public boolean apartmentNameIsDisplayed() {
		return apartmentName.isDisplayed();
	}
	public boolean apartmentDescriptionIsDisplayed() {
		return apartmentDescription.isDisplayed();
	}
	public boolean apartmentPriceAndAreaIsDisplayed() {
		return apartmentPriceAndArea.isDisplayed();
	}
	public boolean apartmentAreaIsDisplayed() {
		return apartmentArea.isDisplayed();
	}
	public boolean apartmentTypeIsDisplayed() {
		return apartmentType.isDisplayed();
	}
	public boolean yourApartmentsTitleIsDisplayed() {
		return yourApartmentsTitle.isDisplayed();
	}
	public boolean apartmentMoreDetailsIsDisplayed() {
		return apartmentMoreDetails.isDisplayed();
	}
}