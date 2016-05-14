package com.ezijing.qa.bl;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class QuizBl extends TestBase{

	private static Logger logger = Logger.getLogger(QuizBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String requestUrl = api_url + webfix;
	
	public QuizBl()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	
	public JSONObject quizShow(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+quizshowfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	
	
	public JSONObject quizSetOptions(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+quizsetoptionsfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
}
