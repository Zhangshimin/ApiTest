package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;
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

public class PostUpdateUser extends TestBase{
  
	static Logger logger = Logger.getLogger(PostUpdateUser.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "123456";

	@BeforeClass
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		headers.put("Charset", "charset=utf-8;");
		username = "AutoTestUser" + Utils.randomString(); 
		headers.put("Charset", "charset=utf-8;");
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" })
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username + "@qq.com");
		JSONObject json = ssobo.createUser(headers, params);
		mid = json.getJSONObject("data").getString("id");
	}

	@Test
	public void updateUserAvatar() {
		logger.info("Test Case is : updateUserAvatar");
		params.clear();
		params.put("mid", mid);
		// params.put("avatar", picone);
		JSONObject json = ssobo.updateUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test
	public void updateUserEmail() {
		logger.info("Test Case is : updateUserEmail");
		params.clear();
		params.put("mid", mid);
		params.put("email", mid + "@sina.com");
		params.put("gender", "男");
		params.put("companyName", "紫荆教育");
		JSONObject json = ssobo.updateUser(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	@AfterClass
	public void deleteUser() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}
}
