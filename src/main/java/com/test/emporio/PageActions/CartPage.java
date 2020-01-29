package com.test.emporio.PageActions;

import java.util.List;

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

public class CartPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@FindBy(css = "a[aria-label='Update Location']")
	WebElement updateLocation;

	@FindBy(css = "input#availability-radio-1")
	WebElement shipTo;

	@FindBy(css = "input#location")
	WebElement zipCode;

	@FindBy(css = ".btn.btn-secondary.btn-md")
	WebElement zipUpdateBtn;

	@FindBy(css = ".listing-header__button>button")
	WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void selectShipToAddress(String zip) {
		log.info("Select Shipping option");
		if (!shipTo.isSelected())
			shipTo.click();
	}

	public void clickCheckout() {
		log.info("Select Checkout button");
		wait.waitForElement(checkoutBtn, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

}
