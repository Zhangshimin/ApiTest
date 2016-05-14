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

public class GetLastPlayed extends TestBase {
	static Logger logger = Logger.getLogger(GetVideoProgress.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	//String cookievalue = "";
	private String requestUrl = api_url + cloudfix + data_version;
	private String token = "";
	@BeforeClass
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		//cookievalue = BaseApiUtil.login(USERNAME, PASSWORD);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void lastPlayedNormal() {

		logger.info("Test Case is : lastPlayedNormal");
		params.clear();
		params.put("video_ids", "758");
		params.put("course_ids", "756");
		requestvo.setRequestVo(headers, params, requestUrl + lastplayedfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test
	public void lastPlayedNoVideoids() {

		logger.info("Test Case is : lastPlayedNormal");
		params.clear();
		// params.put("video_ids","758");
		params.put("course_ids", "756");
		requestvo.setRequestVo(headers, params, requestUrl + lastplayedfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterClass
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
