package com.murali.pom.prjbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.murali.pom.browserfactory.BrowserType;
import com.murali.pom.browserfactory.ChromeBrowser;
import com.murali.pom.browserfactory.FirefoxBrowser;
import com.murali.pom.browserfactory.IEBrowser;
import com.murali.pom.browserfactory.config.ObjectReader;
import com.murali.pom.browserfactory.config.PropertyReader;
import com.murali.pom.helper.LoggerHelper;
import com.murali.pom.helper.ResourceHelper;
import com.murali.pom.helper.WaitHelper;
import com.murali.pom.utils.ExtentManager;

public class MuraliBase {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(MuraliBase.class);
	public static File screenShots;
	
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}
	
	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName()+" test started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName()+ " is PASS");
			String imagePath = captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
	
	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		screenShots = new File(ResourceHelper.getResourcePath("/screenshots"));
		setUpDriver(ObjectReader.reader.getBrowserType());
	}
	
	@AfterTest
	public void afterTest() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	public WebDriver getBrowser(BrowserType btype) throws Exception {
		try {
			switch(btype) {
			case Chrome:
				//get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions options = chrome.getChromeOptions();
				return chrome.getChromeDriver(options);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions option= firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(option);
			case InternetExplorer:
				IEBrowser ie = IEBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
			default:
				throw new Exception("Driver not found: "+btype.name());
			}
		} catch(Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	public void setUpDriver(BrowserType btype) throws Exception {
		driver = getBrowser(btype);
		log.info("Initialize web driver : " +driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public String captureScreen(String fileName) {
		if(driver == null) {
			log.info("driver is null");
			return null;
		}
		if(fileName == "") {
			fileName ="blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
			
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
		try {
			destFile = new File(screenShots+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return destFile.toString();
	}
	
	/**
	public void getNavigationScreen(WebDriver driver) {
		log.info("Capturing UI navigation screen");
		String screen = captureScreen("", driver);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/

}
