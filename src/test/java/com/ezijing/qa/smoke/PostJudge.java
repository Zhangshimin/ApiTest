package com.ezijing.qa.smoke;

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
import com.ezijing.qa.bl.CourseBl;
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
	private String token = "";
	private String cookievalue = "";
	private String errorCode = "";
	private String nid = "3448";
	CourseBl coursebo = new CourseBl();

	@BeforeMethod
	public void login() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		//cookievalue = BaseApiUtil.loginSessid(USERNAME, PASSWORD);
		headers.put("token", token);
		//headers.put("Cookie", cookievalue);
	}

	@Test
	public void courseJudge() {
		
		logger.info("Test Case is : courseJudge");
		params.clear();
		params.put("nid", nid);
		JSONObject json = coursebo.courseJudge(headers, params);
		json.getJSONObject("error_info").getString("error_msg");
	}
	
	@Test(dependsOnMethods = {"courseJudge"},enabled = true)
	public void courseSubscribe()
	{
		logger.info("Test Case is : courseSubscribe");
		params.clear();
		params.put("nid", nid);
		JSONObject json = coursebo.courseSubscribe(headers, params);
		json.getJSONObject("error_info").getString("error_code");
	}
	
//	@Test(dependsOnMethods = {"courseSubscribe"},enabled = true)
//	public void courseUnsubscribe()
//	{
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("Test Case is : courseUnsubscribe");
//		params.clear();
//		params.put("nid", nid);
//		JSONObject json = coursebo.courseUnsubscribe(headers, params);
//		assertEquals(json.getJSONObject("error_info").getString("error_code"),"1010");
//	}
	
	@AfterMethod
	public void logout() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		//BaseApiUtil.logout(cookievalue);
		Thread.sleep(THINKTIME);
	}
}
