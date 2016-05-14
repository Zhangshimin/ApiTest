package com.ezijing.qa.datacenter.player;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class GetPlayerAjaxPush extends TestBase{
  
	static Logger logger = Logger.getLogger(PostPlayerPush.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = api_url+cloudfix+data_version;
	@BeforeMethod
	  public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	  }

	@Test
	  public void ajaxpushPlayerNormal() {
		
		logger.info("Test Case is : ajaxpushPlayerNormal");
		params.clear();
		params.put("uid","10532");
		params.put("data", "396");
		params.put("td", "1433757917740");
		params.put("ts", "1434630215426");
		params.put("vid","903");
		params.put("sid", "902");
		requestvo.setRequestVo(headers, params, requestUrl+playerajaxpushfix);	
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@Test
	  public void ajaxpushPlayerNodata() {
		
		logger.info("Test Case is : ajaxpushPlayerNodata");
		params.clear();
		params.put("uid","10532");
		//params.put("data", "396");
		params.put("td", "1433757917740");
		params.put("ts", "1434630215426");
		params.put("vid","903");
		params.put("sid", "902");
		requestvo.setRequestVo(headers, params, requestUrl+playerajaxpushfix);
				
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@Test
	  public void ajaxpushPlayerNotd() {
		
		logger.info("Test Case is : ajaxpushPlayerNotd");
		params.clear();
		params.put("uid","10532");
		params.put("data", "396");
		//params.put("td", "1433757917740");
		params.put("ts", "1434630215426");
		params.put("vid","903");
		params.put("sid", "902");
		requestvo.setRequestVo(headers, params, requestUrl+playerajaxpushfix);		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	  }
	
	@Test
	  public void ajaxpushPlayerNots() {
		
		logger.info("Test Case is : ajaxpushPlayerNots");
		params.clear();
		params.put("uid","10532");
		params.put("data", "396");
		params.put("td", "1433757917740");
		//params.put("ts", "1434630215426");
		params.put("vid","903");
		params.put("sid", "902");
		requestvo.setRequestVo(headers, params, requestUrl+playerajaxpushfix);		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	
	@Test
	  public void ajaxpushPlayerNovid() {
		
		logger.info("Test Case is : ajaxpushPlayerNovid");
		params.clear();
		params.put("uid","10532");
		params.put("data", "396");
		params.put("td", "1433757917740");
		params.put("ts", "1434630215426");
		//params.put("vid","903");
		params.put("sid", "902");
		requestvo.setRequestVo(headers, params, requestUrl+playerajaxpushfix);
				
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	
	@Test
	  public void ajaxpushPlayerNosid() {
		
		logger.info("Test Case is : ajaxpushPlayerNosid");
		params.clear();
		params.put("uid","10532");
		params.put("data", "396");
		params.put("td", "1433757917740");
		params.put("ts", "1434630215426");
		params.put("vid","903");
		//params.put("sid", "902");
		requestvo.setRequestVo(headers, params, requestUrl+playerajaxpushfix);
				
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),400);
	  }
	
	@AfterMethod
	public void afterMethod() throws InterruptedException
	{
		Thread.sleep(THINKTIME);
	}
	 
}
