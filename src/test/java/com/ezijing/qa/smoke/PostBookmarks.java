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

public class PostBookmarks extends TestBase{
	static Logger logger = Logger.getLogger(PostBookmarks.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	ResponseVo returnvo = new ResponseVo();
	private String requestUrl = "";
	private String token = "";
	
	
	@BeforeMethod
	public void login()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token",token);
	}
	@Test
	public void bookmarks()
	{
		params.clear();
		params.put("js", "TRUE");
		requestvo.setRequestVo(headers,params,"http://sit.ezijing.com/flag/flag/bookmarks/940");
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		//assertEquals(json.getJSONObject("error_info").getString("error_code"),"1010");
	}
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
