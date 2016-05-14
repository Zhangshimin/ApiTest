package com.ezijing.qa.factory.io;

public interface IProject {

	ITestCase createTestCase();
	ITestData createTestData();
	ITestConfig createTestConfig();
}
