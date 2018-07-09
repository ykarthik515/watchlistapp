package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
	
	WebDriver driver;
	
	public LogoutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='userInfoDropDown']")
	WebElement logoutLink;
	
	@FindBy(xpath="//a[@class='customNav pull-right']")
	WebElement logoutButton;

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}


	
	

}
