package com.ezijing.qa.QuestionAnswer;

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
import com.ezijing.qa.bl.QuestionAnswerBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.player.PostPlayerPush;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.vo.RequestVo;

public class PutQuestion extends TestBase{
	static Logger logger = Logger.getLogger(PostPlayerPush.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	QuestionAnswerBl questionbo = new QuestionAnswerBl();
	String token = "";
	String questionid = "";

	@BeforeMethod
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@BeforeMethod(dependsOnMethods = "{beforeTest}")
	public void createQuestionNormal() {
		JSONObject json = questionbo.createQuestion(headers,
				PoiUtils.getTestData(EXCEL_WEB_FILE, "Question", "PostQuestion"));
	}

	@Test
	public void updateQuestionNormal() {
		logger.info("Test Case is : updateQuestionNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "Question", "PutQuestion");
		JSONObject json = questionbo.updateQuestion(headers, params, questionid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterMethod
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
