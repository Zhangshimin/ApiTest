/**
 * 
 */
package com.ezijing.qa.datacenter.mobile;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class GetChannel extends TestBase{

	static Logger logger = Logger.getLogger(GetChannel.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	private String cookievalue = "";
	private String requestUrl = api_url+cloudfix+data_version;
	
	@BeforeMethod
	public void setup()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		headers.put("Cookie", cookievalue);
	}

	@Test
	public void mobileChannelNoraml() {

		logger.info("Test Case is : mobileChannelNoraml");
		requestvo.setRequestVo(headers, params, requestUrl+mobilechannelfix);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException
	{
		Thread.sleep(THINKTIME);
	}
}
