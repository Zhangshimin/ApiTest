package com.ezijing.qa.smoke;

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

public class PostUsersInfo extends TestBase{
 
	static Logger logger = Logger.getLogger(PostUsersInfo.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	
	String token = "";
	
	
	@BeforeMethod
	public void login()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token",token);
	}
	@Test
	public void userInfo()
	{
		params.clear();
		params.put("uid", "25868");
		requestvo.setRequestVo(headers, params, "http://sle.ezijing.com/api/user/info");
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		System.out.println(json);
		assertEquals(json.getJSONObject("error_info").getString("error_code"),"1010");
	}
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}

