package com.ezijing.qa.datacenter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.apache.http.cookie.Cookie;
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
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.ResponseVo;
import com.ezijing.qa.vo.RequestVo;

public class GetIsLogin extends TestBase{
  
	static Logger logger = Logger.getLogger(GetIsLogin.class);
	
  HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	UserBl userbo = new UserBl();
	private String token = "";
	private String requesturl = api_url;

	@BeforeMethod
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void isLoginNormal() {
		params.clear();
		params.put("course_ids", "940");
		JSONObject json = userbo.isLogin(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
		assertEquals(json.getJSONObject("data").getBooleanValue("isLogin"),true);
	}
	
	@Test(dependsOnMethods = {"isLoginNormal"})
	public void isLoginNologin() {
		params.clear();
		headers.clear();
		params.put("course_ids", "940");
		JSONObject json = userbo.isLogin(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
		assertEquals(json.getJSONObject("data").getBooleanValue("isLogin"),false);
	}

	@AfterMethod
	public void logout() throws Exception {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
