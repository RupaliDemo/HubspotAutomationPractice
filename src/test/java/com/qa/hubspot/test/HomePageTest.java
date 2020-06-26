package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listners.TestAllureListener;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestAllureListener.class)
public class HomePageTest {
	
	 Properties prop;
	 WebDriver driver;

	 BasePage basepage;
	 LoginPage loginpage;
	 HomePage homepage;
	
	 @Parameters("browser")
	 @BeforeTest
	public void setUp() {
		
		basepage = new BasePage();
		prop =basepage.init_prop();
		driver =basepage.init_driver(prop);	
	
		loginpage= new LoginPage(driver);
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	
	 @Test(priority =1, description = "verify Homepage title test")
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Home page title on login page")
		@Story("Story Name : To check login page title")
	 public void VerifyHomePageTitleTest() {
		String title = homepage.getHomePageTitle();
		
		System.out.println("Home Page Title is :"+title) ;
		
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		 
	 }
	 
	 
	 @Test(priority =2, description = "verify HomePage Header test")
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Home page header on login page")
		@Story("Story Name : To check home page header")
	 public void VerifyHomePageHeader() {
		String header= homepage.getHomePageHeader();
		System.out.println("Home page header is:" +header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
		
	 }
	 
	 @Test(priority =3, description = "verify login in user name")
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify logged In user")
		@Story("Story Name : To check login page title")
	
	 public void VerifyLoggedInUserTest() {
		String accountName = homepage.getAccountName();
		System.out.println("LoggedIn User is :" +accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	 }

	 
	 
	 
	 @AfterTest
		public void teardown() {
			driver.quit();
		}
}
