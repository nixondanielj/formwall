package com.formwall.entities;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Field {
	@Id private Long id;
	private String label;
	private boolean required;
	private String errorMessage;
	private String requiredMessage;
	private String placeholder;
	private Ref<FieldType> fieldType;
	public Long getId() {
		return id;
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
	public FieldType getFieldType() {
		return fieldType.get();
	}
	public void setFieldType(FieldType fieldType) {
		this.fieldType = Ref.create(fieldType);
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
}
