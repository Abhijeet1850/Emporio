package com.test.emporio.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.test.emporio.helper.resource.ResourceHelper;
 

public class FirefoxBrowser {

	public FirefoxOptions getFirefoxOptions() {

		DesiredCapabilities firefoxCap = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);

		firefoxCap.setCapability(FirefoxDriver.PROFILE, profile);
		firefoxCap.setCapability("marionette", true);
		FirefoxOptions options = new FirefoxOptions(firefoxCap);

		if (System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
	}

	public WebDriver getFirefoxDriver(String path) {
		FirefoxOptions cap = getFirefoxOptions();

		if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath(
					path + "\\Linux\\chromedriver"));
			return new FirefoxDriver(cap);

		} else if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath(
					path + "\\Windows\\geckodriver.exe"));
			return new FirefoxDriver(cap);
		}

		else if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper
					.getResourcePath(path + "\\Mac\\chromedriver"));
			return new FirefoxDriver(cap);
		}
		return null;
	}
}
