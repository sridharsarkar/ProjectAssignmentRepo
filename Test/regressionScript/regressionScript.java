package regressionScript;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import functions.TestCondition_1;

public class regressionScript {

	@Test
	public void test1() throws IOException{
		
		try{

			TestCondition_1 testCondition_1 = new TestCondition_1();

			String chromefilePath = System.getProperty("user.dir")+ "/Resources/Drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromefilePath);

			testCondition_1.testmethod1();

		}
		catch (Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}

}
