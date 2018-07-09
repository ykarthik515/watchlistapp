package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.clari5.helper.logger.LoggerHelper;
import com.clari5.helper.waitHelper.WaitHelper;
import com.clari5.testBase.DataSource;

public class PaymentScreeningp {

	WebDriver driver;
	private WaitHelper waitHelper;
	private static Logger log = LoggerHelper.getLogger(WlUpload.class);
	Select select;
	
	public PaymentScreeningp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(xmlContent, DataSource.getExplicitWait());
	}
	
	@FindBy(xpath="//textarea[@id='xmlContent']")
	WebElement xmlContent;
	
	@FindBy(xpath="//select[@id='messageType']")
	WebElement selectMsgType;
	
	@FindBy(xpath="//div[@class='btn btn-primary']")
	WebElement clickOnSubmit;
	
	@FindBy(xpath="//div[@class='alert ng-trigger ng-trigger-slideTrigger alert-success']/span[1]")
	WebElement output;
	
	public void xmlContent(String xmlfile) {
		log.info("xml data");
		xmlContent.sendKeys(xmlfile);
	}

	public void selectMsgType(String msgType) {
		log.info("selecting the message type as :" +msgType);
		select = new Select(selectMsgType);
		select.selectByVisibleText(msgType);
	}

	public void clickOnSubmit() {
		log.info("clicking on submit button");
		clickOnSubmit.click();
	}

	public WebElement getOutput() {
		return output;
	}
	
	
	public void paymentSCreeningMethod(String xmlfile, String msgType) {
		
		waitHelper.waitForElement(xmlContent, 10);
		xmlContent(xmlfile);
		waitHelper.waitForElement(selectMsgType, 10);
		selectMsgType(msgType);
		waitHelper.waitForElementClickable(clickOnSubmit, 10);
		clickOnSubmit();
		
	}

}
