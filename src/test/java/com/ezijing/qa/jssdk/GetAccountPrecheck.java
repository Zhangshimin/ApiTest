package com.ezijing.qa.jssdk;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOJssdkBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;

public class GetAccountPrecheck extends TestBase{
	static Logger logger = Logger.getLogger(GetAccountPrecheck.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	SSOJssdkBl jssdk = new SSOJssdkBl();
	private String username = "";
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "JSSDKUser" + Utils.randomString();
	}
	@Test
	public void preCheckNormal() {
		logger.info("Test Case is : preCheckNormal");
		params.clear();
		params.put("username", username);
		JSONObject json = jssdk.precheck(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	@Test
	public void preCheckUserExsit() {
		logger.info("Test Case is : preCheckUserExsit");
		params.clear();
		params.put("username", "h");
		JSONObject json = jssdk.precheck(headers, params);
		assertEquals(json.getIntValue("errorCode"),400);
	}
	
	@Test
	public void preCheckEmail() {
		logger.info("Test Case is : preCheckEmail");
		params.clear();
		params.put("username", username);
		params.put("email", username+"@qq.com");
		JSONObject json = jssdk.precheck(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	 @AfterClass(enabled = true)
	  public void afterTest() throws InterruptedException {
			Thread.sleep(THINKTIME);
	  }
	 
}
