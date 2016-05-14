package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class PostUser extends TestBase{
	
	static Logger logger = Logger.getLogger(PostUser.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";
	
	/**
	* Register User
	* 接口调用返回结果数据对象，包括cookie信息及json串
	*/
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		username = "AutoTestUser"+Utils.randomString();	
		headers.put("Charset", "charset=utf-8;");
	}
	@Test(dependsOnMethods = {"testAddUserNoUsername"})
	public void testAddUser() {
		logger.info("Test Case is : testAddUser");
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username + "@qq.com");
		JSONObject json = ssobo.createUser(headers, params);
		mid = json.getJSONObject("data").getString("id");
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	
	@Test(dependsOnMethods = {"testAddUserNoPassword"})
	public void testAddUserNoUsername() {
		logger.info("Test Case is : testAddUserNoUsername");
		params.clear();
		params.put("password", password);
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.createUser(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	
	@Test(dependsOnMethods = {"testAddUserNoEmail"})
	public void testAddUserNoPassword() {
		logger.info("Test Case is : testAddUserNoPassword");
		params.clear();
		params.put("username", "AutoTestUser"+Utils.randomString());
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.createUser(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
	}

	
	@Test
	public void testAddUserNoEmail() {
		logger.info("Test Case is : testAddUserNoEmail");
		params.clear();
		params.put("username", "AutoTestUser"+Utils.randomString());
		params.put("password", password);
		JSONObject json = ssobo.createUser(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	  @AfterClass
	  public void afterTest() throws InterruptedException {
		  params.clear();
			params.put("mid", mid);
			ssobo.deleteUser(headers, params);
			Thread.sleep(THINKTIME);
	  }
}
