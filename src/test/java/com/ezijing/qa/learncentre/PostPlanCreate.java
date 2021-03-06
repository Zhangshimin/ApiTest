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
import com.ezijing.qa.util.Utils;

/**
 * @author cuixiaohui
 *
 */
public class PostPlanCreate extends TestBase{

	static Logger logger = Logger.getLogger(PostPlanCreate.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	LearnBl learnbo = new LearnBl();
	String token = "";
	private String packageID = "";
	@BeforeMethod
	public void setup()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}
	
	@BeforeMethod(dependsOnMethods= {"setup"})
	public void createPackage() {
		logger.info("Test Case is : createPackage");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","PostPackageCreate");
		packageID = learnbo.createPackage(headers, params).getJSONObject("data").getInteger("packageID").toString();
	}
	@Test
	public void createPlan()
	{
		logger.info("Test Case is : createPlan");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","PostPlanCreate");
		params.put("package_id",packageID.toString());
		params.put("started_at", Utils.dataNow());
		JSONObject json = learnbo.createPlan(headers, params);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	@AfterMethod
	  public void logout() throws InterruptedException {
		  BaseApiUtil.SSOLogout(token);
		  Thread.sleep(THINKTIME);
	  }

}
