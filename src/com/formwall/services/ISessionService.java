package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface ISessionService {
	boolean isValidSession(String authcode);

	Session beginSession(CustomUser user);
}
