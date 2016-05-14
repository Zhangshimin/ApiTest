/**
 * 
 */
package com.ezijing.qa.bl;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class SSOBl extends TestBase{
	
	private static Logger logger = Logger.getLogger(SSOBl.class);
	private RequestVo requestvo = new RequestVo();
	HashMap<String,String> params = new HashMap<String,String>();
	HashMap<String,String> headers = new HashMap<String,String>();
	//private String loginRequestUrl = login_url+ssofix+api_version;
	private String requestUrl = api_url + ssofix + api_version;
	
	public SSOBl()
	{
		BaseApiUtil.initLog4j(logger, Level.INFO);
	}
	
	
	/**
	 * get user info
	 * 
	 * */
	public JSONObject userInfo(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result;
		requestvo.setRequestVo(headers, params, requestUrl+userinfofix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	/**
	 * register user
	 * 
	 * */
	public JSONObject registerUser(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+registerfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}

	/**
	 * check if register user
	 * 
	 * */
	public JSONObject registerCheck(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+registerCheckfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * get ticket
	 * 
	 * */
	public JSONObject ticket(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+ticketsfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * ticket TGT is register user
	 * 
	 * */
	public JSONObject ticketTGT(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+registerCheckfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * ticket ST is register user
	 * 
	 * */
	public JSONObject ticketST(HashMap<String,String> headers,HashMap<String,String> params,String tgt)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+ticketsfix+slashfix+tgt);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * delete TGT
	 * 
	 * */
	public JSONObject deleteTGT(HashMap<String,String> headers,HashMap<String,String> params,String tgt)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+ticketsfix+slashfix+tgt);
		result = BaseApiUtil.deleteResponse(requestvo);
		return result;
	}
	
	/**
	 * ticket ST is register user
	 * 
	 * */
	public JSONObject proxyValidate(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, api_url + ssofix+proxyValidatefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	/**
	 * modify password
	 * 
	 * */
	public JSONObject modifyPassword(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+modifyPasswordfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	

	
//*************************************        User       *************************
	
	/**
	 * add User
	 * 
	 * */
	public JSONObject createUser(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl + userfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * put User
	 * 
	 * */
	public JSONObject putUser(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl + userfix);
		result = BaseApiUtil.putResponse(requestvo);
		return result;
	}
	
	/**
	 * put User
	 * 
	 * */
	public JSONObject updateUser(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl + updateuserfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	/**
	 * get User
	 * 
	 * */
	public JSONObject getUser(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl + userfix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}

	/**
	 * delete user
	 * 
	 * */
	public JSONObject deleteUser(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+userfix);
		result = BaseApiUtil.deleteResponse(requestvo);
		return result;
	}
	
	
	//*****************************              Role            ******************************	
	
	/**
	 * 
	 * add role
	 * */
	public JSONObject addRole(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+userRolefix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
	
	/**
	 * 
	 * get role
	 * */
	public JSONObject queryRole(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+userRolefix);
		result = BaseApiUtil.getResponse(requestvo);
		return result;
	}
	
	/**
	 * 
	 * update role
	 * */
	public JSONObject updateRole(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+userRolefix);
		result = BaseApiUtil.putResponse(requestvo);
		return result;
	}
	
	
	public JSONObject validateEmail(HashMap<String,String> headers,HashMap<String,String> params)
	{
		JSONObject result = null;
		requestvo.setRequestVo(headers, params, requestUrl+validateEmailfix);
		result = BaseApiUtil.postResponse(requestvo);
		return result;
	}
}
