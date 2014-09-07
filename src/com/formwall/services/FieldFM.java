package com.formwall.services;

public class FieldFM {
	private Long id;
	private Long fieldTypeId;
	private String label;
	private boolean required;
	private String errorMessage;
	private String requiredMessage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFieldTypeId() {
		return fieldTypeId;
	}
	public void setFieldTypeId(Long fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getRequiredMessage() {
		return requiredMessage;
	}
	public void setRequiredMessage(String requiredMessage) {
		this.requiredMessage = requiredMessage;
	}
}