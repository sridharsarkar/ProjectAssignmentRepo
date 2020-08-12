package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.*; 

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utility {

	public static WebDriver driver;
	public static Properties prop;

	public Utility(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/com"
					+ "/demo/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void waitForPageLoad(WebDriver driver,By locator){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public String getPropValue(String value){
		String propvalue = prop.getProperty(value);
		return propvalue;

	}

	public By xpathAccordingToUserInputValue(String xpath,String passValue){

		String replacedXpath = xpath.replace("**", passValue);
		By finalXpath = By.xpath(replacedXpath);
		return finalXpath;

	}

	public void  selectDropDownByValue(WebElement ele,String value){
		Select select = new Select(ele);
		select.selectByValue(value);

	}

	public boolean isElementPresent(WebDriver driver, By by){
		try{
			waitForPageLoad(driver,by);
			if(driver.findElement(by).isDisplayed());
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
	}
	
	public boolean isElementSelected(WebDriver driver, By by){
		try{
			waitForPageLoad(driver,by);
			if(driver.findElement(by).isEnabled());
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
	}

	public void getscreenshot(WebDriver driver,String fileName) throws Exception 
	{

		String filePath = System.getProperty("user.dir")+ "/Resources/ScreenShots/";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		String finalFilePath = fileName +" "+date + ".png";
		FileUtils.copyFile(scrFile, new File( filePath + finalFilePath));
	}
	
	public void checkPageIsReady(WebDriver driver) {
		  
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  
		  
		  //Initially bellow given if condition will check ready state of page.
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		   //System.out.println("Page Is loaded.");
		   return; 
		  } 
		  
		  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
		  //You can replace your value with 25 If you wants to Increase or decrease wait time.
		  for (int i=0; i<25; i++){ 
		   try {
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		    break; 
		   }   
		  }
		 }


}
