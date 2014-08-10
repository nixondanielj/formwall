package com.formwall.web;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import com.formwall.entities.Session;
import com.formwall.entities.User;
import com.formwall.repositories.ISessionRepository;
import com.formwall.repositories.IUserRepository;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.ConflictException;

@Api(name = "formwallApi", version = "v1")
public class UserEndpoint {
	private IUserRepository repo;
	private ISessionRepository sRepo;
	
	@Inject
	public UserEndpoint(IUserRepository ur, ISessionRepository sr){
		this.repo = ur;
		this.sRepo = sr;
	}

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
