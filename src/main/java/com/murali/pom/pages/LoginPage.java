package com.murali.pom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.murali.pom.browserfactory.config.ObjectReader;
import com.murali.pom.helper.JavaScriptHelper;
import com.murali.pom.helper.LoggerHelper;
import com.murali.pom.helper.VerificationHelper;
import com.murali.pom.helper.WaitHelper;

public class LoginPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement email;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(email, ObjectReader.reader.getExplicitWait());
		//new MuraliBase().getNavigationScreen(driver);
	}
	
	public void clickOnLogin() {
		log.info("Clicked on Login button");
		email.click();
	}
	
	public void enterEmailAddress(String emailAddress) {
		log.info("entering email address.."+emailAddress);
		this.email.sendKeys(emailAddress);
	}
	
	public HomePage clickOnSignInButton() {
		log.info("Clicked on Signin button");
		new JavaScriptHelper(driver).scrollDownVertically();
		email.click();
		return new HomePage(driver);
	}	
	
	public boolean verifySuccessLoginMsg() {
		return new VerificationHelper(driver).isDisplayed(email);
	}
	

}
