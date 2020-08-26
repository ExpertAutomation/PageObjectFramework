package com.murali.pom.tests;

import org.testng.annotations.Test;

import com.murali.pom.prjbase.MuraliBase;

public class TestScreenshot extends MuraliBase{
	
	@Test
	public void testSCreenshot() {
		driver.get("https://google.com");
		captureScreen("googlescreen");
	}

}
