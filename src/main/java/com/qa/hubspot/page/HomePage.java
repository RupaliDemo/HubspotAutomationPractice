package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;

public class HomePage extends BasePage{
	WebDriver driver;
	ElementUtils elementUtils;
	
	By header = By.cssSelector("h1.dashboard-selector__title");
	By accountName =By.cssSelector("span.account-name");
	
	// contact page
	By contactLinkprimery = By.id("nav-primary-contacts-branch");
    By contactLinksecondary = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		
	}
	
	public String getHomePageTitle() {
		return elementUtils.WaitForTitleTobePresence(Constants.HOME_PAGE_TITLE, 10);
		
	}
	
	public String getHomePageHeader()
	{
		if(elementUtils.doIsDisplayed(header))
			{
				return elementUtils.dogetText(header);
			}
		return null;
		
			
	}
	
	public String getAccountName()
	{
		
		return driver.findElement(accountName).getText();
			
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
		
	}
	
	private void clickOnContacts() {
		elementUtils.waitForElementToBePresent(contactLinkprimery,10);
		driver.findElement(contactLinkprimery).click();
		//elementUtils.doClick(contactLinkprimery);
		elementUtils.waitForElementToBePresent(contactLinksecondary, 5);
		driver.findElement(contactLinksecondary).click();
		//elementUtils.doClick(contactLinksecondary);
	}
}
