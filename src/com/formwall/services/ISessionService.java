package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.web.models.SessionVM;

public interface ISessionService {
	SessionVM beginSession(CustomUser user);

	public CustomUser retrieveUser(String authCode);
}
