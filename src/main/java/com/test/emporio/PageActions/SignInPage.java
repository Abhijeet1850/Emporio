package com.test.emporio.PageActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.test.emporio.TestBase.TestBase;
import com.test.emporio.helper.configReader.PropertyReader;
import com.test.emporio.helper.logger.LoggerHelper;
import com.test.emporio.helper.wait.WaitHelper;

public class SignInPage {

	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@FindBy(css=".button-wrap>a")
	WebElement continueAsGuestBtn;
	
	
	   
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}
	
	
	public void init() {
		log.info("log in to the app");
		driver.get("https://www.bestbuy.com/");
		tb.captureScreen("bestbuy homepage");
	}
	
}
