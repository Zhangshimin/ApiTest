package com.ezijing.qa.scenario;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.heatmap.GetUrls;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class LearnPlanScenario extends TestBase{
	
	static Logger logger = Logger.getLogger(LearnPlanScenario.class);
	RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String packageID = "";
	private String signature = "";
	private String updatesignature = "";
	//private String cookievalue = "";
	private String token = "";
	private String datenow = "";
	private String requestUrl = api_url + cloudfix + data_version;
	@BeforeMethod
	public void setup()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		//cookievalue = BaseApiUtil.login(USERNAME, PASSWORD,login_url);
		datenow = Utils.dataNow();
		headers.put("token", token);
	}
	
	@Test
	public void createPackage() {
		
		logger.info("Test Case is : createPackage");
		params.clear();
		params.put("uid", "25868");
		params.put("username", "autotestr");
		params.put("course_ids", "7496");
		
		requestvo.setRequestVo(headers,params,requestUrl+packagecreatefix);

		JSONObject json = BaseApiUtil.postResponse(requestvo);
		packageID = json.getJSONObject("data").getInteger("packageID").toString();
		assertEquals(json.getIntValue("errorCode"),200);

	}
	@Test(dependsOnMethods = {"createPackage"})
	public void createPlan()
	{
		logger.info("Test Case is : createPlan");
		
		params.clear();
		params.put("uid", "25868");
		params.put("package_id",packageID.toString());
		params.put("plan_circle", "6");
		params.put("started_at", datenow);
		
		requestvo.setRequestVo(headers,params,requestUrl+plancreatefix);
		
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		signature = json.getJSONObject("data").getString("signature");
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test(dependsOnMethods = {"createPlan"})
	public void validatePlan()
	{
		logger.info("Test Case is : validatePlan");
		params.clear();
		params.put("uid", "25868");
		params.put("signature",signature);

		
		requestvo.setRequestVo(headers,params,requestUrl+planvalidatefix);
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		

		assertEquals(json.getIntValue("errorCode"),200);
	}
	@Test(dependsOnMethods = {"validatePlan"})
	public void viewPlan()
	{
		logger.info("Test Case is : viewPlan");
		
		params.clear();
		params.put("uid", "25868");

		
		requestvo.setRequestVo(headers,params,requestUrl+planviewfix);
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test(dependsOnMethods = {"viewPlan"})
	public void updatePlan()
	{
		logger.info("Test Case is : updatePlan");
		params.clear();
		params.put("uid", "25868");
		params.put("package_id",packageID.toString());
		params.put("plan_circle", "12");
		params.put("started_at", datenow);
		
		requestvo.setRequestVo(headers,params,requestUrl+planupdatefix);
		
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		updatesignature = json.getJSONObject("data").getString("signature");
		assertEquals(json.getIntValue("errorCode"),200);
	}
	@Test(dependsOnMethods = {"updatePlan"})
	public void validateUpdatePlan()
	{
		logger.info("Test Case is : validateUpdatePlan");
		params.clear();
		params.put("uid", "25868");
		params.put("signature",updatesignature);

		
		requestvo.setRequestVo(headers,params,requestUrl+planvalidatefix);
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		

		assertEquals(json.getIntValue("errorCode"),200);
	}
	@Test(dependsOnMethods = {"validateUpdatePlan"}) 
	public void viewUpdatePlan()
	{
		logger.info("Test Case is : viewUpdatePlan");
		params.clear();
		params.put("uid", "25868");

		
		requestvo.setRequestVo(headers,params,requestUrl+planviewfix);
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@AfterMethod
	  public void logout() throws InterruptedException {
		  //BaseApiUtil.logout(logout_url, cookievalue);
		  BaseApiUtil.SSOLogout(token);
		  Thread.sleep(THINKTIME);
	  }
}
