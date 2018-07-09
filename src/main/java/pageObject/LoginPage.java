package pageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.clari5.testBase.DataSource;
import com.clari5.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

import com.clari5.helper.logger.LoggerHelper;
import com.clari5.helper.waitHelper.WaitHelper;

public class LoginPage extends TestBase {
	
	TestBase tb;
	MainPage mp;
	private WebDriver driver;
	private WaitHelper waitHelper;
	WebElement err;
	private static Logger log = LoggerHelper.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(username, DataSource.getExplicitWait());
	}
	
	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submit;

	@FindBy(xpath="//button[@type='button'][contains(text(),'Yes, Login!')]")
	WebElement yesLogingButton;
	
	public void username(String userName) {
		log.info("entering username:" + userName);
		this.username.sendKeys(userName);	
		}

	public void password(String password) {
		log.info("entering password: "+password);
		this.password.sendKeys(password);
	}

	public void clickOnSubmit() {
		log.info("click on submit button");
		submit.click();
	}

	public void clickOnYesLoginButton() {
		log.info("clicked on yesLogin button");
		yesLogingButton.click();
	}
	
	public boolean yesLoginIsEnabled() {
	return	yesLogingButton.isEnabled();
		
	}
	
	public MainPage loginToApplication(String userName, String password) throws IOException {
		
		try {
		waitHelper.waitForElement(username, 10);
		username(userName);
		waitHelper.waitForElement(this.password, 10);
		password(password);
		waitHelper.waitForElement(submit, 10);
		clickOnSubmit();
		
		
		/*try{
			if(!(driver.findElement(By.xpath("//html//ul[@class='nav navbar-nav']/li[3]/a[1]")).isEnabled())){
					
			}
		}
			catch (Exception e) {
				driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Yes, Login!')]")).click();
				
			}
		
*/		}
		
		catch(Exception ex) {
			
			err = driver.findElement(By.xpath("//div[@class='errMsg text-danger']"));
			waitHelper.waitForElement(err, 10);

			
			TestBase tb1= new TestBase();
		String scr=	tb1.getScreenShot("loginfail");
		test.log(LogStatus.FAIL,test.addScreenCapture(scr));
			
		}

		return new MainPage(driver);

	}
	
	
	
}
