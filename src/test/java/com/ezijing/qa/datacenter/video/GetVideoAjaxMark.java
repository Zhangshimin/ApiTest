package com.ezijing.qa.datacenter.video;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class GetVideoAjaxMark extends TestBase{
	static Logger logger = Logger.getLogger(GetVideoProgress.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	String token = "";
	private String requestUrl = api_url+cloudfix+data_version;
	
	@BeforeClass
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void videoAjaxMarkNormal() {

		logger.info("Test Case is : videoAjaxMarkNormal");
		
		params.clear();
		params.put("video_id", "5006");
		params.put("mark", "1");

		requestvo.setRequestVo(headers, params, requestUrl+ajaxmarksfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test
	public void videoAjaxMarkNoVideoid() {

		logger.info("Test Case is : videoAjaxMarkNoVideoid");
		params.clear();
		//params.put("user_id", "77");
		// params.put("video_id", "5006");
		params.put("mark", "1");
		headers.put("token", token);
		requestvo.setRequestVo(headers, params, requestUrl+ajaxmarksfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void videoAjaxMarkNomark() {

		logger.info("Test Case is : videoAjaxMarkNomark");
		params.clear();
		//params.put("user_id", "77");
		params.put("video_id", "5006");
		// params.put("mark", "1");

		requestvo.setRequestVo(headers, params, requestUrl+ajaxmarksfix);

		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@AfterClass
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
		
	}
}
