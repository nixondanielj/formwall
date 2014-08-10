package com.formwall.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.formwall.entities.User;

public interface IMailService {
	void sendWelcomeEmail(User user) throws UnsupportedEncodingException, MessagingException;
}
