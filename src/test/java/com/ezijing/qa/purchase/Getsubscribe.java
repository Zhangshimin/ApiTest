/**
 * 
 */
package com.ezijing.qa.purchase;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.CourseBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class Getsubscribe extends TestBase{
	static Logger logger = Logger.getLogger(GetPurchased.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	CourseBl coursebo = new CourseBl();
	String token = "";
	String nodeid = "";

	@BeforeMethod
	public void beforeTest() {
		HashMap<String, String> subscribeparam = new HashMap<String, String>();
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
		subscribeparam = PoiUtils.getTestData(EXCEL_WEB_FILE, "Course", "Getsubscribe");
		nodeid = Utils.getMapValue(subscribeparam, "node");
	}

	@Test
	public void getSubscribeNormal() {
		logger.info("Test Case is : getSubscribeNormal");
		JSONObject json = coursebo.courseGetSubscribe(headers, params,nodeid);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterMethod
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
	    Thread.sleep(THINKTIME);
	}
}
