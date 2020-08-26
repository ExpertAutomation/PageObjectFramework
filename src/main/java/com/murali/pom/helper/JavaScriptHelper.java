package com.murali.pom.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.murali.pom.helper.LoggerHelper;;

public class JavaScriptHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialized...");
	}
	
	public Object executeScript(String script) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return jse.executeScript(script);
	}
	
	public Object executeScript(String script, Object...args) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return jse.executeScript(script, args);
	}
	
	public void scrollToElement(WebElement element) {
		log.info("scroll to WebElement...");
		executeScript("window.scrollTo(arguments[0],argument[1])", element.getLocation().x, element.getLocation().y);
	}
	
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("element is clicked :" + element.toString());
	}
	
	public void scrollIntoView(WebElement element) {
		log.info("scroll till web element");
		executeScript("arguments[0].scrollIntoView()",element);
	}
	
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("element is clicked :" + element.toString());
	}
	
	public void scrollDownVertically() {
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollUpVertically() {
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
	
	/**
	 * This method will scroll down till given pixel (e.g:1500)
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBY(0,"+pixel+")");
	}
	
	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBY(0,-"+pixel+")");
	}
	
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	
	/**
	 * This method will zoom screen by 60%
	 */
	public void zoomInBy60Percentage() {
		executeScript("document.body.style.zoom='60%'");
	}
	
	/**
	 * This is click method based on JavaScript executer
	 * @param element
	 */
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();",element);
	}
	

}
