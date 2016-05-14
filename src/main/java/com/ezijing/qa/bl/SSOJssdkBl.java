package com.ezijing.qa.bl;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class SSOJssdkBl extends TestBase{
	private static Logger logger = Logger.getLogger(SSOJssdkBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String requestUrl = api_url + jssdkfix + accountfix;
	
	public SSOJssdkBl()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	
	/**
	 *jssdk login
	 * 
	 * */
	public JSONObject login(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+loginfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk logout
	 * 
	 * */
	public JSONObject logout(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+logoutfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk register
	 * 
	 * */
	public JSONObject register(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+registerfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk precheck
	 * 
	 * */
	public JSONObject precheck(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+precheckfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk show
	 * 
	 * */
	public JSONObject accountShow(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+showfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk show
	 * 
	 * */
	public JSONObject update(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+updatefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk passupdate
	 * 
	 * */
	public JSONObject passupdate(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+passupdatefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk passreset
	 * 
	 * */
	public JSONObject passreset(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+passresetfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 *jssdk users show
	 * 
	 * */
	public JSONObject usersShow(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, api_url + jssdkfix + usersfix + showfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
}
