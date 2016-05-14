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

public class GetQuizSetOptions extends TestBase{
	static Logger logger = Logger.getLogger(GetQuizSetOptions.class);
	String token = "";
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
	public void quizSetOptionsNormal() {

		logger.info("Test Case is : quizSetOptionsNormal");
		params.clear();
		params.put("options", "");
		params.put("value", "1");
		params.put("lastSource", "1");
		JSONObject json = quizbo.quizSetOptions(headers, params);
		assertEquals(json.isEmpty(), false);
	}
	
	@Test(dependsOnMethods = {"quizSetOptionsNormal"},enabled = true)
	public void quizSetOptionsNotLogin() {

		logger.info("Test Case is : quizSetOptionsNotLogin");

		headers.clear();
		params.clear();
		params.put("options", "");
		params.put("value", "1");
		params.put("lastSource", "1");
		JSONObject json = quizbo.quizSetOptions(headers, params);
		//assertEquals(json.getIntValue("errorCode"), 401);
	}
	
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}
