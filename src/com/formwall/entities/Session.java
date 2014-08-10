package com.formwall.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Session {
	private Key id;
	private Key userId;
	public Session(Entity e){
		setUserId((Key)e.getProperty("userId"));
		setId(e.getKey());
	}
	public Session(){
		
	}
	public Entity toEntity(){
		Entity e;
		if(getId() != null){
			e = new Entity(getId());
		} else {
			e = new Entity(Session.class.getSimpleName(), getUserId());
		}
		e.setProperty("userId", getUserId());
		return e;
	}
	public String getAuthCode(){
		return KeyFactory.keyToString(getId());
	}
	public Key getId() {
		return id;
	}
	void setId(Key id) {
		this.id = id;
	}
	public Key getUserId() {
		return userId;
	}
	void setUserId(Key userId) {
		this.userId = userId;
	}
}
