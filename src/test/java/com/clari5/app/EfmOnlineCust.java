package com.clari5.app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class EfmOnlineCust {
  @Test
  public void f() throws InterruptedException {
	  
	  System.setProperty("webdriver.chrome.driver", "/home/karthik/watchlist-app/src/main/resources/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		Thread.sleep(1000);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS );
		driver.get("https://engtestapp2.customerxps.com:2504/efm");
		Thread.sleep(1000);
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
				
		
		
		driver.findElement(By.xpath("//html//ul[@class='nav navbar-nav']/li[3]/a[1]")).click();
		
		driver.findElement(By.xpath("//a[@style='color: white'][contains(text(),'Online Customer Onboarding')]")).click();
		Thread.sleep(1000);
		WebElement data = driver.findElement(By.xpath("//div[@class='selectedMenu']"));
		Actions act=new Actions(driver);
		//act.moveToElement(element).contextClick().build().perform();
		act.moveToElement(data).build().perform();
		
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/genesis/div/genesis/div/efm-menu/object")));
		Thread.sleep(1000);
		
		Select sel = new Select(driver.findElement(By.id("ruleNmDD")));
		
		//sel.selectByVisibleText("CUSTOM -- custom search in  AU, UN, AR");
		sel.selectByIndex(2);
		
		driver.findElement(By.xpath("//table[@id='ruleDetails']/tbody/tr[1]/td/input")).sendKeys("mohammad");
		driver.findElement(By.xpath("//a[@class='abutton submit-w-24']")).click();
	  
  }
}
