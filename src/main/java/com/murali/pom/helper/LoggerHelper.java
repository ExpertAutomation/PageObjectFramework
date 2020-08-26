package com.murali.pom.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.murali.pom.prjbase.MuraliBase;

public class LoggerHelper {
	
	private static boolean root=false;
	
	public static Logger getLogger(Class clazz) {
		if(root) {
			return Logger.getLogger(clazz);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("/src/main/resources/config/log4j.properties"));
		root = true;
		return Logger.getLogger(clazz);
	}
	
	@Test
	public void checkGetLogger() {
		Logger log = LoggerHelper.getLogger(LoggerHelper.class);
		log.info("logger is configured");
		log.info("logger is configured");
		log.info("logger is configured");
	}
	
	/**@Test
	public void test1() {
		Assert.assertTrue(true);
	}
	
	
	@Test
	public void test2() {
		int i = 1;
		if(i == 3) {
			Assert.assertTrue(true);
		} else {
			System.out.println(i);
			i++;
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test3() {
		int i = 1;
		if(i == 3) {
			Assert.assertTrue(true);
		} else {
			System.out.println(i);
			i++;
			Assert.assertTrue(false);
		}
	} **/
		
		
	
}
