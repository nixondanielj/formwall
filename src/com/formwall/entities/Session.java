package com.formwall.entities;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Session{
	@Id private Long id;
	private Ref<CustomUser> user;
	private Date expiration;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public CustomUser getUser() {
		return user.get();
	}
	public void setUser(CustomUser user) {
		this.user = Ref.create(user);
	}
}
