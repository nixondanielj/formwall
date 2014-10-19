package com.formwall.services;

import java.util.ArrayList;
import java.util.Date;
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
	public ArrayList<FieldFM> getFormFields() {
		return formFields;
	}
	public void setFormFields(ArrayList<FieldFM> formFields) {
		this.formFields = formFields;
	}
	private List<FieldType> availableFieldTypes;
	private String title;
	private Long id;
	private boolean active;
	private ArrayList<FieldFM> formFields;
	private String customId;
	private boolean availableNow;
	private Date availabilityStart;
	private Date availabilityEnd;
	private String buttonText;
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public boolean isAvailableNow() {
		return availableNow;
	}
	public void setAvailableNow(boolean availableNow) {
		this.availableNow = availableNow;
	}
	public Date getAvailabilityStart() {
		return availabilityStart;
	}
	public void setAvailabilityStart(Date availabilityStart) {
		this.availabilityStart = availabilityStart;
	}
	public Date getAvailabilityEnd() {
		return availabilityEnd;
	}
	public void setAvailabilityEnd(Date availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
}
