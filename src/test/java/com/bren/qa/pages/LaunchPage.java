package com.bren.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;
import com.bren.qa.helper.SwipeHelper;

public class LaunchPage extends Base {
	@FindBy(className ="android.widget.ImageView")
	WebElement brenLogo;
	@FindBy(xpath ="//*[@text = 'Sign in']")
	WebElement signInButton;
	@FindBy(xpath ="//*[@text = 'Continue as guest']")
	public WebElement continueAsGuestButton;
	@FindBy(xpath = "//*[@resource-id = 'imageDots']")
    public WebElement imageDots;
	public LaunchPage() {
		PageFactory.initElements(driver, this);
	}
	public boolean validateLogoDisplayed() {
		return brenLogo.isDisplayed();
	}
	public boolean validateSignInPageDisplayed() {
		return signInButton.isDisplayed();
	}
	public boolean validateContinueAsGuestDisplayed() {
		return signInButton.isDisplayed();
	}
	public LoginPage clickSignInButton() throws InterruptedException {
		Thread.sleep(5000);
		signInButton.click();
		return new LoginPage();
	}
	public GuestHomePage clickContinueAsGuest() {
		continueAsGuestButton.click();
		return new GuestHomePage();
	}
	public void swipeLaunchScreenImages() {
	    int numberOfImages = imageDots.findElements(By.className("android.view.ViewGroup")).size() - 1;
        System.out.println(numberOfImages);
        System.out.println(driver.getPageSource());
        while(numberOfImages > 1) {
            SwipeHelper.swipe(brenLogo);
            numberOfImages--;
        }
	    
	}
}
