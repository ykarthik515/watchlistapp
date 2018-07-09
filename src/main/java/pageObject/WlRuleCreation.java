package pageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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

public class WlRuleCreation extends TestBase {

	
	WebDriver driver;
	private WaitHelper waitHelper;
	private static Logger log = LoggerHelper.getLogger(WlUpload.class);
	Select select;
	TestBase tb1;
	
	public WlRuleCreation(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElementClickable(clickOnPlus, DataSource.getExplicitWait());
		
	}
	
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-plus']")
	WebElement clickOnPlus;
	
	@FindBy(xpath="//input[@id='addName']")
	WebElement ruleName;
	
	@FindBy(xpath="//select[@id='addEntityType']")
	WebElement selectEntityType;
	
	@FindBy(xpath="//input[@id='addDescription']")
	WebElement discription;
	
	@FindBy(xpath="//input[@id='addMaximumMatch']")
	WebElement maxMatch;
	
	@FindBy(xpath="//input[@id='addCutoffScore']")
	WebElement cutOffScore;
	
	@FindBy(xpath="//select[@id='addWatchListType']")
	WebElement selectWlType;
	
	@FindBy(xpath="(//button[@class='btn btn-default checkBox-size'])[20] ")
	WebElement nameCheckbox;
	
	@FindBy(xpath="(//td[contains(text(), 'Name')])[2]/following-sibling::td[1]/select")
	WebElement selectMathType;
	
	@FindBy(xpath="(//td[contains(text(), 'Name')])[2]/following-sibling::td[2]/select")
	WebElement selectWeight;
	
	@FindBy(xpath="(//td[contains(text(), 'Name')])[2]/following-sibling::td[3]/div/div/input")
	WebElement cutoffScore1;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement addButton;

	public void ClickOnPlus() {
		log.info("click on plus symbol");
		clickOnPlus.click();
	}

	public void addRuleName(String rulename) {
		log.info("add the rule name:" +rulename);	
		ruleName.sendKeys(rulename);
	}

	public void selectEntityType(String entityType) {
		
		log.info("select entityType:" +entityType);
		select= new Select(selectEntityType);
		select.selectByVisibleText(entityType);
	}

	public void description(String descriptiondata) {
		
		log.info("provide discription:" + descriptiondata);
		discription.sendKeys(descriptiondata);
	}

	public void maxMatch(String maxiumMatch) {
		log.info("providing max data:" +maxiumMatch);
		maxMatch.sendKeys(maxiumMatch);
		
	}

	public void cutOffScore(String cutoffscore) {
		log.info("provide cutoff score:" +cutoffscore); 
		cutOffScore.sendKeys(cutoffscore);
	}

	public void selectWlType(String wltype) {
		log.info("select watchlisttype:" +wltype); 
		select=new Select(selectWlType);
		 select.selectByVisibleText(wltype);
	}

	public WebElement nameCheckbox() {
		log.info("click on name attribute");
		return nameCheckbox;
	}

	public void selectMatchType(String matchtype) {
		log.info("selecting the patch type:" +matchtype); 
		select = new Select(selectMathType);
		 select.selectByVisibleText(matchtype);
	}

	public void selectWeight(String weight) {
		log.info("select the weight:" +weight);
		select = new Select(selectWeight);
		select.selectByVisibleText(weight);
	}

	public void cutoffScore1(String cutoffscore1) {
		log.info("provide cutoff score:" +cutoffscore1);
		cutoffScore1.sendKeys(cutoffscore1);
	}

	public void addButton() {
		log.info("clicking on submit button");
		addButton.click();
	}


	public void watchListRuleCreation(String ruleName, String entityType, String descriptiondata, String maxiumMatch, String cutoffscore, String wltype, String matchtype, String weight, String cutoffscore1) throws InterruptedException, IOException
	{
		waitHelper.waitForElementClickable(clickOnPlus, 10);
		ClickOnPlus();
		waitHelper.waitForElement(this.ruleName, 10);
		//Thread.sleep(2000);
		addRuleName(ruleName);
		waitHelper.waitForElement(selectEntityType, 10);
		selectEntityType(entityType);
		waitHelper.waitForElement(discription, 10);
		description(descriptiondata);
		waitHelper.waitForElement(maxMatch, 10);
		maxMatch(maxiumMatch);
		waitHelper.waitForElement(cutOffScore, 10);
		cutOffScore(cutoffscore);
		waitHelper.waitForElement(selectWlType, 10);
		selectWlType(wltype);
		waitHelper.waitForElementClickable(nameCheckbox, 10);
		WebElement butt = nameCheckbox();
		//Thread.sleep(3000);
		boolean dis = butt.isDisplayed();
		System.out.println(dis);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", butt);
		waitHelper.waitForElement(selectMathType, 10); 
		selectMatchType(matchtype);
		waitHelper.waitForElement(selectWeight, 10);
		selectWeight(weight);
		waitHelper.waitForElement(cutoffScore1, 10);
		cutoffScore1(cutoffscore1);
		waitHelper.waitForElementClickable(addButton, 10);
		tb1= new TestBase();
		String scr=	tb1.getScreenShot("wlupload");
		test.log(LogStatus.PASS,test.addScreenCapture(scr));

		addButton();
		
			
		
	}
	
	

}
