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
 * @author Cui Xiaohui
 *
 */
public class QuestionAnswerBl extends TestBase {
	private static Logger logger = Logger.getLogger(QuizBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	private String requestUrl = api_url + webfix + apifix;

	public QuestionAnswerBl() {
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}

	/**
	 * 创建问题
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 创建的问题信息json
	 */
	public JSONObject createQuestion(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + questionfix);
		// requestvo.setRequestVo(headers, params, api_url + webfix +
		// questionfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	/**
	 * 查询
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 查询的问题信息json
	 */
	public JSONObject queryQuestion(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + questionfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	/**
	 * 查询
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 查询的问题信息json
	 */
	public JSONObject queryQuestionId(HashMap<String, String> headers, HashMap<String, String> params,
			String questionid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + questionfix + slashfix + questionid);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	/**
	 * 修改问题
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 修改问题信息json
	 */
	public JSONObject updateQuestion(HashMap<String, String> headers, HashMap<String, String> params,
			String questionid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + questionfix + slashfix + questionid);
		result = BaseApiUtil.putResponse(requestvo);
		return result;
	}

	/**
	 * 删除问题
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 删除问题信息json
	 */
	public JSONObject deleteQuestion(HashMap<String, String> headers, HashMap<String, String> params,
			String questionid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + questionfix + slashfix + questionid);
		result = BaseApiUtil.deleteResponse(requestvo);
		return result;
	}

	//************************************Answer********************************************
	
	
	
	//**************************************************************************************
	
	
	/**
	 * 创建答案
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 创建的答案信息json
	 */
	public JSONObject createAnswer(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + answerfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	/**
	 * 查询
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 查询的答案信息json
	 */
	public JSONObject queryAnswer(HashMap<String, String> headers, HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + answerfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	/**
	 * 查询
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 查询的答案信息json
	 */
	public JSONObject queryAnswerId(HashMap<String, String> headers, HashMap<String, String> params,
			String answerid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + answerfix + slashfix + answerid);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	/**
	 * 修改答案
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 修改答案信息json
	 */
	public JSONObject updateAnswer(HashMap<String, String> headers, HashMap<String, String> params,
			String answerid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + answerfix + slashfix + answerid);
		result = BaseApiUtil.putResponse(requestvo);
		return result;
	}

	/**
	 * 删除答案
	 * 
	 * @author Cui Xiaohui
	 * @param headers
	 * @param params
	 * @return 删除答案信息json
	 */
	public JSONObject deleteAnswer(HashMap<String, String> headers, HashMap<String, String> params,
			String answerid) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + answerfix + slashfix + answerid);
		result = BaseApiUtil.deleteResponse(requestvo);
		return result;
	}
}
