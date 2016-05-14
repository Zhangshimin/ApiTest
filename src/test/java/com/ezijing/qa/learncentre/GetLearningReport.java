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
public class GetLearningReport extends TestBase{

	static Logger logger = Logger.getLogger(GetLearningReport.class);
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
	public void learningReportNormal() {
		
		logger.info("Test Case is : learningReportNormal");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","GetLearningReport");
		JSONObject json = learnbo.learningReport(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@AfterMethod
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}
