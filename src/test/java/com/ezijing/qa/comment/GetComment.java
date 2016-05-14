/**
 * 
 */
package com.ezijing.qa.comment;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class GetComment extends TestBase{

	@BeforeMethod
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	

	@Test
	public void test() {
	
		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Cookis", "user.uid=1; user.sign=3a77b744687bce4d833fa850ce79645c;");
		RequestVo requestvo = new RequestVo();
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(api_url+commentfix+"/38");
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		
		System.out.println(json);
		AssertJUnit.assertEquals(json.getIntValue("errorCode"),200);
	}
	
	@Test
	public void testNoExist() {

		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Cookis", "user.uid=1; user.sign=3a77b744687bce4d833fa850ce79645c;");
		RequestVo requestvo = new RequestVo();
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(api_url+commentfix+"/1");
		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		
		System.out.println(json);
		AssertJUnit.assertEquals(json.getIntValue("errorCode"),404);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(THINKTIME);
	}
}
