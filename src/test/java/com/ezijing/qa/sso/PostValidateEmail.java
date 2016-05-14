package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class PostValidateEmail extends TestBase{
	static Logger logger = Logger.getLogger(PostValidateEmail.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";

	/**
	 * Register User 接口调用返回结果数据对象，包括cookie信息及json串
	 */

	@BeforeClass
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		headers.put("Charset", "charset=utf-8;");
		username = "AutoTestUser" + Utils.randomString(); // 随机生成用户名
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" })
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.registerUser(headers, params);
		mid = json.getJSONObject("data").getString("mid");
	}
	/**
	 * Post tickets/TGT
	 */
	@Test
	public void validateEmailNormal()
	{
		logger.info("Test Case is : validateEmailNormal");
		params.clear();
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.validateEmail(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
		assertEquals(json.getString("data"),"exist");
	}
	
	@Test
	public void validateEmailNoExist()
	{
		logger.info("Test Case is : validateEmailNoExist");
		params.clear();
		params.put("email", username+"@sina.com");
		JSONObject json = ssobo.validateEmail(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
		assertEquals(json.getString("data"),"none");
	}
	
	@Test
	public void validateEmailNoemail()
	{
		logger.info("Test Case is : validateEmailNoemail");
		params.clear();
		params.put("email", username+"sina.com");
		JSONObject json = ssobo.validateEmail(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
		assertEquals(json.getString("data"),"none");
	}
	
	@AfterClass
	public void afterTest() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}
}
