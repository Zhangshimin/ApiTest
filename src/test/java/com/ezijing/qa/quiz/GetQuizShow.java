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
import com.ezijing.qa.bl.QuizBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.player.PostPlayerPush;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class GetQuizShow extends TestBase{
	static Logger logger = Logger.getLogger(PostPlayerPush.class);
	private String token = "";
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	QuizBl quizbo = new QuizBl();
	
	
	@BeforeMethod
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void quizShowNormal() {

		logger.info("Test Case is : quizShowNormal");

		params.clear();
		params.put("source", "1");
		params.put("nid", "7109");
		JSONObject json = quizbo.quizShow(headers, params);
		assertEquals(json.isEmpty(), false);
	}
	
	@Test(dependsOnMethods = {"quizShowNormal"}, enabled = true)
	public void quizShowNoLogin() {

		logger.info("Test Case is : quizShowNoLogin");

		params.clear();
		headers.clear();
		params.put("source", "1");
		params.put("nid", "7109");
		JSONObject json = quizbo.quizShow(headers, params);
		//assertEquals(json.getIntValue("errorCode"), 401);
	}
	
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}
