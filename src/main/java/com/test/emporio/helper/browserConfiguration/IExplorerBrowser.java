package com.test.emporio.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.test.emporio.helper.resource.ResourceHelper;

public class IExplorerBrowser {

	public InternetExplorerOptions getIEOptions() {

		DesiredCapabilities ieCap = DesiredCapabilities.internetExplorer();

		ieCap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		ieCap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieCap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieCap.setJavascriptEnabled(true);

		InternetExplorerOptions options = new InternetExplorerOptions(ieCap);
		return options;
	}

	public WebDriver getIEDriver(String path) {

		InternetExplorerOptions cap = getIEOptions();
		if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath(path + "\\Linux\\chromedriver"));
			return new InternetExplorerDriver(cap);

		} else if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.ie.driver",
					ResourceHelper.getResourcePath(path + "\\Windows\\IEDriverServer.exe"));
			return new InternetExplorerDriver(cap);
		}

		/*
		 * else if (System.getProperty("os.name").contains("Mac")) {
		 * System.setProperty("webdriver.ie.driver", ResourceHelper .getResourcePath(
		 * "src\\main\\java\\com\\test\\automation\\MindValley\\Drivers\\Mac\\chromedriver"
		 * )); return new InternetExplorerDriver(cap); }
		 */
		return null;
	}

}
