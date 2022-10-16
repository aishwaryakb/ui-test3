package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;
import com.bren.qa.helper.TapHelper;

public class RewardsPage extends Base {
	@FindBy(xpath ="//*[@text='Your referrals']")
	public WebElement yourReferralsTitle;
	@FindBy(xpath ="//*[@text='Refer a friend']")
	public WebElement referAFriendButton;
	@FindBy(xpath ="//*[@content-desc='button']")
	public WebElement referAFriendButton1;
	@FindBy(xpath ="//*[@resource-id='referralCard']")
	public WebElement referralCard;
	@FindBy(xpath ="//*[@content-desc='questionMark']")
	public WebElement yourReferralsHelpIcon;
	
	@FindBy(xpath ="//*[@content-desc='name']")
	public WebElement nameInReferralCard;
	
	@FindBy(xpath ="//*[@content-desc='mobileNumber']")
	public WebElement mobileNumberInReferralCard;
	
	@FindBy(xpath ="//*[@content-desc='email']")
	public WebElement emailIdInReferralCard;
	
	@FindBy(xpath ="//*[@text='Project:']")
	public WebElement projectTitleInReferralCard;
	
	@FindBy(xpath ="//*[@text='Status:']")
	public WebElement statusTitleInReferralCard;
	@FindBy(xpath = "//*[@content-desc = 'supportIcon']")
	public WebElement createTicketIcon;
	
	public RewardsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public ReferAndEarnFormPage clickReferAFriendButton() {
		TapHelper.tapElementAt(referAFriendButton, 0.8, 0.6);
		return new ReferAndEarnFormPage();
	}
	public HowToEarnRewardsPage clickOnYourReferralsIcon() {
		yourReferralsHelpIcon.click();
		return new HowToEarnRewardsPage();
	}
}
