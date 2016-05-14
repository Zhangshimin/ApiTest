package com.ezijing.qa.sso;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

public class PostProxyValidate extends TestBase{

	static Logger logger = Logger.getLogger(PostProxyValidate.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	SSOBl ssobo = new SSOBl();
	private String username = "";
	private String password = "123456";
	private String mid = "";
	private String tgt = "";
	private String ticketid = "";

	/**
	 * Register User 接口调用返回结果数据对象，包括cookie信息及json串
	 */

	@BeforeClass
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		headers.put("Charset", "charset=utf-8;");
		username = "AutoTestUser" + Utils.randomString(); // 随机生成用户名
	}

	@BeforeClass(dependsOnMethods = { "setLog4j" })
	public void registerUser() {
		params.clear();
		params.put("username", username);
		params.put("password", password);
		params.put("email", username+"@qq.com");
		JSONObject json = ssobo.registerUser(headers, params);
		mid = json.getJSONObject("data").getString("mid");
		tgt = json.getJSONObject("data").getString("tgt");
	}
	/**
	 * Post tickets/TGT
	 */
	@BeforeClass(dependsOnMethods = { "registerUser" })
	public void PostTicketTGT() {
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		ticketid = ssobo.ticketST(headers, params, tgt).getJSONObject("data").getString("ticket_id");
	}
	/**
	 * Post tickets/TGT 接口调用返回结果数据对象，包括cookie信息及json串
	 * 
	 */
	@Test
	public void postProxyValidateNormal() {
		logger.info("Test Case is : postProxyValidateNormal");
		params = PoiUtils.getTestData(EXCEL_WEB_FILE, "SSO", "PostTickets");
		params.put("ticket", ticketid);
		JSONObject json = ssobo.proxyValidate(headers, params);
		assertEquals(Utils.validateJson(json), true);
		assertEquals(json.getIntValue("errorCode"), 200);
	}
	@AfterClass
	public void afterTest() throws InterruptedException {
		params.clear();
		params.put("mid", mid);
		ssobo.deleteUser(headers, params);
		Thread.sleep(THINKTIME);
	}
}
