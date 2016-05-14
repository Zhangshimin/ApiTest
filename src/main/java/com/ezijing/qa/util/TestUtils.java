/**
 * 
 */
package com.ezijing.qa.util;

import java.util.Hashtable;



import org.apache.poi.ss.usermodel.Workbook;

import com.ezijing.qa.factory.io.ITestData;

/**
 * @author Administrator
 *
 */
public class TestUtils{

	
	//判断case是否需要执行
	public static boolean isExecutable(Workbook workbook,String testName, PoiUtils xls)
	{
		for(int rowNum = 1; rowNum <= xls.getRowCount(workbook,"TestCases"); rowNum++)
		{
			if(xls.getCellData(workbook,"TestCases", "ClassName", rowNum).equals(testName)){
				if(xls.getCellData(workbook,"TestCases", "Runmode", rowNum).equals("Y")){
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}
	
	
	public static Object[][] getData(Workbook workbook,String testName, PoiUtils xls){
		
		int testStartRowNum = 0;
		for(int rNum = 1; rNum <= xls.getRowCount(workbook,"TestData"); rNum++ ){
			if(xls.getCellData(workbook,"TestData", 0, rNum).equals(testName)){
				testStartRowNum = rNum;
				break;
			}
		}
		System.out.println("Test "+ testName +" starts from "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
        int totalCols=0;
		while(!xls.getCellData(workbook,"TestData", totalCols, colStartRowNum).equals("")){
			totalCols++;
		}
		System.out.println("Total Cols in test "+ testName + " are "+ totalCols);
		
		
		//rows
		int dataStartRowNum = testStartRowNum + 2;
		int totalRows = 0;
		while(!xls.getCellData(workbook,"Test Data", 0, dataStartRowNum+totalRows).equals("")){
            totalRows++;
        }
        System.out.println("Total Rows in test "+ testName + " are "+ totalRows);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
        Object[][] data = new Object[totalRows][1];
        int index=0;
        Hashtable<String,String> table=null;
        for(int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++){
            table = new Hashtable<String,String>();
            for(int cNum=0;cNum<totalCols;cNum++){
                table.put(xls.getCellData(workbook,"Test Data", cNum, colStartRowNum), xls.getCellData(workbook,"Test Data", cNum, rNum));
                System.out.print(xls.getCellData(workbook,"Test Data", cNum, rNum) +" -- ");
            }
            data[index][0]= table;
            index++;
            System.out.println();
        }
        System.out.println("done");

        return data;
    }

}
