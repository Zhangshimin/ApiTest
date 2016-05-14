package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.GetProgress;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class GetOauthTicket extends TestBase{
	
	static Logger logger = Logger.getLogger(GetProgress.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	String token = "";
	String requrl = oauth_url;
	String access_token = "";
	
	
	@BeforeClass
	public void setup() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	
	
	@BeforeClass(dependsOnMethods = {"setup"})
	public void qqAccessToken()
	{
		params.clear();
		params.put("grant_type", "client_credential");
		params.put("appid", WEIXIN_APPID);
		params.put("secret", WEIXIN_APPSERCRET);
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(WEIXINURL);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		access_token = json.getString("access_token");
	}
	
	
	@Test(enabled = false)
	public void orderNormal()
	{
		logger.info("Test Case is : orderNormal");
		params.clear();
		params.put("access_token", access_token);
		params.put("uid", "12111431");
		params.put("platform", "wx");
		//headers.put("token", token);
		requestvo.setRequestParams(params);
		// requestvo.setRequesturl(api_url+sitcloudfix+previousfix+playerreviewfix);
		requestvo.setRequestUrl(requrl+oauthTicketfix);

		JSONObject json = BaseApiUtil.getResponse(requestvo);

		// System.out.println("TestCase name is : "+ "playerReviewNormal");
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	@Test(enabled = false)
	public void test()
	{
		logger.info("Test Case is : test");
		params.clear();
		params.put("access_token", access_token);
		params.put("openid", "12111431");
		//headers.put("token", token);
		requestvo.setRequestParams(params);
		// requestvo.setRequesturl(api_url+sitcloudfix+previousfix+playerreviewfix);
		requestvo.setRequestUrl("https://api.weixin.qq.com/sns/userinfo");

		JSONObject json = BaseApiUtil.getResponse(requestvo);

		// System.out.println("TestCase name is : "+ "playerReviewNormal");
		assertEquals(json.getIntValue("errorCode"), 200);
		//https://api.weixin.qq.com/sns/userinfo?openid=12111431&access_token=
	}
	
	
}
