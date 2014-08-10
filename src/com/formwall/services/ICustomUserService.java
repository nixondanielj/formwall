package com.formwall.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface ICustomUserService {

	boolean alreadyExists(String email);

	CustomUser registerByEmail(String email) throws UnsupportedEncodingException, MessagingException;

	Session beginSession(CustomUser user);

}
