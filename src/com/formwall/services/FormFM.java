package com.formwall.services;

import java.util.ArrayList;
import java.util.List;

import com.formwall.entities.FieldType;

public class FormFM {
	public List<FieldType> getAvailableFieldTypes() {
		return availableFieldTypes;
	}
	public void setAvailableFieldTypes(List<FieldType> availableFieldTypes) {
		this.availableFieldTypes = availableFieldTypes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<FieldFM> getFields() {
		if(fields == null){
			setFields(new ArrayList<FieldFM>());
		}
		return fields;
	}
	public void setFields(List<FieldFM> fields) {
		this.fields = fields;
	}
	private List<FieldType> availableFieldTypes;
	private String title;
	private Long id;
	private boolean active;
	private List<FieldFM> fields;
}
