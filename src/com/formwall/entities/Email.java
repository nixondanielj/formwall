package com.formwall.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Email {
	@Id private String id;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	private String senderTitle;

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

	public String getId() {
		return id;
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

	public void setId(String id) {
		this.id = id;
	}

}
