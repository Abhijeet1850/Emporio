package com.test.emporio.helper.browserConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.test.emporio.helper.configReader.PropertyReader;
import com.test.emporio.helper.logger.LoggerHelper;
import com.test.emporio.helper.resource.ResourceHelper;
import com.test.emporio.helper.wait.WaitHelper;

public class DriverFactory {

	private Logger log = LoggerHelper.getLogger(DriverFactory.class);
	public static WebDriver driver;
	PropertyReader reader = new PropertyReader();
	protected static DesiredCapabilities capability;
	protected static String node;
	protected static String browserName;
	protected static String browserVersion;
	protected static String platform;
	protected static String host;
	private static ThreadLocal<RemoteBroswer> remoteDriver = new ThreadLocal<RemoteBroswer>();

	public static RemoteBroswer getDriver() {
		return remoteDriver.get();
	}

	public static void setDriver(RemoteBroswer driver) {
		remoteDriver.set(driver);
	}

	public static void setRemoteDriverProperties() {
		PropertyReader reader = new PropertyReader();
		browserName = reader.getBroswerName();
		browserVersion = reader.getBroswerVersion();
		platform = reader.getPlatform();
		host = reader.getHost();

	}

	public WebDriver getBrowser(BrowserType btype, String path) {
		try {
			switch (btype) {
			case Chrome:
				ChromeBrowser chrome = new ChromeBrowser();
				return chrome.getChromeDriver(path);
			case Firefox:
				FirefoxBrowser firefox = new FirefoxBrowser();
				return firefox.getFirefoxDriver(path);
			case Iexplorer:
				IExplorerBrowser ie = new IExplorerBrowser();
				return ie.getIEDriver(path);
			case Remote:
				setRemoteDriverProperties();
				RemoteBroswer rm = new RemoteBroswer(browserName, browserVersion, platform, host);
				setDriver(rm);
				return remoteDriver.get().getRemoteInstance();
			default:
				throw new Exception("Driver Not Found" + btype.name());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	public void setupDriver(BrowserType btype, String path) {
		driver = getBrowser(btype, path);
		log.info("Initialize Web driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.setPageLoadTimeOut(reader.getPageLoadTime(), TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	public String captureScreen(String fileName) {
		if (driver == null)
			log.info("driver is null");

		if (fileName.equals(""))
			log.info("file name is null");

		File destFile = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		destFile = new File(ResourceHelper.getResourcePath("/src/main/resources/Screenshots") + "/" + fileName + "_"
				+ formater.format(cal.getTime()) + ".png");
		try {
			Files.copy(srcFile.toPath(), destFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("snapshot taken at " + destFile.toString());
		return destFile.toString();
	}

	public void shutDownDriver() {
		driver.quit();
	}

}
