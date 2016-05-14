/**
 * 
 */
package com.ezijing.qa.sms;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class PostSendSms extends TestBase{

	/**
	 * @throws java.lang.Exception
	 */
	static Logger logger = Logger.getLogger(PostSendSms.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String,String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = api_url + ssofix + api_version;
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}

	/**
	 * @throws java.lang.Exception
	 */

	@Test
	public void test() {

		
		params.put("mobile", "13811880202");
		params.put("content", "_test");
		RequestVo requestvo = new RequestVo();
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(requestUrl+sendSmsfix);
		JSONObject json = BaseApiUtil.postResponse(requestvo);
		assertEquals(json.getIntValue("errorCode"),200);
	}
	
	
}
