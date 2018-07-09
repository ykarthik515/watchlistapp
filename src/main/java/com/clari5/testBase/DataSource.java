package com.clari5.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.clari5.helper.logger.LoggerHelper;
import com.clari5.utility.ResourceHelper;

public class DataSource {

	private static Logger log = LoggerHelper.getLogger(DataSource.class);

	public static Properties OR;

	private String browerType;
	private String userName;
	private String password;
	private String url;
	long implicitWait;
	long explicitWait;
	long pageLoadTime;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrowerType() {
		return browerType;
	}

	public void setBrowerType(String browerType) {
		this.browerType = browerType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static long getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitWait"));
	}

	public static long getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}

	public static long getExplicitWait1(int explicitWait) {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}
	public static long getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageLoadTime"));
	}


	static {
		log.info("loading config.properties..");
		OR = new Properties();
		File f1 = new File(ResourceHelper.getResourcePath("/src/main/resources/projectConfig/config.properties"));
		try {
			FileInputStream file = new FileInputStream(f1);
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("loading config.properties done");
	}

}
