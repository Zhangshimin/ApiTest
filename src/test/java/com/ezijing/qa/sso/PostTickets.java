package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;

public class PostTickets extends TestBase {

	static Logger logger = Logger.getLogger(PostTickets.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";

	@BeforeClass
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "AutoTestUser"  + Utils.randomString(); // 随机生成用户名
		headers.put("Charset", "charset=utf-8;");
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" },enabled = true)
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username+"@qq.com");
		mid = ssobo.registerUser(headers, params).getJSONObject("data").getString("mid");
	}

	@Test(enabled = true)
	public void getTGTNormal() {

		logger.info("Test Case is : getTGTNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("username", username);
		params.put("password", password);
		JSONObject json = ssobo.ticket(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test(enabled = true)
	public void getTGTNoUsername() {

		logger.info("Test Case is : getTGTNoUsername");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("password", password);
		JSONObject json = ssobo.ticket(headers, params);
		assertEquals(json.getIntValue("errorCode"), 1010);
	}

	@Test(enabled = true)
	public void getTGTNoPassword() {

		logger.info("Test Case is : getTGTNoPassword");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("username", username);
		JSONObject json = ssobo.ticket(headers, params);
		assertEquals(json.getIntValue("errorCode"), 1010);
	}

	@Test(enabled = true)
	public void getTGTWrongUsername() {

		logger.info("Test Case is : getTGTWrongUsername");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("username", username + 1);
		params.put("password", password);
		JSONObject json = ssobo.ticket(headers, params);
		assertEquals(json.getIntValue("errorCode"), 1002);
	}

	@Test(enabled = true)
	public void getTGTWrongPassword() {

		logger.info("Test Case is : getTGTWrongPassword");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("username", username);
		params.put("password", password + 1);
		JSONObject json = ssobo.ticket(headers, params);
		assertEquals(json.getIntValue("errorCode"), 1002);
	}

	@AfterClass(enabled = true)
	public void afterTest() throws InterruptedException {

		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}
}
