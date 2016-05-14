package com.ezijing.qa.jssdk;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOJssdkBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;

public class GetAccountLogin extends TestBase{
	static Logger logger = Logger.getLogger(GetAccountLogin.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	SSOJssdkBl jssdk = new SSOJssdkBl();
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	@Test
	public void loginNormal() {
		logger.info("Test Case is : loginNormal");
		params = PoiUtils.getTestData(EXCEL_SSO_FILE, "JSSDK", "GetAccountLogin");
		JSONObject json = jssdk.login(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test
	public void loginNousername() {
		logger.info("Test Case is : loginNousername");
		params = PoiUtils.getTestData(EXCEL_SSO_FILE, "JSSDK", "GetAccountLogin");
		params.remove("username");
		JSONObject json = jssdk.login(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),400);
	}
	
	@Test
	public void loginNopassword() {
		logger.info("Test Case is : loginNopassword");
		params = PoiUtils.getTestData(EXCEL_SSO_FILE, "JSSDK", "GetAccountLogin");
		params.remove("password");
		JSONObject json = jssdk.login(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),400);
	}
	
	 @AfterClass(enabled = true)
	  public void afterTest() throws InterruptedException {
			Thread.sleep(THINKTIME);
	  }
}
