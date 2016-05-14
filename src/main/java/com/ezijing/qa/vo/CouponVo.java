/**
 * 
 */
package com.ezijing.qa.vo;

import java.io.Serializable;

/**
 * @author cuixiaohui
 *
 */
@SuppressWarnings("serial")

public class CouponVo implements Serializable{
	
	private String appkey = "";
	private String secretkey = "";
	private String CouponCode = "";
	
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
	public String getCouponCode() {
		return CouponCode;
	}
	public void setCouponCode(String couponCode) {
		CouponCode = couponCode;
	}
	
	
}
