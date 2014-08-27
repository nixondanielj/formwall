package com.formwall.entities;

import java.util.List;

public class Form {
	private String id;
	private String title;
	private List<String> fieldIds;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getFieldIds() {
		return fieldIds;
	}
	public void setFieldIds(List<String> fieldIds) {
		this.fieldIds = fieldIds;
	}
	
}
