package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class TicketCreationSuccessPage extends Base {

	@FindBy(xpath = "//*[@text = 'Support Ticket Created!']")
	public WebElement supportTicketCreatedTitle;
	@FindBy(xpath = "//*[@text = 'We have received your ticket. Please expect a response within 24-48 hrs.']")
	public WebElement ticketCreatedDescreption;
	@FindBy(xpath = "//*[@text = 'View ticket']")
	public WebElement viewTicketButton;
	@FindBy(xpath = "//*[@content-desc = 'Go back']")
	public WebElement goBackButton;
	public TicketCreationSuccessPage() {
		PageFactory.initElements(driver, this);
	}
	public CreateTicketPage clickBackButton() {
		goBackButton.click();
		return new CreateTicketPage();
	}
}
