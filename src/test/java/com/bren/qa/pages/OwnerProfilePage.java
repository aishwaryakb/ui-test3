package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class OwnerProfilePage extends Base {
	@FindBy(xpath = "//*[@text = 'Name']")
	public WebElement nameTitle;
	@FindBy(xpath = "//android.widget.EditText[@index = '2']")
	public WebElement nameInputField;
	@FindBy(xpath = "//*[@text = 'Email address']")
	public WebElement emailAddressTitle;
	@FindBy(xpath = "//android.widget.EditText[@index = '4']")
	public WebElement emailInputField;
	@FindBy(xpath = "//*[@text = 'Phone number']")
	public WebElement phoneNumberTitle;
	@FindBy(xpath = "//android.widget.EditText[@index = '7']")
	public WebElement mobileInputField;
	@FindBy(xpath = "//android.widget.EditText[@index = '6']")
	public WebElement countryCodeInputField;
	@FindBy(xpath = "//*[@text = 'Log out']")
	public WebElement logOutButton;
	@FindBy(xpath = "//android.widget.ImageView[@resource-id = 'RNE__Image']")
	public WebElement profilePicture;
	
	public OwnerProfilePage() {
		PageFactory.initElements(driver, this);
	}
}