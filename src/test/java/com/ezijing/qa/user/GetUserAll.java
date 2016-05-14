package com.ezijing.qa.user;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.PlayerBl;
import com.ezijing.qa.bl.UserBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.player.PostPlayerPush;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;

public class GetUserAll extends TestBase{
	static Logger logger = Logger.getLogger(PostPlayerPush.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	UserBl userbo = new UserBl();
	
	@BeforeMethod
	  public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	  }

	@Test
	  public void pushPlayerNormal() {
		
		logger.info("Test Case is : pushPlayerNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "User", "GetUserAll");
		JSONObject json = userbo.userAll(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	  }
		
	
	@Test
	  public void pushPlayerNots() {
		
		logger.info("Test Case is : pushPlayerNots");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "User", "GetUserAll");
		params.remove("limit");
		params.remove("page");
		JSONObject json = userbo.userAll(headers, params);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
}
