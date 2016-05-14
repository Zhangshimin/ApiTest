package com.ezijing.qa.comment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.scenario.LearnPlanScenario;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class DeleteComment extends TestBase{
	
	static Logger logger = Logger.getLogger(DeleteComment.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	private String token = "";
	private String requestUrl = api_url;
	private String commentid = "42";
	
	@BeforeMethod
	public void setUp(){
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void testDeleteNoComment() {

		
	
		requestvo.setRequestVo(headers,params,requestUrl+commentfix+"/42");
		
		JSONObject json = BaseApiUtil.deleteResponse(requestvo);
		
		System.out.println(json);
		AssertJUnit.assertEquals(json.getIntValue("errorCode"),404);
	}

	@Test
	public void testDeleteComment() {

		
		//requestvo.setRequestparams(params);
		requestvo.setRequestUrl(api_url+commentfix+"/38");
		
		JSONObject json = BaseApiUtil.deleteResponse(requestvo);
		
		System.out.println(json);
		
		AssertJUnit.assertEquals(json.getIntValue("errorCode"),204);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
}
