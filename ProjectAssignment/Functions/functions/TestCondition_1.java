package functions;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utility.Utility;

import pageImplementation.homePageImpl;

public class TestCondition_1 {
	
	@BeforeClass
	public void teststart(){
		
	}
	
	@Test
	public void testmethod1() throws Exception{
		
		try{
		
		Utility utility = new Utility();
		
		homePageImpl hp = new homePageImpl();
		
		hp.launchURL(utility.getPropValue("URL"));
		hp.closethePopup1();
		hp.goToSection((utility.getPropValue("Section")));
		hp.shopByScreenSize((utility.getPropValue("ScreenSize")));
		hp.priceRangeSelect("20000","60000");
		hp.resolutionSelect();
		hp.getTotalNumberOfItemsShowing();
		hp.addThreeTVtoCompare();
		hp.compareItems();
		hp.selectMinimumPriceFromCompareList();
		hp.clickOnHelpCenter();
		hp.clickButtonPlaceOrder();
		System.out.println("Execution Completed");
		}
		
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Execution Failed");
			Assert.fail();
		}
	}
	
	
	
	
	
	
	
	

}
