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
 * @author Administrator
 *
 */
public class MessageBl extends TestBase {
	private static Logger logger = Logger.getLogger(QuizBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	private String requestUrl = api_url + webfix + apifix;

	public MessageBl() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}

	// *************************************** Thread
	public JSONObject createThread(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + threadfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	public JSONObject getThread(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + threadfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	public JSONObject getThreadId(HashMap<String, String> headers, HashMap<String, String> params, String threadid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + threadfix + slashfix + threadid);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	public JSONObject putThreadId(HashMap<String, String> headers, HashMap<String, String> params,String threadid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + threadfix + slashfix + threadid);
		result = BaseApiUtil.putResponse(requestvo);
		return result;
	}

	public JSONObject deleteThreadId(HashMap<String, String> headers, HashMap<String, String> params,String threadid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + threadfix + slashfix + threadid);
		result = BaseApiUtil.deleteResponse(requestvo);
		return result;
	}

	// *************************************** Message

	public JSONObject createMessage(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + messagefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	public JSONObject getMessage(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + messagefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	public JSONObject getMessageId(HashMap<String, String> headers, HashMap<String, String> params,String messageid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + messagefix + slashfix + messageid);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	public JSONObject putMessageId(HashMap<String, String> headers, HashMap<String, String> params,String messageid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + messagefix  + slashfix + messageid );
		result = BaseApiUtil.putResponse(requestvo);
		return result;
	}

	public JSONObject deleteMessageId(HashMap<String, String> headers, HashMap<String, String> params, String messageid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + messagefix  + slashfix + messageid);
		result = BaseApiUtil.deleteResponse(requestvo);
		return result;
	}

}
