package com.clari5.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import com.clari5.app.Utility;
import com.clari5.excel.tutorial.ReadDataFromExcelSheet;
import com.clari5.utility.ExtentManager;
import com.clari5.utility.ResourceHelper;
import com.clari5.helper.logger.LoggerHelper;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	public static  WebDriver driver;
/* 	public static ExtentReports report;
 	public static ExtentTest test;
*/ 	
 	/*public static void reports(){
 		
 	
	report=ExtentManager.getInstance();

 	}
*/
	
	public static Logger log = LoggerHelper.getLogger(TestBase.class);

	/**
	 * This method will give excel data in 2D array based on sheet Name
	 * 
	 * @param excellocation
	 * @param sheetName
	 * @return
	 */
	public String[][] getExcelData(String excelName, String sheetName) {
		String excellocation = ResourceHelper.getResourcePath("/src/main/resources/testData/") + excelName;
		ReadDataFromExcelSheet readDataFromExcelSheet = new ReadDataFromExcelSheet();
		return readDataFromExcelSheet.getExcelData(excellocation, sheetName);
	}
	
	/**
	 * This method will give excel data in 2D array based on sheet Name
	 * 
	 * @param excellocation
	 * @param sheetName
	 * @return
	 */
	public Object[][] getExcelData(String excelName, String sheetName, String testName) {
		String excellocation = ResourceHelper.getResourcePath("/src/main/resources/testData/") + excelName;
		ReadDataFromExcelSheet readDataFromExcelSheet = new ReadDataFromExcelSheet();
		return readDataFromExcelSheet.getExcelDataBasedOnStartingPoint(excellocation, sheetName, testName);
	}
	
    /**
     * 
     * @param data
     * @param col
     * @return
     */
	public Object[] dataParsing(String[][] data, int col){
		ReadDataFromExcelSheet readDataFromExcelSheet = new ReadDataFromExcelSheet();
		return readDataFromExcelSheet.parseData(data,col);
	}

	/**
	 * This method will update result in excel sheet.
	 * 
	 * @param excellocation
	 * @param sheetName
	 * @param testCaseName
	 * @param testStatus
	 * @throws IOException
	 */
	public void updateResult(String excellocation, String sheetName, String testCaseName, String testStatus) throws IOException {
		ReadDataFromExcelSheet readDataFromExcelSheet = new ReadDataFromExcelSheet();
		readDataFromExcelSheet.updateResult(excellocation, sheetName, testCaseName, testStatus);
	}

	/**
	 * This method will create browser Objects.
	 * 
	 * @param browser
	 * @return
	 */
	public void getBrowser(String browser) {
		
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
				this.driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
				this.driver = new ChromeDriver();
			}
		 
			
		}
	

	
	public static String addScreenshot() {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    String encodedBase64 = null;
	    FileInputStream fileInputStreamReader = null;
	    try {
	        fileInputStreamReader = new FileInputStream(scrFile);
	        byte[] bytes = new byte[(int)scrFile.length()];
	        fileInputStreamReader.read(bytes);
	        encodedBase64 = new String(Base64.encodeBase64(bytes));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "data:image/png;base64,"+encodedBase64;
	}
	public String getScreenShot(String imageName) throws IOException {
		// in case you don't want to supply screen shot name
		if (imageName.equals("")) {
			imageName = "blank";
		}
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String imagelocation = ResourceHelper.getBaseResourcePath() + "/src/main/resources/screenShots/";

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String actualImageName = imagelocation  +imageName+ "_" + formater.format(calendar.getTime()) + ".png";
		
		File destFile = new File(actualImageName);
		
		Files.copy(image, destFile);
		
		return actualImageName;
	}

	/**
	 * This method will help us to get data from properties file
	 * 
	 * @param name
	 * @return
	 */
	public String getData(String name) {
		return DataSource.OR.getProperty(name);
	}

	@BeforeTest
	public void launchBrowser(){
		getBrowser(getData("browerType"));
		driver.manage().timeouts().implicitlyWait(DataSource.getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(DataSource.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void getApplicationURL(String url){
		log.info(url);
		driver.get(url);
	}

	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/report/test" + formater.format(calendar.getTime()) + ".html", false);
	}

	
	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		extent.endTest(test);
		extent.flush();
	}


}
