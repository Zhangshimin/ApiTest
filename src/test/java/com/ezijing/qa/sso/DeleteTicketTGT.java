package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;

public class DeleteTicketTGT extends TestBase {
	static Logger logger = Logger.getLogger(PostTicketsTGT.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";
	private String tgt = "";

	
	/**
	 * Register User 接口调用返回结果数据对象，包括cookie信息及json串
	 */

	@BeforeClass
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "AutoTestUser" + Utils.randomString(); // 随机生成用户名
		headers.put("Charset", "charset=utf-8;");
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" },enabled = true)
	public void registerUser() {
		
		logger.info("Test Case is : DeleteTicketTGT.registerUser");
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username + "@qq.com");
		mid = ssobo.registerUser(headers, params).getJSONObject("data").getString("mid");
	}

	/**
	 * Post Tickets 接口调用返回结果数据对象，包括cookie信息及json串 获得data.tgt_id
	 */

	@BeforeClass(dependsOnMethods = { "registerUser" },enabled = true)
	public void PostTickets() {
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("username", username);
		params.put("password", password);
		tgt = ssobo.ticket(headers, params).getJSONObject("data").getString("tgt_id");
	}

	@Test(enabled = true)
	public void deleteTGT() {
		
		logger.info("Test Case is : deleteTGT");
		params.clear();
		JSONObject json = ssobo.deleteTGT(headers, params, tgt);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterClass
	public void afterTest() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}
}
