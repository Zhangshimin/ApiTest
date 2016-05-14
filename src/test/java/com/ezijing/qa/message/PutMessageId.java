package com.ezijing.qa.message;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.MessageBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.player.PostPlayerPush;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.vo.RequestVo;

public class PutMessageId extends TestBase{
	static Logger logger = Logger.getLogger(PutMessageId.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	MessageBl messagebo = new MessageBl();
	String token = "";
	String messageid = "";

	@BeforeMethod
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@BeforeMethod(dependsOnMethods = { "beforeTest" })
	public void createMessage() {
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "Message", "PostMessage");
		messageid = messagebo.createMessage(headers, params).getJSONObject("message").getString("mid");
	}

	@Test
	public void putMessageIdNormal() {
		logger.info("Test Case is : getMessageIdNormal");
		params.clear();
		params.put("is_new", "0");
		JSONObject json = messagebo.putMessageId(headers, params, messageid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	@Test(enabled = true,dependsOnMethods = { "putMessageIdNormal" })
	public void messageIdNoLogin() {
		logger.info("Test Case is : messageNoLogin");
		headers.clear();
		JSONObject json = messagebo.getMessageId(headers, params, messageid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	@AfterMethod
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
