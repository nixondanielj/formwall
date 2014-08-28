package com.formwall.entities;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Permission {
	@Id private Long id;
	private String level;
	private Ref<CustomUser> user;
	private Ref<Form> form;
	public Long getId() {
		return id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public CustomUser getUser() {
		return user.get();
	}
	public void setUser(CustomUser user) {
		this.user = Ref.create(user);
	}
	public Form getForm() {
		return form.get();
	}
	public void setForm(Form form) {
		this.form = Ref.create(form);
	}
	
	
}
