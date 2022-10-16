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
public class SingleApartmentHomePage extends Base {
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
	@FindBy(xpath = "//*[@resource-id = 'apartmentTileDescriptionAndMoreDetails']")
	WebElement aboutThisPropertySection;
	@FindBy(xpath = "//*[@resource-id = 'apartmentTypeAndAddressView']")
	WebElement apartmentTypeAndAddressView;
	@FindBy(xpath = "//*[@resource-id = 'apartmentImage']")
	WebElement apartmentImage;
	@FindBy(xpath = "//*[@resource-id = 'commonHeader']")
	WebElement commonHeader;
	@FindBy(xpath = "//*[@resource-id = 'RNE__Image__children__container']")
	WebElement brenLogo;
	@FindBy(xpath = "//*[@resource-id = 'apartmentStatusWIP']")
	WebElement apartmentStatus;
	@FindBy(xpath = "//*[@resource-id = 'apartmentName']")
	WebElement apartmentName;
	@FindBy(xpath = "//*[@resource-id = 'apartmentPriceAndArea']")
	WebElement apartmentPriceAndArea;
	@FindBy(xpath = "//*[@resource-id = 'apartmentArea']")
	WebElement apartmentArea;
	@FindBy(xpath = "//*[@resource-id = 'apartmentTile']")
	WebElement aboutThisPropertyTitle;
	@FindBy(xpath = "//*[@resource-id = 'apartmentDescription']")
	WebElement apartmentDescription;
	@FindBy(xpath = "//*[@resource-id = 'apartmentType']")
	WebElement apartmentType;
	@FindBy(xpath = "//*[@resource-id = 'apartmentFloorPlanAndDocumentsView']")
	WebElement apartmentFloorPlanAndDocumentsView;
	@FindBy(xpath = "//*[@resource-id = 'interiorDesignImagesContainer']")
	WebElement interiorDesignImagesContainer;
	@FindBy(xpath = "//*[@resource-id = 'interiorDesignName']")
	WebElement interiorDesignName;
	@FindBy(xpath = "//*[@resource-id = 'interiorDesignImages']")
	WebElement interiorDesignCard;
	@FindBy(xpath = "//*[@text = 'View options']")
	WebElement viewOptions;
	@FindBy(xpath = "//*[@resource-id = 'referFriendAndEarnView']")
	WebElement referFriendAndEarnView;
	@FindBy(xpath = "//*[@content-desc = 'upAndDownArrow']")
	WebElement projectStatusList;
	
	@FindBy(xpath = "//*[@text = 'Start of construction']")
	WebElement startOfConstructionStatus;
	@FindBy(xpath = "//*[@text = 'Building structure']")
	WebElement buildingStructureStatus;
	@FindBy(xpath = "//*[@text = 'Interior finishes']")
	WebElement interiorFinishesStatus;
	@FindBy(xpath = "//*[@text = 'External finishes']")
	WebElement externalFinishesStatus;
	@FindBy(xpath = "//*[@text = 'Ready for occupancy']")
	WebElement readyForOccupancyStatus;
	@FindBy(xpath = "//*[@text = 'Project complete']")
	WebElement projectCompleteStatus;
	@FindBy(xpath = "//*[@text = 'REFER A FRIEND']")
	public WebElement referAFriendButton;
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement createTicketIcon;
	
