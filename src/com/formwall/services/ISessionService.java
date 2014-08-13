package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface ISessionService {
	public boolean isValidSession(Session session);
	
	Session beginSession(CustomUser user);
	
	Session retrieveSession(String authCode);
	
	Session retrieveSession(CustomUser user);
}
