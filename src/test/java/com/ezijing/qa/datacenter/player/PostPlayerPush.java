package com.ezijing.qa.datacenter.player;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.PlayerBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.GetProgress;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.vo.RequestVo;

public class PostPlayerPush extends TestBase{
  
  static Logger logger = Logger.getLogger(PostPlayerPush.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	PlayerBl playerbo = new PlayerBl();
	String token = "";
	
	@BeforeMethod
	  public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	  }

	@Test
	  public void pushPlayerNormal() {
		
		logger.info("Test Case is : pushPlayerNormal");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerPush");
		JSONObject json = playerbo.playerPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	  }
		
	
	@Test
	  public void pushPlayerNots() {
		
		logger.info("Test Case is : pushPlayerNots");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerPush");
		params.remove("ts");
		JSONObject json = playerbo.playerPush(headers, params);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	
	
	@Test
	  public void pushPlayerNovid() {
		
		logger.info("Test Case is : pushPlayerNovid");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerPush");
		params.remove("vid");
		JSONObject json = playerbo.playerPush(headers, params);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	
	@Test
	  public void pushPlayerNovsid() {
		
		logger.info("Test Case is : pushPlayerNovsid");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerPush");
		params.remove("sid");
		JSONObject json = playerbo.playerPush(headers, params);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	@AfterMethod
	  public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	  }

}
