package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listners.TestAllureListener;
import com.qa.hubspot.page.CompaniesPage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.utils.Constants;

@Listeners(TestAllureListener.class)

public class CompaniesPageTest {
	
	 Properties prop;
	 WebDriver driver;

	 BasePage basepage;
	 LoginPage loginpage;
	 HomePage homepage;
	 ContactsPage contactpage;
	 CompaniesPage companiespage;
	 
	 @BeforeTest
		public void setUp() {
			
			basepage = new BasePage();
			prop =basepage.init_prop();
			driver =basepage.init_driver(prop);	
		
			loginpage= new LoginPage(driver);
			homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		    
			contactpage = homepage.goToContactsPage();
			companiespage= contactpage.goToCompaniesPage();
			

		}
	 
	 @Test(priority =1)
	 public void verifyCompaniesPageTitleTest() {
		String title = companiespage.getCompaniesPageTitle();
		System.out.println("Companies Page Title is :"+title);
		Assert.assertEquals(title,Constants.COMPANIES_PAGE_TITLE);
	 }
	 
	 
	 @AfterTest
		public void teardown() {
			driver.quit();
		}

}
