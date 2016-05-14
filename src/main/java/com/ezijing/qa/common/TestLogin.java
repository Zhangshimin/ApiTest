/**
 * 
 */
package com.ezijing.qa.common;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.util.CookieObject;
import com.ezijing.qa.vo.UserVo;

/**
 * @author cuixiaohui
 *
 */
public class TestLogin extends TestBase{

	JSONObject json = new JSONObject(); 
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		
	}

	@Test
	public void testLogin() {
//		UserVo user = BaseApiUtil.createUser(USERNAME, PASSWORD);
//		json = BaseApiUtil.login(user);
//		System.out.println(json);
//		SESSID = json.getJSONObject("register").getString("sessid");
//        SESSIONNAME = json.getJSONObject("register").getString("session_name");
//		assertEquals(200, json.getJSONObject("error_info").getIntValue("error_code"));
		
	}

}
