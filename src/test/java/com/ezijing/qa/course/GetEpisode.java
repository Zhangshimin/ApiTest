package com.ezijing.qa.course;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;
import com.ezijing.qa.vo.ResponseVo;

public class GetEpisode extends TestBase{
  
	static Logger logger = Logger.getLogger(GetEpisode.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = sit_url + api_version;
	private String nid = "6573";


	@BeforeMethod
	public void init()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	@Test
	public void episode() throws UnsupportedEncodingException {
		
		logger.info("Test Case is : episode");
		requestvo.setRequestVo(headers, params, requestUrl+episodefix+"/"+nid);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
		String ppt = json.getJSONArray("data").getJSONObject(1).getString("ppt_url");
		ppt = URLDecoder.decode(ppt,"UTF-8");
		System.out.println(ppt);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
}
}
