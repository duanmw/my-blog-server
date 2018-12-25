package com.mw.model;

import java.io.Serializable;

public class Article implements Serializable {
	private int id;//ID
	private String title;//标题
	private String type;//类别
	private String tags;//标签
	private String content;//内容
	private String create_time;//创建时间
	private String update_time;//更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public Article(int id, String title, String type, String tags, String content, String create_time,
			String update_time) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.tags = tags;
		this.content = content;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	public Article() {
		// TODO Auto-generated constructor stub
	}
	

}
