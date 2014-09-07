package com.formwall.entities;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class CustomUser {
	@Id private String email;
	private String password;
	private List<String> roles;
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
