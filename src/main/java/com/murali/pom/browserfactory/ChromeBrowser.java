package com.murali.pom.browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.murali.pom.helper.ResourceHelper;

public class ChromeBrowser {
	
	public ChromeOptions getChromeOptions() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");
		
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		
		options.setCapability(ChromeOptions.CAPABILITY, chrome);
		
		//Linux
		if(System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
	}
	
	public WebDriver getChromeDriver(ChromeOptions cap) {
		if(System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/chromedriver.exe"));
			return new ChromeDriver(cap);
		} else if(System.getProperty("os.name").contains("Mac")){
		    System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/chrome"));
		    return new ChromeDriver(cap);
		} else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver(cap);
		}
		return null;
	}

}
