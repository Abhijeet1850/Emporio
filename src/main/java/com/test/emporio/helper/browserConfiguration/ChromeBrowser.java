package com.test.emporio.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.test.emporio.helper.resource.ResourceHelper;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--test-type");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-popup-blocking");
		DesiredCapabilities chromeCap = DesiredCapabilities.chrome();
		chromeCap.setJavascriptEnabled(true);
		options.setCapability(ChromeOptions.CAPABILITY, chromeCap);
		if (System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
	}

	public WebDriver getChromeDriver(String path) {
		ChromeOptions cap = getChromeOptions();
		if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath(path + "\\Linux\\chromedriver"));
			return new ChromeDriver(cap);

		} else if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath(path + "\\Windows\\chromedriver.exe"));
			return new ChromeDriver(cap);
		}

		else if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath(path + "\\Mac\\chromedriver"));
			return new ChromeDriver(cap);
		}
		return null;
	}

}
