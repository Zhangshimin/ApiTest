package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.IProject;
import com.ezijing.qa.factory.io.ITestCase;
import com.ezijing.qa.factory.io.ITestConfig;
import com.ezijing.qa.factory.io.ITestData;

public class APITestProject implements IProject {

	public APITestProject()
	{
		createTestCase();
		createTestData();
		createTestConfig();
	}
	public ITestCase createTestCase() {
		// TODO Auto-generated method stub
		System.out.println("APITestProject API TestCase");
		return new APITestCase();

	}

	public ITestData createTestData() {
		// TODO Auto-generated method stub
		System.out.println("APITestProject API TestData");
		return new APITestData();
	}

	public ITestConfig createTestConfig() {
		// TODO Auto-generated method stub
		System.out.println("APITestProject API TestConfig");
		return new APITestConfig();
	}
	
}
