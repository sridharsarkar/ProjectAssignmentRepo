package pageImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.utility.Utility;

import page.homePage;

public class homePageImpl extends Utility implements homePage {

	WebDriver driver=new ChromeDriver();
	Utility utility = new Utility();

	By closeLoginPopup = By.xpath("//button[@class='_2AkmmA _29YdH8']");
	By minPriceRange = By.xpath("//div/span[contains(text(),'Price')]/../../following-sibling::div[3]/div[1]/select[1]");
	By maxPriceRange = By.xpath("//div/span[contains(text(),'Price')]/../../following-sibling::div[3]/div[3]/select[1]");
	By buttonResolution = By.xpath("//div/div[contains(text(),'Resolution')]");
	By checkBoxUltra = By.xpath("//label[div[contains(text(),'Ultra HD (4K)')] and ./input/@type='checkbox']");
	By totalNumberOfProducts = By.xpath("//span[contains(text(),'Showing')][contains(text(),'products')]");
	By compareButton = By.xpath("//span/span[contains(text(),'COMPARE')]");
	By priceList = By.xpath("//div[contains(@class,'col col-3-12')]/div/div/div[1]");
	By selectAddToCartOfTheLowestAmount = By.xpath("//div/div/div/button");
	By clickOnHelpCenter = By.linkText("Help Center");
	By preOrderAndForthcoming = By.xpath("//div/div/p[contains(text(),\"What does 'Preorder' or 'Forthcoming' mean?\")]");
	By helpfulYesButton = By.xpath("//div/span[contains(text(),'Was this helpful?')]/following-sibling::div/button[contains(text(),'Yes')]");
	By buttonPlaceOrder = By.xpath("//button/span[contains(text(),'Place Order')]");
	
	

	@Override
	public void launchURL(String URL) throws Exception {
		
		try{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.manage().window().maximize();
		}
		catch(Exception e ){
			e.printStackTrace();
			driver.close();
			Assert.fail();
		}
	}

	@Override
	public void closethePopup() {
		utility.waitForPageLoad(driver,closeLoginPopup);
		driver.findElement(closeLoginPopup).click();
	}
	
	@Override
	public void closethePopup1() {
		utility.waitForPageLoad(driver,closeLoginPopup);
		Actions action= new Actions(driver);
		WebElement ele = driver.findElement(closeLoginPopup);
		action.moveToElement(ele).click().perform();
	}
	
	@Override
	public void goToSection(String section) {
		
		String xpath = "//span[contains(text(),'**')]";
		
		/*String replacedXpath = xpath.replace("**", section);
		By finalXpath = By.xpath(replacedXpath);
		driver.findElement(finalXpath).click();*/
		
		driver.findElement(xpathAccordingToUserInputValue(xpath,section)).click();
	}

	@Override
	public void shopByScreenSize(String size) {
		
		String xpath = "//li[contains(text(),'Shop by Screen Size')]/following::li/a[contains(text(),'**')]";
		/*String replacedXpath = xpath.replace("**", subSection);
		By finalXpath = By.xpath(replacedXpath);
		utility.waitForPageLoad(driver,finalXpath);
		driver.findElement(finalXpath).click();*/
		utility.waitForPageLoad(driver,xpathAccordingToUserInputValue(xpath,size));
		driver.findElement(xpathAccordingToUserInputValue(xpath,size)).click();
		
		
	}

	@Override
	public void priceRangeSelect(String min, String max) throws InterruptedException {
		
		WebElement eleMin = driver.findElement(minPriceRange);
		WebElement eleMax = driver.findElement(maxPriceRange);
		utility.selectDropDownByValue(eleMin,min);
		utility.waitForPageLoad(driver,maxPriceRange);
		TimeUnit.SECONDS.sleep(3);
		utility.selectDropDownByValue(eleMax,max);
		
	}
	
	@Override
	public void resolutionSelect() throws InterruptedException {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1001)");
		
