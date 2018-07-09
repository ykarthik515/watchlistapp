package pageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
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

public class WlMapping extends TestBase {

	WebDriver driver;
	private WaitHelper waitHelper;
	private static Logger log = LoggerHelper.getLogger(WlUpload.class);
	Select select;
	TestBase tb1;
	public WlMapping(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(clickonAddsymbol, DataSource.getExplicitWait());
		
	}
	
	@FindBy(xpath="//div[@class='btn btn-primary']")
	WebElement clickonAddsymbol;
	
	@FindBy(xpath="//select[@id='addSystem']")
	WebElement selectSystem;
	
	
	@FindBy(xpath="//select[@id='addMessageType']")
	WebElement selectMsgType;
	
	@FindBy(xpath="//select[@id='addMatchEntity']")
	WebElement selectMatchEntity;
	
	@FindBy(xpath="//select[@id='addTags']")
	WebElement selectTags;
	
	@FindBy(xpath="//select[@id='addEntityType']")
	WebElement selectRuleEntityType;
	
	@FindBy(xpath="//select[@id='addWlRule']")
	WebElement selectwlRule;
	
	@FindBy(xpath="//button[@class='btn btn-info']")
	WebElement clickonAdd;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement clickOnSubmit;
	
	@FindBy(xpath="//button[@type='button'][contains(text(),'OK')]")
	WebElement clcikOnOK;

	public void clickonAddsymbol() {
		log.info("clicking on add symbol"); 
		test.log(LogStatus.INFO, "clicking on add symbol");
		clickonAddsymbol.click();
	}

	public void selectSystem(String system) {
		log.info("selecting the system type:" +system);
		test.log(LogStatus.INFO, "selecting the system type as :" +system);
		select = new Select(selectSystem);
		select.selectByVisibleText(system);
		
	}

	public void selectMsgType(String msgType) {
		
		log.info("selecting the msgtype:"+msgType);
		test.log(LogStatus.INFO, "selecting the msgtype as :" +msgType);
		select = new Select(selectMsgType);
		select.selectByVisibleText(msgType);
	}

	public void selectMatchEntity(String matchEntity) {
		log.info("select the matchentityType as :" +matchEntity);
		test.log(LogStatus.INFO, "select the matchentityType as :" +matchEntity);
		select = new Select(selectMatchEntity);
		select.selectByVisibleText(matchEntity);
		
	}

	public void selectTags(String tag) {
		log.info("select the tag:" + tag);
		test.log(LogStatus.INFO, "select the tag:" + tag);
		select = new Select(selectTags);
		select.selectByVisibleText(tag);
	}

	public void selectRuleEntityType(String entityType) {
		log.info("select the entityType as:"+entityType); 
		test.log(LogStatus.INFO, "select the entityType as: " +entityType);
		select= new Select(selectRuleEntityType);
		select.selectByVisibleText(entityType);
	}

	public void selectwlRule(String wlrule) {
		log.info("select the watchlist rule as:" +wlrule);
		test.log(LogStatus.INFO, "select the watchlist rule as :" +wlrule);
		select= new Select(selectwlRule);
		select.selectByVisibleText(wlrule);
	}

	public void clickingonAddButton() {
		log.info("clicking on Add button");
		test.log(LogStatus.INFO, "clicking on Add button");
		clickonAdd.click();
	}

	public void clickingOnSubmit() {
		log.info("clciking on submit button");
		test.log(LogStatus.INFO, "clciking on submit button");
		clickOnSubmit.click();
	}

	public void clcikingOnOK() {
		log.info("clicking on ok button");
		test.log(LogStatus.INFO, "clicking on ok button");
		clcikOnOK.click();
	}
	
	
	public void watchlistMappingMethod(String system, String msgType, String matchEntity, String tag, String entityType, String wlrule) throws IOException {
		
		clickonAddsymbol();
		waitHelper.waitForElement(selectSystem, 10);
		selectSystem(system);
		waitHelper.waitForElement(selectMsgType, 10);
		selectMsgType(msgType);
		waitHelper.waitForElement(selectMatchEntity, 10);
		selectMatchEntity(matchEntity);
		waitHelper.waitForElement(selectTags, 10);
		selectTags(tag);
		waitHelper.waitForElement(selectRuleEntityType, 10);
		selectRuleEntityType(entityType);
		waitHelper.waitForElement(selectwlRule, 10);
		selectwlRule(wlrule);
		waitHelper.waitForElementClickable(clickonAdd, 10);
		clickingonAddButton();
		waitHelper.waitForElementClickable(clickOnSubmit, 10);
		clickingOnSubmit();
		waitHelper.waitForElementClickable(clcikOnOK, 10);
		tb1= new TestBase();
		String scr=	tb1.getScreenShot("swiftRule");
		test.log(LogStatus.PASS,test.addScreenCapture(scr));

		clcikingOnOK();
		
	}
}
