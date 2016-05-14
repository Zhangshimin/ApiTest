package com.ezijing.qa.order;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.OrderBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.GetProgress;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class PostPurchase extends TestBase{
  
	
	
	static Logger logger = Logger.getLogger(PostPurchase.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	OrderBl orderbo = new OrderBl();
	private String token = "";
	private String cookievalue = "";
	private String oid = "";
	@BeforeClass
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		//token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		//headers.put("token", token);
	}
	
	
	@Test(enabled = true)
	public void orderNormal()
	{
		logger.info("Test Case is : orderNormal");
		params = PoiUtils.getTestData("webapi", "Order", "PostPurchase");
		String nodeid = Utils.getMapValue(params,"node_nid");
		JSONObject json = orderbo.purchase(headers, params, nodeid);
		oid = String.valueOf(json.getJSONObject("data").getIntValue("id"));
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	
	@Test(dependsOnMethods = {"orderNormal"},enabled = false)
	public void queryorderNormal()
	{
		logger.info("Test Case is : queryorderNormal");
		params.put("id", oid);
		JSONObject json = orderbo.queryOrder(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	
	@Test(dependsOnMethods = {"queryorderNormal"}, enabled = false)
	public void deleteorderNormal()
	{
		logger.info("Test Case is : queryorderNormal");
		JSONObject json = orderbo.deleteOrder(headers, params, oid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	
	@AfterClass
	public void logout() throws InterruptedException
	{
		//BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
