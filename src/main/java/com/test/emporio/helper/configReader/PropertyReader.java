package com.test.emporio.helper.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.test.emporio.helper.browserConfiguration.BrowserType;
import com.test.emporio.helper.resource.ResourceHelper;

public class PropertyReader {

	public static FileInputStream fis;
	public static Properties mvConfig = new Properties();

	public PropertyReader() {
		try {
			fis = new FileInputStream(
					ResourceHelper.getResourcePath("\\src\\main\\resources\\ConfigFile\\emporio.properties"));
			mvConfig.load(fis);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Long getImplicitWait() {
		return Long.parseLong(mvConfig.getProperty("implicitwait"));
	}

	public Long getExplicitWait() {
		return Long.parseLong(mvConfig.getProperty("explicitwait"));
	}
	
	public Long getLessExplicitWait() {
		return Long.parseLong(mvConfig.getProperty("LessExplicitWait"));
	}

	public Long getPageLoadTime() {
		return Long.parseLong(mvConfig.getProperty("pageloadTime"));
	}

	public Long getPollingTime() {
		return Long.parseLong(mvConfig.getProperty("pollingTime"));
	}

	public BrowserType getBroswerType() {
		return BrowserType.valueOf(mvConfig.getProperty("broswertype"));
	}

	public String getBroswerName() {
		return mvConfig.getProperty("browserName");
	}

	public String getHost() {
		return mvConfig.getProperty("host");
	}

	public String getBroswerVersion() {
		return mvConfig.getProperty("browserVersion");
	}

	public String getPlatform() {
		return mvConfig.getProperty("platform");
	}

	public String getAppRedirectURL() {
		return mvConfig.getProperty("AppLogin_NotSuccessFull_URL");
	}
}
