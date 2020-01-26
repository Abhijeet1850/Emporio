package com.test.emporio.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	private static boolean root =false;
	public final static String log4jconfigpath = "log4j.properties";
	public static Logger getLogger(Class cls)
	{
		if(root)
		{
			return Logger.getLogger(cls);	
		}
		PropertyConfigurator.configure(log4jconfigpath);
		root = true;
		return Logger.getLogger(cls);	
	}
}
