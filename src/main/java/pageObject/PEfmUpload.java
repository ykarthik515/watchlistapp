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

public class PEfmUpload extends TestBase {

	private WebDriver driver;
	private WaitHelper waitHelper;
	private static Logger log = LoggerHelper.getLogger(WlUpload.class);
	TestBase tb1;

	public PEfmUpload(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		// waitHelper.waitForElement(clickOnwlUploadButton,
		// DataSource.getExplicitWait());

	}

	@FindBy(xpath = "//html//ul[@class='nav navbar-nav']/li[3]/a[1]")
	WebElement amlModuleLink;

	@FindBy(xpath = "//button[@type='button'][contains(text(),'Yes, Login!')]")
	WebElement yesLoginButton;

	@FindBy(xpath = "//a[@style='color: white'][contains(text(),'Watchlist Upload')]")
	WebElement watchListUploadLink;

	@FindBy(xpath = "/html/body/genesis/div/genesis/div/efm-menu/object")
	WebElement objectTag;

	@FindBy(xpath = "//a[@onclick='openUploadSrn()']")
	WebElement uploadWatchlistButton;

	@FindBy(xpath = "//select[@id='wlType']")
	WebElement selectFileType;

	@FindBy(xpath = "//input[@id='file_upload']")
	WebElement chooseFile;

	@FindBy(xpath = "//input[@id='subButton']")
	WebElement uploadToMergeJobQueueButton;

	@FindBy(xpath = "//td[contains(text(),'ACCUITY#0')]/../td[1]/input")
	WebElement includeCheckBox;

	@FindBy(xpath = "//input[@id='atmBtn']")
	WebElement addToMergeJobQueueButton;

	@FindBy(xpath = "//td[contains(text(),'ACCUITY#0')]/../td[6]/a")
	WebElement status;

	public void clickOnAmlModuleLink() {
		log.info("clicking on AML Module");
		test.log(LogStatus.INFO, "click on upload button");
		amlModuleLink.click();

	}

	public void clickOnYesLoginButton() {
		log.info("clicking on yes Login Button");
		test.log(LogStatus.INFO, "clicking on yes Login Button");
		yesLoginButton.click();

	}

	public void clickOnWatchlistUploadLink() {
		log.info("clicked on Watchlist Upload Menu");
		test.log(LogStatus.INFO, "clicked on Watchlist Upload Menu");
		watchListUploadLink.click();
	}
	
	public void moveToObjectFrame() {
		log.info("Moving to Object Frame");
		test.log(LogStatus.INFO, "Moving to Object Frame");
		driver.switchTo().frame(objectTag);
	}

	public void clickOnUploadWatchlistButton() {
		log.info("clicked on Upload Watchlist Button");
		test.log(LogStatus.INFO, "clicked on Upload Watchlist Button");
		uploadWatchlistButton.click();
	}

	public void selectFileType(String fileType) {
		log.info("selected file type as: "+fileType);
		test.log(LogStatus.INFO, "selected file type as: "+fileType);
		Select sel = new Select(selectFileType);
		sel.selectByVisibleText(fileType);

	}

	public void chooseFile(String filePath) {
		log.info("Chosing the file as : "+filePath);
		test.log(LogStatus.INFO, "Chosing the file as : "+filePath);
		chooseFile.sendKeys(filePath);
	}

	public void clickOnUploadToMergeJobQueueButton() {
		log.info("Clicking on Include checkbox");
		test.log(LogStatus.INFO, "Clicking on Include checkbox");
		uploadToMergeJobQueueButton.click();
	}

	public void clickOnInclude() {
		log.info("Clicking on Include checkbox");
		test.log(LogStatus.INFO, "Clicking on Include checkbox");
		includeCheckBox.click();
	}

	public void clickOnAddToMergeJobQueueButton() {
		log.info("Clicking on Include checkbox");
		test.log(LogStatus.INFO, "Clicking on Include checkbox");
		addToMergeJobQueueButton.click();
	}
	
	public WebElement getStatus() {
		
		return status;
	}
	
	
	
	public void uploadWlFile(String fileType,String filePath) throws InterruptedException, IOException {
		
		
		
		waitHelper.waitForElementClickable(uploadWatchlistButton, 10);
		clickOnUploadWatchlistButton();
		waitHelper.waitForElementClickable(selectFileType, 10);
		selectFileType(fileType);
		waitHelper.waitForElementClickable(chooseFile, 10);
		chooseFile(filePath);
		waitHelper.waitForElementClickable(uploadToMergeJobQueueButton, 10);
		clickOnUploadToMergeJobQueueButton();
		Thread.sleep(2000);
		waitHelper.waitForElementClickable(includeCheckBox, 10);
		clickOnInclude();
		waitHelper.waitForElementClickable(addToMergeJobQueueButton, 10);
		clickOnAddToMergeJobQueueButton();
		
		tb1= new TestBase();
		String scr=	tb1.getScreenShot("wlupload");
		test.log(LogStatus.PASS,test.addScreenCapture(scr));

	}
	
	public void status_Check(int n) throws InterruptedException
	{
		WebElement status;
		String statusTitle; 
		
		int count = 1;
		boolean condition = true;
		while (condition ) {
		 waitHelper.waitForElementClickable(getStatus(), 10000);
			//wait.until(ExpectedConditions.elementToBeClickable(getStatus()));
		 statusTitle = getStatus().getAttribute("title");
			System.out.println("status title"+statusTitle);
		if (statusTitle.equalsIgnoreCase("In progress")) {
					Thread.sleep(n);
				System.out.println("in progress");
				condition =true;
				count ++;
				System.out.println(count);
				if( count >10000)
				{
					break;
				}								
			}
			
				
			else if(statusTitle.contains("Error"))
			{
				System.out.println("Test case is failed");
				condition = false;
			}
			
			else if(statusTitle.contains("Indexing complete"))
			{
				System.out.println("Test case is passed");
				condition = false;
			}
			
			
		}
	}
	}

/*	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}*/

	
