package com.qa.hubspot.test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
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
	 Logger log = Logger.getLogger(LoginPageTest.class);
		

	 BasePage basepage;
	 LoginPage loginpage;
	
	
	 @BeforeTest
	public void setUp() {
		
		basepage = new BasePage();
		prop =basepage.init_prop();
		
		driver =basepage.init_driver(prop);	
		
		loginpage= new LoginPage(driver);
		log.info("Intializing login page");
	}
	
	@Test(priority =1, description = "verify loginpage titletest")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify login page title on login page")
	@Story("Story Name : To check login page title")
	public void verifyLoginPageTitleTest() {
		log.info("*************************Starting test case*****************************");
		log.info("****************Hub Spot Login Page  Test ******************");
		
	String title =	loginpage.getLoginPageTitle();
	System.out.println("login page title is"+title);
	log.info("login page Title is :-" +title);
	
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Title not matched...");
	
	
	log.info("***************Ending Test Case ***************");
	log.info("*******Hub Spot Login Test ***********************");
	
   }
	
	
	
	
	@Test(priority =2, description = "verify Signup Link Test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify login page title on login page")
	@Story("Story Name : To check login page title")
	public void  verifySignUpLinkTest()
	{
		log.info("*************************Starting VerifySignUP Link test case*****************************");
		log.info("****************Hub Spot VerifySignUp Link Page  Test ******************");
		loginpage.checkSignUpLink();
		Assert.assertTrue(loginpage.checkSignUpLink());
		
		log.info("************************* Ending VerifySignUP Link test case*****************************");
		log.info("****************Hub Spot VerifySignUp Link Page  Test ******************");
		
	}

	
	@Test(priority =1, description = "verify loginpage titletest")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify login page title on login page")
	@Story("Story Name : To check login page title")
	public void loginTest()
	{
		log.info("*************************Starting login test case*****************************");
		log.info("****************Hub Spot loginpage  Test ******************");
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		log.info("*************************Starting login test case*****************************");
		log.info("****************Hub Spot login test Page  Test ******************");
	}
	
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
		
		log.info("*************browser closed ******************");
	}
}
