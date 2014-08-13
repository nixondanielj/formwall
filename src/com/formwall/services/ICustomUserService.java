package com.formwall.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;

public interface ICustomUserService {

	boolean alreadyExists(String email);

	/**
	 * Registers a new user by an email address
	 * code:
	 * if duplicate email: throw duplicate exception
	 * new user
	 * generate random password
	 * persist user
	 * try:
	 *  add role
	 *  send welcome mail
	 * catch:
	 *  roll back user
	 * return user
	 * @param email The new user's email
	 * @return The created user
	 * @throws UnsupportedEncodingException If the email address is not in a supported format
	 * @throws MessagingException If the messaging system fails
	 * @throws DuplicateException If email is duplicate
	 */
	CustomUser registerByEmail(String email) throws UnsupportedEncodingException, MessagingException, DuplicateException;

}
