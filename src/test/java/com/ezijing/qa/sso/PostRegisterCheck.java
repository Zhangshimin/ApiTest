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

public class PostRegisterCheck extends TestBase{
	
	static Logger logger = Logger.getLogger(PostRegisterCheck.class);
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
		username = "AutoTestUser"+Utils.randomString();					//随机生成用户名
		headers.put("Charset", "charset=utf-8;");
	}
	@BeforeClass(dependsOnMethods = {"setLog4j"})
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username+"@qq.com");
		mid = ssobo.registerUser(headers, params).getJSONObject("data").getString("mid");
	}

	@Test
	public void testRegisterCheckExistUser()
	{
		logger.info("Test Case is : testRegisterCheckExistUser");
		params.clear();
		params.put("username", username);
		JSONObject json = ssobo.registerCheck(headers, params);
		assertEquals(json.getIntValue("errorCode"),1004);
	}

	@Test
	public void testRegisterCheckNoExistUser()
	{
		logger.info("Test Case is : testRegisterCheckNoExistUser");
		params.clear();
		params.put("username", Utils.randomString());
		JSONObject json = ssobo.registerCheck(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@AfterClass
	public void afterTest() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	  }
}
