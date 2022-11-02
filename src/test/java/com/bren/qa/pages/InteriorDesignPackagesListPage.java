package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class InteriorDesignPackagesListPage extends Base {
	
	@FindBy(xpath = "//*[@resource-id = 'InteriorPackageListCard']")
	public WebElement interiorPackageListCard;
	@FindBy(xpath = "//*[@text = 'Interior design packages']")
	public WebElement pageHeading;
	@FindBy(xpath = "//*[@resource-id = 'interiorImages']")
	public WebElement interiorImagesSection;
	@FindBy(xpath = "//*[@resource-id = 'interiorDetails']")
	public WebElement interiorDetailsSection;
	@FindBy(xpath = "//*[@resource-id = 'interiorNameAndDescription']")
	public WebElement interiorNameAndDescriptionSection;
	@FindBy(xpath = "//*[@resource-id = 'interiorName']")
	public WebElement interiorName;
	@FindBy(xpath = "//*[@resource-id = 'viewMoreDetails']")
	public WebElement viewMoreDetailsLink;
	
	
	public InteriorDesignPackagesListPage() {
		PageFactory.initElements(driver, this);
	}
	public InteriorDesignDetailPage clickViewMoreDetails() {
		driver.findElementByXPath("//*[@resource-id = 'interiorName']").click();
		return new InteriorDesignDetailPage();
	}
	
	public InteriorDesignDetailPage clickInteriorPackageListCard() {
		interiorPackageListCard.click();
		return new InteriorDesignDetailPage();
	}
	public boolean interiorNameAndDescriptionSectionIsDisplayed() {
		return interiorNameAndDescriptionSection.isDisplayed();
	}
	
	public boolean interiorPackageListCardIsDisplayed() {
		return interiorPackageListCard.isDisplayed();
	}
	public boolean interiorImagesSectionIsDisplayed() {
		return interiorImagesSection.isDisplayed();
	}
	public boolean interiorDetailsSectionIsDisplayed() {
		return interiorDetailsSection.isDisplayed();
	}
	public boolean interiorNameIsDisplayed() {
		return interiorName.isDisplayed();
	}
	public boolean viewMoreDetailsLinkIsDisplayed() {
		return viewMoreDetailsLink.isDisplayed();
	}
	


}
