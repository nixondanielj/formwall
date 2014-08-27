package com.formwall.entities;

import com.formwall.services.PermissionLevels;

public class Permission {
	private String id;
	private PermissionLevels level;
	private String userId;
	private String formId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PermissionLevels getLevel() {
		return level;
	}
	public void setLevel(PermissionLevels level) {
		this.level = level;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	
}
