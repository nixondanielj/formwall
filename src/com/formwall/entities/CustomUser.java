package com.formwall.entities;

import java.util.ArrayList;
import java.util.List;


public class CustomUser {
	private String id;
	private String email;
	private String password;
	private List<String> roles;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		if(roles == null){
			roles = new ArrayList<String>();
		}
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
