package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.clari5.helper.logger.LoggerHelper;
import com.clari5.helper.waitHelper.WaitHelper;
import com.clari5.testBase.DataSource;
import com.relevantcodes.extentreports.LogStatus;

public class MainPage {

	private WebDriver driver;
	private WaitHelper waitHelper;

	private static Logger log = LoggerHelper.getLogger(LoginPage.class);
	
	public MainPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElementClickable(amlModule, DataSource.getExplicitWait());
	}
	
	@FindBy(xpath="//html//ul[@class='nav navbar-nav']/li[3]/a[1]")
	WebElement amlModule;
	
	@FindBy(xpath = "//a[@style='color: white'][contains(text(),'Watchlist Upload')]")
	WebElement watchListUploadLink;

	@FindBy(xpath="//a[@class='customNav']")
	WebElement logoutLink;

	@FindBy(xpath="//a[@style='color: white'][contains(text(),'Watchlist Rules')]")
	WebElement watchlistRuleLink;
	
	@FindBy(xpath = "/html/body/genesis/div/genesis/div/efm-menu/object")
	WebElement objectTag;

	
	
	public boolean amlIsEnabled() {
		
		log.info("checking for AML Module is displayed or not? ...");


		return amlModule.isEnabled();	
		
	}

	public void logout() {
		log.info("clicking on logout link...");
		waitHelper.waitForElementClickable(logoutLink, DataSource.getExplicitWait());

		logoutLink.click();
		
		log.info("signed out successfully...");
	}
	
	public void amlUpload(){
		log.info("clicking  on aml Module");
		waitHelper.waitForElementClickable(amlModule, 10);

		amlModule.click();
		
	}
	public PEfmUpload clickOnWatchlistUploadlink(){
		log.info("navigating to leads");
		waitHelper.waitForElementClickable(watchListUploadLink, 10);

		watchListUploadLink.click();
		return new PEfmUpload(driver);
	}
	
	
	public void moveToObjectFrame() {
		log.info("Moving to Object Frame");
		driver.switchTo().frame(objectTag);
	}
	
	
	
	
}
