package com.test.emporio.helper.generic;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import com.test.emporio.helper.logger.LoggerHelper;

public class AlertHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
	}

	public Alert getAlert() {
		log.info("alert text :" + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
	}

	public void dismissAlert() {
		getAlert().dismiss();
	}

	public String getAlertText() {
		return getAlert().getText();
	}

	public void acceptAlertPrompt(String text) {
		getAlert().sendKeys(text);
	}

}
