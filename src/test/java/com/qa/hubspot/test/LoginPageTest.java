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
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestAllureListener.class)
public class LoginPageTest
 {
     
	 Properties prop;
	 WebDriver driver;

	 BasePage basepage;
	 LoginPage loginpage;
	
	
	 @BeforeTest
	public void setUp() {
		
		basepage = new BasePage();
		prop =basepage.init_prop();
		driver =basepage.init_driver(prop);	
		
		loginpage= new LoginPage(driver);
		
	}
	
	@Test(priority =1, description = "verify loginpage titletest")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify login page title on login page")
	@Story("Story Name : To check login page title")
	public void verifyLoginPageTitleTest() {
		
	String title =	loginpage.getLoginPageTitle();
	System.out.println("login page title is"+title);
	
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Title not matched...");
	
   }
	
	
	
	
	@Test(priority =2, description = "verify Signup Link Test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify login page title on login page")
	@Story("Story Name : To check login page title")
	public void  verifySignUpLinkTest()
	{
		loginpage.checkSignUpLink();
		Assert.assertTrue(loginpage.checkSignUpLink());
	}

	
	@Test(priority =1, description = "verify loginpage titletest")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify login page title on login page")
	@Story("Story Name : To check login page title")
	public void loginTest()
	{
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
