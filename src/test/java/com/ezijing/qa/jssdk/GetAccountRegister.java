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

public class GetAccountRegister extends TestBase{
	static Logger logger = Logger.getLogger(GetAccountRegister.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	SSOJssdkBl jssdk = new SSOJssdkBl();
	private String username = "";
	private String password = "123456";
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "JSSDKUser" + Utils.randomString();
	}
	@Test(dependsOnMethods = {"registerNoEmail"})
	public void registerNormal() {
		logger.info("Test Case is : registerNormal");
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username+"@qq.com");
		JSONObject json = jssdk.register(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	@Test
	public void registerNoUsername() {
		logger.info("Test Case is : registerNoUsername");
		params.clear();
		params.put("password", password);
		params.put("email", username+"@qq.com");
		JSONObject json = jssdk.register(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),400);
	}
	@Test(dependsOnMethods = {"registerNoUsername"})
	public void registerNoPassword() {
		logger.info("Test Case is : registerNoPassword");
		params.clear();
		params.put("username", username);
		params.put("email", username+"@qq.com");
		JSONObject json = jssdk.register(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),400);
	}
	@Test(dependsOnMethods = {"registerNoPassword"})
	public void registerNoEmail() {
		logger.info("Test Case is : registerNoEmail");
		params.clear();
		params.put("username", username);
		params.put("password", password);
		JSONObject json = jssdk.register(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),400);
	}
	
	 @AfterClass(enabled = true)
	  public void afterTest() throws InterruptedException {
			Thread.sleep(THINKTIME);
	  }
}
