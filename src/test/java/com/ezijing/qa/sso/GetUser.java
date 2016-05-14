package com.ezijing.qa.sso;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class GetUser extends TestBase {
	static Logger logger = Logger.getLogger(GetUser.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	SSOBl ssobl =  new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "123456";
	/**
	 * Register User 接口调用返回结果数据对象，包括cookie信息及json串
	 * @throws InterruptedException 
	 */

	@BeforeClass
	public void setLog4j() throws InterruptedException {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "AutoTestUser" + Utils.randomString(); // 随机生成用户名
		//headers.put("Charset", "charset=utf-8;");
		Thread.sleep(THINKTIME*2);
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" })
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username + "@qq.com");
		mid = ssobl.registerUser(headers, params).getJSONObject("data").getString("mid");
	}

	@Test
	public void getUserNormal() {

		logger.info("Test Case is : getUserNormal");
		params.clear();
		params.put("mid", mid);
		params.put("pagesize", "10");
		JSONObject json = ssobl.getUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	
	@Test
	public void getUserNormaluid() {

		logger.info("Test Case is : getUserNormaluid");
		params.clear();
		params.put("uid", mid);
		params.put("pagesize", "10");
		JSONObject json = ssobl.getUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test(dependsOnMethods = { "getUserNormal" },enabled = true)
	public void getUserNoId() {

		logger.info("Test Case is : getUserNoId");
		params.clear();
		params.put("mid", mid);
		params.put("pagesize", "10");
		JSONObject json = ssobl.getUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterClass
	public void deleteUser() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		//ssobl.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}

}
