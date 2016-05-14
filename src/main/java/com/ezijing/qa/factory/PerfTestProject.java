package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.IProject;
import com.ezijing.qa.factory.io.ITestCase;
import com.ezijing.qa.factory.io.ITestConfig;
import com.ezijing.qa.factory.io.ITestData;

public class PerfTestProject implements IProject {

	public ITestCase createTestCase() {
		// TODO Auto-generated method stub
		return new PerfTestCase();
	}

	public ITestData createTestData() {
		// TODO Auto-generated method stub
		return new PerfTestData();
	}

	public ITestConfig createTestConfig() {
		// TODO Auto-generated method stub
		return new PerfTestConfig();
	}

}
