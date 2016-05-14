package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.ITestCase;

public class TestCaseFactory {

	public static ITestCase createTestCase(String type)
	{
		ITestCase testcase = null;
		if(type.equals("1"))
		{
			testcase = new APITestCase();
		}else if(type.equals("2"))
		{
			testcase = new UITestCase();
		}else if(type.equals("3"))
		{
			testcase = new PerfTestCase();
		}
		return testcase;
	}
}
