/**
 * 
 */
package com.ezijing.qa.factory;

import com.ezijing.qa.factory.io.IAbstractTestProjectFactory;

/**
 * @author cuixiaohui
 *
 */
public class ProjectDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProjectCreater creater = new ProjectCreater();
		
		IAbstractTestProjectFactory factory = new EzijingFactory();
		
		String projectType = "1";
		
		creater.produceProject(factory, projectType);
		
	}

}
