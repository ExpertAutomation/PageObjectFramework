package com.murali.pom.browserfactory.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.murali.pom.browserfactory.BrowserType;
import com.murali.pom.helper.ResourceHelper;

public class PropertyReader implements ConfigReader{
	
	private static FileInputStream file;
	public static Properties prop;
	
	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("/src/main/resources/config/config.properties");
			file = new FileInputStream(new File(filePath));
			prop = new Properties();
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(prop.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(prop.getProperty("browserType"));
	}

}
