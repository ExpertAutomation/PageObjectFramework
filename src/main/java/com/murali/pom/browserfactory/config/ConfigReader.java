package com.murali.pom.browserfactory.config;

import com.murali.pom.browserfactory.BrowserType;

public interface ConfigReader {
	
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	

}
