package com.clari5.app;

import org.testng.annotations.Test;

import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.WlMapping;
import pageObject.WlUpload;
import resorces1.base;

public class SwiftRuleCreation3 extends TestBase {

	public static Logger log =LogManager.getLogger(base.class.getName());
	 
	 LoginPage login;
	 MainPage home;
	 
	 @DataProvider(name="wlmapping")
		public Object[] wlmapping(){
			Object[][] data = getExcelData("demo.xlsx", "wlmapping");
			return data;
		}
	 @BeforeClass
		public void beforeClass() {
			getApplicationURL(DataSource.OR.getProperty("url"));
			login = new LoginPage(driver);
		}
	
	@Test(dataProvider="wlmapping")
	public void swiftrule(String userName, String password,String system, String msgType, String matchEntity, String tag, String entityType, String wlrule, String runMode) throws InterruptedException, IOException
	{
		
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
	 home = login.loginToApplication(userName, password);
	 Thread.sleep(3000);
	 WlMapping swift = home.navigateToWlMapping();
	 swift.watchlistMappingMethod(system, msgType, matchEntity, tag, entityType, wlrule);
	 Thread.sleep(3000);
	 home.logout();
		
	}
	@AfterTest
	 public void closeBrowser()
	 {
		driver.close();
	 }
}
