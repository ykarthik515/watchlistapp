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

import pageObject.CustomerOnBoardingp;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.WlUpload;
import resorces1.base;

public class CustomerOnBoarding3 extends TestBase {

	public static Logger log =LogManager.getLogger(base.class.getName());
	 
	 LoginPage login;
	 MainPage home;
	 
	 @DataProvider(name="onboarding")
		public Object[] onboarding(){
			Object[][] data = getExcelData("demo.xlsx", "onboarding");
			return data;
		}
	 @BeforeClass
		public void beforeClass() {
			getApplicationURL(DataSource.OR.getProperty("url"));
			login = new LoginPage(driver);
		}

	@Test(dataProvider="onboarding")
	
	public void customerOnboarding(String userName, String password,String entityType, String ruleName, String searchvalue, String runMode) throws InterruptedException, IOException
	{
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
	 home = login.loginToApplication(userName, password);
	 Thread.sleep(3000);
	 CustomerOnBoardingp onboard = home.navigateToCustomerOnboarding();
	 onboard.customeronboarding(entityType, ruleName, searchvalue);
	 Thread.sleep(3000);
	 home.logout();
	
	
	
	}
	@AfterTest
	 public void closeBrowser()
	 {
		driver.close();
	 }

}
