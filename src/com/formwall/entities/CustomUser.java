package com.formwall.entities;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class CustomUser {
	private Key id;
	private String email;
	private String password;
	private List<String> roles;
	public CustomUser(){
		
	}
	@SuppressWarnings("unchecked")
	public CustomUser(Entity e){
		setId(e.getKey());
		setEmail(e.getProperty("email").toString());
		if(e.getProperty("roles") != null){
			roles = (List<String>) e.getProperty("roles");
		}
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
		e.setProperty("roles", getRoles());
		return e;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
