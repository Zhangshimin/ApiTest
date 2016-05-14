package com.ezijing.qa.order;

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
import com.ezijing.qa.bl.OrderBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.GetProgress;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.vo.RequestVo;

public class GetOrderTn extends TestBase{

	static Logger logger = Logger.getLogger(GetOrderTn.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	OrderBl orderbo = new OrderBl();
	private String token = "";
	private String orderid = "";
	
	@BeforeMethod
	  public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "Order", "PostOrder");
		orderid = orderbo.order(headers, params).getJSONObject("data").getString("id");
	  }
	@Test
	public void getTn()
	{
		logger.info("Test Case is : getTn");
		params.clear();
		JSONObject json = orderbo.getOrderTn(headers, params, orderid);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@AfterMethod
	public void logout() throws InterruptedException
	{
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
