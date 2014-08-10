package com.formwall.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class CustomUser {
	private Key id;
	private String email;
	private String password;
	public CustomUser(){
		
	}
	public CustomUser(Entity e){
		setId(e.getKey());
		setEmail(e.getProperty("email").toString());
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Entity toEntity(){
		Entity e;
		if(getId() != null){
			e = new Entity(getId());
		} else {
			e = new Entity(CustomUser.class.getSimpleName());
		}
		e.setProperty("email", getEmail());
		e.setProperty("password", getPassword());
		return e;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
