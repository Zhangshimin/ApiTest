package com.ezijing.qa.datacenter.player;

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

public class GetPlayerAjaxReview extends TestBase{
	static Logger logger = Logger.getLogger(PostPlayerPush.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = api_url+cloudfix+data_version;
	String token = "";

	@BeforeClass
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void ajaxreviewPlayerNormal() {

		logger.info("Test Case is : ajaxreviewPlayerNormal");
		params.clear();
		params.put("uid", "10532");
		params.put("video_id", "903");
		params.put("result", "1");
		params.put("device_type", "h5");
		params.put("quiz_id", "4684");
		params.put("cost_time", "0");
		params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test
	public void ajaxreviewPlayerNoVideoid() {

		logger.info("Test Case is : ajaxreviewPlayerNoVideoid");
		params.clear();
		params.put("uid", "10532");
		// params.put("video_id","903");
		params.put("result", "1");
		params.put("device_type", "h5");
		params.put("quiz_id", "4684");
		params.put("cost_time", "0");
		params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void ajaxreviewPlayerNoResult() {

		logger.info("Test Case is : ajaxreviewPlayerNoResult");
		params.clear();
		params.put("uid", "10532");
		params.put("video_id", "903");
		// params.put("result", "1");
		params.put("device_type", "h5");
		params.put("quiz_id", "4684");
		params.put("cost_time", "0");
		params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void ajaxreviewPlayerNoDeviceType() {

		logger.info("Test Case is : ajaxreviewPlayerNoDeviceType");
		params.clear();
		params.put("uid", "10532");
		params.put("video_id", "903");
		params.put("result", "1");
		// params.put("device_type","h5");
		params.put("quiz_id", "4684");
		params.put("cost_time", "0");
		params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void ajaxreviewPlayerNoQuiid() {

		logger.info("Test Case is : ajaxreviewPlayerNoQuiid");
		params.clear();
		params.put("uid", "10532");
		params.put("video_id", "903");
		params.put("result", "1");
		params.put("device_type", "h5");
		// params.put("quiz_id", "4684");
		params.put("cost_time", "0");
		params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void ajaxreviewPlayerNoCostTime() {

		logger.info("Test Case is : ajaxreviewPlayerNoCostTime");
		params.clear();
		params.put("uid", "10532");
		params.put("video_id", "903");
		params.put("result", "1");
		params.put("device_type", "h5");
		params.put("quiz_id", "4684");
		// params.put("cost_time", "0");
		params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void ajaxreviewPlayerNoSeriesid() {

		logger.info("Test Case is : ajaxreviewPlayerNoSeriesid");
		params.clear();
		params.put("uid", "10532");
		params.put("video_id", "903");
		params.put("result", "1");
		params.put("device_type", "h5");
		params.put("quiz_id", "4684");
		params.put("cost_time", "0");
		// params.put("series_id", "902");
		requestvo.setRequestVo(headers, params, requestUrl + playerajaxreviewfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 400);
	}
	@AfterClass
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
