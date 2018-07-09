package com.clari5.app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewFirefox {
  @Test
  public void f() throws InterruptedException {
	  
	  
	  
	  System.setProperty("webdriver.gecko.driver", "/home/karthik/geckodriver");
  	WebDriver driver = new FirefoxDriver();
  	System.out.println("its working");
  	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS );
  	driver.get("https://cxsvn.customerxps.com:4025/efm");
  	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("superuser2");
		driver.findElement(By.name("password")).sendKeys("testing111");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//driver.findElement(By.xpath("//a[@routerlink='wl-upload']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//html//ul[@class='nav navbar-nav']/li[3]/a[1]")).click();
		
		driver.findElement(By.xpath("//a[@style='color: white'][contains(text(),'Watchlist Upload')]")).click();
		Thread.sleep(2000);
		/*WebElement element=driver.findElement(By.xpath("/html/body/genesis/div/genesis/div/efm-menu/object"));
		List<WebElement> ele=element.findElements(By.xpath("//a[contains(text(),'atchlist')]"));
		System.out.println(ele.size());
		for(int i=0;i<ele.size();i++) {
			System.out.println(ele.get(i).getText());
		}*/
		driver.switchTo().frame(0);
		System.out.println("after switching frame");
  	WebElement element=driver.findElement(By.xpath("//a[@onclick='openUploadSrn()']"));
  	System.out.println(element.getText());
  	driver.findElement(By.xpath("//a[@onclick='openUploadSrn()']")).click();
  	
  	Select sel =new Select(driver.findElement(By.id("wlType")));
  	sel.selectByIndex(1);
  	
  	driver.findElement(By.id("file_upload")).sendKeys("/home/karthik/Downloads/MLGWLXML/0.ZIP");
  	driver.findElement(By.id("subButton")).click();
  	
  	WebElement t1 = driver.findElement(By.xpath("//td[contains(text(),'ACCUITY#0')]/preceding-sibling::td[1]/input"));
  	 
  	System.out.println(t1.isDisplayed());
  	
  	t1.click();
  	
  	
  	driver.findElement(By.id("atmBtn")).click();
  	
  	//WebElement err=driver.findElement(By.xpath("//a[@class='error-w-16-wc abutton']"));
  	
  	
  	//System.out.println(err.isDisplayed());
  	//System.out.println(err.getAttribute("title"));
  	
  	WebElement success = driver.findElement(By.xpath("//a[@class='check-w-16-wc abutton']"));
  	
  	String suc= success.getAttribute("title");
  	
  	
  	WebElement inProgress = driver.findElement(By.xpath("//a[@class='progrestts-w-def abuon']"));
  	
  	System.out.println(inProgress.getAttribute("title"));
  	//in progress
  	
  	WebDriverWait wait = new WebDriverWait(driver, 20);
  	//wait.until(ExpectedConditions.attributeContains(inProgress, "title", "Indexing complete"));
  	wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@class='progrestts-w-def abuon']"))));
  	
  	Assert.assertEquals("", "");
	  
  }
  
  
  
}
