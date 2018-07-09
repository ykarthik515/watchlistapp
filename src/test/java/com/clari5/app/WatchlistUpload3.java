package com.clari5.app;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.WlUpload;
import resorces1.base;

public class WatchlistUpload3 extends TestBase 
{
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	 	public static ExtentTest test;
	 	public static ExtentReports report;


	 LoginPage login;
	 MainPage home;
	 
	 @DataProvider(name="testData")
		public Object[] []testData(){
			Object[][] data = getExcelData("demo.xlsx", "wlupload");
			return data;
		}
	 @BeforeClass
		public void beforeClass() {
			getApplicationURL(DataSource.OR.getProperty("url"));
			login = new LoginPage(driver);
		}
	 
	 @Test(dataProvider="testData")
	 public void testWlUpload(String userName, String password, String fileType, String filePath, String runMode) throws InterruptedException, IOException
	 {
		 if(runMode.equalsIgnoreCase("n")){
				throw new SkipException("Run mode for this set of data is marked N");
			}
		 
		 home = login.loginToApplication(userName, password);
		 Thread.sleep(3000);
		 WlUpload upload = home.navigateToWLupload();
		 upload.uploadFile(fileType, filePath);
		 Thread.sleep(3000);
		 home.logout();
	 }
	/* @AfterTest
	 public void closeBrowser()
	 {
		driver.close();
	 }*/
	 
}
