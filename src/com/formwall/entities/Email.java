package com.formwall.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class Email {
	private Key id;
	private String name;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	private String senderTitle;

	public Email(Entity entity) {
		setId(entity.getKey());
		setName(entity.getProperty("name").toString());
		setFrom(entity.getProperty("from").toString());
		setSubject(entity.getProperty("subject").toString());
		setMessage(entity.getProperty("message").toString());
		if (entity.getProperty("senderTitle") != null) {
			setSenderTitle(entity.getProperty("senderTitle").toString());
		}
		if (entity.getProperty("to") != null) {
			this.to = entity.getProperty("to").toString();
		}
		if (entity.getProperty("cc") != null) {
			this.cc = entity.getProperty("cc").toString();
		}
		if (entity.getProperty("bcc") != null) {
			this.bcc = entity.getProperty("bcc").toString();
		}
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSenderTitle() {
		return senderTitle;
	}

	public void setSenderTitle(String title) {
		this.senderTitle = title;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
