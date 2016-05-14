package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.IProject;
import com.ezijing.qa.factory.io.ITestCase;
import com.ezijing.qa.factory.io.ITestConfig;
import com.ezijing.qa.factory.io.ITestData;

public class UITestProject implements IProject {

	public ITestCase createTestCase() {
		// TODO Auto-generated method stub
		return new UITestCase();
	}

	public ITestData createTestData() {
		// TODO Auto-generated method stub
		return new UITestData();
	}

	public ITestConfig createTestConfig() {
		// TODO Auto-generated method stub
		return new UITestConfig();
	}



}
