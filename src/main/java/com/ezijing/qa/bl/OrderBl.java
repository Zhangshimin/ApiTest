/**
 * 
 */
package com.ezijing.qa.bl;

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
public class OrderBl extends TestBase {
	private static Logger logger = Logger.getLogger(QuizBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	private String requestUrl = api_url + webfix + apifix;
	//private String testurl = sle_url + apifix;
	public OrderBl() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}

	/**
	 * 
	 * 
	 * */
	public JSONObject order(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + orderfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	public JSONObject purchase(HashMap<String, String> headers,
			HashMap<String, String> params,String nodeid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + nodefix + slashfix + nodeid + purchasefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	/**
	 * 
	 * 
	 * */
	public JSONObject getOrderTn(HashMap<String, String> headers,
			HashMap<String, String> params, String orderid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + orderfix
				+ slashfix + orderid + "/getTn");
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	/**
	 * 
	 * 
	 * */

	public JSONObject queryOrder(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, api_url + webfix + api_version
				+ orderfix + queryfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	/**
	 * 
	 * 
	 * */
	public JSONObject deleteOrder(HashMap<String, String> headers,
			HashMap<String, String> params, String orderid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, api_url + webfix + api_version
				+ orderfix + slashfix + orderid + "/delete");
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
}
