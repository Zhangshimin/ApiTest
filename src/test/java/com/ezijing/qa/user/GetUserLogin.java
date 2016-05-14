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
import com.ezijing.qa.smoke.PostJudge;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;
import com.ezijing.qa.vo.ResponseVo;

public class GetUserLogin extends TestBase
{
  
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
	}
	@Test(enabled = true)
	public void userLogin()
	{
		
		params.clear();
		headers.clear();
		//headers.put("token", token);
		params.put("username", "wwwtest01");
		params.put("pass", "123456");
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl("http://www.ezijing.com/api/users/login");
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		assertEquals(json.getString("errorCode"),"200");
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
	
}
