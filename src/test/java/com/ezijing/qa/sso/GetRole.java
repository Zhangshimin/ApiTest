package com.ezijing.qa.sso;

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

import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetRole extends TestBase{
	static Logger logger = Logger.getLogger(GetRole.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	SSOBl ssobo = new SSOBl();
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		headers.put("Charset", "charset=utf-8;");
	}
	
	@Test
	 public void getAllRole() {
		
		logger.info("Test Case is : getRole");
		params.clear();
		JSONObject json = ssobo.queryRole(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@Test
	 public void getRoleNoId() {
		
		logger.info("Test Case is : getRoleNoId");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "GetRole");
		JSONObject json = ssobo.queryRole(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
}
