package com.test.emporio.TestBase;

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
import com.test.emporio.helper.browserConfiguration.BrowserType;
import com.test.emporio.helper.browserConfiguration.DriverFactory;
import com.test.emporio.helper.configReader.PropertyReader;
import com.test.emporio.helper.logger.LoggerHelper;
import com.test.emporio.helper.resource.ResourceHelper;
import com.test.emporio.helper.wait.WaitHelper;

public class TestBase {

	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	PropertyReader reader = new PropertyReader();
	DriverFactory driverfactory = new DriverFactory();

	public void setupDriver(BrowserType btype, String path) {
		driver = driverfactory.getBrowser(btype, path);
		log.info("Initialize Web driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.setPageLoadTimeOut(reader.getPageLoadTime(), TimeUnit.SECONDS);
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
