/**
 * 
 */
package com.ezijing.qa.vo;

import java.util.List;

import org.apache.http.cookie.Cookie;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cuixiaohui
 *
 */
public class ResponseVo {

	private JSONObject json;
	
	private List<Cookie> cookies;
	
	public JSONObject getJson() {
		return json;
	}
	public void setJson(JSONObject json) {
		this.json = json;
	}
	public List<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
	
}
