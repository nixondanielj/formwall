package com.formwall.entities;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Form {
	@Id private Long id;
	private String title;
	private List<Ref<Field>> fields;
	private boolean active;
	public Form(){
		fields = new ArrayList<Ref<Field>>();
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Field> getFields() {
		List<Field> loadedFields = new ArrayList<Field>();
		for(Ref<Field> fieldRef : fields){
			loadedFields.add(fieldRef.get());
		}
		return loadedFields;
	}
	public void addFields(List<Field> fields2) {
		for(Field field : fields2){
			addField(field);
		}
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void addField(Field field) {
		this.fields.add(Ref.create(field));
	}
	
}
