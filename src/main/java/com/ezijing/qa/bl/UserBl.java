package com.ezijing.qa.bl;

import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class UserBl extends TestBase{

	//private static Logger logger = Logger.getLogger(UserBo.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	//private String requestUrl = api_url + webfix;
	//private String data_requestUrl = api_url+cloudfix+data_version;
	
	
	//***********************************cloud*****************************
	public JSONObject isLogin(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, api_url + cloudfix + userisloginfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject userInformation(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, api_url + cloudfix + userinformationfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	
	
	//***********************************webapi*****************************
	
	public JSONObject userAll(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, sit_url + api_version + userallfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
}
