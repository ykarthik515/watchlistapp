package pageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.clari5.helper.logger.LoggerHelper;
import com.clari5.helper.waitHelper.WaitHelper;
import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CustomerOnBoardingp extends TestBase {

	WebDriver driver;
	private WaitHelper waitHelper;
	private static Logger log = LoggerHelper.getLogger(WlUpload.class);
	Select select;
	TestBase tb1;
	
	public CustomerOnBoardingp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(selectEntityType, DataSource.getExplicitWait());
		
	}
	
	@FindBy(xpath="//div[@class='panel-heading divBoxWithBorder-Heading']/a[1]")
	WebElement searchWl;
	
	@FindBy(xpath="//select[@name='entityType']")
	WebElement selectEntityType;
	
	
	@FindBy(xpath="//select[@name='rule']")
	WebElement selectRuleName;
	
	@FindBy(xpath="//input[@id='searchQueryName']")
	WebElement searchdata;
	//driver.findElement(By.xpath("//button[@class='btn btn-primary search-button-custom-css']")).click();
	@FindBy(xpath="//button[@class='btn btn-primary search-button-custom-css']")
	WebElement clickOnSearch;
	
	@FindBy(xpath="//tbody[@class='ui-datatable-data ui-widget-content']/tr/td[1]")
	WebElement searchResultsRecordIds;

	@FindBy(xpath="//div[@class='col-sm-8 remove-padding-for-header']")
	WebElement searchResults;
	
	
	public void searchWlsymbol() {
		log.info("click on - symbol");
		searchWl.click();
	}

	public void selectEntityType(String entityType) {
		log.info("select entity type:" +entityType);
		test.log(LogStatus.INFO, "selecting entity type as :" +entityType);

		select = new Select(selectEntityType);
		select.selectByVisibleText(entityType);
	}

	public void selectRuleName(String ruleName) {
		log.info("selecting the rule:" +ruleName);
		test.log(LogStatus.INFO, "selecting the rule as :" +ruleName);

		select = new Select(selectRuleName);
		select.selectByVisibleText(ruleName);
	}

	public void searchdata(String searchvalue) {
		log.info("providing the search value:" +searchvalue);
		test.log(LogStatus.INFO, "providing the search value as :" +searchvalue);

		searchdata.sendKeys(searchvalue);
		
	}

	public void clickingOnSearch() {
		log.info("click on search"); 
		test.log(LogStatus.INFO, "clicking on search button");

		clickOnSearch.click();
	}

	public WebElement searchResultsRecordIds() {
		return searchResultsRecordIds;
	}
	

	public void customeronboarding(String entityType, String ruleName, String searchvalue) throws IOException {
		
		waitHelper.waitForElement(selectEntityType, 10);
		selectEntityType(entityType);
		waitHelper.waitForElement(selectRuleName, 10);
		selectRuleName(ruleName);
		waitHelper.waitForElement(searchdata, 10);
		searchdata(searchvalue);
		waitHelper.waitForElement(clickOnSearch, 10);
		clickingOnSearch();
		waitHelper.waitForElement(searchResults, 10);
		tb1= new TestBase();
		String scr=	tb1.getScreenShot("customerOnBoardingResults");
		test.log(LogStatus.PASS,test.addScreenCapture(scr));

		
		
	}
	

}
