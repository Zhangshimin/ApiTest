package com.ezijing.qa.course;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;
import com.ezijing.qa.vo.ResponseVo;

public class PostJudge extends TestBase{
  
	static Logger logger = Logger.getLogger(PostJudge.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = api_url + webfix;
	private String token = "";
	private String cookievalue = "";
	private String errorCode = "";
	private String subscribeErrorCode = "";
	private String nid = "3448";

	@BeforeMethod
	public void login() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		//token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		cookievalue = BaseApiUtil.loginSessid(USERNAME, PASSWORD);
		//headers.put("token", token);
		headers.put("Cookie", cookievalue);
	}

	@Test
	public void courseJudge() {
		
		logger.info("Test Case is : courseJudge");
		params.clear();
		params.put("nid", nid);
		requestvo.setRequestVo(headers, params, requestUrl+coursejudgefix);

		JSONObject json = BaseApiUtil.postResponse(requestvo);
		// assertEquals(json.getIntValue("error_code"),1010);
		errorCode = json.getJSONObject("error_info").getString("error_code");
	}
	
	@Test(dependsOnMethods = {"courseJudge"},enabled = true)
	public void courseSubscribe()
	{
		logger.info("Test Case is : courseSubscribe");
		params.clear();
		params.put("nid", nid);
		requestvo.setRequestVo(headers, params, requestUrl+coursesubscribefix);

		JSONObject json = BaseApiUtil.postResponse(requestvo);
		subscribeErrorCode = json.getJSONObject("error_info").getString("error_code");
	}

	@Test(dependsOnMethods = {"courseSubscribe"},enabled = false)
	public void courseJudgeAfterSubscribe() {
		
		logger.info("Test Case is : courseJudgeAfterSubscribe");
		params.clear();
		params.put("nid", nid);
		requestvo.setRequestVo(headers, params, requestUrl+coursejudgefix);

		JSONObject json = BaseApiUtil.postResponse(requestvo);
		// assertEquals(json.getIntValue("error_code"),1010);
		assertEquals(json.getJSONObject("error_info").getString("error_code"),subscribeErrorCode);
	}
	
	@AfterMethod
	public void logout() throws InterruptedException {
		//BaseApiUtil.SSOLogout(token);
		BaseApiUtil.logout(cookievalue);
		Thread.sleep(THINKTIME);
	}
}