		driver.findElement(buttonResolution).click();
		driver.findElement(checkBoxUltra).click();
		/*if(!utility.isElementSelected(driver, checkBoxUltra)){
			driver.findElement(checkBoxUltra).click();
		}*/
		
	}
	
	

	@Override
	public void getTotalNumberOfItemsShowing() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement ele = driver.findElement(totalNumberOfProducts);
		String totalNumberProducts = ele.getText();
		System.out.println(totalNumberProducts);
	}
	
	@Override
	public void addThreeTVtoCompare() throws NoSuchElementException {
		List<WebElement> li = new ArrayList<WebElement>(); 
		li = driver.findElements(By.xpath("//label/span[contains(text(),'Add to Compare')]/../preceding-sibling::span/div/label/div"));
		int size = li.size();
		
		if (size>=3){
			for (int i=0;i<3;i++){
				li.get(i).click();
			}
		}
			
		
	}
	
	@Override
	public void compareItems() throws Exception {
		if(utility.isElementPresent(driver, compareButton)){
			driver.findElement(compareButton).click();
			//utility.getscreenshot(driver,"CompareScreenShot");
		}
	}

	public static String addCommasToNumericString(String digits) {
	    String result = "";
	    for (int i=1; i <= digits.length(); ++i) {
	        char ch = digits.charAt(digits.length() - i);
	        if (i % 3 == 1 && i > 1) {
	            result = "," + result;
	        }
	        result = ch + result;
	    }

	    return result;
	}
	
	
	public void selectAddToCartOfTheLowestAmount(int i) throws Exception {
		List<WebElement> li = new ArrayList<WebElement>();
			li = driver.findElements(selectAddToCartOfTheLowestAmount);
			if (li.size()>=1){
				for (int m = 0; m<li.size(); m++ ){
					if (m == i){
						li.get(m).click();
					}
				}
			}
	}
	

	
	@Override
	public void selectMinimumPriceFromCompareList() throws Exception {
		List<WebElement> li = new ArrayList<WebElement>(); 
		List<Integer> prices = new ArrayList<Integer>(); 
		List<String> StringPrices = new ArrayList<String>();
		List<String> CommaStringPrices = new ArrayList<String>();
		li = driver.findElements(priceList);
		//int size = li.size();
		li.remove(0);
		int i = 0;
		
		
		for(WebElement ele : li){
			String str = ele.getText();
			String fstr = str.substring(1);
			String ffstr = fstr.replace(",", "");
			StringPrices.add(ffstr);
			Integer integer = Integer.parseInt(ffstr);
			prices.add(integer);
		}
		
		Collections.sort(prices);
		//System.out.println(prices.get(0));
		String commaValue = addCommasToNumericString(prices.get(0).toString());
		//System.out.println(commaValue);
		
		for (String StrPrices : StringPrices ){
			CommaStringPrices.add((addCommasToNumericString(StrPrices)));
		}
		
		
		for (String sttt : CommaStringPrices){
			if(sttt.contains(commaValue)){
			i = CommaStringPrices.indexOf(sttt);
			//System.out.println("The Postion is: " + (i));
			}
		}
		
		selectAddToCartOfTheLowestAmount(i);
	}

	
	
	@Override
	public void clickOnHelpCenter() throws Exception {
		TimeUnit.SECONDS.sleep(5);
		utility.checkPageIsReady(driver);
		waitForPageLoad(driver, clickOnHelpCenter);
		String parentWindow = driver.getWindowHandle();
		driver.findElement(clickOnHelpCenter).click();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		utility.checkPageIsReady(driver);
		
		Set<String> windows = new HashSet<String>();
		windows = driver.getWindowHandles();
		
		for (String childWindow : windows){
			if (!childWindow.equals(parentWindow)){
				driver.switchTo().window(childWindow);
			}
		}
		
		utility.checkPageIsReady(driver);
		driver.findElement(preOrderAndForthcoming).click();
		
		if(utility.isElementPresent(driver, helpfulYesButton)){
			driver.findElement(helpfulYesButton).click();
		}
		
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.navigate().refresh();
	}
	
	@Override
	public void clickButtonPlaceOrder() throws Exception {
		utility.checkPageIsReady(driver);
		if(utility.isElementPresent(driver, buttonPlaceOrder)){
			driver.findElement(buttonPlaceOrder).click();
			utility.checkPageIsReady(driver);
		}
	}
	
}
