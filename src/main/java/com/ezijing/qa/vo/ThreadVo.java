package com.ezijing.qa.vo;

import java.util.ArrayList;

public class ThreadVo {

	private String appkey = "";
	private String secretkey = "";
	private ArrayList<Integer> recipients;
	private String subjetc = "";
	private String body = "";
	
	
	
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public ArrayList<Integer> getRecipients() {
		return recipients;
	}
	public void setRecipients(ArrayList<Integer> recipients) {
		this.recipients = recipients;
	}
	public String getSubjetc() {
		return subjetc;
	}
	public void setSubjetc(String subjetc) {
		this.subjetc = subjetc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
