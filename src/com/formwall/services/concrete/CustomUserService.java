package com.formwall.services.concrete;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.ICustomUserService;
import com.formwall.services.IMailService;

public class CustomUserService implements ICustomUserService {
	
	private IMailService mailSvc;
	private IUserRepository userRepo;

	@Inject
	public CustomUserService(IMailService mailSvc, IUserRepository userRepo){
		this.mailSvc = mailSvc;
		this.userRepo = userRepo;
	}

	@Override
	public boolean alreadyExists(String email) {
		return userRepo.getByEmail(email) != null;
	}

	@Override
	public CustomUser registerByEmail(String email) throws UnsupportedEncodingException, MessagingException {
		CustomUser user = new CustomUser();
		user.setEmail(email);
		String pwd = UUID.randomUUID().toString();
		user.setPassword(pwd.substring(pwd.lastIndexOf("-")+1));
		userRepo.create(user);
		mailSvc.sendWelcomeEmail(user);
		return user;
	}

}
