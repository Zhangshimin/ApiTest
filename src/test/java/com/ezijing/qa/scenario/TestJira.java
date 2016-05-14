package com.ezijing.qa.scenario;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

public class TestJira {
	
	static Logger logger = Logger.getLogger(TestJira.class);
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	RequestVo requestvo = new RequestVo();
	private String requestUrl = "http://jira.test.ezijing.com/rest/api/2/issue/TEST-106";
  @Test
  public void f() {
	  
		headers.put("Authorization", "Basic "+Base64.encode("cuixiaohui:123456".trim().getBytes()));
		headers.put("Content-Type", "application/json");
		requestvo.setRequestVo(headers, params, requestUrl);
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		System.out.println(json);
  }
}
