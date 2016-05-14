/**
 * 
 */
package com.ezijing.qa.vo;

import java.util.HashMap;

/**
 * @author cuixiaohui
 *
 */
public class RequestVo {

	private String _requesturl = "";
	private HashMap<String,String> _requestparams = new HashMap<String,String>();
	private HashMap<String,String> _requestheaders = new HashMap<String,String>();
	
	public String getRequestUrl() {
		return _requesturl;
	}
	public void setRequestUrl(String requesturl) {
		this._requesturl = requesturl;
	}
	public HashMap<String,String> getRequestParams() {
		return _requestparams;
	}
	public void setRequestParams(HashMap<String, String> params) {
		this._requestparams = params;
	}
	public HashMap<String, String> getRequestHeaders() {
		return _requestheaders;
	}
	public void setRquestHeaders(HashMap<String, String> _requestheaders) {
		this._requestheaders = _requestheaders;
	}
	public void setRequestVo(HashMap<String,String> headers,HashMap<String,String> params,String requesturl)
	{
		setRquestHeaders(headers);
		setRequestParams(params);
		setRequestUrl(requesturl);
	}
}
