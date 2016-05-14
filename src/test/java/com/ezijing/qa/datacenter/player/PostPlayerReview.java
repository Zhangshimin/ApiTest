package com.ezijing.qa.datacenter.player;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.PlayerBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.datacenter.GetProgress;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.PoiUtils;


public class PostPlayerReview extends TestBase {
	static Logger logger = Logger.getLogger(GetProgress.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	PlayerBl playerbo = new PlayerBl();
	String token = "";

	@BeforeClass
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void playerReviewNormal() {
		logger.info("Test Case is : playerReviewNormal");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test
	public void playerReviewNovideoid() {
		logger.info("Test Case is : playerReviewNovideoid");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		params.remove("video_id");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void playerReviewNoresult() {
		logger.info("Test Case is : playerReviewNoresult");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		params.remove("result");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void playerReviewNodevicetype() {
		logger.info("Test Case is : playerReviewNodevicetype");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		params.remove("device_type");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void playerReviewNoquizid() {
		logger.info("Test Case is : playerReviewNoquizid");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		params.remove("quiz_id");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void playerReviewNocosttime() {
		logger.info("Test Case is : playerReviewNocosttime");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		params.remove("cost_time");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@Test
	public void playerReviewNoseriesid() {
		logger.info("Test Case is : playerReviewNoseriesid");
		params = PoiUtils.getTestData(EXCEL_DATA_FILE, "DataCenterPlayer", "PostPlayerReview");
		params.remove("series_id");
		JSONObject json = playerbo.playerReview(headers, params);
		assertEquals(json.getIntValue("errorCode"), 400);
	}

	@AfterClass
	public void afterTest() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
	    Thread.sleep(THINKTIME);
	}

}
