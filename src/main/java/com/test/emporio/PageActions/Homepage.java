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

public class Homepage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(Homepage.class);
	TestBase tb = new TestBase();
	WaitHelper wait;
	PropertyReader reader = new PropertyReader();
	Actions action;

	@FindBy(css = "div[lang='en']>.country-selection>a.us-link")
	WebElement chooseUSCountry;

	@FindBy(css = ".language-menu>ul>li>button[lang='en']")
	WebElement engLanguage;

	@FindBy(css = "input[title='Sign Up']")
	WebElement signUpPopMenu;

	@FindBy(css = "div.modal-content>div.modal-header>button.close")
	List<WebElement> modalClose;

	@FindBy(css = "#gh-search-input")
	WebElement productSearchInput;

	@FindBy(css = ".header-search-button")
	WebElement productSearchSubmit;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		action = new Actions(driver);
	}

	public void init() {
		log.info("log in to the best buy webapp");
		driver.get("https://www.bestbuy.com/");
		tb.captureScreen("bestbuy homepage");
	}

	public boolean isChooseCountryPopupPresent() {
		try {
			if (wait.waitForElement(engLanguage, reader.getLessExplicitWait(), reader.getPollingTime()).isDisplayed())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void chooseLanguage() {
		log.info("selecting English language");
		engLanguage.click();
	}

	public void chooseCountry() {
		log.info("Selecting US as the country");
		chooseUSCountry.click();
	}

	public boolean isSignUpPopVisible() {
		try {
			if (wait.waitForElement(signUpPopMenu, reader.getExplicitWait(), reader.getPollingTime()).isDisplayed())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Signup Popup is not visible");
			return false;
		}
	}

	public void closeSignUpPopup() {
		log.info("Singup Popup is closed");
		modalClose.get(1).click();
	}

	public void enterProductSearch(String searchText) {
		wait.waitForElement(productSearchInput, reader.getExplicitWait(), reader.getPollingTime()).sendKeys(searchText);
		log.info("product item search entered");
		wait.waitForMillis(3000);
		wait.waitForElement(productSearchSubmit, reader.getExplicitWait(), reader.getPollingTime()).click();

	}

}
