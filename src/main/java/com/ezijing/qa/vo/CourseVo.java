/**
 * 
 */
package com.ezijing.qa.vo;

import java.io.Serializable;

import com.ezijing.qa.common.TestBase;

/**
 * @author cuixiaohui
 *
 */
@SuppressWarnings("serial")
public class CourseVo implements Serializable {

	private int limit;
	
	private int page;
	
	private int nid;
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
