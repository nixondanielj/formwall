package com.formwall.web;

import java.util.UUID;

import javax.inject.Named;

import com.formwall.entities.Session;
import com.formwall.entities.SessionRepository;
import com.formwall.entities.User;
import com.formwall.entities.UserRepository;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.ConflictException;

@Api(name = "formwallApi", version = "v1")
public class UserEndpoint {
	private UserRepository repo = new UserRepository();
	private SessionRepository sRepo = new SessionRepository();

	public Session register(@Named("email") String email)
			throws ConflictException {
		User user = repo.getByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			String pwd = UUID.randomUUID().toString();
			user.setPassword(pwd.substring(pwd.lastIndexOf("-")+1));
			repo.create(user);
			return sRepo.createSessionForUser(user);
		}
		throw new ConflictException("User already exists");
	}
}
