package com.ezijing.qa.util;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.ezijing.qa.factory.io.ITestData;

public class ExcelData implements ITestData {

	public Object[][] getData(String caseName, String dataFile) {
		// TODO Auto-generated method stub
		return getData(caseName,dataFile,0);
	}

	public Object[][] getData(String caseName, String dataFile, String modelName) {
		// TODO Auto-generated method stub
		HashMap<String, String> data = null;
		data = PoiUtils.getAPIParams(PoiUtils.readExcel(dataFile),modelName,caseName);
		return null;
	}

	public Object[][] getData(String caseName, String dataFile, int beginNum,
			int endNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[][] getData(String caseName, String dataFile, int colNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
