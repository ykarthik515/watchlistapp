package com.clari5.app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EfmWlRule {
  @Test
  public void f() throws InterruptedException {
	  
		System.setProperty("webdriver.chrome.driver", "/home/karthik/watchlist-app/src/main/resources/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		Thread.sleep(1000);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS );
		driver.get("https://engtestapp2.customerxps.com:2504/efm");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("inbox_2");
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
		
		driver.findElement(By.xpath("//a[@style='color: white'][contains(text(),'Watchlist Rules')]")).click();
		Thread.sleep(1000);
		WebElement data = driver.findElement(By.xpath("//div[@class='selectedMenu']"));
		Actions act=new Actions(driver);
		//act.moveToElement(element).contextClick().build().perform();
		act.moveToElement(data).build().perform();
		
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/genesis/div/genesis/div/efm-menu/object")));
		Thread.sleep(1000);

		driver.findElement(By.id("addWatchList")).click();
		driver.findElement(By.id("wlrnTI")).sendKeys("test6");
		driver.findElement(By.id("ocsTI")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "75");
		driver.findElement(By.id("mmTI")).sendKeys("10");
		
		
		Select sel =new Select(driver.findElement(By.id("wltDD")));
		sel.selectByIndex(0);
		sel.selectByIndex(1);
		sel.selectByIndex(2);
		sel.selectByIndex(3);
		WebElement name =driver.findElement(By.xpath("//*[@id='wlAttrTbl']/tbody/tr[4]/td[1]/input"));
		System.out.println(name.isDisplayed());
		
		driver.findElement(By.xpath("//span[contains(text(),'name')]/../../td/input")).click();
		
		Select sel2 = new Select(driver.findElement(By.xpath("//span[contains(text(),'name')]/../../td[3]/select")));
		sel2.selectByIndex(2);
		
		Select sel3 = new Select(driver.findElement(By.xpath("//span[contains(text(),'name')]/../../td[4]/select")));
		sel3.selectByVisibleText("6");
		
		driver.findElement(By.xpath("//span[contains(text(),'name')]/../../td[5]/input")).sendKeys("75");
		driver.findElement(By.xpath("//span[contains(text(),'name')]/../../td[6]/input")).click();
		
		driver.findElement(By.xpath("//a[@class='abutton submit-w-24']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("test6");
		
		WebElement verifyRule = driver.findElement(By.xpath("//table[@id='wlTable']/tbody/tr/td[2]"));
		
		System.out.println(verifyRule.getText());
		String actualRule="test6".toUpperCase();
		String expectedRule = verifyRule.getText();
		
		Assert.assertEquals(actualRule, expectedRule);
		
		
		

  }
}
