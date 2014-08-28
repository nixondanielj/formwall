package com.formwall.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Email {
	@Id private Long id;
	private String name;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	private String senderTitle;

	public Long getId() {
		return id;
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
