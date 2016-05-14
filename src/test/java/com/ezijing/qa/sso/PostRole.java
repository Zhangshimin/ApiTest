package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;

public class PostRole extends TestBase{
	static Logger logger = Logger.getLogger(PutRole.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	SSOBl ssobo = new SSOBl();
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		headers.put("Charset", "charset=utf-8;");
	}
	
	@Test(enabled=false)
	 public void postRoleNormal() {
		
		logger.info("Test Case is : postRoleNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostRole");
		JSONObject json = ssobo.addRole(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@Test(enabled=false)
	 public void postRoleNoName() {
		
		logger.info("Test Case is : postRoleNoName");
		params.clear();
		JSONObject json = ssobo.addRole(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
	  }
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
}
