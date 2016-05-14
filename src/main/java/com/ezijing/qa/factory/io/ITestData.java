package com.ezijing.qa.factory.io;

public interface ITestData {

	public Object[][] getData(String caseName, String dataFile);
	public Object[][] getData(String caseName, String dataFile,int colNum);
	public Object[][] getData(String caseName, String dataFile,int beginNum,int endNum);
}
