package com.formwall.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class FieldType {
	@Id private Long id;
	private String htmlType;
	private String defaultRegexValidator;
	private String defaultRequiredMessage;
	private String defaultErrorMessage;

	public FieldType() {

	}

	public Long getId() {
		return id;
	}
	public String getHtmlType() {
		return htmlType;
	}
	public String getDefaultRegexValidator() {
		return defaultRegexValidator;
	}

	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}

	public void setDefaultRegexValidator(String regexValidator) {
		this.defaultRegexValidator = regexValidator;
	}

	public String getDefaultRequiredMessage() {
		return defaultRequiredMessage;
	}

	public void setDefaultRequiredMessage(String defaultRequiredMessage) {
		this.defaultRequiredMessage = defaultRequiredMessage;
	}

	public String getDefaultErrorMessage() {
		return defaultErrorMessage;
	}

	public void setDefaultErrorMessage(String defaultErrorMessage) {
		this.defaultErrorMessage = defaultErrorMessage;
	}
}
