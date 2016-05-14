/**
 * 
 */
package com.ezijing.qa.util;

/**
 * @author Cui Xiaohui
 *
 */
public class OSUtils {

	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public static boolean isLinux()
	{
		return OS.indexOf("linux") >= 0;
	}
	
	public static boolean isMacOS(){
	
		boolean result = false;
		if(OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0)
			result = true;
		return result;
	}
	
	public static boolean isWindows()
	{
		return OS.indexOf("windows") >= 0;
	}
}
