/*package com.clari5.app;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PaymentScreeningp;
import resorces1.base;

public class PaymentScreening extends base {

	MainPage mp;
	PaymentScreeningp ps;
	
	
	@BeforeTest
	public void initialize() throws IOException
	{
	
		 driver =initializeDriver();

	}
	@Test 
	
	public void paymentScreening() throws InterruptedException, FileNotFoundException
	
	{
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		Thread.sleep(3000);
		LoginPage lp=new LoginPage(driver);

		lp.getUsername().sendKeys("superuser1");
		lp.getPassword().sendKeys("Watchlist@123");
		lp.getSubmit().click();
		mp=new MainPage(driver);
		driver.manage().window().maximize();
		
		BufferedReader br = new BufferedReader(new FileReader("/home/karthik/Music/Automation/xmldata.xml"));
		String content = br.lines().collect(Collectors.joining("\n"));
		
		mp.getPaymentScreening().click();
		ps=new PaymentScreeningp(driver);
		
		ps.getXmlContent().sendKeys(content);
		Thread.sleep(3000);
		Select sel=new Select(ps.getSelectMsgType());
		sel.selectByVisibleText("MT");
		ps.getClickOnSubmit().click();
		String caseId = ps.getOutput().getText();
		System.out.println(caseId);
		String str1 = caseId.replaceAll("[\r\n]+", " ");
		String str2 = str1.replace("\"", " ");
		String str3=str2.replace("{", " ");
		String str4=str3.replace("}", " ");
		String str5=str4.replaceAll("\\s+","");
		String str6=str5.replace(":", ",");
		//Transaction Success { caseID : ALONE-3957800302JS-33 , 3957800302JS : true }
		System.out.println(str6);
		String str7=str6.split(",")[1];
		System.out.println(str7);
		Thread.sleep(2000);

		Utility.logout();
		
	}
	@AfterTest
	 public void closeBrowser()
	 {
		driver.close();
	 }
}
*/