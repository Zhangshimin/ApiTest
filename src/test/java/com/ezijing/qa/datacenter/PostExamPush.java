package com.ezijing.qa.datacenter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.CourseBl;
import com.ezijing.qa.bl.UserBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class PostExamPush extends TestBase {
	static Logger logger = Logger.getLogger(PostExamPush.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	CourseBl coursebo = new CourseBl();
	private String token = "";

	@BeforeMethod
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void examPushNormal() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");
		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test
	public void examPushNouid() {
		params.clear();
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNocourseid() {
		params.clear();
		params.put("uid", "27244");
		// params.put("course_id","756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNoscore() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		// params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNofullscore() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		// params.put("fullscore","0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNoaccuracy() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		// params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNodevicetype() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		// params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNouserip() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		// params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNostarttime() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		// params.put("time_start", "1430918942");
		params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void examPushNoendtime() {
		params.clear();
		params.put("uid", "27244");
		params.put("course_id", "756");
		params.put("score", "0.99");
		params.put("fullscore", "0.1");
		params.put("accuracy", "100.99");
		params.put("device_type", "2");
		params.put("time_start", "1430918942");
		// params.put("time_end", "1430919009");
		params.put("user_ip", "121.61.182.113");

		JSONObject json = coursebo.coursePreviousExamPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@AfterMethod
	public void logout() throws Exception {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}
