package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;

public class PostUserInfo extends TestBase{
	static Logger logger = Logger.getLogger(PostUserInfo.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	SSOBl ssobo = new SSOBl();
	String token = "";
	String zjut = "";
	
	@BeforeClass(enabled = true)
	  public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		zjut = BaseApiUtil.login(USERNAME, PASSWORD);
		//headers.put("token", token);
	  }

	@Test
	  public void postUserInfo() {
		
		logger.info("Test Case is : postUserInfo");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostUserInfo");
		//params.put("service", "http://www.ezijing.com");
		params.put("zjut", zjut.substring(5));
		JSONObject json = ssobo.userInfo(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	  }
	
	@AfterClass(enabled = false)
	  public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	  }
}
