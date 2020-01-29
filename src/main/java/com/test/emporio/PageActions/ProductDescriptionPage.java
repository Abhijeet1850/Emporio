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

public class ProductDescriptionPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	public static final String verifyAddToCartText = "Item added to cart";

	@FindBy(css = "div.fulfillment-add-to-cart-button>div>button")
	List<WebElement> addToCart;

	@FindBy(css = "span.success-text")
	WebElement addToCartSuccess;

	@FindBy(css = "a.cart-nav")
	WebElement goToCart;

	public ProductDescriptionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void addItemToCart() {
		tb.captureScreen("bestbuyproductdescription");
		log.info("Added Item to Cart");
		wait.waitForElement(addToCart.get(0), reader.getExplicitWait(), reader.getPollingTime()).click();
	}

	public boolean verifyIfItemAddedToCart() {
		if (wait.waitForElement(addToCartSuccess, reader.getExplicitWait(), reader.getPollingTime())
				.getAttribute("innerHTML").equals(verifyAddToCartText))
			return true;
		return false;
	}

	public void goToCart() {
		log.info("Select Go TO Cart function");
		wait.waitForElement(goToCart, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

}
