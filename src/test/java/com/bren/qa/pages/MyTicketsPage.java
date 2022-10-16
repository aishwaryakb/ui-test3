package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class MyTicketsPage extends Base{

	@FindBy(xpath ="//*[resource-id = 'ticketCard']")
	public WebElement ticketCard;
	
	@FindBy(xpath = "//*[resource-id = 'ticketSubject']")
	public WebElement ticketSubject;
	public MyTicketsPage() {
		PageFactory.initElements(driver, this);	
	}
}
