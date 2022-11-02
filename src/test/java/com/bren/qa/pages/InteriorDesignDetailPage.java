package com.bren.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;
import com.bren.qa.helper.SwipeHelper;

public class InteriorDesignDetailPage extends Base{
	@FindBy(xpath = "//*[@resource-id = 'InteriorPackageListCard']")
	public WebElement interiorPackageListCard;
	@FindBy(xpath = "//*[@text = 'Interior design details']")
	public WebElement pageHeading;
	@FindBy(xpath = "//*[@resource-id = 'InteriorImages']")
	public WebElement interiorImages;
	@FindBy(xpath = "//*[@resource-id = 'interiorDetails']")
	public WebElement interiorDetails;
	@FindBy(xpath = "//*[@resource-id = 'interiorNameAndDescription']")
	public WebElement interiorNameAndDescription;
	@FindBy(xpath = "//*[@resource-id = 'iamInterested']")
	public WebElement iamInterestedBtn;
	@FindBy(xpath = "//*[@resource-id = 'imageDots']")
	public WebElement imageDots;
	@FindBy(xpath = "//*[@resource-id = 'dotsAndArrow']")
	public WebElement dotsAndArrow;
	@FindBy(className = "android.widget.HorizontalScrollView")
	public WebElement horizontalView;
	
	public InteriorDesignDetailPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void swipeInteriorImages () {
		int numberOfImages = imageDots.findElements(By.className("android.view.ViewGroup")).size() - 1;
		System.out.println(numberOfImages);
		System.out.println(driver.getPageSource());
		while(numberOfImages > 1) {
			SwipeHelper.swipe(interiorImages);
			numberOfImages--;
		}
		
	}
	public InteriorDesignConfirmationScreenPage clickIAmInterested() {
		iamInterestedBtn.click();
		return new InteriorDesignConfirmationScreenPage();
	}
	public boolean interiorImagesIsDisplayed() {
		return interiorImages.isDisplayed();
	}
	public boolean interiorPackageListCardIsDisplayed() {
		return interiorPackageListCard.isDisplayed();
	}
	public boolean interiorNameAndDescriptionIsDisplayed() {
		return interiorNameAndDescription.isDisplayed();
	}
	public boolean interiorDetailsIsDisplayed() {
		return interiorDetails.isDisplayed();
	}
	public boolean iamInterestedBtnIsDisplayed() {
		return iamInterestedBtn.isDisplayed();
	}
}
