package com.murali.pom.helper;

import org.testng.annotations.Test;

public class ResourceHelper {
	
	public static String getResourcePath(String path) {
		String basePath = System.getProperty("user.dir");
		return basePath + path;
	}
	
	@Test
	public void checkResourcePath() {
		String path = ResourceHelper.getResourcePath("/src/main/resources/config/log4j.properties");
		System.out.println(path);
		
	}

}
