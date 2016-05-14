

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.baseinfo.TestBase;
import com.ezijing.qa.heatmap.TestGetUrls;
import com.ezijing.qa.message.PostMessage;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class APITestCaseTemplate extends TestBases{
	
	static Logger logger = Logger.getLogger(APITestCaseTemplate.class);
	String token = "";
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	
	@BeforeTest
	public void beforeTest() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);  //sso login
	}

	@Test
	public void testCaseNormal() {

		logger.info("Test Case is : createMessageNormal");

		headers.clear();
		params.clear();
		headers.put("token",token);
		params.put("body", "test");
		params.put("thread", "449");
		requestvo.setRequestparams(params);
		requestvo.setRequesturl(api_url + webfix + messagefix);

		JSONObject json = BaseApiUtil.postResponse(requestvo, headers);
		assertEquals(json.getIntValue("errorCode"), 200);
	}

	@Test(enabled = false)
	public void createMessageNoLogin() {

		logger.info("Test Case is : createMessageNoLogin");
		headers.clear();
		params.clear();
		params.put("body", "test");
		params.put("thread", "449");
		requestvo.setRequestparams(params);
		requestvo.setRequesturl(api_url + webfix + messagefix);

		JSONObject json = BaseApiUtil.postResponse(requestvo, headers);
		assertEquals(json.getIntValue("errorCode"), 401);
	}

	

	@AfterTest
	public void afterTest() {
		BaseApiUtil.SSOLogout(token);
	}
}
