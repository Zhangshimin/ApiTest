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
import com.ezijing.qa.vo.RequestVo;

/**
 * 
 * 此case测试http://ip/v1/tickets/TGT 接口 测试register->ticket->ticket/TGT
 * 测试用例1：正常获得data.ticket_id，检查errorCode是否为200，且参数返回格式正确
 * 测试用例2：调用ticket/TGT时不传递service参数，检查errorCode是否返回正确，
 * 
 * 
 */

public class PostTicketsTGT extends TestBase {

	static Logger logger = Logger.getLogger(PostTicketsTGT.class);
	HashMap<String, String> params = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
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

	@BeforeClass(dependsOnMethods = { "setLog4j" })
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username + "@qq.com");
		mid = ssobo.registerUser(headers, params).getJSONObject("data").getString("mid");
	}

	/**
	 * Post Tickets 接口调用返回结果数据对象，包括cookie信息及json串 获得data.tgt_id
	 */
	@BeforeClass(dependsOnMethods = { "registerUser" })
	public void PostTickets() {
		// 初始化请求参数
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("username", username);
		params.put("password", password);
		tgt = ssobo.ticket(headers, params).getJSONObject("data").getString("tgt_id");
	}

	/**
	 * Post tickets/TGT 接口调用返回结果数据对象，包括cookie信息及json串
	 */
	@Test
	public void testPostSTNormal() {
		logger.info("Test Case is : testPostSTNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		JSONObject json = ssobo.ticketST(headers, params, tgt);
		assertEquals(Utils.validateJson(json), true);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	/**
	 * Post tickets/TGT 接口调用返回结果数据对象，包括cookie信息及json串 测试不带service参数的返回结果是否正确
	 */
	@Test(enabled = false)
	public void testPostSTNoservice() {
		logger.info("Test Case is : testPostSTNoservice");
		params.clear();
		JSONObject json = ssobo.ticketST(headers, params, tgt);
		assertEquals(Utils.validateJson(json), true);
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
