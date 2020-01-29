package com.test.emporio.PageActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.emporio.TestBase.TestBase;
import com.test.emporio.helper.configReader.PropertyReader;
import com.test.emporio.helper.generic.JavaScriptHelper;
import com.test.emporio.helper.logger.LoggerHelper;
import com.test.emporio.helper.wait.WaitHelper;

public class ProductListPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	JavaScriptHelper jsHelper;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@FindBy(css = "fieldset[name='Price']>ul>li>div>div>div>label>div>i")
	List<WebElement> priceFilter;

	@FindBy(css = "div.fulfillment-add-to-cart-button>div>button")
	List<WebElement> addToCart;

	@FindBy(css = "div.image-column>a")
	List<WebElement> productImage;

	public ProductListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		jsHelper = new JavaScriptHelper(driver);
		action = new Actions(driver);
	}

	public void selectPriceFilter() {
		jsHelper.scrollIntoViewAndClick(priceFilter.get(1));
		log.info("price Filter selected");
		wait.waitForMillis(2500);
	}

	public void selectFirstAvailableProduct() {
		addToCart = wait.waitForElementsLocatedBy(By.cssSelector("div.fulfillment-add-to-cart-button>div>button"),
				reader.getLessExplicitWait(), reader.getPollingTime());
		for (int i = 0; i < addToCart.size(); i++) {
			if (addToCart.get(i).isEnabled()) {
				productImage.get(i).click();
				break;
			}
		}
		log.info("item added to cart");
	}

}
