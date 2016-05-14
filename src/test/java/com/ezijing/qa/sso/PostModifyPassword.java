package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class PostModifyPassword extends TestBase{
	static Logger logger = Logger.getLogger(PostModifyPassword.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String newpassword = "1234567";
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
		JSONObject json = ssobo.registerUser(headers, params);
		mid = json.getJSONObject("data").getString("mid");
	}
	
	@Test(enabled = true)
	public void testModifyPasswordNormal()
	{
		logger.info("Test Case is : testModifyPasswordNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostModifyPassword");
		params.put("username", username);
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test(dependsOnMethods = {"testModifyPasswordNormal"})
	public void testResetPasswordNormal()
	{
		logger.info("Test Case is : testResetPasswordNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostResetPassword");
		params.put("username", username);
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
		assertEquals(Utils.validateJson(json),true);
	}
	
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testModifyPasswordNoAction()
	{
		logger.info("Test Case is : testModifyPasswordNoAction");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostResetPassword");
		params.put("username", username);
		params.remove("action");
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testModifyPasswordNoUsername()
	{
		logger.info("Test Case is : testModifyPasswordNoUsername");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostResetPassword");
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}

	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testModifyPasswordNopassword()
	{
		logger.info("Test Case is : testModifyPasswordNopassword");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostResetPassword");
		params.put("username", username);
		params.remove("password");
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testModifyPasswordNoNewpassword()
	{
		logger.info("Test Case is : testModifyPasswordNoNewpassword");
		params.clear();
		params.put("action", "modify");
		params.put("username", username);
		params.put("password", password);
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),1010);
	}
	
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testResetPasswordNoAction()
	{
		logger.info("Test Case is : testResetPasswordNoAction");
		params.clear();
		//params.put("action", "reset");
		params.put("username", username);
		params.put("password", newpassword);
		params.put("newPassword", password);
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
		assertEquals(Utils.validateJson(json),true);
	}
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testResetPasswordNoUsername()
	{
		logger.info("Test Case is : testResetPasswordNoUsername");
		params.clear();
		params.put("action", "reset");
		//params.put("username", username);
		params.put("password", newpassword);
		params.put("newPassword", password);
		
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
		assertEquals(Utils.validateJson(json),true);
		
	}
	
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testResetPasswordNoPassword()
	{
		logger.info("Test Case is : testResetPasswordNoPassword");
		params.clear();
		params.put("action", "reset");
		params.put("username", username);
		//params.put("password", newpassword);
		params.put("newPassword", password);
		
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
		assertEquals(Utils.validateJson(json),true);
		
	}
	
	@Test(dependsOnMethods = {"testResetPasswordNormal"})
	public void testResetPasswordNoNewpassword()
	{
		logger.info("Test Case is : testResetPasswordNoNewpassword");
		params.clear();
		params.put("action", "reset");
		params.put("username", username);
		params.put("password", newpassword);
		//params.put("newPassword", password);
		JSONObject json = ssobo.modifyPassword(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
		assertEquals(Utils.validateJson(json),true);
	}
	
	@AfterClass
	  public void afterTest() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	  }
}
