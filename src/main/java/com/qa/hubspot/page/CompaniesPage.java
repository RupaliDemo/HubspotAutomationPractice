package com.qa.hubspot.page;

import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtils;
import com.qa.hubspot.utils.JavaScriptUtil;
import com.qa.hubspot.utils.TimeUtils;

public class CompaniesPage extends BasePage {

	WebDriver driver;
	ElementUtils elementUtils;
	JavaScriptUtil jsUtil;
	TimeUtils timeutils;
	
	
	
	

	public CompaniesPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		
	}



	public String getCompaniesPageTitle()
	{
		            
		return driver.getTitle();
		     
	}
	
	
}
