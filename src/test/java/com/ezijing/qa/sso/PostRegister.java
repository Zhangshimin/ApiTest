package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class PostRegister extends TestBase{
	static Logger logger = Logger.getLogger(PostTicketsTGT.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";
	private String mobilenum = "";
	
	/**
	* Register User
	* 接口调用返回结果数据对象，包括cookie信息及json串
	*/
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "AutoTestUser"+Utils.randomString();
		mobilenum = Utils.randomTelNumber();
		headers.put("Charset", "charset=utf-8;");
	}
	
	@Test(enabled = true)
	public void postRegisterUserNoUsername() {
		logger.info("Test Case is : postRegisterUserNoUsername");
		params.clear();
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.registerUser(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	@Test(enabled = true)
	public void postRegisterUserNoPassword() {
		logger.info("Test Case is : postRegisterUserNoPassword");
		params.clear();
		params.put("username", username);
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.registerUser(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	@Test(enabled = true)
	public void postRegisterUserNoEmail() {
		logger.info("Test Case is : postRegisterUserNoEmail");
		params.clear();
		params.put("username", username);
		params.put("password", password);
		JSONObject json = ssobo.registerUser(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	@Test
	public void postRegisterEmailNormal() {
		logger.info("Test Case is : postRegisterEmailNormal");
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username+"@sina.com");
		JSONObject json = ssobo.registerUser(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void postRegisterChineseCharacter() {
		logger.info("Test Case is : postRegisterChineseCharacter");
		params.clear();
		params.put("username", username+URLEncoder.encode("测试"));
		params.put("password", password);
		params.put("email", username+"@sohu.com");
		JSONObject json = ssobo.registerUser(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test
	public void postRegisterTelNormal() {
		logger.info("Test Case is : postRegisterTelNormal");
		params.clear();
		params.put("mobile", mobilenum);
		params.put("password", password);
		JSONObject json = ssobo.registerUser(headers, params);
		mid = json.getJSONObject("data").getString("mid");
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	 @AfterClass(enabled = true)
	  public void afterTest() throws InterruptedException {
		  	params.clear();
			params.put("mid", mid);
			ssobo.deleteUser(headers, params);
			Thread.sleep(THINKTIME);
	  }
}
