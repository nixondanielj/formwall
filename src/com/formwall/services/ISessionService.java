package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface ISessionService {
	Session beginSession(CustomUser user);

	public CustomUser retrieveUser(String authCode);
}
