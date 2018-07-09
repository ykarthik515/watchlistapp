package com.clari5.app;

import org.testng.annotations.Test;

import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.WlRuleCreation;
import pageObject.WlUpload;
import resorces1.base;

public class WatchListRuleCreation3 extends TestBase {
	
	LoginPage login;
	 MainPage home;
	 
	
	 @DataProvider(name="wlruledata")
		public Object[] wlruledata(){
			Object[][] data = getExcelData("demo.xlsx", "wlrulecreation");
			return data;
		}
	 @BeforeClass
		public void beforeClass() {
			getApplicationURL(DataSource.OR.getProperty("url"));
			login = new LoginPage(driver);
		}
	
	 
	 	@Test(dataProvider="wlruledata")
	 	public void wlRuleCreation(String userName,String password,String ruleName, String entityType, String descriptiondata, String maxiumMatch, String cutoffscore, String wltype, String matchtype, String weight, String cutoffscore1, String runMode) throws InterruptedException, IOException
	 	{
	 		 if(runMode.equalsIgnoreCase("n")){
					throw new SkipException("Run mode for this set of data is marked N");
				}
			 home = login.loginToApplication(userName, password);
			Thread.sleep(3000);
			 WlRuleCreation rule = home.navigateToWLRuleCreation();
			 rule.watchListRuleCreation(ruleName, entityType, descriptiondata, maxiumMatch, cutoffscore, wltype, matchtype, weight, cutoffscore1);
			 Thread.sleep(3000);
			 home.logout();
	 	}
	 	/*@AfterTest
	 	public void closeBrowser()
	 	{
	 		driver.close();
	 	}*/

}
