package com.formwall.entities;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Session{
	private Key id;
	private Key userId;
	private Date expiration;
	public Session(Entity e){
		setUserId((Key)e.getProperty("userId"));
		setId(e.getKey());
		setExpiration((Date)e.getProperty("expiration"));
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
		e.setProperty("expiration", getExpiration());
		return e;
	}
	public String getAuthCode(){
		return KeyFactory.keyToString(getId());
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Key getUserId() {
		return userId;
	}
	public void setUserId(Key userId) {
		this.userId = userId;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
