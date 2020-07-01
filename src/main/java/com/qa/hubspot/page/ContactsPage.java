package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;
import com.qa.hubspot.utils.JavaScriptUtil;
import com.qa.hubspot.utils.TimeUtils;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	JavaScriptUtil jsUtil;
	TimeUtils timeutils;
	
	By creatcontacts = By.xpath("(//span[text()='Create contact'])[1]");
	By createcontactsform = By.xpath("(//span[text()='Create contact'])[2]");
	By createanothercontact = By.xpath("//i18n-string[text() ='Create and add another']");
	By contactLinkprimery = By.id("nav-primary-contacts-branch");
	
	By Contactemail = By.xpath("//input[@data-field='email']");
	By Contactfname = By.xpath("//input[@data-field ='firstname']");
	By Contactlname = By.xpath("//input[@data-field ='lastname']");
	By Contactjobtitle = By.xpath("//input[@data-field ='jobtitle']");
	By contactsNavigationLink = By.xpath("(//i18n-string[text()='Contacts'])[2]");
	
	// Companies Page
	
	By Companies = By.xpath("//a[@id ='nav-secondary-companies']");
	
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		
	}
	

	
	public String getContactsPageTitle()
	{
		return driver.getTitle();
	
	}
	
	
	public String createNewContact(String email, String fname, String lname,String jobtitle) {
		elementUtils.WaitForElementTobeClickable(creatcontacts, 10);
	//	elementUtils.doClick(creatcontacts);
		elementUtils.doActionClick(creatcontacts);
		
		elementUtils.waitForElementToBePresent(Contactemail, 5).sendKeys(email);;
		
		elementUtils.waitForElementToBePresent(Contactfname, 5).sendKeys(fname);
		elementUtils.waitForElementToBePresent(Contactlname, 5).sendKeys(lname);
		elementUtils.waitForElementToBePresent(Contactjobtitle, 5).sendKeys(jobtitle);
		
		elementUtils.WaitForElementTobeClickable(createcontactsform, 10);
		
		 //jsUtil.clickElementByJS(elementUtils.getElement(createcontactsform));
		
		elementUtils.doActionClick(createcontactsform);
		
		
		  String fullname = fname+ " "+lname ; String nameXpath
		  ="(//span[text()='"+fullname+"'])[2]";
		  
		  elementUtils.waitForElementToBePresent(contactsNavigationLink, 10);
		  
		  String contactname =elementUtils.dogetText(By.xpath(nameXpath)).trim();
		  elementUtils.doActionClick(contactsNavigationLink);
		  
		  //elementUtils.doClick(contactsNavigationLink);
		  
		  return contactname;
		 
		
	}
	
	public CompaniesPage goToCompaniesPage() {
		clickOnCompanies();
		return new CompaniesPage(driver);
		
	}
	
	
	private void clickOnCompanies() {
		elementUtils.waitForElementToBePresent(contactLinkprimery,10);
		driver.findElement(contactLinkprimery).click();
		//elementUtils.doClick(contactLinkprimery);
		elementUtils.waitForElementToBePresent(Companies, 5);
		driver.findElement(Companies).click();
		//elementUtils.doClick(contactLinksecondary);
	}
}

