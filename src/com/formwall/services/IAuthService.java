package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface IAuthService {
	public void addRoleToUser(CustomUser user, Roles role);

	public boolean isAuthorized(Session session, Roles role);
	
	public boolean isAuthorized(String authCode, Roles role);
	
	public Session authenticate(Credentials credentials);
	
	public boolean isAuthenticated(Session session);
	
	public boolean isAuthenticated(String authCode);
	
	public String generatePassword();
}
