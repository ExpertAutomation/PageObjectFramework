package com.murali.pom.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);
	
	public FrameHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * This method will switchToFrame based on frame index
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("switched to : "  + frameIndex + " frame");
	}
	
	/**
	 * This method will switchToFrame based on frame name
	 * @param frameNamex
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to : "  + frameName + " frame");
	}
	
	/**
	 * This method will switchToFrame based on frame web element
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to frame : "  + element.toString());
	}
	
	

}
