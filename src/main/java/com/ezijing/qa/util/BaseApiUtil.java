package com.ezijing.qa.util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.vo.ResponseVo;
import com.ezijing.qa.vo.RequestVo;


public class BaseApiUtil extends TestBase{

	/**
	 * @author cuixiaohui
	 * @param 1. value requestvo
	 * @param 1. type RequestVo
	 * @param 2. value header hashmap
	 * @param 2. type hashmap
	 * @return JSONObject
	 * @exception 
	 * 
	 * */
	public static JSONObject getResponse(RequestVo requestvo) {
		// TODO Auto-generated method stub
		ResponseVo returnvo = null;
			try {
				returnvo = Utils.httpGet(requestvo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return returnvo.getJson();
	}
	
	/**
	 * @author cuixiaohui
	 * @param value requestvo
	 * @param type RequestVo
	 * @param value header hashmap
	 * @param type hashmap
	 * @return JSONObject
	 * @exception 
	 * 
	 * */
	public static JSONObject postResponse(RequestVo requestvo) {
		// TODO Auto-generated method stub
		ResponseVo returnvo = null;
		returnvo = Utils.httpPost(requestvo);
		return returnvo.getJson();
	}

	/**
	 * @author cuixiaohui
	 * @param value requestvo
	 * @param type RequestVo
	 * @param value header hashmap
	 * @param type hashmap
	 * @return JSONObject
	 * @exception 
	 * */
	public static JSONObject deleteResponse(RequestVo requestvo) {
		// TODO Auto-generated method stub
		ResponseVo returnvo = null;
		if(requestvo.getRequestParams().isEmpty()){
			returnvo = Utils.httpdelete(requestvo);
		}else
		{
			returnvo = Utils.httpDeleteWithBody(requestvo);
		}
		return returnvo.getJson();
	}
	
	/**
	 * @author cuixiaohui
	 * @param value requestvo
	 * @param type RequestVo
	 * @param value header hashmap
	 * @param type hashmap
	 * @return JSONObject
	 * @exception 
	 * 
	 * */
	public static JSONObject putResponse(RequestVo requestvo) {
		// TODO Auto-generated method stub
		ResponseVo returnvo = null;
		returnvo = Utils.httpPut(requestvo);
		return returnvo.getJson();
	}
	
	/**
	 * @author cuixiaohui
	 * @param username,password,service
	 * @return login token info
	*/
	public static String SSOLogin(String username, String password)
	{
		String tgtid = "";
		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		RequestVo requestvo = new RequestVo();
		ResponseVo returnvo = new ResponseVo();
		headers.put("Charset", "charset=utf-8;");
		params.put("username", username);
		params.put("password",password);
		params.put("service", SERVICE_SIT);
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(api_url+ssofix+api_version+ticketsfix);
		//requestvo.setRequesturl(docker_url + ticketsfix);
		returnvo = Utils.httpPost(requestvo);
		JSONObject json = returnvo.getJson();
		if(json.getIntValue("errorCode")==200) 
		{
			tgtid = json.getJSONObject("data").getString("tgt_id");
			System.out.println("SSO Login successfully!");
			return tgtid;
			
		}else
		{
			System.out.println("Login failed!");
			return json.getString("errorCode");
		}
		
	}
	
	/**
	 * @author cuixiaohui
	 * @param token
	 * @return void
	 * 
	*/
	public static void SSOLogout(String token)
	{
		HashMap<String,String> params = new HashMap<String,String>();
		RequestVo requestvo = new RequestVo();
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Charset", "charset=utf-8;");
			
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(api_url+ssofix+api_version+ticketsfix+"/"+token);
		requestvo.setRquestHeaders(headers);
		JSONObject json = BaseApiUtil.deleteResponse(requestvo);
		if(json.getIntValue("errorCode")==200)
		{
			System.out.println("Logout successfully!");
		}else
		{
			System.out.println("Logout failed!");
		}
		
	}
	/**
	 * @author cuixiaohui
	 * @param username,password,host
	 * @return login cookie info
	 * 
	*/
	public static String login(String username, String password)
	{
		
		String usercookiekey = "zjut";
		String usercookievalue = "";
		String cookievalue = "";
		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		RequestVo requestvo = new RequestVo();
		ResponseVo returnvo = new ResponseVo();
		List<Cookie> cookies = null;
		params.put("name", username);
		params.put("password",password);	
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(sit_sso_login);
		returnvo = Utils.httpPost(requestvo);
		cookies = returnvo.getCookies();
		usercookievalue = Utils.getCookieValue(cookies, usercookiekey);
		
		JSONObject json = returnvo.getJson();
		if(json.getIntValue("errorCode")==200)  
		{
			cookievalue = usercookiekey+"="+usercookievalue+"; ";
			System.out.println("Login successfully!");
			return cookievalue;
		}else
		{
			System.out.println("Login failed!");
			return null;
		}
	}
	
	
	public static String loginSessid(String username, String password)
	{
		String cookievalue = "";
		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		RequestVo requestvo = new RequestVo();
		ResponseVo returnvo = new ResponseVo();
		  
		params.put("name", username);
		params.put("password",password);	
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(api_login_url);
		requestvo.setRquestHeaders(headers);
		  
		returnvo = Utils.httpPost(requestvo);
		JSONObject json = returnvo.getJson();
		if(json.getJSONObject("error_info").getIntValue("error_code")==200)  
		{
			cookievalue = json.getJSONObject("register").getString("session_name")+"="+json.getJSONObject("register").getString("sessid")+"; ";
			System.out.println("Login successfully!");
			return cookievalue;
		}else
		{
			System.out.println("Login failed!");
			return null;
		}
	}

	/**
	 * @author cuixiaohui
	 * @param username,password,host
	 * @return login cookie info
	 * 
	*/
	public static boolean logout(String cookievalue)
	{
		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		RequestVo requestvo = new RequestVo();
		  
		headers.put("Cookie", cookievalue);
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl(sit_sso_logout);
		requestvo.setRquestHeaders(headers);		
		JSONObject json = BaseApiUtil.getResponse(requestvo);
		
		if(json.getIntValue("errorCode")==200)
		{
			System.out.println("Logout Successfully!");
			return true;
		}else
		{
			System.out.println("Logout failed");
			return false;
		}
	}

	/**
	 * @author cuixiaohui
	 * Name: JSSDK Login
	 * @param username,password,host
	 * @return login cookie info
	 * 
	*/
	public static String jssdkLogin(String username, String password)
	{
		String usercookiekey = "zjut";
		String usercookievalue = "";
		String cookievalue = "";
		String phpsessionid = "PHPSESSID";
		String phpsessidvalue = "";
		HashMap<String,String> params = new HashMap<String,String>();
		HashMap<String,String> headers = new HashMap<String,String>();
		RequestVo requestvo = new RequestVo();
		ResponseVo returnvo = new ResponseVo();
		List<Cookie> cookies = null;
		params.put("username",username);
		params.put("password",password);	
		requestvo.setRequestParams(params);
		requestvo.setRequestUrl("https://api.ezijing.com/jssdk/account/login");
		try {
			returnvo = Utils.httpGet(requestvo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cookies = returnvo.getCookies();
		usercookievalue = Utils.getCookieValue(cookies, usercookiekey);
		phpsessidvalue = Utils.getCookieValue(cookies, phpsessionid);
		JSONObject json = returnvo.getJson();
		if(json.getIntValue("errorCode")==200)  
		{
			cookievalue = usercookiekey+"="+usercookievalue+"; ";
			cookievalue = cookievalue + phpsessionid+"="+phpsessidvalue+";";
			System.out.println("Login successfully!");
			return cookievalue;
		}else
		{
			System.out.println("Login failed!");
			return null;
		}
	}
	
	
	
	/**
	 * @author cuixiaohui
	 * @param Logger , log level
	 * 
	*/
	public static void initLog4j(Logger logger,Level level)
	{
		System.setProperty("WORKDIR", System.getProperty("user.dir"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+"//config//Log4j.properties");
		logger.setLevel(level);
	}
}
