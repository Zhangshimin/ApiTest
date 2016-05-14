/**
 * 
 */
package com.ezijing.qa.learncentre;

import static org.testng.AssertJUnit.assertEquals;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.LearnBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;

/**
 * @author cuixiaohui
 *
 */
public class GetLearningList extends TestBase{
static Logger logger = Logger.getLogger(GetLearningList.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	LearnBl learnbo = new LearnBl();
	String token = "";

	@BeforeMethod
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}
	@Test
	public void learningListNormal() {
		
		logger.info("Test Case is : learningListNormal");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","GetLearningList");
		JSONObject json = learnbo.learningList(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@AfterMethod
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
