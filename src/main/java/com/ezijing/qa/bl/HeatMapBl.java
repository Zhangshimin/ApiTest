package com.ezijing.qa.bl;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class HeatMapBl extends TestBase{

	//private static Logger logger = Logger.getLogger(HeatMapBo.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	//private String requestUrl = api_url + webfix + apifix;
	//private String data_requestUrl = api_url + cloudfix + data_version;

	public JSONObject getUrls(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, "http://sit.cloud.ezijing.com/v1/heatmap/urls");
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	public JSONObject heatMapPush(HashMap<String, String> headers,
			HashMap<String, String> params) {
		JSONObject result;
		requestvo.setRequestVo(headers, params, "http://sit.cloud.ezijing.com/v1/heatmap/push");
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
}
