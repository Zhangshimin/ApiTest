package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.ITestCase;

public class APITestCase implements ITestCase {

	public APITestCase()
	{
		initTestConfig();
		initTestData();
		executeTest();
	}
	public void initTestConfig() {
		// TODO Auto-generated method stub
		System.out.println("initTestConfig");
	}

	public void initTestData() {
		// TODO Auto-generated method stub
		System.out.println("initTestData");
	}

	public void executeTest() {
		// TODO Auto-generated method stub
		System.out.println("executeTest");
	}
	public void initTestCases() {
		// TODO Auto-generated method stub
		createTestPackage();
		createTestCases();
	}
	
	private void createTestCases() {
		// TODO Auto-generated method stub
		
	}
	private void createTestPackage() {
		// TODO Auto-generated method stub
		
	}

}
