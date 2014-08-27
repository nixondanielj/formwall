package com.formwall.services.concrete;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.DuplicateException;
import com.formwall.services.IAuthService;
import com.formwall.services.ICustomUserService;
import com.formwall.services.IMailService;
import com.formwall.services.Roles;

public class CustomUserService implements ICustomUserService {

	private IMailService mailSvc;
	private IUserRepository userRepo;
	private IAuthService authSvc;

	@Inject
	public CustomUserService(IMailService mailSvc, IUserRepository userRepo,
			IAuthService authSvc) {
		this.mailSvc = mailSvc;
		this.userRepo = userRepo;
		this.authSvc = authSvc;
	}

	@Override
	public boolean alreadyExists(String email) {
		return userRepo.getByEmail(email) != null;
	}

	@Override
	public CustomUser registerCustom(String email)
			throws UnsupportedEncodingException, MessagingException, DuplicateException {
		if(alreadyExists(email)){
			throw new DuplicateException();
		}
		CustomUser user = new CustomUser();
		user.setEmail(email);
		user.setPassword(authSvc.generatePassword());
		user = userRepo.create(user);
		try {
			authSvc.addRoleToUser(user, Roles.User);
			mailSvc.sendWelcomeEmail(user);
		} catch (Exception e) {
			// TODO roll back user
		}
		return user;
	}

	@Override
	public CustomUser registerGoogle(String email) {
		if(alreadyExists(email)){
			return userRepo.getByEmail(email);
		} else {
			CustomUser user = new CustomUser();
			user.setEmail(email);
			return userRepo.create(user);
		}
	}

}
