package com.ezijing.qa.factory.io;

public interface ITestCase {

	void initTestConfig();
	
	void initTestData();
	
	void initTestCases();
	
	void executeTest();
}
