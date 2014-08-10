package com.formwall.web;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

import com.formwall.entities.Session;
import com.formwall.entities.User;
import com.formwall.repositories.ISessionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.IMailService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.ConflictException;

@Api(name = "formwallApi", version = "v1")
public class UserEndpoint {
	private IUserRepository repo;
	private ISessionRepository sRepo;
	private IMailService mailSvc;
	
	@Inject
	public UserEndpoint(IUserRepository ur, ISessionRepository sr, IMailService mailSvc){
		this.repo = ur;
		this.sRepo = sr;
		this.mailSvc = mailSvc;
	}

	public Session register(@Named("email") String email)
			throws ConflictException, UnsupportedEncodingException, MessagingException {
		User user = repo.getByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			String pwd = UUID.randomUUID().toString();
			user.setPassword(pwd.substring(pwd.lastIndexOf("-")+1));
			repo.create(user);
			mailSvc.sendWelcomeEmail(user);
			return sRepo.createSessionForUser(user);
		}
		throw new ConflictException("User already exists");
	}
}
