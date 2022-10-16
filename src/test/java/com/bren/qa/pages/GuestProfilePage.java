package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class GuestProfilePage extends Base {
	@FindBy(xpath = "//*[@text = 'Your profile']")
	public WebElement yourProfileTitle;
	@FindBy(xpath = "//*[@text = 'If you are Bren apartment owner, login with your registered mobile number to access your profile']")
	public WebElement yourProfileDescreption;
	@FindBy(xpath = "//*[@text = 'Login']")
	public WebElement loginButton;
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement getInTouchFormIcon;
	
	public GuestProfilePage() {
		PageFactory.initElements(driver, this);	
	}
	public LoginPage clickOnLoginButton() {
		loginButton.click();
		return new LoginPage();
	}
	
}
