package com.test.emporio.helper.generic;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.test.emporio.helper.logger.LoggerHelper;

public class JavaScriptHelper {

	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	private WebDriver driver;

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("javascriptHelper object initialized");
	}

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}

	public void scrollToElement(WebElement element) {
		log.info("scrolling to element");
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
	}

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("scrolling to element and clicking it ");
	}

	public void scrollIntoView(WebElement element) {
		log.info("scrolling to element");
		executeScript("arguments[0].scrollIntoView()", element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("scrolling and clicking element");
	}

	public void scrollDownVertically() {
		log.info("scrolling down vertically");
		executeScript("window.scrollTo(0,document.body.scrollHeight");
	}

	public void scrollUpVertically() {
		log.info("scrolling up vertically");
		executeScript("window.scrollTo(0,-document.body.scrollHeight");
	}

	public void scrollDownByPixel(int pixel) {
		log.info("scrolling up vertically");
		executeScript("window.scrollTo(0," + pixel + ")");
	}

	public void scrollUpByPixel(int pixel) {
		log.info("scrolling up vertically");
		executeScript("window.scrollTo(0,-" + pixel + ")");
	}

	public void zoomInBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	public void zoomInBypercentage(int x) {
		executeScript("document.body.style.zoom='" + x + "%'");
	}

}
