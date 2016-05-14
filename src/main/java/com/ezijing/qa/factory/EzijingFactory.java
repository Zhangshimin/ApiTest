/**
 * 
 */
package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.IAbstractTestProjectFactory;
import com.ezijing.qa.factory.io.IProject;
import com.ezijing.qa.factory.io.ITestCase;
import com.ezijing.qa.factory.io.ITestConfig;
import com.ezijing.qa.factory.io.ITestData;

/**
 * @author cuixiaohui
 *
 */
public class EzijingFactory implements IAbstractTestProjectFactory {

	public IProject createProject(String projectType) {
		// TODO Auto-generated method stub
		if(projectType.equals("1")){
			System.out.println("EzijingFactory createProject type 1");
			return new APITestProject();
		}
		else if(projectType.equals("2")){
			return new UITestProject();
		}else{
			return new PerfTestProject();
		}
	}

}
