package com.test.emporio.helper.generic;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.test.emporio.helper.logger.LoggerHelper;

public class DropDownHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("Select using value and value is :" + value);
		select.selectByValue(value);
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("Select using index and index is :" + index);
		select.selectByIndex(index);
	}

	public void selectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("Select using visibleText and visibleText is :" + visibleText);
		select.selectByVisibleText(visibleText);
	}

	public void deSelectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("Deselect using value and value is :" + value);
		select.deselectByValue(value);
	}

	public void deSelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deSelect using index and index is :" + index);
		select.deselectByIndex(index);
	}

	public void deSelectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("Deselect using visibleText and visibleText is :" + visibleText);
		select.deselectByVisibleText(visibleText);
	}

	public List<String> getAllDropdownData(WebElement element) {
		Select select = new Select(element);
		List<String> values = new ArrayList<String>();
		for (WebElement e : select.getOptions()) {
			values.add(e.getText());
		}
		return values;
	}

	public List<String> getAllSelectedData(WebElement element) {
		Select select = new Select(element);
		List<String> values = new ArrayList<String>();
		for (WebElement e : select.getAllSelectedOptions()) {
			values.add(e.getText());
		}
		return values;
	}

}
