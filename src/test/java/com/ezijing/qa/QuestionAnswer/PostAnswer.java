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
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.vo.RequestVo;

public class PostAnswer extends TestBase{
	static Logger logger = Logger.getLogger(PostAnswer.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	QuestionAnswerBl answerbo = new QuestionAnswerBl();
	String token = "";
	String questionid = "";
	
	@BeforeMethod
	  public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		JSONObject json = answerbo.createQuestion(headers,
				PoiUtils.getTestData(EXCEL_WEB_FILE, "Question", "PostQuestion"));
		headers.put("token", token);
	  }
	@Test
	  public void createAnswerNormal() {
		logger.info("Test Case is : createAnswerNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "Question", "PostAnswer");
		params.put("question_id", questionid);
		JSONObject json = answerbo.createAnswer(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	  }
	@AfterMethod
	  public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	 }
}
