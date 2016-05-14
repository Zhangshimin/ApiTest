package com.ezijing.qa.learncentre;

import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.LearnBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;
import com.ezijing.qa.util.Utils;

public class GetPlanAjaxUpdate extends TestBase{
	static Logger logger = Logger.getLogger(GetPlanAjaxUpdate.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	LearnBl learnbo = new LearnBl();
	String token = "";
	private String packageID = "";
	private String signature = "";
	
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
	@BeforeMethod(dependsOnMethods= {"createPackage"})
	public void createPlan()
	{
		logger.info("Test Case is : createPlan");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","PostPlanCreate");
		params.put("package_id",packageID.toString());
		params.put("started_at", Utils.dataNow());
		signature = learnbo.createPlan(headers, params).getJSONObject("data").getString("signature");
	}
	@BeforeMethod(dependsOnMethods= {"createPlan"})
	public void validatePlan()
	{
		logger.info("Test Case is : validatePlan");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","GetPlanValidate");
		params.put("signature",signature);
		learnbo.planValidate(headers, params);
	}

	@BeforeMethod(dependsOnMethods = { "validatePlan" })
	public void viewPlan() {
		logger.info("Test Case is : viewPlan");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","GetPlanView");
		learnbo.planView(headers, params);
	}
	@Test
	public void ajaxUpdatePlan() {
		logger.info("Test Case is : ajaxUpdatePlan");
		
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "LearnCenter","GetPlanAjaxUpdate");
		params.put("package_id", packageID.toString());
		params.put("started_at", Utils.dataNow());
		JSONObject json = learnbo.updateAjaxPlan(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterMethod
	public void logout() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
}
