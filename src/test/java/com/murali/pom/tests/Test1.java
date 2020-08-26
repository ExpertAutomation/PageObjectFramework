package com.murali.pom.tests;

import org.testng.annotations.Test;

import com.murali.pom.helper.AssertionHelper;
import com.murali.pom.prjbase.MuraliBase;

public class Test1 extends MuraliBase{
	
	@Test
	public void testLogin1() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin2() {
		AssertionHelper.makeFalse();
	}
	
	@Test
	public void testLogin3() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin4() {
		AssertionHelper.makeFalse();
	}

}
