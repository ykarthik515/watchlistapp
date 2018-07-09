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


public class WlUpload extends TestBase {
	
	WebDriver driver;
	private WaitHelper waitHelper;
	private static Logger log = LoggerHelper.getLogger(WlUpload.class);
	TestBase tb1;


	public WlUpload(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(clickOnwlUploadButton, DataSource.getExplicitWait());
		
	}
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement clickOnwlUploadButton;

	@FindBy(xpath="//select[@name='fileType']")
	WebElement selectBoxofFileType;
	
	@FindBy(xpath="//input[@id='uploadWl']")
	WebElement chooseFilebox;
	
	@FindBy(xpath="//div[@class='btn btn-primary']")
	WebElement clickOnfileUploadButton;
	
	@FindBy(xpath="//button[@class='swal2-confirm swal2-styled']")
	WebElement clickOnOKbutton;
	
	@FindBy(xpath="//button[@class='btn btn-default checkBox-size']")
	WebElement clickOnUploadedFileCheckbox;
	
	@FindBy(xpath="//button[contains(text(),'Index')]")
	WebElement clickOnIndexbutton;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-ok-circle success-alert-color-custom']")
	WebElement successStatussymbol;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-exclamation-sign fail-alert-color-custom']")
	WebElement failureStatussymbol;
	
	@FindBy(xpath="//tbody[@class='ui-datatable-data ui-widget-content']/tr/td[1]")
	WebElement tableFilenamesdata;
	
	@FindBy(xpath="//tr[1]/td[6]/span")
	WebElement firstRowStatussymbol;
	Select select;

	public void clickOnwlUploadButton() {
		log.info("clicking on upload button");
		test.log(LogStatus.INFO, "click on upload button");
		clickOnwlUploadButton.click();
		//return clickOnwlUploadButton;
	}

	public void selectBoxofFileType(String fileType) {
		log.info("selecting the file type ");
		test.log(LogStatus.INFO, "selecting the file type: "+fileType);
		//return selectBoxofFileType;
		select =new Select(selectBoxofFileType);
		select.selectByVisibleText(fileType);
		
	}

	public void chooseFilebox(String filePath) {
		//return chooseFilebox;
		log.info("select the file path which needs to be uploaded");
		test.log(LogStatus.INFO, "selecting the file path which needs to be uploaded: "+filePath);
		chooseFilebox.sendKeys(filePath);
	}

	public void clickOnfileUploadButton() {
	
		log.info("click on file upload button");	
		test.log(LogStatus.INFO, "clicking on file upload button");
		clickOnfileUploadButton.click();
	}

	public void clickOnOKbutton() {
		//return clickOnOKbutton;
		log.info("click on ok button");
		test.log(LogStatus.INFO, "clicking on ok button");
		clickOnOKbutton.click();
		
	}

	public void clickOnUploadedFileCheckbox() {
		//return clickOnUploadedFileCheckbox;
		log.info("selecting the file to index");
		test.log(LogStatus.INFO, "selecting the file to index");
		clickOnUploadedFileCheckbox.click();
	}

	public void clickOnIndexbutton() {
		//return clickOnIndexbutton;
		log.info("click on Index button");
		test.log(LogStatus.INFO, "clicking on Index button");
		clickOnIndexbutton.click();
	}

	public void successStatussymbol() {
		//return successStatussymbol;
		log.info("click on success pop up button");
		test.log(LogStatus.INFO, "clicking on success pop up button");
		successStatussymbol.click();
		
	}

	public void failureStatussymbol() {
		//return failureStatussymbol;
	}

	public void tableFilenamesdata() {
		//return tableFilenamesdata;
	}

	public void firstRowStatussymbol() {
		//return firstRowStatussymbol;
	}
	
	
	public void uploadFile(String fileType,String filePath) throws InterruptedException, IOException
		{
		waitHelper.waitForElementClickable(clickOnwlUploadButton, 10);
		clickOnwlUploadButton();
		waitHelper.waitForElement(selectBoxofFileType, 10);
		selectBoxofFileType(fileType);
		waitHelper.waitForElement(chooseFilebox, 10);
		chooseFilebox(filePath);
		waitHelper.waitForElementClickable(clickOnfileUploadButton, 10);
		clickOnfileUploadButton();
		waitHelper.waitForElementClickable(clickOnOKbutton, 10);
		clickOnOKbutton();
		waitHelper.waitForElementClickable(clickOnUploadedFileCheckbox, 20);
		clickOnUploadedFileCheckbox();
		waitHelper.waitForElementClickable(clickOnIndexbutton, 10);
		tb1= new TestBase();
		String scr=	tb1.getScreenShot("wlupload");
		test.log(LogStatus.PASS,test.addScreenCapture(scr));

		clickOnIndexbutton();
		
		}
}