	String referAndEarnTitle = "Refer and earn";
	String interiorDesignTitle = "Explore interior design furnishings for your home";
	public SingleApartmentHomePage() {
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
	public boolean verifyAllStatusDisplayed() {
		boolean startOfConstructionStatusIsDisplayed = startOfConstructionStatus.isDisplayed();
		boolean buildingStructureStatusIsDisplayed = buildingStructureStatus.isDisplayed();
		boolean interiorFinishesStatusIsDisplayed = interiorFinishesStatus.isDisplayed();
		boolean externalFinishesStatusIsDisplayed = externalFinishesStatus.isDisplayed();
		boolean readyForOccupancyStatusIsDisplayed = readyForOccupancyStatus.isDisplayed();
		boolean projectCompleteStatusIsDisplayed = projectCompleteStatus.isDisplayed();
		if(startOfConstructionStatusIsDisplayed && buildingStructureStatusIsDisplayed && interiorFinishesStatusIsDisplayed &&
				externalFinishesStatusIsDisplayed && readyForOccupancyStatusIsDisplayed && projectCompleteStatusIsDisplayed)
			return true;
		else
			return false;
	
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
	public ReferAndEarnFormPage clickReferAFriendButton() {
		referAFriendButton.click();
		return new ReferAndEarnFormPage();
	}
	
	public void verifySingleApartmentHomePage() {
		Assert.assertTrue(aboutThisPropertySectionIsDisplayed(), "Not showing Apartment details as Home Page for "
				+ "Single Apartment Owner ");
		ExtentManager.getExtentTest().log(Status.PASS, "showing Apartment details as Home Page  for Single Appartment Owner");

		Assert.assertTrue(commonHeaderIsDisplayed(), "Common-Header not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Common-Header is Displayed");

		Assert.assertTrue(brenLogoIsDisplayed(), "Bren Logo is not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Bren Logo is not Displayed");

		Assert.assertTrue(apartmentImageIsDisplayed(), "Apartment-Image not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Image is Displayed");

		Assert.assertTrue(apartmentStatusIsDisplayed(), "Apartment-Status not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Status is Displayed");

		Assert.assertTrue(apartmentNameIsDisplayed(), "Apartment-Name not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Name is Displayed");

		Assert.assertTrue(apartmentTypeAndAddressViewIsDisplayed(), "Apartment-Type-And-Address-View not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Type-And-Address-View is Displayed");

		Assert.assertTrue(apartmentTypeIsDisplayed(), "Apartment-type not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-type is Displayed");

		Assert.assertTrue(apartmentPriceAndAreaIsDisplayed(), "Apartment-Price-And-Area not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Price-And-Area is Displayed");

		Assert.assertTrue(apartmentAreaIsDisplayed(), "Apartment-Area not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Area is Displayed");

		Assert.assertTrue(aboutThisPropertySectionIsDisplayed(), "About-This-Property Section is not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "About-This-Property Section is Displayed");

		Assert.assertTrue(apartmentDescriptionIsDisplayed(), "Apartment-Description not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Description is Displayed");

		scrollDownUntil("Plans");

		Assert.assertTrue(apartmentFloorPlanAndDocumentsViewIsDisplayed(), "Apartment-Floor-Plan-And-Documents-View not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment-Floor-Plan-And-Documents-View is Displayed");

		scrollDownUntil(referAndEarnTitle);

		Assert.assertTrue(interiorDesignImagesContainerIsDisplayed(), "Interior-Design-Images-Container not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior-Design-Images-Container is Displayed");

		Assert.assertTrue(viewOptionsIsDisplayed(), "View Options inside Interior design section not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "View Options inside Interior design section is Displayed");

		Assert.assertTrue(referFriendAndEarnViewIsDisplayed(), "Refer-Friend-And-Earn-View not Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer-Friend-And-Earn-View is Displayed");
	}
	public InteriorDesignPackagesListPage clickInteriorDesignCard() {
		interiorDesignCard.click();
		return new InteriorDesignPackagesListPage();
	}
	public InteriorDesignPackagesListPage clickViewOptions() {
		viewOptions.click();
		return new InteriorDesignPackagesListPage();
	}
	
	public void clickProjectStatus() {
		projectStatusList.click();
	}
	public void scrollDownUntil(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
				+ "scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
	}
	public boolean aboutThisPropertySectionIsDisplayed() {
		return aboutThisPropertySection.isDisplayed();
	}
	public ApartmentsListPage clickApartmentsTab() {
		apartmentsTab.click();
		return new ApartmentsListPage(); 
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
	public boolean apartmentPriceAndAreaIsDisplayed() {
		return apartmentPriceAndArea.isDisplayed();
	}
	public boolean apartmentAreaIsDisplayed() {
		return apartmentArea.isDisplayed();
	}
	public boolean aboutThisPropertyTitleIsDisplayed() {
		return aboutThisPropertyTitle.isDisplayed();
	}
	public boolean apartmentDescriptionIsDisplayed() {
		return apartmentDescription.isDisplayed();
	}
	public boolean apartmentTypeIsDisplayed() {
		return apartmentType.isDisplayed();
	}
	public boolean apartmentFloorPlanAndDocumentsViewIsDisplayed() {
		return apartmentFloorPlanAndDocumentsView.isDisplayed();
	}
	public boolean interiorDesignImagesContainerIsDisplayed() {
		return interiorDesignImagesContainer.isDisplayed();
	}
	public boolean viewOptionsIsDisplayed() {
		return interiorDesignName.isDisplayed();
	}
	
	public boolean referFriendAndEarnViewIsDisplayed() {
		return referFriendAndEarnView.isDisplayed();
	}
}