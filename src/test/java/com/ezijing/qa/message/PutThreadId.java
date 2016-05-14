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

public class PutThreadId extends TestBase{
	static Logger logger = Logger.getLogger(PutThreadId.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	MessageBl messagebo = new MessageBl();
	String token = "";
	String threadid = "";

	@BeforeMethod
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@BeforeMethod(dependsOnMethods = {"beforeTest"})
	public void createThread() {
		threadid = messagebo
				.createThread(
						headers,
						PoiUtils.getTestData(EXCEL_WEB_FILE, "Message",
								"PostThread")).getJSONObject("message")
				.getString("thread_id");
	}

	@Test
	public void putThreadIdNormal() {
		logger.info("Test Case is : putThreadIdNormal");
		params.put("is_new", "1");
		JSONObject json = messagebo.putThreadId(headers, params, threadid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	@AfterMethod
	  public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	  }
}
