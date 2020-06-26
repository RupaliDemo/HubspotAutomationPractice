package com.qa.hubspot.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtils;
import com.qa.hubspot.utils.TimeUtils;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	 WebDriver driver;
	 ElementUtils elementutils;
	
	//1. Locator declaration
	
	By username = By.id("username");
	By password = By.id("password");
	By login = By.id("loginBtn");
	By singuplink = By.linkText("Sign up");
			
	//2. Constructor of the page class
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		elementutils = new  ElementUtils(driver);
		
	}
	
	//3. Page action method
	@Step("Getting Login Page Title")
	public String getLoginPageTitle()
	{
		return driver.getTitle();
	}

	@Step("Veryfying SignUp lLink on login Page")
	public  boolean checkSignUpLink()
	{
		return driver.findElement(singuplink).isDisplayed();
	}
	@Step("Veryfying login with UserName:{0} and password:{1} step...")
	public HomePage doLogin(String un, String pass)
	{
		driver.findElement(username).sendKeys(un);
		driver.findElement(password).sendKeys(pass);
		
		driver.findElement(login).click();
	 	TimeUtils.mediumWait();
		return new HomePage(driver);
	}
}
