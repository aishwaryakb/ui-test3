package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class HowToEarnRewardsPage extends Base {
	
	@FindBy(xpath = "//*[@text = 'Step 1: Refer people to buy an apartment with Bren']")
	public WebElement referAFriendstep1;
	@FindBy(xpath = "//*[@text = 'Step 2: Bren customer support will get in touch with them']")
	public WebElement referAFriendstep2;
	@FindBy(xpath = "//*[@text = 'Step 3: Earn a reward when the referred person buys an apartment']")
	public WebElement referAFriendstep3;
	@FindBy(xpath = "//*[@text = 'How to earn rewards']")
	public WebElement howToEarnRewardsTitle;
	
	
	public HowToEarnRewardsPage() {
		PageFactory.initElements(driver, this);
	}
}