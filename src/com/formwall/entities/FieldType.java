package com.formwall.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class FieldType {
	private Key id;
	private String htmlType;
	private String regexValidator;

	FieldType() {

	}

	public FieldType(Entity e) {
		setId(e.getKey());
		setHtmlType(e.getProperty("htmlType").toString());
		if (e.getProperty("regexValidator") != null) {
			setRegexValidator(e.getProperty("regexValidator").toString());
		}
	}

	public Key getId() {
		return id;
	}

	public String getHtmlType() {
		return htmlType;
	}

	public String getRegexValidator() {
		return regexValidator;
	}

	void setId(Key key) {
		this.id = key;
	}

	void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}

	void setRegexValidator(String regexValidator) {
		this.regexValidator = regexValidator;
	}

	public Entity toEntity() {
		Entity e;
		if (getId() == null) {
			e = new Entity(FieldType.class.getSimpleName());
		} else {
			e = new Entity(getId());
		}
		e.setProperty("htmlType", getHtmlType());
		e.setProperty("regexValidator", getRegexValidator());
		return e;
	}
}
