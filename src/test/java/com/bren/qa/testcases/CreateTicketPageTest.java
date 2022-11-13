package com.bren.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;
import com.bren.qa.pages.CreateTicketPage;
import com.bren.qa.pages.DocumentsPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.MyTicketsPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.TicketCreationSuccessPage;
import com.bren.qa.report.ExtentManager;

public class CreateTicketPageTest extends Base{
    LaunchPage launchPage;
    LoginPage loginPage;
    OtpVerificationPage otpVerificationPage;
    MultipleApartmentHomePage myHomePage;
    DocumentsPage docsPage;
    CreateTicketPage createTicketPage;
    TicketCreationSuccessPage ticketCreationSuccessPage;
    MyTicketsPage myTicketsPage;
    public CreateTicketPageTest() {
        super();
    }
    @BeforeMethod
    public void setup() throws MalformedURLException, InterruptedException {
        initialization();
        launchPage = new LaunchPage();
        loginPage = launchPage.clickSignInButton();
        otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
        Thread.sleep(8000);
        driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text = 'Enter OTP']");
        myHomePage = otpVerificationPage.inputOtpForMultupleApartmentAccount(prop.getProperty("multpleApartmentsOwnerOtp").toString());
        Thread.sleep(3000);
        createTicketPage = myHomePage.clickCreateTicketButton();
        
    }
    @Test(priority = 1)
    public void verifyTheDetailsOfTheScreenOpeningWhenUserClicksOnTheIconForTicketCreation() {
        Assert.assertTrue(createTicketPage.createATicketTab.isDisplayed(), "Create Ticket Tab isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Create Ticket Tab is Displayed");
        
        Assert.assertTrue(createTicketPage.myTicketsTab.isDisplayed(), "My-Ticket Tab isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "My-Tickets Tab is Displayed");
        
        Assert.assertTrue(createTicketPage.createATicketDesc.isDisplayed(), "Create A Ticket Descreption isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Create A Ticket Descreption  is Displayed");
        
        Assert.assertTrue(createTicketPage.ticketTitle.isDisplayed(), "Ticket Title isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Ticket Title is Displayed");
        
        Assert.assertTrue(createTicketPage.ticketTitleInputField.isDisplayed(), "Ticket Title Input Field isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Ticket Title Input Field is Displayed");
        
        Assert.assertTrue(createTicketPage.describeYourIssueTitle.isDisplayed(), "Describe Your Issue Title isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Describe Your Issue Title is Displayed");
        
        Assert.assertTrue(createTicketPage.describeYourIssueInputField.isDisplayed(), "Describe Your Issue Input field isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Describe Your Issue Input Field is Displayed");
        
        Assert.assertTrue(createTicketPage.issueTypeTitle.isDisplayed(), "Issue Type Title isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Issue Type Title is Displayed");
        
        Assert.assertTrue(createTicketPage.issueTypeDropDownList.isDisplayed(), "Issue Type Drop Down List isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Issue Type Drop Down List is Displayed");
    
        Assert.assertTrue(createTicketPage.unitNameTitle.isDisplayed(), "Unit Name Title isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Unit Name Title is Displayed");
        
        Assert.assertTrue(createTicketPage.unitNameDropDownList.isDisplayed(), "Unit Name Drop Down List isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Unit Name Drop Down List is Displayed");
        
        ScrollHelper.scrollDown();
        Assert.assertTrue(createTicketPage.attachmentsTitle.isDisplayed(), "Attachments Title isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Attachments Title is Displayed");
    
        Assert.assertTrue(createTicketPage.chooseFileButton.isDisplayed(), "Choose File Button isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Choose File Button is Displayed");
        ScrollHelper.scrollDown();
        Assert.assertTrue(createTicketPage.createATicketButton.isDisplayed(), "Create A Ticket Button isn't Displayed");
        ExtentManager.getExtentTest().log(Status.PASS, "Create A Ticket Button is Displayed");
    }
    @Test(priority = 2)
    public void verifyThatTheUserCanEditTheTicketTitleAndDescreptionField() {
        createTicketPage.ticketTitleInputField.sendKeys("ticket-title");
        Assert.assertTrue(createTicketPage.ticketTitleInputField.getAttribute("text").length() > 0, "Ticket Title Input field isn't Editable");
        ExtentManager.getExtentTest().log(Status.PASS, "Ticket Title Input field is editable");
        
        createTicketPage.describeYourIssueInputField.sendKeys("ticket-descreption");
        Assert.assertTrue(createTicketPage.describeYourIssueInputField.getAttribute("text").length() > 0, "Describe-your-issue Input field isn't Editable");
        ExtentManager.getExtentTest().log(Status.PASS, "Describe your issue Input field is editable");
    }
    @Test(priority = 3)
    public void verifyTicketTitleDescriptionAndUnitNameFieldsInTheFormAreMandatory() throws InterruptedException {
        Thread.sleep(3000);
        ScrollHelper.scrollDown();
        createTicketPage.createATicketButton.click();
        Assert.assertTrue(createTicketPage.pleaseEnterIssueTitleValidationMessage.isDisplayed(), "Issue Title isn't showing as Mandatory");
        ExtentManager.getExtentTest().log(Status.PASS, "Issue Title is showing as Mandatory");
        
        Assert.assertTrue(createTicketPage.pleaseEnterIssueDescriptionValidationMessage.isDisplayed(), "Issue Descreption isn't showing as Mandatory");
        ExtentManager.getExtentTest().log(Status.PASS, "Issue Descreption is showing as Mandatory");
        ScrollHelper.scrollDown();
        Assert.assertTrue(createTicketPage.pleaseSelectUnitValidationMessage.isDisplayed(), "Unit Name isn't showing as Mandatory");
        ExtentManager.getExtentTest().log(Status.PASS, "Unit Name is showing as Mandatory");
    }
    
    @Test(priority = 4)
    public void verifyTicketCreation() throws IOException, InterruptedException {
//      Ticket successfully created
        String expectedToastMessage = "Ticket successfully created";
        createTicketPage.ticketTitleInputField.sendKeys("ticket-title");
        createTicketPage.describeYourIssueInputField.sendKeys("descreption");
        createTicketPage.issueTypeDropDownList.click();
        createTicketPage.issueTypeDropDownListElement.click();
        createTicketPage.unitNameDropDownList.click();
        createTicketPage.unitNameDropDownListElement.click();
        ScrollHelper.scrollDown();
        createTicketPage.chooseFileButton.click();
        // upload file start
        driver.findElementByXPath("//*[@text = 'Camera']").click();
        driver.findElementByXPath("//*[@text = 'WHILE USING THE APP']").click();
        Thread.sleep(5000);
        driver.findElementByXPath("//*[@content-desc = 'Shutter']");
        driver.findElementByXPath("//*[@content-desc = 'Shutter']").click();
        driver.findElementByXPath("//*[@content-desc = 'Done']").click();
        driver.findElementByXPath("//*[@text = 'Unit name']");
        ScrollHelper.scrollDown();
        ticketCreationSuccessPage = createTicketPage.clickCreateATicketButton();
        Assert.assertTrue(ticketCreationSuccessPage.supportTicketCreatedTitle.isDisplayed(), "Not redirecting to Ticket Creation Successful screen");
        ExtentManager.getExtentTest().log(Status.PASS, "Redirecting to Ticket Creation Successful screen");
    }
    @Test(priority = 5)
    public void verifyANewlyCreatedTicketIsAddedIntoTheList() throws InterruptedException {
        createTicketPage.ticketTitleInputField.sendKeys("ticket-title");
        Thread.sleep(2000);
        String inputTicketTitle = createTicketPage.ticketTitleInputField.getAttribute("text");
        createTicketPage.describeYourIssueInputField.sendKeys("descreption");
        createTicketPage.issueTypeDropDownList.click();
        createTicketPage.issueTypeDropDownListElement.click();
        createTicketPage.unitNameDropDownList.click();
        createTicketPage.unitNameDropDownListElement.click();
        ScrollHelper.scrollDown();
        ticketCreationSuccessPage = createTicketPage.clickCreateATicketButton();
        createTicketPage = ticketCreationSuccessPage.clickBackButton();
        myTicketsPage = createTicketPage.clickMyTicketsTab();
        Thread.sleep(3000);
        createTicketPage.pullToRefresh();
        Thread.sleep(6000);
        WebElement ticketSubjectElement = driver.findElementsByXPath("//*[@resource-id = 'ticketSubject']").get(0);
        String ticketSubject = ticketSubjectElement.findElement(By.className("android.widget.TextView")).getAttribute("text");
        Assert.assertEquals(ticketSubject, inputTicketTitle, "Newly Created Ticket Isn't Added into The list");
        ExtentManager.getExtentTest().log(Status.PASS, "Verified that Newly Created Ticket Is Added into The list");
        System.out.print(ticketSubject);

        Thread.sleep(3000);

    }
    @Test(priority = 6 )
    public void verifyThatTheMyTicketsScreenContainsTheListOfTheTicketsCreated() throws InterruptedException, IOException {
        createTicketPage.myTicketsTab.click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElementByXPath("//*[@resource-id='ticketCard']").isDisplayed(), "The MyTickets Screen doesnt contains The List of the Tickets created");
        ExtentManager.getExtentTest().log(Status.PASS, "The MyTickets Screen  contains The List of the Tickets created");
        
    }
    @AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}