package com.ezijing.qa.datacenter.mobile;

import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class GetSwitch extends TestBase{
	static Logger logger = Logger.getLogger(GetSwitch.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = api_url+cloudfix+data_version;
	
	@BeforeMethod
	public void setup()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}

	@Test
	public void mobileSwitchNoraml() {

		logger.info("Test Case is : mobileChannelNoraml");
		requestvo.setRequestVo(headers, params, requestUrl+mobileswitchfix);		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException
	{
		Thread.sleep(THINKTIME);
	}
}
