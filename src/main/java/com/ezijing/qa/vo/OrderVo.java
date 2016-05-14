/**
 * 
 */
package com.ezijing.qa.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ezijing.qa.common.TestBase;

/**
 * @author cuixiaohui
 *
 */
public class OrderVo {

	private HashMap<String,String> line_item = null;


	public OrderVo(String[] params,String[] values){
	
		line_item = new HashMap<String, String>();
		for(int i = 0; i < params.length; i++)
		{
			line_item.put(params[i]+"",values[i]+"");
		}
	}
	
	public HashMap<String, String> getLine_item() {
		return line_item;
	}

	public void setLine_item(HashMap<String, String> line_item) {
		this.line_item = line_item;
	}

	
}
