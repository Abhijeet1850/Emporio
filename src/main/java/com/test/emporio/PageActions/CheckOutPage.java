package com.test.emporio.PageActions;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.test.emporio.TestBase.TestBase;
import com.test.emporio.helper.configReader.PropertyReader;
import com.test.emporio.helper.generic.JavaScriptHelper;
import com.test.emporio.helper.logger.LoggerHelper;
import com.test.emporio.helper.wait.WaitHelper;

public class CheckOutPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	JavaScriptHelper jsHelper;
	PropertyReader reader = new PropertyReader();
	Actions action;

	public final String warningMessage = "Heads up";

	@FindBy(css = ".button-wrap>a")
	WebElement continueAsGuestBtn;

	@FindBy(css = "input.form-control")
	List<WebElement> regForm;

	@FindBy(css = "input.form-control.autocomplete__input")
	WebElement address1;

	@FindBy(css = ".button--continue>button")
	WebElement continuePayment;

	@FindBy(css = "div.modal-content")
	WebElement confirmAddressPopup;

	@FindBy(css = "div.modal-content>div>button.btn.btn-secondary.btn-block")
	WebElement keepAddressBtn;

	@FindBy(css = "input#save-for-billing-address-ui_address_2")
	WebElement sameAsBillingAddress;

	@FindBy(css = "div.error-spacing>div>span")
	WebElement warningUpdate;

	@FindBy(css = "#optimized-cc-card-number")
	WebElement creditCard;

	@FindBy(css = "select[name='expiration-month']")
	WebElement expMonth;

	@FindBy(css = "select[name='expiration-year']")
	WebElement expYear;

	@FindBy(css = "#credit-card-cvv")
	WebElement secCode;

	@FindBy(css = ".button--place-order>button")
	WebElement placeOrder;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
		jsHelper = new JavaScriptHelper(driver);
	}

	public void selectContinueAsGuest() {
		wait.waitForElement(continueAsGuestBtn, reader.getExplicitWait(), reader.getPollingTime()).click();
	}

	public void fillShippingInfo(Map<String, String> shippingInfo) {
		log.info("enter Shipping info");
		wait.waitForElement(regForm.get(0), reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(shippingInfo.get("FirstName"));
		wait.waitForElement(regForm.get(1), reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(shippingInfo.get("LastName"));
		wait.waitForElement(address1, reader.getExplicitWait(), reader.getPollingTime()).sendKeys("27 ");
		wait.waitForMillis(2500);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		wait.waitForMillis(1500);
		wait.waitForElement(regForm.get(3), reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(shippingInfo.get("Address2"));
		wait.waitForElement(regForm.get(5), reader.getExplicitWait(), reader.getPollingTime()).clear();
		wait.waitForMillis(1500);
		wait.waitForElement(regForm.get(5), reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(shippingInfo.get("zipCode"));

		log.info("select billing info same as shipping info");

		jsHelper.scrollIntoView(sameAsBillingAddress);
		if (!sameAsBillingAddress.isSelected())
			sameAsBillingAddress.click();

		wait.waitForElement(regForm.get(6), reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(shippingInfo.get("EmailAddress"));
		wait.waitForElement(regForm.get(7), reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(shippingInfo.get("PhoneNumber"));
		wait.waitForElement(continuePayment, reader.getExplicitWait(), reader.getPollingTime()).click();
		wait.waitForMillis(3000);
		log.info("Select continue to Pay");
		continueWithAddressEntered();

	}

	public void continueWithAddressEntered() {
		try {
			if (wait.waitForElement(confirmAddressPopup, reader.getLessExplicitWait(), reader.getPollingTime())
					.isDisplayed()) {
				wait.waitForMillis(2000);
				log.info("Select Keep the address entered earlier");
				wait.waitForElement(keepAddressBtn, reader.getLessExplicitWait(), reader.getPollingTime()).click();
				wait.waitForMillis(4000);
				System.out.println(checkIfAddressHeadsUpMessageShown());
				jsHelper.scrollIntoViewAndClick(
						wait.waitForElement(continuePayment, reader.getLessExplicitWait(), reader.getPollingTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkIfAddressHeadsUpMessageShown() {
		try {
			if (wait.waitForElement(warningUpdate, reader.getLessExplicitWait(), reader.getPollingTime()).isDisplayed())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void enterPaymentDetails(Map<String, String> paymentInfo) {

		wait.waitForElement(creditCard, reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(paymentInfo.get("cardNumber"));
		Select month = new Select(wait.waitForElement(expMonth, reader.getExplicitWait(), reader.getPollingTime()));
		Select year = new Select(wait.waitForElement(expYear, reader.getExplicitWait(), reader.getPollingTime()));
		month.selectByValue(paymentInfo.get("monthExp"));
		year.selectByValue(paymentInfo.get("yearExp"));
		wait.waitForElement(secCode, reader.getExplicitWait(), reader.getPollingTime())
				.sendKeys(paymentInfo.get("CVV"));

	}

	public void placeOrder() {
		wait.waitForElement(placeOrder, reader.getLessExplicitWait(), reader.getPollingTime()).click();
		System.out.println(wait.waitForElement(warningUpdate, reader.getLessExplicitWait(), reader.getPollingTime())
				.getAttribute("innerHTML"));
	}
}
