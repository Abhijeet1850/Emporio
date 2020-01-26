package com.test.emporio.helper.generic;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class NestedMenu {

	private WebDriver driver;
	private Actions action;

	public NestedMenu(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
	}

	Consumer<By> hover = (By by) -> {
		action.moveToElement(driver.findElement(by)).perform();
	};

	BiPredicate<By, String> isSubMenuPresent = (by, text) -> {
		return driver.findElement(by).getText().equalsIgnoreCase(text);

	};

	public void mouseHoverOnMenuItem(By by) {
		action.moveToElement(driver.findElement(by)).perform();
	}

	public boolean isSubMenuPresent(By by, String text) {
		return (driver.findElement(by).getText().equalsIgnoreCase(text));
	}

	public void clickonMenuItem(By by) {
		action.moveToElement(driver.findElement(by)).click().build().perform();
	}

}
