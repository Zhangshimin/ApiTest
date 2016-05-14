package com.ezijing.qa.sso;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class PutUser extends TestBase {
	static Logger logger = Logger.getLogger(PutUser.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";


	/**
	 * Register User 接口调用返回结果数据对象，包括cookie信息及json串
	 */

	@BeforeClass
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "AutoTestUser" + Utils.randomString(); // 随机生成用户名
		headers.put("Charset", "charset=utf-8;");
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" })
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username + "@qq.com");
		JSONObject json = ssobo.createUser(headers, params);
		mid = json.getJSONObject("data").getString("id");
	}

	@Test
	public void modifyUserInfoNormal() {
		logger.info("Test Case is : modifyUserInfoNormal");
		params.clear();
		params.put("mid", mid);
		params.put("email", mid + "@sina.com");
		JSONObject json = ssobo.putUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test(dependsOnMethods = { "modifyUserInfoNormal" })
	public void modifyUserInfoNoId() {
		logger.info("Test Case is : modifyUserInfoNoId");
		params.clear();
		params.put("email", mid + "@sina.com");
		JSONObject json = ssobo.putUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 1009);
	}

	@Test(dependsOnMethods = { "modifyUserInfoNormal" })
	public void modifyUserInfoNoOther() {

		logger.info("Test Case is : modifyUserInfoNoOther");
		params.clear();
		params.put("mid", mid);
		JSONObject json = ssobo.putUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterClass
	public void deleteUser() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}
}
