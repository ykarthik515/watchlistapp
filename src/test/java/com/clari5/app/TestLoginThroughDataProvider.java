package com.clari5.app;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import resorces1.base;

public class TestLoginThroughDataProvider extends base{
	WebDriver driver;
	String path;
	Object sampleData[][];
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	/* public void initialize() throws IOException
		{
		 driver =initializeDriver();
		}
	*/ 
	 public Object[][] getData(String ExcelName, String testcase) {
			 path = "/home/karthik/watchlist-app/resorces/Login.xlsx";

			Excel_Reader Data = new Excel_Reader(path);
			int rowNum = Data.getRowCount(testcase);
			System.out.println(rowNum);
			int colNum = Data.getColumnCount(testcase);
			Object sampleData[][] = new Object[rowNum - 1][colNum];
			for (int i = 2; i <=rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
				}
			}
			return sampleData;
		}
		
		@DataProvider
		public Object[][] loginData(){
			Object[][] data = getData("Login.xlsx", "Login");
			return data;
		}
		
		@Test(dataProvider = "loginData")
		public void TestLoginWithDataProvider(String TestCaseName, String Email, String Password,String runMode) throws InterruptedException{
	        //log.info("I am from data provider gest");
			if(runMode.equals("N")){
				throw new SkipException("Skipping the test");
			}
			/*signIn = new SignIn(driver);
			signIn.loginToApplication(Email, Password);
			Thread.sleep(3000);
			signIn.logout();
		*/	
			 System.setProperty("webdriver.chrome.driver", "/home/karthik/Music/Automation/chromedriver");
			 driver=new ChromeDriver();
			driver.get("http://automationpractice.com/index.php");
			driver.findElement(By.xpath("//a[@class='login']"));
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Email);
			driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(Password);
			driver.findElement(By.xpath("//button[@id='SubmitLogin']//span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='logout']")).click();
			//System.out.println(sampleData);
			driver.close();
		}
		
}
