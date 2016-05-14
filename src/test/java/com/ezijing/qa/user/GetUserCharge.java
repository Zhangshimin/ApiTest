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
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;
import com.ezijing.qa.vo.ResponseVo;

public class GetUserCharge extends TestBase{
	static Logger logger = Logger.getLogger(GetUserLogin.class);
	String token = "";
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	ResponseVo returnvo = new ResponseVo();
	RequestVo requestvo = new RequestVo();
	
	@BeforeMethod
	public void login()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
	}
	@Test(enabled = true)
	public void userCharge()
	{
		params.clear();
		headers.clear();
		headers.put("token", token);
		params.put("page_count", "1");
		params.put("page_size", "1");
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(api_url+"web/v1/"+userchargefix);
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getString("errorCode"),"200");
	}
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}