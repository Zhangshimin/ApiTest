package com.ezijing.qa.quiz;

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
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.player.PostPlayerPush;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class GetCourseExamFeedback extends TestBase{
	static Logger logger = Logger.getLogger(PostPlayerPush.class);
	String token = "";
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	
	
	
	@BeforeMethod
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
	}

	@Test
	public void courseExamfeedbackNormal() {

		logger.info("Test Case is : courseExamfeedbackNormal");

		headers.clear();
		params.clear();
		headers.put("token", token);
		params.put("params", "1");
		requestvo.setRequestVo(headers,params,api_url + webfix + courseexamfeedbackfix);

		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.isEmpty(), false);
	}
	
	@Test(enabled = true)
	public void courseExamfeedbackNoLogin() {

		logger.info("Test Case is : courseExamfeedbackNoLogin");

		headers.clear();
		params.clear();
		params.put("params", "1");
		requestvo.setRequestVo(headers,params,api_url + webfix + courseexamfeedbackfix);

		JSONObject json = BaseApiUtil.getResponse(requestvo);
		//assertEquals(json.getIntValue("errorCode"), 401);
	}
	
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}
