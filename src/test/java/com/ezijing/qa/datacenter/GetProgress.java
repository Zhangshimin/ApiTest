package com.ezijing.qa.datacenter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.CourseBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.smoke.PostJudge;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.Utils;
import com.ezijing.qa.vo.RequestVo;
import com.ezijing.qa.vo.ResponseVo;

public class GetProgress extends TestBase{
	
	static Logger logger = Logger.getLogger(GetProgress.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	CourseBl coursebo = new CourseBl();
	private String token = "";
	private String requesturl = api_url;

	@BeforeMethod
	public void setLog4j() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}

	@Test
	public void courseProgress() {
		params.clear();
		params.put("course_ids", "981");
		JSONObject json = coursebo.courseProgress(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@AfterMethod
	public void logout() throws Exception {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}

}
