package com.ezijing.qa.jssdk;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.SSOJssdkBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;

public class GetAccountShow extends TestBase{
	static Logger logger = Logger.getLogger(GetAccountShow.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	SSOJssdkBl jssdk = new SSOJssdkBl();
	private String cookie = "";
	
	@BeforeClass
	public void setLog4j()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		cookie = BaseApiUtil.jssdkLogin(USERNAME, PASSWORD);
	}
	@Test
	public void accountShow() {
		logger.info("Test Case is : accountShow");
		headers.put("Cookie",cookie);
		JSONObject json = jssdk.accountShow(headers, params);
		assertEquals(Utils.validateJson(json),true);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	 @AfterClass(enabled = true)
	  public void afterTest() throws InterruptedException {
			Thread.sleep(THINKTIME);
	  }
}
