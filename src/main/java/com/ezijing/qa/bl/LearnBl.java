package com.ezijing.qa.bl;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class LearnBl extends TestBase{

	private static Logger logger = Logger.getLogger(QuizBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String requestUrl = api_url+cloudfix+data_version;
	
	public LearnBl()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	
	public JSONObject learningReport(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+learningreportfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject learningList(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+learninglistfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject createAjaxPackage(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+packageajaxcreatefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject updateAjaxPlan(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+planajaxupdatefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject planValidate(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+planvalidatefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject planView(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+planviewfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject createPackage(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+packagecreatefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	public JSONObject createPlan(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+plancreatefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	public JSONObject updatePlan(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+planupdatefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
}
