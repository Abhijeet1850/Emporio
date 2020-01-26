package com.test.emporio.helper.browserConfiguration;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.test.emporio.helper.logger.LoggerHelper;

public class RemoteBroswer {

	RemoteWebDriver driver;
	private Logger log = LoggerHelper.getLogger(RemoteBroswer.class);
	protected static Capabilities capability;
	protected static DesiredCapabilities dCap;
	protected static String node;
	protected static String oPlatform;
	public String browserName;
	public String browserVersion;
	public String platform;
	public String host;

	public RemoteBroswer(String browserName, String browserVersion, String platform, String host) {
		log.info("WebDriver Intialised with " + "browserVersion: " + browserVersion + " platform : " + platform
				+ " browserName : " + browserName + " host: " + host);
		this.browserName = browserName;
		this.browserVersion = browserVersion;
		this.platform = platform;
		this.host = host;
	}

	public DesiredCapabilities setCapablity(String browserName) {
		ChromeBrowser ch = new ChromeBrowser();
		FirefoxBrowser firefox = new FirefoxBrowser();
		switch (browserName) {
		case "Chrome":
			capability = ch.getChromeOptions();
			dCap = new DesiredCapabilities(capability);
			// dCap.setBrowserName(browserName);
			// setPlatform(platform);
			// dCap.setVersion(browserVersion);
			return dCap;
		case "Firefox":
			capability = firefox.getFirefoxOptions();
			dCap = new DesiredCapabilities(capability);
			dCap.setBrowserName(browserName);
			setPlatform(platform);
			dCap.setVersion(browserVersion);
			return dCap;
		default:
			capability = ch.getChromeOptions();
			dCap = new DesiredCapabilities(capability);
			dCap.setBrowserName(browserName);
			setPlatform(platform);
			dCap.setVersion(browserVersion);
			return dCap;
		}
	}

	public RemoteWebDriver getRemoteInstance() {
		if (driver == null) {
			try {
				driver = new RemoteWebDriver(new URL(host), setCapablity(browserName));
				return driver;
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
		}
		return driver;
	}

	public void setPlatform(String platform) {
		if (platform.equals("linux")) {
			dCap.setPlatform(Platform.LINUX);
		} else {
			dCap.setPlatform(Platform.WINDOWS);
		}
	}

}
