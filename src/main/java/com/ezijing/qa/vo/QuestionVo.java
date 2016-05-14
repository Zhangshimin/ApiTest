/**
 * 
 */
package com.ezijing.qa.vo;

/**
 * @author cuixiaohui
 *
 */
public class QuestionVo {

	private int user_uid;
	private int node_nid;
	private String title;
	private String content;
	public enum status
	{
		Anonymous, Deleted, Locked,Unpublished,Sticky,Promoted
	}
	public int getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(int user_uid) {
		this.user_uid = user_uid;
	}
	public int getNode_nid() {
		return node_nid;
	}
	public void setNode_nid(int node_nid) {
		this.node_nid = node_nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	};
	
}
