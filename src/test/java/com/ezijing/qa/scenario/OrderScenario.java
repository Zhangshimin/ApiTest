package com.ezijing.qa.scenario;

import static org.testng.AssertJUnit.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class OrderScenario extends TestBase{

	static Logger logger = Logger.getLogger(OrderScenario.class);
	RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String cookievalue = "";
	private String token = "";
	private String datenow = "";
	@BeforeMethod
	public void setup()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		//cookievalue = BaseApiUtil.loginSessid(USERNAME, PASSWORD);
	}
	
	
	@Test
	public void getCourses() {
		
		logger.info("Test Case is : getCourses");
		
		headers.clear();
		params.clear();
		headers.put("token", token);
		headers.put("Cookie", cookievalue);
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(sit_url+api_version+coursefix+slashfix+"3479");
		requestvo.setRquestHeaders(headers);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test
	public void getUserAjaxMessage() {
		
		logger.info("Test Case is : getUserAjaxMessage");
		
		headers.clear();
		params.clear();
		headers.put("token", token);
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(sit_url+messagesfix+userajaxfix);
		requestvo.setRquestHeaders(headers);
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@AfterMethod
	  public void logout() throws InterruptedException {
		  BaseApiUtil.SSOLogout(token);
		  Thread.sleep(THINKTIME);
	  }
}
