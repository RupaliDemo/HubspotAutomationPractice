package com.qa.hubspot.listners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.qa.hubspot.base.BasePage;

public class ScreenshotListener  extends TestListenerAdapter{

	@Override
	public void onTestFailure(ITestResult result) {
		BasePage basepage = new BasePage();
		Calendar calendar =  Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		String methodName =result.getName();
		if(!result.isSuccess()) {
			
			File scr =((TakesScreenshot) basepage.getDriver()).getScreenshotAs(OutputType.FILE);
			String path =System.getProperty("user.dir") +"/failurescreenshots/" +System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(scr, destination);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		  
			
		}
	}
}
