package com.murali.pom.listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.murali.pom.helper.LoggerHelper;;

public class Retry implements IRetryAnalyzer{
	
	private int retryCount = 0;
	private int maxRetryCount = 2;
	private Logger log = LoggerHelper.getLogger(Retry.class);

	public boolean retry(ITestResult result) {
		if(retryCount < maxRetryCount) {
			log.info("Retrying test "+ result.getName()+ " with status "+getResultStatusName(result.getStatus())
			                         +"for the "+(retryCount+1)+" times.");
			retryCount++;
			return true;
		}
		return false;
	}
	
	public String getResultStatusName(int status) {
		String resultName = null;
		if(status == 1) {
			resultName = "SUCCESS";
		}
		if(status == 2) {
			resultName = "FAILURE";
		}
		if(status == 3) {
			resultName = "SKIP";
		}
		return resultName;
	}

}
