package com.formwall.services.concrete;

import java.util.UUID;

import javax.inject.Inject;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;
import com.formwall.entities.Session;
import com.formwall.repositories.IPermissionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.Credentials;
import com.formwall.services.IAuthService;
import com.formwall.services.ISessionService;
import com.formwall.services.PermissionLevels;
import com.formwall.services.Roles;

public class CustomAuthService implements IAuthService {

	private IUserRepository userRepo;
	private ISessionService sessionSvc;
	@Inject
	public CustomAuthService(IUserRepository userRepo, ISessionService sessionSvc, IPermissionRepository permissionRepo){
		this.userRepo = userRepo;
		this.sessionSvc = sessionSvc;
	}
	@Override
	public void addRoleToUser(CustomUser user, Roles role) {
		if(!user.getRoles().contains(role.name())){
			user.getRoles().add(role.name());
			userRepo.persist(user);
		}
	}
	@Override
	public boolean isAuthorized(Session session, Roles role) {
		if(sessionSvc.isValidSession(session)){
			CustomUser user = userRepo.getById(session.getUserId());
			return user.getRoles().contains(role.name());
		}
		return false;
	}
	@Override
	public Session authenticate(Credentials credentials) {
		CustomUser user = userRepo.getByEmail(credentials.getUsername());
		if(user != null){
			return sessionSvc.retrieveSession(user);
		}
		return null;
	}
	@Override
	public boolean isAuthenticated(Session session) {
		return isAuthorized(session, Roles.User);
	}
	@Override
	public String generatePassword() {
		String random = UUID.randomUUID().toString();
		return random.substring(random.lastIndexOf("-") + 1);
	}
	@Override
	public boolean isAuthorized(String authCode, Roles role) {
		return isAuthorized(sessionSvc.retrieveSession(authCode), role);
	}
	@Override
	public boolean isAuthenticated(String authCode) {
		return isAuthenticated(sessionSvc.retrieveSession(authCode));
	}
	@Override
	public CustomUser getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hasPermission(Form form, PermissionLevels permission) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean canHaveMoreForms() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void addPermission(Form form, PermissionLevels permission) {
		// TODO Auto-generated method stub
		
	}

}
