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

public class PutRole extends TestBase{
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

	
	@Test
	 public void putUserRoleNormal() {
		
		logger.info("Test Case is : putUserRoleNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PutRole");
		JSONObject json = ssobo.updateRole(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@Test(dependsOnMethods = {"putUserRoleNormal"})
	 public void putUserRoleNoName() {
		
		logger.info("Test Case is : putUserRoleNoName");
		params.clear();
		params.put("id", "4");
		JSONObject json = ssobo.updateRole(headers, params);
		assertEquals(json.getIntValue("errorCode"),1010);
	  }
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
}
