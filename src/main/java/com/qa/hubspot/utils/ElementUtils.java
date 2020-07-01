package com.qa.hubspot.utils;


	import java.util.ArrayList;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class ElementUtils {

		
		WebDriver driver;
		JavaScriptUtil jsutils;
		
		public ElementUtils(WebDriver driver)
		{
			this.driver = driver;
			
		}
		
		public WebElement getElement(By locator)
		{
			 WebElement element = driver.findElement(locator);
			// jsutils.flash(element);
			 
			 return element;
		
		}
		
		public void doSendKey(By locator, String value) {
			
			getElement(locator).sendKeys(value);
			
		}
		
		public boolean doIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}
		
		public void doActionSendKey(By locator,String value) {
			Actions action = new Actions(driver);
			
		    WebElement element=	getElement(locator);
			action.sendKeys(element, value).build().perform();;
			
		}
		/**Use Actions class to to Handle Element is Not Clickable at Point Exception in Selenium Webdriver 
		 */
		
		public void doActionClick(By locator) {
			Actions action = new Actions(driver);
			
		    WebElement element=	getElement(locator);
			action.click(element).build().perform();
		}
		
		
		public void doClick(By locator) {
			getElement(locator).click();
			
		}
		
		public String dogetText(By locator) {
			
			return getElement(locator).getText();
			
		}
		// drop down Util method
		
		public void getDropDownValues(By locator)
		{
			Select select = new Select(getElement(locator));
			List<WebElement>optionlist = select.getOptions();
			System.out.println("Total value in month drop down is -->" +optionlist.size());
			for(int i =0;i<optionlist.size();i++)
			{
				System.out.println(optionlist.get(i).getText());
			}
			
		}
		
		
		public  void SelectDropDown(By locator , String value) {
			  
	  		Select select = new Select(getElement(locator));
	  		select.selectByVisibleText(value);
		}

		public void SelectDropDown(By locator , int index) {
	  
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}

		public  void SelectDropDownValue(By locator , String index) {
	  
	  		Select select = new Select(getElement(locator));
	  		select.selectByValue(index);
		}
		
 
	
		
		public  void  SelectDropDownVal( By locator , String value) {
			
			Select select = new Select(getElement(locator));
			
			List<WebElement> monthlist = select.getOptions();
			
			for(int i =0;i< monthlist.size();i++)
			{
				
				 String  text = monthlist.get(i).getText();
				 if(text.equalsIgnoreCase( value))
				 {
					 monthlist.get(i).click();
					 break;
				 }
				 
			}
	}
		
		/**
		 *  Method to select drop down option without select class using xpath Z& CSS selector
		 */
	 
		public  void doDropDownSelectWithoutselectclass( String locator, String locatorvalue , String Value) {
			
			List<WebElement> valuelist= null;
			if(locator.equalsIgnoreCase("xpath"))
			{
	          valuelist =driver.findElements(By.xpath(locatorvalue));
	          
	        }else 
	        	if(locator.equalsIgnoreCase("css"))
	        {
	        	valuelist =driver.findElements(By.cssSelector(locatorvalue));
	        }
			
			System.out.println(valuelist.size());	
			
			for(int i =0;i< valuelist.size();i++)
			{
				 
				 String  text = valuelist.get(i).getText();
				 if(text.equalsIgnoreCase( Value))
				 {
					 valuelist.get(i).click();
					 break;
				 }
				 
			}
		}
			 
		
		public  ArrayList<String> getDropDownValuesR(By locator) 
			 { 
				 
				 ArrayList<String> ar = new ArrayList<String>();
				 
				  Select select =new Select(getElement(locator)); 
				  List<WebElement>optionlist = select.getOptions();
				  System.out.println("Total value in month drop down is -->" +optionlist.size()); 
				  for(int i =0;i<optionlist.size();i++) 
				  {
					  //System.out.println(optionlist.get(i).getText());
					  ar.add(optionlist.get(i).getText());
					  
				  }
			         return ar;
			  } 

	     // wait costom methods : wit utils
		
		
		public void VisibilityOfAllElements(List<WebElement> elements, int timeout) {
			
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			 
			 
		}
		
		public WebElement waitForElementToBePresent(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return getElement(locator);
		}


		



		public WebElement WaitForElementTobeClickable(By locator ,int timeout) {
			 
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.elementToBeClickable(locator));
			 return getElement(locator);
		 }

		public WebElement WaitForElementVisibilityOfElementLocated(By locator,int timeout ) {
			 
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			 return getElement(locator);
		 }
		
		public void ClickWhenReady(By locator,int timeout ) {
			 
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.elementToBeClickable(locator));
			 getElement(locator).click();
		 }
		
		public String WaitForUrl(String url,int timeout ) {
			 
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.urlContains(url));
			 return driver.getCurrentUrl();
		 }
		
		public boolean WaitAlertToBePresent(int timeout ) {
			 
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.alertIsPresent());
			 return true;
		 }
		
		
		public WebElement WaitForElementTobeVisible(By locator ,int timeout) {
			 
			 WebElement element = getElement(locator);
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.visibilityOf(element));
			 return element;
		 
		}
		
		
			public String WaitForTitleTobePresence(String title ,int timeout) {
			 
			 WebDriverWait wait = new WebDriverWait(driver, timeout);
		   //  wait.until(ExpectedConditions.textToBePresentInElementLocated(getElement(By locator), title));
			 wait.until(ExpectedConditions.titleContains(title));
			 return driver.getTitle();
		 }
		
		
		
	}




