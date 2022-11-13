package com.bren.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;

public class DifferentDocumentCategoriesPage extends Base{

	@FindBy(xpath = "//*[@text = 'Documents']")
	WebElement documentsTitle;
	@FindBy(xpath = "//*[@resource-id = 'categoryName']")
	WebElement categoryName;
	@FindBy(xpath = "//*[@resource-id = 'numberOfDocuments']")
	WebElement numberOfDocuments;

	public DifferentDocumentCategoriesPage() {
		PageFactory.initElements(driver, this);
	}
	
	public DocumentsPage clickDocument() {
		categoryName.click();
		return new DocumentsPage();
	}
	public DifferentDocumentCategoriesPage clickNextDocument() {
        categoryName.click();
        return new DifferentDocumentCategoriesPage();
    }
}
