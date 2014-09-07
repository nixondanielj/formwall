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
import com.google.inject.Provider;

public class CustomUserService implements ICustomUserService {

	private IMailService mailSvc;
	private IUserRepository userRepo;
	private Provider<IAuthService> authSvcPrvdr;

	@Inject
	public CustomUserService(IMailService mailSvc, IUserRepository userRepo,
			Provider<IAuthService> authSvcPrvdr) {
		this.mailSvc = mailSvc;
		this.userRepo = userRepo;
		this.authSvcPrvdr = authSvcPrvdr;
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
		user.setPassword(authSvcPrvdr.get().generatePassword());
		userRepo.persist(user);
		try {
			authSvcPrvdr.get().addRoleToUser(user, Roles.User);
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
			userRepo.persist(user);
			authSvcPrvdr.get().addRoleToUser(user, Roles.User);
			return user;
		}
	}

}
