package com.ezijing.qa.scenario;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.CourseBl;
import com.ezijing.qa.bl.OrderBl;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.order.PostPurchase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;

public class PurchaseScen extends TestBase {
	static Logger logger = Logger.getLogger(PostPurchase.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	OrderBl orderbo = new OrderBl();
	CourseBl coursebl = new CourseBl();
	String token = "";
	String nodeid = "";

	@BeforeClass
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin("19910001007","001007");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "Order", "PostPurchase");
		nodeid = Utils.getMapValue(params, "node_nid");
		headers.put("token", token);
	}

	@Test
	public void purchaseNoLogin() {
		logger.info("Test Case is : purchaseLogin");
		headers.clear();
		JSONObject json = orderbo.purchase(headers, params, nodeid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}


	@AfterClass 
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
