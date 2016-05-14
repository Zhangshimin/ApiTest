package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.ITestCase;
import com.ezijing.qa.factory.io.ITestData;

public class TestDataFactory {

	public static ITestData createTestCase(int type)
	{
		ITestData testdata = null;
		if(type == 1)
		{
			testdata = new APITestData();
		}else if(type == 2)
		{
			testdata = new UITestData();
		}else if(type == 3)
		{
			testdata = new PerfTestData();
		}
		return testdata;
	}
}
