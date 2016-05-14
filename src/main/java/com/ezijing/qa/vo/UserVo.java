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
public class UserVo implements Serializable{
	
	private String name = "";
	private String password = "";
	
	public String getUsername() {
		return name;
	}
	public void setUsername(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
