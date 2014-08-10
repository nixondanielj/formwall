package com.formwall.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;

public interface IMailService {
	void sendWelcomeEmail(CustomUser user) throws UnsupportedEncodingException, MessagingException;
}
