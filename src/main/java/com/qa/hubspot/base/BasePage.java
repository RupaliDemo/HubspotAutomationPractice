package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.TimeUtils;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	OptionsManager optionmanager;
	
//	ThrreadLocal concept used  for handling multithreading in Extent report 
// to take ScreenShot
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver  getDriver() {
		return tlDriver.get();
		
	}
	
	
	/**
	 * This method used to initialize the driver based on  browser
	 *
	 *
	 */
	public WebDriver init_driver(Properties prop) {
		
		optionmanager = new OptionsManager(prop);
	    String browser =prop.getProperty("browser");
		
		System.out.println("browser namw is: " +browser);
		
	
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver(optionmanager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionmanager.getChromeOptions()));
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionmanager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionmanager.getFirefoxOptions()));
		}
		else if (browser.equalsIgnoreCase("safari"))
		{
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
	    TimeUtils.mediumWait();
		
		
		return getDriver();
	}
	
	
	public Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public String getScreenshot() {
		
		
		File scr =((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path =System.getProperty("user.dir") +"/screenshots/" +System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(scr, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return path;
	}

}
