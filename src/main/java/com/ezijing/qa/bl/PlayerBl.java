package com.ezijing.qa.bl;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class PlayerBl extends TestBase{
	private static Logger logger = Logger.getLogger(QuizBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	private String requestUrl = api_url+cloudfix+data_version;
	
	public PlayerBl()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	
	public JSONObject playerReview(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+playerreviewfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	public JSONObject playerPush(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+playerpushfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
}
