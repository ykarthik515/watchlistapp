package com.clari5.app;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mozilla.javascript.ast.WhileLoop;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.clari5.helper.waitHelper.WaitHelper;
import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PEfmUpload;
import pageObject.WlUpload;
import resorces1.base;

public class EfmWlUpload extends TestBase {
 
	public static Logger log =LogManager.getLogger(base.class.getName());
 	public static ExtentTest test;
 	public static ExtentReports report;


 LoginPage loginPage;
 MainPage mainPage;
 
 @DataProvider(name="testData")
	public Object[][] testData(){
		Object[][] data = getExcelData("demo.xlsx", "wlupload");
		return data;
	}
 @BeforeClass
	public void beforeClass() {
		getApplicationURL(DataSource.OR.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
 
 @Test(dataProvider="testData")
 public void testWlUpload(String userName, String password, String fileType, String filePath, String runMode) throws InterruptedException, IOException
 {
	 if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
	 
	 mainPage = loginPage.loginToApplication(userName, password);
	 try{
		if(loginPage.yesLoginIsEnabled()){
				
		}
	}
		catch (Exception e) {
			loginPage.clickOnYesLoginButton();			
		}

	 
	 
	 Thread.sleep(3000);
	 
	 mainPage.amlUpload();
	 //home.clickOnWatchlistUpload();
	// home.moveToObjectFrame();
	 
	 PEfmUpload upload = mainPage.clickOnWatchlistUploadlink();
	 
	 mainPage.moveToObjectFrame();
	 
	 upload.uploadWlFile(fileType, filePath);
	 
	 Thread.sleep(2000);
	 upload.status_Check(10000);
	 driver.switchTo().defaultContent();
	 mainPage.logout();


}
}