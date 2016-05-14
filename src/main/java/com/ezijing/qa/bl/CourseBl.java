package com.ezijing.qa.bl;

import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class CourseBl extends TestBase{

	private static Logger logger = Logger.getLogger(CourseBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String requestUrl = api_url + webfix + apifix;
	private String data_requestUrl = api_url+cloudfix+data_version;
	private String data_previous_requestUrl = api_url + cloudfix + previousfix;
	
	//judge message
	public static final String COURSE_JUDGE_FREE_NOPART = "This course users do not have to buy did not participate";
	public static final String COURSE_JUDGE_NO_COLLECTION = "This course no collection";
	//subscribe message
	public static final String COURSE_SUBSCRIBE_FREE = "This course users do not have to buy";
	
	//unsubscribe message
	public static final String COURSE_UNSUBSCRIBE_FREE = "";
	
	public CourseBl()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	/**
	 * judge course status
	 * @author Cui Xiaohui
	 * @param header
	 * @param params
	 * @return JSONObject object of course status
	 * 
	 * */
	public JSONObject courseJudge(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+coursejudgefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * 参加课程
	 * @author Cui Xiaohui
	 * @param header
	 * @param params
	 * @return JSONObject object of course status
	 * 
	 * */
	public JSONObject courseSubscribe(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+coursesubscribefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * 获得参加的课程信息
	 * @author Cui Xiaohui
	 * @param header
	 * @param params
	 * @return JSONObject object of course status
	 * 
	 * */
	public JSONObject courseGetSubscribed(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+nodefix + subscribedfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * 判断是否参加课程
	 * @author Cui Xiaohui
	 * @param header
	 * @param params
	 * @return JSONObject object of course status
	 * 
	 * */
	public JSONObject courseGetSubscribe(HashMap<String,String> headers,HashMap<String,String> params,String nodeid)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+nodefix + slashfix + nodeid + subscribefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	/**
	 * 退出课程
	 * @author Cui Xiaohui
	 * @param header
	 * @param params
	 * @return JSONObject object of course status
	 * 
	 * */
	public JSONObject courseUnsubscribe(HashMap<String,String> headers,HashMap<String,String> params,String nodeid)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+nodefix + slashfix + nodeid + unsubscribefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	public JSONObject courseUnsubscribed(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+courseunsubscribefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject coursePurchase(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + coursepurchasefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject coursePurchased(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl + purchasedfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	//**************************************datacenter**********************************
	
	public JSONObject courseInformation(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + courseinformationfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject courseMyfavorite(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + coursemyfavoritefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject courseMyJoined(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + coursemyjoinedfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject courseMyPurchased(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + coursemypurchasedfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject courseProgress(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + courseProgressfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	public JSONObject courseProgressAll(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + courseProgressallfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject coursePreviousExamPush(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_previous_requestUrl + exampushfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	public JSONObject courseHot(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, data_requestUrl + coursehotfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	
}

