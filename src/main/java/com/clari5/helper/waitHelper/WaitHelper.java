package com.clari5.helper.waitHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clari5.helper.logger.LoggerHelper;

public class WaitHelper {
	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private WebDriver driver;

	public WaitHelper(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	/**
	 * This method will wait for element
	 * @param element
	 * @param time
	 */
	public void waitForElement(WebElement element, long time){
		log.info("waiting for element to present..");
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is present...");
	}
	
	public void waitForElementClickable(WebElement element, long time){
		log.info("waiting for button to clickable..");
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is present...");
	}
	
	public void waitForCheckboxClickable(WebElement element, long time){
		log.info("waiting for element to present..");
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeSelected(element));
		log.info("element is present...");
	}
	
}
