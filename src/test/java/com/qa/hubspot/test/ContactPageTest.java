package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listners.TestAllureListener;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtils;
@Listeners(TestAllureListener.class)
public class ContactPageTest {


	 Properties prop;
	 WebDriver driver;

	 BasePage basepage;
	 LoginPage loginpage;
	 HomePage homepage;
	 ContactsPage contactpage;
	
	 @BeforeTest
	public void setUp() {
		
		basepage = new BasePage();
		prop =basepage.init_prop();
		driver =basepage.init_driver(prop);	
	
		loginpage= new LoginPage(driver);
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		contactpage= homepage.goToContactsPage();
	}
	 
	 @Test(priority =1)
	 public void verifyContactPageTitleTest() {
		String title = contactpage.getContactsPageTitle();
		System.out.println("Contact Page Title is :"+title);
		Assert.assertEquals(title,Constants.CONTACTS_PAGE_TITLE );
	 }
	 
	 
	 @DataProvider
	 public Object[][] getContactsTeatData() {
		Object data[][] =ExcelUtils.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	 }
	 
	 
	 @Test(priority =2,dataProvider ="getContactsTeatData")
	 public void createNewContactTest(String email,String firstname,String lastname,String jobtitle) {
	 String name =	 contactpage.createNewContact(email,firstname,lastname,jobtitle); 
		 
		 Assert.assertEquals(name, firstname+" " +lastname);
	 }
	 
	 
	 @AfterTest
		public void teardown() {
			driver.quit();
		}
}
