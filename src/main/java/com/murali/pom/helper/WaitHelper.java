package com.murali.pom.helper;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.murali.pom.helper.LoggerHelper;

public class WaitHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.info("WaitHelper object created..");
	}
	
	/**
	 * This is ImplcitWait method
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit Wait has been set to: "+timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	/**
	 * This will help us to get WebDriverWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMilliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	/**
	 * This method will make sure element is visible
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting to :" + element.toString() + " for :" +  timeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMilliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info(" element is visible now ");
	}
	
	/**
	 * This method will make sure elementToBeClickable
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
		log.info("Waiting to :" + element.toString() + " for :" +  timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info(" element is clickable now ");
	}
	
	/**
	 * This method will make sure invisibilityOf element
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		log.info("waiting to :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
		return status;
	}
	
	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("waiting to :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
	}
	
	/**
	 * This method will give us fluentWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 * @return
	 */
	private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMilliSec))
				.ignoring(NoSuchElementException.class);
		return fluentWait;
	}
	
	/**
	 * This is will help us waitForElement
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilliSec
	 * @return 
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		Wait<WebDriver> fluentWait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
		fluentWait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/**
	 * This will give pageLoadTime in seconds
	 * @param timeout
	 * @param unit
	 */
	public void pageLoadTime(long timeout, TimeUnit unit) {
		log.info("waiting for page to load for : " + timeout + " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
	}
	
	/**
	 * This method will make sure element to be visible
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is visible now ");
	}
	

}
