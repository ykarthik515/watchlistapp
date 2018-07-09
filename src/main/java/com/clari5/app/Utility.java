package com.clari5.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pageObject.LoginPage;
import pageObject.LogoutPage;
import resorces1.base;
import scala.sys.Prop;

public class Utility extends base {
	
	public static LoginPage lp;
	public static LogoutPage lo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fis;
	public String path;
	
	
	
	public static void login(String username, String password)
	{
		
		 lp=new LoginPage(driver);

			lp.username(username);
			lp.password(password);
			lp.clickOnSubmit();


	}
	
	public static  String cellData(int rownum, int colnum) throws IOException
	{
		 //fis= new FileInputStream("/home/karthik/watchlist-app/data.xlsx");
		System.out.println(prop.getProperty("excel"));
		fis=new FileInputStream(prop.getProperty("excel"));
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(prop.getProperty("sheet"));
		row=sh.getRow(rownum);
		cell=row.getCell(colnum);
		
		return cell.getStringCellValue();
		
	}
	
	public static void logout() throws InterruptedException
	{
		 lo=new LogoutPage(driver);

		lo.getLogoutLink().click();
		Thread.sleep(2000);
		lo.getLogoutButton().click();
	}
	

}
