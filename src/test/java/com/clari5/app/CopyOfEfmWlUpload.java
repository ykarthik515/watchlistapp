package com.clari5.app;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.clari5.helper.waitHelper.WaitHelper;
import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PEfmUpload;
import resorces1.base;

public class CopyOfEfmWlUpload extends TestBase {
 
	public static Logger log =LogManager.getLogger(base.class.getName());
 	public static ExtentTest test;
 	public static ExtentReports report;


 	LoginPage login;
 	MainPage home;
 	
 	
 	 @BeforeClass
		public void beforeClass() {
			getApplicationURL(DataSource.OR.getProperty("url"));
			login = new LoginPage(driver);
		}
 	 
 	 
  @Test
  public void f() throws InterruptedException {
	  

	  
	  	//System.setProperty("webdriver.chrome.driver", "/home/karthik/watchlist-app/src/main/resources/drivers/chromedriver");

		//WebDriver driver1 = new ChromeDriver();
		//WebDriver driver=(WebDriver) new PEfmUpload(driver1);
	  	//WebDriver webDriver;
		//EfmWlUpload driver1=new EfmWlUpload(webDriver);
	  	//PEfmUpload driver=driver1;
	  	
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("inbox_1");
		driver.findElement(By.name("password")).sendKeys("testing111");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String title=driver.getTitle();
		System.out.println(title);
		
		try{
			if(!(driver.findElement(By.xpath("//html//ul[@class='nav navbar-nav']/li[3]/a[1]")).isEnabled())){
					
			}
		}
			catch (Exception e) {
				driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Yes, Login!')]")).click();
				
			}
				
		
		clickOnAmlModuleLink();
		//driver.findElement(By.xpath("//html//ul[@class='nav navbar-nav']/li[3]/a[1]")).click();
		clickOnWatchlistUploadLink();
		//driver.findElement(By.xpath("//a[@style='color: white'][contains(text(),'Watchlist Upload')]")).click();
		Thread.sleep(1000);
		moveToObjectFrame();		
		//driver.switchTo().frame(driver.findElement(By.xpath("/html/body/genesis/div/genesis/div/efm-menu/object")));
		clickOnUploadWatchlistButton();
		//driver.findElement(By.xpath("//a[@onclick='openUploadSrn()']")).click();
		
		//Select sel =new Select(driver.findElement(By.id("wlType")));
		//sel.selectByIndex(1);
		chooseFile();
		//driver.findElement(By.id("file_upload")).sendKeys("/home/karthik/Downloads/MLGWLXML/0.ZIP");
		clickOnUploadToMergeJobQueueButton();
		//driver.findElement(By.id("subButton")).click();
		
		Thread.sleep(2000);
  	
		//WebElement t1 = driver.findElement(By.xpath("//td[contains(text(),'ACCUITY#0')]/preceding-sibling::td[1]/input"));
  	 
	//	System.out.println(t1.isDisplayed());
  	
		clickOnInclude();
		//t1.click();
  
		//code for correctness
		
		clickOnAddToMergeJobQueueButton();
		//driver.findElement(By.id("atmBtn")).click();
		
		Thread.sleep(1000);
		//code for correctness

		
    		
		WebElement status;
		String statusTitle; 
		int count = 1;
		boolean data = true;
		while (data ) {
			
		// status=driver.findElement(By.xpath("//td[contains(text(),'ACCUITY#0')]/../td[6]/a"));
		// Thread.sleep(2000);
		 wait.until(ExpectedConditions.elementToBeClickable(getStatus()));
		 statusTitle = getStatus().getAttribute("title");
			System.out.println("status title"+statusTitle);
		if (statusTitle.equalsIgnoreCase("In progress")) {
					Thread.sleep(2000);
				System.out.println("in progress");
				data =true;
				
				
				count ++;
				
				System.out.println(count);
				
				if( count >10000)
				{
					break;
				}
																			
			}
			
				
			else if(statusTitle.contains("Error"))
			{
				System.out.println("Test case is failed");
				data = false;
			}
			
			else if(statusTitle.contains("Indexing complete"))
			{
				System.out.println("Test case is passed");
				data = false;
			}
			
			
		}
		
		
		
		
			
		getDriver().switchTo().defaultContent();
  	
		getDriver().findElement(By.xpath("//a[@class='customNav']")).click();
		
		getDriver().close();
		
  }
}