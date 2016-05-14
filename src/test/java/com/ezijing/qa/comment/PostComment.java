package com.ezijing.qa.comment;

import static org.testng.AssertJUnit.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.QuestionVo;
import com.ezijing.qa.vo.RequestVo;

public class PostComment extends TestBase{

	static Logger logger = Logger.getLogger(PostComment.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	private String token = "";
	private String requestUrl = api_url + webfix + apifix;
	private String commentid = "42";
	
	@BeforeMethod
	public void setUp(){
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void testNormal() {

		params.clear();
		params.put("parent_type", "question");
		params.put("parent_id","10");
		params.put("content", "a");
		requestvo.setRequestVo(headers,params,requestUrl+coursecommentfix);
		
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),201);
	}
	
	
	@Test
	public void testNocookie() {
		
		params.clear();
		params.put("parent_type", "question");
		params.put("parent_id","10");
		params.put("content", "a");
		requestvo.setRequestVo(headers,params,requestUrl+coursecommentfix);
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),403);
	}

	@Test
	public void testParams() {

		params.clear();
		params.put("parent_id","10");
		params.put("content", "a");
		requestvo.setRequestVo(headers,params,requestUrl+coursecommentfix);
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),422);
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
