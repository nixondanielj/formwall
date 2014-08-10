package com.formwall.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.formwall.entities.User;

public interface IMailer {
	void sendWelcomeEmail(User user) throws UnsupportedEncodingException, MessagingException;
}
