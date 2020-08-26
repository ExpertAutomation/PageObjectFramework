package com.murali.pom.helper;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.murali.pom.helper.LoggerHelper;

public class WindowHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);
	
	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * This method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will switch to child window based on index
	 * @param index
	 */
	public void switchToParentWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for(String window : windows) {
			if(i == index) {
				log.info("switched to : " + index + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}
	
	/**
	 * This method will close all tabbed windows and 
	 * switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		
		for(String window : windows) {
			if(!window.equalsIgnoreCase(mainWindow)) {
				driver.close();
			}
		}
		log.info("switched to main window");
		driver.switchTo().window(mainWindow);
	}
	
	/**
	 * This method will navigate back on browser
	 */
	public void navigateBack() {
		log.info("navigating to back");
		driver.navigate().back();
	}
	
	/**
	 * This method will navigate to forward on browser
	 */
	public void navigateForward() {
		log.info("navigating to farword");
		driver.navigate().forward();;
	}
	
	

}
