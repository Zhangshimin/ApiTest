package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.IAbstractTestProjectFactory;
import com.ezijing.qa.factory.io.IProject;
import com.ezijing.qa.factory.io.ITestCase;
import com.ezijing.qa.factory.io.ITestConfig;
import com.ezijing.qa.factory.io.ITestData;

public class ProjectCreater {

	private IProject project = null;

	
	public void produceProject(IAbstractTestProjectFactory testFactory,String projectType)
	{
		System.out.println("produceProject");
		
		prepareTestProject(testFactory,projectType);
		
	}
	
	private void prepareTestProject(IAbstractTestProjectFactory testFactory,String projectType)
	{
		System.out.println("prepareTestProject");
		
		this.project = testFactory.createProject(projectType);

		
	}
}
